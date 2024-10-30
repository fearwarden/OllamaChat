package com.fearwarden.OllamaChat.config;

import io.micrometer.observation.ObservationRegistry;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.ai.vectorstore.ElasticsearchVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class ElasticsearchVectorStoreConfig {
    @Value("${spring.ai.ollama.chat.model}")
    private String model;
    @Value("${elasticsearch.url}")
    private String url;
    @Value("${elasticsearch.username}")
    private String username;
    @Value("${elasticsearch.password}")
    private String password;

    @Bean
    public RestClient restClient() {
        var splitUrl = url.split(":");
        String host = splitUrl[0];
        int port = Integer.parseInt(splitUrl[1]);
        String encodedAuth = encodeElasticCredentials();
        return RestClient.builder(new HttpHost(host, port, "http"))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "Basic " + encodedAuth)
                })
                .build();
    }

    @Bean
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }

    @Bean
    public ModelManagementOptions managementOptions() {
        return ModelManagementOptions.defaults();
    }

    @Bean
    public EmbeddingModel embeddingModel(ObservationRegistry observationRegistry, ModelManagementOptions managementOptions) {
        var ollamaApi = new OllamaApi();
        var ollamaOptions = OllamaOptions.builder()
                .withModel(model)
                .build();
        return new OllamaEmbeddingModel(ollamaApi, ollamaOptions, observationRegistry, managementOptions);
    }

    @Bean
    public ElasticsearchVectorStore vectorStore(RestClient restClient, EmbeddingModel embeddingModel) {
        return new ElasticsearchVectorStore(restClient, embeddingModel, true);
    }

    private String encodeElasticCredentials() {
        String auth = username + ":" + password;
        return Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }
}
