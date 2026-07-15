import axios from "axios";
import {useEffect, useState} from 'react'
export default function Notifications({userId}){
    const [notifications, setNotifications] = useState([])
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
    useEffect(() =>{
            if (userId !== -1) {
        loadNotifications();
        }
        }, [userId]);

    return (
        <div>
        <h2>Notifications</h2>
        {notifications.map(notification => (
            <div key = {notification.type}>
                <h3>test</h3>
            </div>
        ))}
        </div>
    );
}
