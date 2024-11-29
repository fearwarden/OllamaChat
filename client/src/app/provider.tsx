import React from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ErrorBoundary } from "react-error-boundary";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import { queryConfig } from "@/lib/react-query";
import MainErrorFallback from "@/components/errors/main";
import { LoadingSpinner } from "@/components/ui/loading-spinner";
import { ThemeProvider } from "@/components/theme-provider";
import { AuthLoader } from "@/lib/auth";

type AppProviderProps = {
  children: React.ReactNode;
};

function AppProvider({ children }: AppProviderProps) {
  const [queryClient] = React.useState(
    () =>
      new QueryClient({
        defaultOptions: queryConfig,
      })
  );
  return (
    <React.Suspense
      fallback={
        <div className="flex h-screen w-screen items-center justify-center">
          <LoadingSpinner />
        </div>
      }
    >
      <ErrorBoundary FallbackComponent={MainErrorFallback} fallback={undefined}>
        <QueryClientProvider client={queryClient}>
          <AuthLoader
            renderLoading={() => (
              <div className="flex h-screen w-screen items-center justify-center">
                <LoadingSpinner />
              </div>
            )}
          >
            <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
              {children}
              <ReactQueryDevtools />
            </ThemeProvider>
          </AuthLoader>
        </QueryClientProvider>
      </ErrorBoundary>
    </React.Suspense>
  );
}

export default AppProvider;
