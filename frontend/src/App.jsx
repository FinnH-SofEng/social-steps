import { useEffect, useState } from 'react'
import CreateWalk from './WalkForm'
import CreateAccount from './CreateForm'
import Login from './LoginForm'
import axios from 'axios'

function App() {
  const [walks, setWalks] = useState([])
  const [status, setStatus] = useState('logged out')
  const [id, setId] = useState(-1)
  const [name, setName] = useState('name')
  const [password, setPassword] = useState('password')

  async function loadWalks() {
    try {
      const response = await axios.get(
        `http://localhost:8081/api/walks/user/${id}`
      )
      setWalks(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  async function createAccount(){
    setStatus('logged in');
  }

  async function login(user){
    setStatus('logged in');
    setId(user.id);
    setName(user.username);
    setPassword(user.password);
    console.log(user); 
  }

  useEffect(() => {
  if (status === 'logged in' && id !== -1) {
    loadWalks();
  }
  }, [status, id]);


  if (status === 'logged in'){
    return <div>
    <h1>Walks</h1>

    <CreateWalk userId = {id} onWalkCreated={loadWalks} />

    {walks.map(walk => (
      <div key={walk.id}>
        <h3>{walk.name}</h3>
        <p>{walk.time}</p>
      </div>
    ))}
  </div>  
  }

  return (
    <>
    <CreateAccount onAccountCreated={createAccount}></CreateAccount>
    <Login OnLoggedIn={login}></Login>
    </>
  );
}

export default App