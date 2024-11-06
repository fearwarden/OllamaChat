import { useQueryClient } from "@tanstack/react-query";
import { useMemo } from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import LandingRoute from "./routes/landing";
import { AppRoot, AppRootErrorBoundary } from "./routes/app/root";
import { paths } from "./config/paths";
import { LoginRoute } from "./routes/auth/login";
import { ProtectedRoute } from "@/lib/auth";

export const createAppRouter = () =>
  createBrowserRouter([
    {
      path: paths.home.path,
      element: <LandingRoute />,
    },
    {
      path: paths.auth.login.path,
      element: <LoginRoute />,
    },
    {
      path: paths.app.root.path,
      element: (
        <ProtectedRoute>
          <AppRoot />
        </ProtectedRoute>
      ),
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
