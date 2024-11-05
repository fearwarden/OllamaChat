import { configureAuth } from "react-query-auth";
import { z } from "zod";

import { api } from "./api-client";
import { User } from "@/types/api";
import { useNavigate } from "react-router-dom";
import { paths } from "@/app/config/paths";

// TODO: add types
const getUser = async () => {
  const response = await api.get("/users/me");

  return response.data;
};

const logout = (): Promise<void> => {
  // TODO: add logout api when it's done on backend
  return Promise.resolve();
};

export const LoginInputSchema = z.object({
  email: z.string().min(1, "Required").email("Invalid email"),
  password: z.string().min(5, "Required"),
});
export type LoginInput = z.infer<typeof LoginInputSchema>;
const loginWithEmailAndPassword = (data: LoginInput): Promise<User> => {
  return api.post("/auth/login", data);
};

const authConfig = {
  userFn: getUser,
  loginFn: async (data: LoginInput) => {
    const response = await loginWithEmailAndPassword(data);
    return response;
  },
  // TODO: implement register
  registerFn: async () => {},
  logoutFn: logout,
};

export const { useUser, useLogin, useLogout, useRegister, AuthLoader } =
  configureAuth(authConfig);

export const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const user = useUser();
  const navigate = useNavigate();

  if (!user.data) {
    navigate(paths.auth.login.path);
  }

  return children;
};
