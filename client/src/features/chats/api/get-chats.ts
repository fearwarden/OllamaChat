import { queryOptions, useQuery } from "@tanstack/react-query";

import { api } from "@/lib/api-client";
import { QueryConfig } from "@/lib/react-query";
import { Chat } from "@/types/api";

export const getChats = (): Promise<Chat[]> => {
  return api.get("/chat");
};

export const getChatsQueryOptions = () => {
  return queryOptions({
    queryKey: ["chats"],
    queryFn: getChats,
  });
};

type UseChatsOptions = {
  queryConfig?: QueryConfig<typeof getChatsQueryOptions>;
};

export const useChats = ({ queryConfig }: UseChatsOptions = {}) => {
  return useQuery({
    ...getChatsQueryOptions(),
    ...queryConfig,
  });
};
