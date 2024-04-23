import React from "react";
import UserTries from "./usertries/UserTries";
import Map from "./map/Map";

const MainLayout = ({tries, getDistance}) => {
  return (
    <main className="flex justify-between w-11/12 mx-auto px-36 pt-8">
      <UserTries tries={tries} getDistance={getDistance} />
      <Map />
    </main>
  );
};
export default MainLayout;
