import SidebarLayout from "@/components/layouts/sidebar/sidebar-layout";
import Chat from "@/features/chats/components/chat";

export const ChatRoute = () => {
  return (
    <SidebarLayout>
      <Chat />
    </SidebarLayout>
  );
};
