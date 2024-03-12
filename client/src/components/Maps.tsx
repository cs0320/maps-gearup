import Map, { Layer, MapLayerMouseEvent, Source, ViewStateChangeEvent} from "react-map-gl";
import "mapbox-gl/dist/mapbox-gl.css";
import { useState } from "react";
const MAPBOX_API_KEY = process.env.MAPBOX_TOKEN;
if (!MAPBOX_API_KEY) {
  console.error("Mapbox API key not found. Please add it to your .env file.");
}
const ProvidenceLatLong = {
  lat: 41.824,
  long: -71.4128,
};
const initialZoom = 12;
export default function Maps() {
  const [viewState, setViewState] = useState({
    longitude: ProvidenceLatLong.long,
    latitude: ProvidenceLatLong.lat,
    zoom: initialZoom,
  });
  function onMapClick(e: MapLayerMouseEvent) {
    console.log(e);
    console.log("Latitude is " + e.lngLat.lat);
    console.log("Longitude is " + e.lngLat.lng);
  }

  return (
    <div className="map">
      <Map 
        mapboxAccessToken= {MAPBOX_API_KEY}
        longitude={viewState.longitude}
        latitude={viewState.latitude}
        zoom={viewState.zoom}
        onMove={(ev: ViewStateChangeEvent) => setViewState(ev.viewState)}
        style={{ width: window.innerWidth, height: window.innerHeight }}
        mapStyle={"mapbox://styles/mapbox/streets-v12"}
        onClick={(ev: MapLayerMouseEvent) => onMapClick(ev)}
        >
          <Source id="geo_data" type="geojson">
          </Source>
      </Map>
    {/* TODO: MAPS : PLACE MAP CONTENT HERE (all steps are in this file.) */}
    </div>
  );
  
}
