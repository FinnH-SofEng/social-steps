import axios from "axios";
export default function CreateWalk({ onWalkCreated }) {
  async function handleCreateWalk(formData) {
    const name = formData.get("name");
    const time = formData.get("time");

    await axios.post("http://localhost:8081/api/walks", {
      name,
      time
    });

    onWalkCreated();
  }

  return (
    <form action={handleCreateWalk}>
      <input name="name" placeholder="Walk name" />
      <input name="time" type="datetime-local" />
      <button type="submit">Create Walk</button>
    </form>
  );
}