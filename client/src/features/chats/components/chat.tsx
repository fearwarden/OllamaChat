import {useSelectedChat} from "@/hooks/use-contexts.ts";
import {useMessages} from "@/features/messages/api/get-messages.ts";
import {NewChatInput} from "@/features/chats/components/new-chat-input.tsx";
import {Messages} from "@/features/chats/components/messages.tsx";

// TODO: organize code and styling for new chat, and for existing chat
// Separate styling for new chat and for existing one
// in other words, top div in this file, move to the NewChatInput component, here only render fragment and rest the components
export const Chat = () => {
    const {selectedChat} = useSelectedChat();
    const {data} = useMessages({chatId: selectedChat?.id ?? null, queryConfig: {enabled: Boolean(selectedChat?.id)}});

    return (
        <div className="flex flex-1 flex-col items-center justify-center gap-4 pb-28">
            {data && selectedChat && <h1 className="text-2xl">{selectedChat.title}</h1>}
            <Messages data={data} />
            <NewChatInput isNewChat={!data}/>
        </div>
    );
}

export default Chat;
