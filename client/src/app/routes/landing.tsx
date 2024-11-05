import SidebarLayout from "@/components/layouts/sidebar-layout";

function LandingRoute() {
  return (
    <div className="flex gap-96">
      <div>
        <SidebarLayout children />
      </div>
      <div>Boris</div>
    </div>
  );
}

export default LandingRoute;
