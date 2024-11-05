export const paths = {
  home: {
    path: "/",
    getHref: () => "/",
  },
  app: {
    root: {
      path: "/app",
      getHref: () => "/app",
    },
    chats: {
      path: "",
      getHref: () => "/app",
    },
  },
} as const;
