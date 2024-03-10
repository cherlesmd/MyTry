import React from "react";
import UserTries from "./usertries/UserTries";
import Map from "./map/Map";

const MainLayout = ({ tries }) => {
    return (
        <main className="flex justify-between">
            <UserTries tries={tries} />
            <Map />
        </main>
    );
};
export default MainLayout;
