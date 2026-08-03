import axios from "axios";
import {useEffect, useState} from 'react'
export default function Notifications({userId}){
    const [notifications, setNotifications] = useState([])
    const NOTIFICATION_MESSAGES = {
        FRIENDREQ: "New Friend Request From ",
        WALKREQ: "New Walk Request From"
    };
    async function loadNotifications(){
        try{
            const response = await axios.get(
                `http://localhost:8081/api/user/notifications/${userId}`
            )
            setNotifications(response.data)
            
        }
        catch(error){
            console.error(error)
        }
    }

    async function acceptRequest(formData){
        
        const senderId = formData.get("senderId");
        const notificationId = formData.get("notificationId");
        await axios.post("http://localhost:8081/api/user/accept", {
            notificationId: notificationId,
            senderId: senderId,
            recipientId: userId
        }).catch(error=>{
            console.log(error);
        });
        loadNotifications();
    }

    async function ignoreRequest(formData){
        const notificationId = formData.get("notificationId");
        await axios.post("http://localhost:8081/api/user/ignore", {
            notificationId: notificationId,
        }).catch(error=>{
            console.log(error);
        });
        loadNotifications();
    }

    async function acceptWalkInvite(formData){
        const notificationId = formData.get("notificationId");
        await axios.post("http://localhost:8081/api/walks/accept", {
            notificationId: notificationId,
        }).catch(error=>{
            console.log(error);
        });
        loadNotifications();
    }

    async function declineWalkInvite(formData){
        const notificationId = formData.get("notificationId");
        await axios.post("http://localhost:8081/api/walks/decline", {
            notificationId: notificationId,
        }).catch(error=>{
            console.log(error);
        });
        loadNotifications();
    }
    useEffect(() =>{
            if (userId !== -1) {
        loadNotifications();
        }
        }, [userId]);

    
    return (
        <div>
        <h2>Notifications</h2>
        {notifications.map(notification => (
    <div key={notification.id}>
        {NOTIFICATION_MESSAGES[notification.type]}
        {" "}
        {notification.sender.username}

        {notification.type === "FRIENDREQ" && (
            <>
                <form action={acceptRequest}>
                    <input
                        type="hidden"
                        name="senderId"
                        value={notification.sender.id}
                    />
                    <input
                        type="hidden"
                        name="notificationId"
                        value={notification.id}
                    />
                    <button type="submit">Accept Friend</button>
                </form>

                <form action={ignoreRequest}>
                    <input
                        type="hidden"
                        name="notificationId"
                        value={notification.id}
                    />
                    <button type="submit">Ignore</button>
                </form>
            </>
        )}

        {notification.type === "WALKREQ" && (
            <>
                <form action={acceptWalkInvite}>
                    <input
                        type="hidden"
                        name="notificationId"
                        value={notification.id}
                    />
                    <button type="submit">Join Walk</button>
                </form>

                <form action={declineWalkInvite}>
                    <input
                        type="hidden"
                        name="notificationId"
                        value={notification.id}
                    />
                    <button type="submit">Decline</button>
                </form>
            </>
        )}
    </div>
))}
        </div>
    );
}
