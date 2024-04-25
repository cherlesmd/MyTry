import Map from "./map/Map";
import React from "react";
import UserTries from "./usertries/UserTries";

const MainLayout = ({
  tries,
  setTries,
  getDistance,
  lng,
  setLng,
  lat,
  setLat,
}) => {
  return (
    <main className="flex justify-between w-11/12 mx-auto px-36 pt-8">
      <UserTries tries={tries} setTries={setTries} getDistance={getDistance}
      ></UserTries>
      <Map lng={lng} setLng={setLng} lat={lat} setLat={setLat}></Map>
    </main>
  );
};
export default MainLayout;
