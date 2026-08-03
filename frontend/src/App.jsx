import { useEffect, useState } from 'react'
import CreateWalk from './WalkForm'
import CreateAccount from './CreateForm'
import Login from './LoginForm'
import axios from 'axios'
import Friends from './Friends'
import CreateInvite from './FriendForm'
import Notifications from './Notifications'
import SendInvite from './WalkInvite'

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
  async function inviteSent(){
    console.log("sent")
  }
  async function createAccount(){
    setStatus('logged in');
  }
  async function loadNotifications(){

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
      <h1>Create Walk</h1>
    <CreateWalk userId = {id} onWalkCreated={loadWalks} />

    <h1>Walks</h1>
    
    {walks.map(walk => (
      <div key={walk.id}>
        <h2>{walk.name}</h2>
        <p>{walk.time}</p>
        <h4>Walk Participants</h4>
        {walk.participants.map(participant =>(
          <div key={participant.id}>
            <p>{participant.username}</p>
          </div>
        ))}
        <SendInvite
        userId={id}
        walkId={walk.id}
        onInviteSent={loadNotifications}
        />
      </div>  
    ))}
    
    <Friends id = {id}></Friends>

    <CreateInvite userId = {id} onInviteSent={inviteSent}></CreateInvite>

    <Notifications userId = {id} ></Notifications>
      
    
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