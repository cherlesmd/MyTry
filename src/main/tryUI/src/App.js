import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect } from "react";
import MainLayout from "./components/MainLayout";
import Header from "./components/header/Header";

function App() {
  const [tries, setTries] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const getTries = async (latitude, longitude) => {
      try {
        const response = await api.get(
          `/api/v1/tries?longitude=${longitude}&latitude=${latitude}`,
        );
        console.log(response.data);
        setTries(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    const fetchLocation = () => {
      if (!navigator.geolocation) {
        setError("Browser does not support geolocation");
        return;
      }

      navigator.geolocation.getCurrentPosition(success, error);

      function success(position) {
        getTries(position.coords.latitude, position.coords.longitude);
      }

      function error() {
        setError("Unable to retrieve current location");
      }
    };

    fetchLocation();
  }, []);

  return (
    <div className="box-border text-center">
      <Header />
      <MainLayout tries={tries} />
      {error && <p>{error}</p>}
    </div>
  );
}

export default App;
