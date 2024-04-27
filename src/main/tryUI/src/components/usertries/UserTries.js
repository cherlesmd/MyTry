import { Fragment } from "react";
import api from "../../api/axiosConfig";
import DistanceButton from "../button/DistanceButton";

const UserTries = ({ tries, setTries, getDistance }) => {

  const deleteTry = async (index) => {
    try {
      const response = await api.delete(
        `/api/v1/tries?name=${tries[index].name}&longitude=${tries[index].location.x}&latitude=${tries[index].location.y}`,
      );
      const updatedTries = tries.filter((_, i) => i !== index);
      setTries(updatedTries);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Fragment>
      <ul>
        <DistanceButton getDistance={getDistance}></DistanceButton>
        {tries?.map((item, index) => {
          return (
            <li key={index} className="h-8 mb-3 pt-1">
              <span>{item.name}</span>
              <button onClick={() => deleteTry(index)}> - </button>
            </li>
          );
        })}
      </ul>
    </Fragment>
  );
};
export default UserTries;
