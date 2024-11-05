import { useNavigate } from "react-router-dom";
import { paths } from "../config/paths";
import { useEffect } from "react";

function LandingRoute() {
  const navigate = useNavigate();
  useEffect(() => {
    navigate(paths.app.root.path + paths.app.chats.path);
  }, []);
  return <div>Landing page</div>;
}

export default LandingRoute;
