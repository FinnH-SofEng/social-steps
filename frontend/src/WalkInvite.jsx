import { useEffect, useState } from "react";
import axios from "axios";

export default function SendInvite({ userId, walkId, onInviteSent }) {
  const [friends, setFriends] = useState([]);
  const [selectedFriendId, setSelectedFriendId] = useState("");

  useEffect(() => {
    async function loadFriends() {
      try {
        const response = await axios.get(
          `http://localhost:8081/api/user/friends/${userId}`
        );

        setFriends(response.data);
      } catch (error) {
        console.error("Failed to load friends", error);
      }
    }

    if (userId !== undefined && userId !== null && userId !== -1) {
      loadFriends();
    }
  }, [userId]);

  async function handleSendInvite(event) {
    event.preventDefault();

    if (!selectedFriendId) {
      return;
    }

    try {
      await axios.post("http://localhost:8081/api/walks/invite", {
        userId,
        walkId,
        recipientId: Number(selectedFriendId)
      });

      setSelectedFriendId("");
      onInviteSent?.();
    } catch (error) {
      console.error("Failed to send invite", error);
    }
  }

  return (
    <form onSubmit={handleSendInvite}>
      <label htmlFor="friend">Invite a friend</label>

      <select
        id="friend"
        value={selectedFriendId}
        onChange={(event) => setSelectedFriendId(event.target.value)}
      >
        <option value="">Select a friend</option>

        {friends.map((friend) => (
          <option key={friend.id} value={friend.id}>
            {friend.username}
          </option>
        ))}
      </select>

      <button type="submit" disabled={!selectedFriendId}>
        Send Invite
      </button>
    </form>
  );
}