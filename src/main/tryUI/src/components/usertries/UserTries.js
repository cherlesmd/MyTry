import React from "react";
import { Fragment } from "react";

const UserTries = ({ tries }) => {
    return (
        <Fragment>
            <ul>
                {tries?.map((item) => {
                    return <li className="h-8 mb-3 pt-1">{item.name}</li>;
                })}
            </ul>
        </Fragment>
    );
};
export default UserTries;
