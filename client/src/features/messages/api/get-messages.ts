import {api} from "@/lib/api-client.ts";
import {queryOptions, useQuery} from "@tanstack/react-query";
import {QueryConfig} from "@/lib/react-query.ts";
import {Message} from "@/types/api.ts";

export const getMessages = (chatId: string | null): Promise<Message[] | null> => {
    if (chatId) {
        return api.get(`/chat/${chatId}/messages`);
    }
    return Promise.resolve(null);
}
export const getMessagesQueryOptions = (chatId: string | null) => {
    return queryOptions({
        queryKey: [chatId, "messages"],
        queryFn: () => getMessages(chatId),
    })
}

type UseMessagesOptions = {
    queryConfig: QueryConfig<typeof getMessagesQueryOptions>
    chatId: string | null
};

export const useMessages = ({queryConfig, chatId}: UseMessagesOptions) => {
    return useQuery({
        ...getMessagesQueryOptions(chatId),
        ...queryConfig,
    })
}