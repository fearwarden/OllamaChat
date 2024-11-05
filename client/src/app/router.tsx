import { QueryClient, useQueryClient } from "@tanstack/react-query";
import { useMemo } from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import LandingRoute from "./routes/landing";

export const createAppRouter = (queryClient: QueryClient) =>
  createBrowserRouter([
    {
      path: "/",
      element: <LandingRoute />,
    },
  ]);

export const AppRouter = () => {
  const queryClient = useQueryClient();

  const router = useMemo(() => createAppRouter(queryClient), [queryClient]);

  return <RouterProvider router={router} />;
};
