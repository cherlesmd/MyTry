import React, { useState } from "react";
import { Fragment } from "react";
import { Outlet } from "react-router-dom";

const UserTries = ({ tries }) => {
  const [triesList, setTriesList] = useState(tries || null);

  function deleteTry(index) {
    console.log(index);
    const updatedTries = tries.filter((_, i) => i !== index);
    setTriesList(updatedTries);
    console.log("set");
  }

  return (
    <Fragment>
      <ul>
        <Outlet />
        {tries?.map((item, index) => {
          return (
            <li key={index} className="h-8 mb-3 pt-1">
              <span>{item.name}</span>
              <button onClick={() => deleteTry(index)}> - + </button>
            </li>
          );
        })}
      </ul>
    </Fragment>
  );
};
export default UserTries;
