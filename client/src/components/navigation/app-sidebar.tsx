import { ChatsList } from "@/features/chats/components/chats-list";
import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarHeader,
} from "../../components/ui/sidebar";
import { CreateChat } from "@/features/chats/components/create-chat";

function AppSidebar() {
  return (
    <Sidebar>
      <SidebarHeader className="flex items-end pt-6">
        <CreateChat />
      </SidebarHeader>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel className="text-2xl">
            Recent chats
          </SidebarGroupLabel>
          <SidebarGroupContent className="flex flex-col items-center gap-2 text-lg pt-10">
            <ChatsList />
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  );
}

export default AppSidebar;
