import axios from "axios";
export default function CreateInvite({ userId, onInviteSent }){
    async function handleCreateInvite(formData){
        const recipient = formData.get("recipient");

        await axios.post("http://localhost:8081/api/user/invite", {
            senderId: userId,
            recipientUsername: recipient
        });

        onInviteSent();
    }

    return (
        <form action={handleCreateInvite}>
            <input name= "recipient" placeholder="Invite by Username" />
            <button type="submit"> Send </button>
        </form>
    )
}