import { useQueryClient } from "@tanstack/react-query";
import { useMemo } from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import LandingRoute from "./routes/landing";
import { AppRoot, AppRootErrorBoundary } from "./routes/app/root";
import { paths } from "./config/paths";

export const createAppRouter = () =>
  createBrowserRouter([
    {
      path: paths.home.path,
      element: <LandingRoute />,
    },
    {
      path: paths.app.root.path,
      element: <AppRoot />,
      ErrorBoundary: AppRootErrorBoundary,
      children: [
        {
          path: paths.app.chats.path,
          lazy: async () => {
            const { ChatRoute } = await import("@/app/routes/app/chats");
            return {
              Component: ChatRoute,
            };
          },
        },
      ],
    },
  ]);

export const AppRouter = () => {
  const queryClient = useQueryClient();

  const router = useMemo(() => createAppRouter(), [queryClient]);

  return <RouterProvider router={router} />;
};
