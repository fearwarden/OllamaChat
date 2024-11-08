import { LoadingSpinner } from "@/components/ui/loading-spinner";
import { useChats } from "../api/get-chats";
import {
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar";

export const ChatsList = () => {
  const chatsQuery = useChats();

  if (chatsQuery.isLoading) {
    return (
      <div className="flex h-48 w-full items-center justify-center">
        <LoadingSpinner />
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
  console.log(chats);

  if (!chats) return null;

  return (
    <SidebarMenu className="flex gap-2">
      {chats.map((chat) => (
        <SidebarMenuItem key={chat.id}>
          <SidebarMenuButton asChild>
            <span className="whitespace-nowrap hover:cursor-pointer">
              {chat.title}
            </span>
          </SidebarMenuButton>
        </SidebarMenuItem>
      ))}
    </SidebarMenu>
  );
};
