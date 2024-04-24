import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect } from "react";
import MainLayout from "./components/MainLayout";
import Header from "./components/header/Header";
import DistanceButton from "./components/button/DistanceButton";
import { Route, Routes } from "react-router-dom";
import UserTries from "./components/usertries/UserTries";
import { Map } from "mapbox-gl";

function App() {
  const [tries, setTries] = useState([]);
  const [error, setError] = useState("");
  const [location, setLocation] = useState({ latitude: 0, longitude: 0 });
  const [distance, setDistance] = useState("0");

  useEffect(() => {
    const fetchLocation = () => {
      if (!navigator.geolocation) {
        setError("Browser does not support geolocation");
        return;
      }

      navigator.geolocation.getCurrentPosition(success, error);

      function success(position) {
        setLocation({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        });
      }

      function error() {
        setError("Unable to retrieve current location");
      }
    };

    fetchLocation();
  }, [location]);

  const getTries = async (bDistance) => {
    try {
      const response = await api.get(
        `/api/v1/tries?longitude=${location.longitude}&latitude=${location.latitude}&distance=${bDistance}`,
      );
      setTries(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const getDistance = (data) => {
    setDistance(data);
    console.log(data);
    getTries(data);
  };

  return distance === "0" ? (
    <div className="box-border text-center">
      <Header />
      <Routes>
        <Route path="/" element={<DistanceButton getDistance={getDistance} />} />
      </Routes>
    </div>
  ) : (
    <div className="box-border text-center">
      <Header />
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route path="/" element={<UserTries tries={tries} />}>
            <Route path="/" element={<DistanceButton getDistance={getDistance} />} />
          </Route>
          <Route path="/" element={<Map />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
