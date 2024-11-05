import ContentLayout from "@/components/layouts/content-layout";
import SidebarLayout from "@/components/layouts/sidebar-layout";
import Chat from "@/features/chats/components/chat";

export const ChatRoute = () => {
  return (
    <ContentLayout>
      <div>
        <SidebarLayout children />
      </div>
      <div>
        <Chat />
      </div>
    </ContentLayout>
  );
};
