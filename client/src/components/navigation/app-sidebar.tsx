import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
} from "../../components/ui/sidebar";

function AppSidebar() {
  return (
    <Sidebar>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel className="text-2xl">
            Recent chats
          </SidebarGroupLabel>
          <SidebarGroupContent className="flex flex-col items-center gap-2 text-lg pt-10">
            <h1>Render all chets here...</h1>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  );
}

export default AppSidebar;
