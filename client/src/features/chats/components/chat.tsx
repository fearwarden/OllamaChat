import {CircleArrowUp} from "lucide-react";

import {Input} from "@/components/ui/input";
import {useSelectedChat} from "@/hooks/use-contexts.ts";
import {useMessages} from "@/features/messages/api/get-messages.ts";

export const Chat = () => {
    const {selectedChat} = useSelectedChat();
    const {data} = useMessages({chatId: selectedChat?.id ?? null, queryConfig: {enabled: Boolean(selectedChat?.id)}});
    console.log(data)
    return (
        <div className="flex flex-1 flex-col items-center justify-center gap-4 pb-28">
            <h1 className="text-3xl font-semibold">What can I help you with?</h1>
            <div className="relative">
                <Input
                    type="text"
                    placeholder="Message Ollama"
                    className="rounded-full w-[48rem] h-14 px-6 text-md bg-primary-foreground focus-visible:ring-sidebar"
                />
                <CircleArrowUp className="absolute right-6 top-1/2 transform -translate-y-1/2 text-gray-400"/>
            </div>
        </div>
    );
}

export default Chat;
