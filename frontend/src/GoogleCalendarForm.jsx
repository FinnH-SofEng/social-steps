import axios from "axios";
export default function ConnectCalendar({userId}){
    
    async function connectCalendar(formData){
        window.location.href =
    `http://localhost:8081/api/google/connect?userId=${userId}`;
    }

    return(
        <div>
            <button onClick={connectCalendar}>
                Connect Google Calendar
            </button>
        </div>
    );
}