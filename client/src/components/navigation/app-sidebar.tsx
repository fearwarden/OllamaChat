import {ChatsList} from "@/features/chats/components/chats-list";
import {
    Sidebar,
    SidebarContent,
    SidebarGroup,
    SidebarGroupContent,
    SidebarGroupLabel,
    SidebarMenu,
} from "../../components/ui/sidebar";
import {CreateChat} from "@/features/chats/components/create-chat";

function AppSidebar() {
    return (
        <Sidebar>
            <SidebarContent className="pt-4">
                <SidebarGroup>
                    <div className="flex justify-between items-center">
                        <SidebarGroupLabel className="text-2xl">
                            Recent chats
                        </SidebarGroupLabel>
                        <SidebarGroupLabel>
                            <CreateChat/>
                        </SidebarGroupLabel>
                    </div>
                    <SidebarGroupContent className="flex flex-col items-center gap-2 text-lg pt-10">
                        <SidebarMenu className="flex gap-2">
                            <ChatsList/>
                        </SidebarMenu>
                    </SidebarGroupContent>
                </SidebarGroup>
            </SidebarContent>
        </Sidebar>
    );
}

export default AppSidebar;
