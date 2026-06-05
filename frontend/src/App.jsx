import { useEffect, useState } from 'react'
import CreateWalk from './WalkForm'
import axios from 'axios'

function App() {
  const [walks, setWalks] = useState([])

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

  useEffect(() => {
  axios.get('http://localhost:8081/api/walks')
    .then(response => {
      setWalks(response.data)
    })
    .catch(error => {
      console.error(error)
    })
}, [])

  return (
  <div>
    <h1>Walks</h1>

    <CreateWalk onWalkCreated={loadWalks} />

    {walks.map(walk => (
      <div key={walk.id}>
        <h3>{walk.name}</h3>
        <p>{walk.time}</p>
      </div>
    ))}
  </div>
);
}

export default App