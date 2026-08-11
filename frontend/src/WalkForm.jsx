import { useState } from "react";
import axios from "axios";
import LocationPicker from "./LocationPicker";

export default function CreateWalk({ userId, onWalkCreated }) {
  const [location, setLocation] = useState(null);

  async function handleCreateWalk(formData) {
    if (!location) {
      alert("Select a location on the map.");
      return;
    }

    const name = formData.get("name");
    const time = formData.get("time");

    await axios.post(
      "http://localhost:8081/api/walks/create",
      {
        creatorId: userId,
        name,
        time,
        latitude: location.latitude,
        longitude: location.longitude
      }
    );

    onWalkCreated();
  }

  return (
    <form action={handleCreateWalk}>
      <input name="name" placeholder="Walk name" required />

      <input name="time" type="datetime-local" required />

      <LocationPicker
        selectedLocation={location}
        onLocationSelected={setLocation}
      />

      {location && (
        <p>
          Selected: {location.latitude.toFixed(5)},{" "}
          {location.longitude.toFixed(5)}
        </p>
      )}

      <button type="submit">Create Walk</button>
    </form>
  );
}