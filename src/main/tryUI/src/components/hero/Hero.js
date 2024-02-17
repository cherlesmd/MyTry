import React from "react";

const Hero = ({ tries }) => {
    return (
        <div>
            {tries?.map((item) => {
                return (
                    <div>
                        <p>{item.name}</p>
                    </div>
                );
            })}
        </div>
    );
};
export default Hero;
