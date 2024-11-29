import AppSidebar from "@/components/navigation/app-sidebar";
import {SidebarInset, SidebarProvider} from "@/components/ui/sidebar";
import React, {useState} from "react";
import {SidebarHeader} from "./components/header";
import {SelectedChatContext} from "@/app/store/contexts.ts";
import {Chat} from "@/types/api.ts";

function SidebarLayout({children}: { children: React.ReactNode }) {
    const [selectedChat, setSelectedChat] = useState<Chat | null>(null);
    return (
        <SidebarProvider>
            <SelectedChatContext.Provider value={{selectedChat, setSelectedChat}}>
                <AppSidebar/>
                <SidebarInset>
                    <SidebarHeader/>
                    {children}
                </SidebarInset>
            </SelectedChatContext.Provider>
        </SidebarProvider>
    );
}

export default SidebarLayout;
