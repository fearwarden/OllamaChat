import { paths } from "@/app/config/paths";
import axios, { InternalAxiosRequestConfig } from "axios";

function authRequestInterceptor(config: InternalAxiosRequestConfig) {
  if (config.headers) {
    config.headers.Accept = "application/json";
  }
  config.withCredentials = true;
  return config;
}

export const api = axios.create({
  // TODO: put url in env
  baseURL: "/api/v1",
});

api.interceptors.request.use(authRequestInterceptor);
api.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    const message = error.response?.data?.message || error.message;
    console.log(message);
    if (error.response?.status === 401) {
      window.location.href = paths.auth.login.path;
    }
    return Promise.reject(error);
  }
);
