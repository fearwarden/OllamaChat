import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { SidebarTrigger } from "@/components/ui/sidebar";
import { useUser } from "@/lib/auth";

export const SidebarHeader = () => {
  const { data } = useUser();
  const userInitials = data.firstName.split("")[0] + data.lastName.split("")[0];
  return (
    <header className="flex h-14 shrink-0 items-center pt-2">
      <div className="flex flex-1 items-center justify-between gap-2 px-3">
        <SidebarTrigger />
        <Avatar>
          <AvatarFallback className="hover:cursor-pointer">
            {userInitials}
          </AvatarFallback>
        </Avatar>
      </div>
    </header>
  );
};
