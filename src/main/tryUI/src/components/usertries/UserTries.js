import React from "react";
import { Fragment } from "react";

const UserTries = ({ tries }) => {
    return (
        <Fragment>
            <ul className="border-solid border-b-[2px] border-b-black">
                {tries?.map((item) => {
                    return <li>{item.name}</li>;
                })}
            </ul>
        </Fragment>
    );
};
export default UserTries;
