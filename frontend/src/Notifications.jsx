import axios from "axios";
import {useEffect, useState} from 'react'
export default function Notifications({userId}){
    const [notifications, setNotifications] = useState([])
    const NOTIFICATION_MESSAGES = {
        FRIENDREQ: "New Friend Request From "
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
        const notificationId = formData.get("notificationId")
        await axios.post("http://localhost:8081/api/user/accept", {
            notificationId: notificationId,
            senderId: senderId,
            recipientId: userId
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
            <div key = {notification.id}>
                {NOTIFICATION_MESSAGES[notification.type]}
                {notification.sender.username}
                {"     "}
                <form action={acceptRequest}>
                    <input type = "hidden" name = "senderId" value={notification.sender.id}></input>
                    <input type = "hidden" name = "notificationId" value={notification.id}></input>
                    <button type="submit"> Accept </button>
                </form>
                {"     "}
                <form>
                    <button> Ignore </button>
                </form>

            </div>
        ))}
        </div>
    );
}
