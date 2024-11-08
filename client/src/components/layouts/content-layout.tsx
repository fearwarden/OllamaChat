import React from "react";

type ContentLayoutProps = {
  children: React.ReactNode;
};

function ContentLayout({ children }: ContentLayoutProps) {
  return <div>{children}</div>;
}

export default ContentLayout;
