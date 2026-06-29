import axios from "axios";
export default function CreateWalk({ userId, onWalkCreated }) {
  async function handleCreateWalk(formData) {
    const name = formData.get("name");
    const time = formData.get("time");
    
    console.log(userId);
    await axios.post("http://localhost:8081/api/walks/create", {
      creatorId: userId,
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