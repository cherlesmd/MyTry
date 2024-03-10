import React from "react";
import UserTries from "./usertries/UserTries";
import Map from "./map/Map";

const MainLayout = ({ tries }) => {
    return (
        <main className="flex justify-between w-11/12 mx-auto px-36 pt-8">
            <UserTries tries={tries} />
            <Map />
        </main>
    );
};
export default MainLayout;
