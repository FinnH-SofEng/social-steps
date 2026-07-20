import {useEffect, useState} from 'react'
import axios from "axios";
export default function Friends({id}){
    const [friends, setFriends] = useState([])
    async function loadFriends(){
        try {
        const response = await axios.get(
            `http://localhost:8081/api/user/friends/${id}`
        )
        setFriends(response.data)
        console.log(response.data);
        } catch (error) {
        console.error(error)
        }
    } 
    useEffect(() =>{
        if (id !== -1) {
    loadFriends();
    }
    }, [id]);

    return (
        <div>
        <h2>Friends</h2>
        {friends.map(user => (
            <div key = {user.id}>
                {user.id}
                <h3>{user.username}</h3>
            </div>
        ))}
        </div>
    );
}