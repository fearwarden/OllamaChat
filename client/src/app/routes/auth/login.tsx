import { paths } from "@/app/config/paths";
import { AuthLayout } from "@/components/layouts/auth-layout";
import { LoginForm } from "@/features/auth/components/login-form";
import { useNavigate } from "react-router-dom";

export const LoginRoute = () => {
  const navigate = useNavigate();
  return (
    <AuthLayout
      title="Login"
      description="Enter your email below to login to your account"
    >
      <LoginForm
        onSuccess={() => {
          navigate(paths.app.root.path + paths.app.chats.path);
        }}
      />
    </AuthLayout>
  );
};
