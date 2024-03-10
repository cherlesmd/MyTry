import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect } from "react";
import MainLayout from "./components/MainLayout";
import Header from "./components/header/Header";

function App() {
    const [tries, setTries] = useState();

    const getTries = async () => {
        try {
            const response = await api.get("/api/v1/tries");
            console.log(response.data);
            setTries(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getTries();
    }, []);

    return (
        <div class="box-border text-center">
            <Header />
            <MainLayout tries={tries} />
        </div>
    );
}

export default App;
