import { Fragment } from "react";
import api from "../../api/axiosConfig";
import DistanceButton from "../button/DistanceButton";
import Search from "../search/Search";

const UserTries = ({ tries, setTries, getDistance, feature, setFeature }) => {

  const deleteTry = async (index) => {
    console.log(tries[index].location.x);
    try {
      const response = await api.delete(
        `/api/v1/tries/661f43c121e852e0fdc00e81?name=${tries[index].name}&longitude=${tries[index].location.x}&latitude=${tries[index].location.y}`,
      );
      const updatedTries = tries.filter((_, i) => i !== index);
      setTries(updatedTries);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Fragment>
      <ul>
        <Search feature={feature} setFeature={setFeature} tries={tries} setTries={setTries}></Search>
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
