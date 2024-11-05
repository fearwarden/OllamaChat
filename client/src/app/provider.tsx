import React from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ErrorBoundary } from "react-error-boundary";

import { queryConfig } from "@/lib/react-query";
import MainErrorFallback from "@/components/errors/main";
import { LoadingSpinner } from "@/components/ui/loading-spinner";
import { ThemeProvider } from "@/components/theme-provider";

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
      <ErrorBoundary FallbackComponent={MainErrorFallback}>
        <QueryClientProvider client={queryClient}>
          <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
            {children}
          </ThemeProvider>
        </QueryClientProvider>
      </ErrorBoundary>
    </React.Suspense>
  );
}

export default AppProvider;
