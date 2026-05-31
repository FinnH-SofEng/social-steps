import axios from "axios";
export default function CreateWalk() {
  async function handleCreateWalk(formData) {
    const query = formData.get("query");
    await axios.post("http://localhost:8081/api/walks",
    {
        name: "name",
        time: "time"
    });
    onWalkCreated();
    alert(`You created a walk for '${query}'`);
  }
  return (
    <form action={handleCreateWalk}>
      <input name="name" defaultValue={"name"}/>
      <input name="time" defaultValue={"time 'MM/DD/YYYY xx:xx am/pm'"}/>
      <button type="submit">Create Walk</button>
    </form>
  );
}