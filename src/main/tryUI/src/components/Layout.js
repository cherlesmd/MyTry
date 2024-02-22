import { Outlet } from "react-router-dom";
import React from "react";

const Layout = () => {
  return (
    <main className="flex justify-between">
      <Outlet />
    </main>
  )
}
export default Layout;
