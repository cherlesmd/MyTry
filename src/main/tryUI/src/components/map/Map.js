import React, { useRef, useEffect, useState } from "react";
// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "!mapbox-gl";

mapboxgl.accessToken =
    "";

const Map = () => {
    const mapContainer = useRef(null);
    const map = useRef(null);
    const zoom = 13;
    const setLng = useState(null);
    const setLat = useState(null);
    const setZoom = useState(null);
    useEffect(() => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition((position) => {
                if (map.current) return; // initialize map only once
                map.current = new mapboxgl.Map({
                    container: mapContainer.current,
                    style: "mapbox://styles/mapbox/streets-v12",
                    center: [position.coords.longitude, position.coords.latitude],
                    zoom: zoom,
                });
                map.current.on("move", () => {
                    setLng(map.current.getCenter().lng.toFixed(4));
                    setLat(map.current.getCenter().lat.toFixed(4));
                    setZoom(map.current.getZoom().toFixed(2));
                });
            });
        }
    });

    return <div ref={mapContainer} className="h-96 w-5/12" />;
};
export default Map;
