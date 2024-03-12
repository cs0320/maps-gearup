
const MAPBOX_API_KEY = process.env.MAPBOX_TOKEN;
if (!MAPBOX_API_KEY) {
  console.error("Mapbox API key not found. Please add it to your .env file.");
}

export default function Maps() {
  return <div className="map">{/* TODO: MAPS : PLACE MAP CONTENT HERE (all steps are in this file.) */}</div>;
}
