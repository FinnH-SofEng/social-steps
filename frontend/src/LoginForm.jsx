import axios from 'axios'
export default function Login({OnLoggedIn}){
    async function handleLogin(formData){
        const isLoggedIn = true;
        const username = formData.get("username");
        const password = formData.get("password");

        await axios.post("http://localhost:8081/api/login", {
            isLoggedIn,
            username,
            password
        }).then(response=>{
            OnLoggedIn(response.data);
        }).catch(error=>{
            console.log(error);
        })

        

    }
    return( 
        <form action={handleLogin}>
            <input name="username" placeholder="Username" />
            <input name="password" type="password" />
            <button type="submit">Login</button>
        </form>
    );
}