import React from "react";

const Hero = ({ tries }) => {
    return (
        <div>
            {tries?.map((item) => {
                return (
                    <div className="border-solid border-b-[2px] border-b-black">
                        <p>{item.name}</p>
                    </div>
                );
            })}
        </div>
    );
};
export default Hero;
