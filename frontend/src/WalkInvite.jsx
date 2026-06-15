import axios from "axios";
export default function SendInvite({ onInviteSent }) {
  async function handleSendInvite(formData) {
    const name = formData.get("name");
    

    await axios.post("http://localhost:8081/api/walks", {
      name
    });

    onInviteSent();
  }

  return (
    <form action={handleSendInvite}>
      <input name="name" placeholder="Username" />
      <input name="time" type="datetime-local" />
      <button type="submit">Create Walk</button>
    </form>
  );
}