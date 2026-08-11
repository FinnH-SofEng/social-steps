import { MapContainer, Marker, TileLayer, useMapEvents } from "react-leaflet";
import "leaflet/dist/leaflet.css";

function ClickHandler({ selectedLocation, onLocationSelected }) {
  useMapEvents({
    click(event) {
      onLocationSelected({
        latitude: event.latlng.lat,
        longitude: event.latlng.lng
      });
    }
  });

  if (!selectedLocation) {
    return null;
  }

  return (
    <Marker
      position={[
        selectedLocation.latitude,
        selectedLocation.longitude
      ]}
    />
  );
}

export default function LocationPicker({
  selectedLocation,
  onLocationSelected
}) {
  // Replace this with a sensible center for your app's area.
  const initialPosition = [40.7128, -74.006];

  return (
    <MapContainer
      center={initialPosition}
      zoom={13}
      style={{ height: "400px", width: "100%" }}
    >
      <TileLayer
        url="https://tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution="&copy; OpenStreetMap contributors"
      />

      <ClickHandler
        selectedLocation={selectedLocation}
        onLocationSelected={onLocationSelected}
      />
    </MapContainer>
  );
}