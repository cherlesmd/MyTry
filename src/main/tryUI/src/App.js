import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect } from "react";
import MainLayout from "./components/MainLayout";
import Header from "./components/header/Header";
import DistanceButton from "./components/button/DistanceButton";
import { Route, Routes } from "react-router-dom";

function App() {
  const [tries, setTries] = useState([]);
  const [error, setError] = useState("");
  const [lng, setLng] = useState(null);
  const [lat, setLat] = useState(null);
  const [distance, setDistance] = useState("0");

  useEffect(() => {
    const fetchLocation = () => {
      if (!navigator.geolocation) {
        setError("Browser does not support geolocation");
        return;
      }

      navigator.geolocation.getCurrentPosition(success, error);

      function success(position) {
        setLng(position.coords.longitude);
        setLat(position.coords.latitude);
      }

      function error() {
        setError("Unable to retrieve current location");
      }
    };

    fetchLocation();
  }, []);

  const getTries = async (bDistance) => {
    try {
      const response = await api.get(
        `/api/v1/tries/661f43c121e852e0fdc00e81?longitude=${lng}&latitude=${lat}&distance=${bDistance}`,
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
        <Route
          path="/"
          element={<DistanceButton getDistance={getDistance} />}
        />
      </Routes>
    </div>
  ) : (
    <div className="box-border text-center">
      <Header />
      <Routes>
        <Route
          path="/"
          element={
            <MainLayout
              tries={tries}
              setTries={setTries}
              getDistance={getDistance}
              lng={lng}
              setLng={setLng}
              lat={lat}
              setLat={setLat}
            />
          }
        ></Route>
      </Routes>
    </div>
  );
}

export default App;
