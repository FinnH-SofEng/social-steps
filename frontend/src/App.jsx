import { useEffect, useState } from 'react'
import CreateWalk from './WalkForm'
import CreateAccount from './LoginForm'
import axios from 'axios'

function App() {
  const [walks, setWalks] = useState([])
  const [status, setStatus] = useState('logged out')

  async function loadWalks() {
    try {
      const response = await axios.get(
        'http://localhost:8081/api/walks'
      )
      setWalks(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  async function createAccount(){
    setStatus('logged in')
  }

  useEffect(() => {
  axios.get('http://localhost:8081/api/walks')
    .then(response => {
      setWalks(response.data)
    })
    .catch(error => {
      console.error(error)
    })
}, [])

  if (status === 'logged in'){
    return <div>
    <h1>Walks</h1>

    <CreateWalk onWalkCreated={loadWalks} />

    {walks.map(walk => (
      <div key={walk.id}>
        <h3>{walk.name}</h3>
        <p>{walk.time}</p>
      </div>
    ))}
  </div>  
  }

  return (
    <CreateAccount onAccountCreated={createAccount}></CreateAccount>
  );
}

export default App