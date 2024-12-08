import {useSelectedChat} from "@/hooks/use-contexts.ts";
import {useMessages} from "@/features/messages/api/get-messages.ts";
import {NewChatInput} from "@/features/chats/components/new-chat-input.tsx";
import {Messages} from "@/features/chats/components/messages.tsx";

export const Chat = () => {
    const {selectedChat} = useSelectedChat();
    const {data} = useMessages({chatId: selectedChat?.id ?? null, queryConfig: {enabled: Boolean(selectedChat?.id)}});

    return (
        <div className="flex flex-col h-screen">
            {!data ? (<div className="flex flex-1 flex-col items-center justify-center pb-28">
                <NewChatInput isNewChat={true}/>
            </div> ) : (
            <div className="flex-none p-4">
                {data && selectedChat && <h1 className="text-2xl">{selectedChat.title}</h1>}
                <div className="flex-1 overflow-y-auto">
                    <Messages data={data}/>
                </div>
                <div className="flex-none p-4">
                    <NewChatInput isNewChat={false} />
                </div>
            </div>)}
        </div>
    );
}

export default Chat;
