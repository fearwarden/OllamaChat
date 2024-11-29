import {useContext} from "react";
import {SelectedChatContext} from "@/app/store/contexts.ts";

export const useSelectedChat = () => {
    const context = useContext(SelectedChatContext);
    if (!context) {
        throw new Error("useSelectedChat must be used within a SelectedChatProvider");
    }
    return context;
}