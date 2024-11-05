import { api } from "./api-client";

// TODO: add types
const getUser = async () => {
  const response = await api.get("/users/me");

  return response.data;
};
