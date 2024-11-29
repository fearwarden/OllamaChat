import {Chat} from "@/types/api.ts";

export type SelectedChatContextType = {
    selectedChat: Chat | null;
    setSelectedChat: (chat: Chat | null) => void;
}