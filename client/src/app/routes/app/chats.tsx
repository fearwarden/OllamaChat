import SidebarLayout from "@/components/layouts/sidebar-layout";

function Chats() {
  return (
    <div className="flex gap-96">
      <div>
        <SidebarLayout children />
      </div>
      <div>Boris</div>
    </div>
  );
}

export default Chats;
