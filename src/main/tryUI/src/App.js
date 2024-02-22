import "./App.css";
import api from "./api/axiosConfig";
import { useState, useEffect } from "react";
import Layout from "./components/Layout";
import { Routes, Route } from "react-router-dom";
import Home from "./components/home/Home";
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
        <div class="text-center">
            <Header />
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route path="/" element={<Home tries={tries} />}></Route>
                </Route>
            </Routes>
        </div>
    );
}

export default App;
