import React from "react";
import { Outlet } from "react-router-dom";

const MainLayout = ({ tries, getDistance }) => {
  return (
    <main className="flex justify-between w-11/12 mx-auto px-36 pt-8">
      <Outlet />
    </main>
  );
};
export default MainLayout;
