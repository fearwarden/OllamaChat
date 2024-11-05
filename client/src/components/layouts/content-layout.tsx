import React from "react";

type ContentLayoutProps = {
  children: React.ReactNode;
};

function ContentLayout({ children }: ContentLayoutProps) {
  return <div className="flex gap-96">{children}</div>;
}

export default ContentLayout;
