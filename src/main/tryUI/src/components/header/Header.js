import React from "react";

const Header = () => {
    return (
        <nav className="h-16 flex gap-10 justify-between mt-1 mb-6 mx-4">
            <div className="flex items-center flex-shrink-0">
                <a href="/">My Try</a>
            </div>
            <div className="flex flex-shrink-0 justify-between md:gap-3 items-center">
                <a href="/">itinerary</a>
                <a href="/">Profile</a>
            </div>
        </nav>
    );
};
export default Header;
