import { useLogin } from "@/lib/auth";

type LoginFormProps = {
  onSuccess: () => void;
};

// TODO: add tanstack form
export const LoginForm = ({ onSuccess }: LoginFormProps) => {
  const login = useLogin({ onSuccess });
  console.log(login);

  return <div>LoginForm here...</div>;
};
