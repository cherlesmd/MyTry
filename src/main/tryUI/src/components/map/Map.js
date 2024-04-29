import React, { useRef, useEffect, useState, Fragment } from "react";
// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "!mapbox-gl";
import Search from "../search/Search";

mapboxgl.accessToken =
  "";

const Map = ({ lng, setLng, lat, setLat }) => {
  const mapContainer = useRef(null);
  const map = useRef(null);
  const [zoom, setZoom] = useState(13);

  useEffect(() => {
    if (map.current) return; // initialize map only once

    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/mapbox/streets-v12",
      center: [lng, lat],
      zoom: zoom,
    });

    map.current.on("move", () => {
      setLng(map.current.getCenter().lng.toFixed(4));
      setLat(map.current.getCenter().lat.toFixed(4));
      setZoom(map.current.getZoom().toFixed(2));
    });
  }, [zoom, lng, lat, setLng, setLat]);

  return (
    <Fragment>
      <div ref={mapContainer} className="h-96 w-5/12 z-1" />
    </Fragment>
  );
};
export default Map;
