import {LoadingSpinner} from "@/components/ui/loading-spinner";
import {useChats} from "../api/get-chats";
import {SidebarMenuButton, SidebarMenuItem,} from "@/components/ui/sidebar";
import {useSelectedChat} from "@/hooks/use-contexts.ts";

export const ChatsList = () => {
    const chatsQuery = useChats();
    const {setSelectedChat} = useSelectedChat();

    if (chatsQuery.isLoading) {
        return (
            <div className="flex h-48 w-full items-center justify-center">
                <LoadingSpinner/>
            </div>
        );
    } else if (chatsQuery.isError) {
        return (
            <div>
                <h1>{chatsQuery.error.message}</h1>
            </div>
        );
    }

    const chats = chatsQuery.data;
    if (!chats) return null;

    return (
        <>
            {chats.map((chat) => (
                <SidebarMenuItem key={chat.id}>
                    <SidebarMenuButton asChild onClick={() => setSelectedChat(chat)}>
                        <span className="whitespace-nowrap hover:cursor-pointer">
                          {chat.title}
                        </span>
                    </SidebarMenuButton>
                </SidebarMenuItem>
            ))}
        </>
    );
};
