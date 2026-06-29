import axios from 'axios'
export default function CreateAccount({ onAccountCreated }){

    async function handleCreateAccount(formData) {
    const isLoggedIn = true;
    const username = formData.get("username");
    const password = formData.get("password");

    await axios.post("http://localhost:8081/api/user", {
      isLoggedIn,
      username,
      password
    });

    onAccountCreated();
  }

    return( 
        <form action={handleCreateAccount}>
            <input name="username" placeholder="Username" />
            <input name="password" type="password" />
            <button type="submit">Create Account</button>
        </form>
    );
}

