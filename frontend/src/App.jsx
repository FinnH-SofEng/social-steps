import { useEffect, useState } from 'react'
import axios from 'axios'

function App() {

  const [walks, setWalks] = useState([])

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

      {walks.map(walk => (
        <div key={walk.id}>
          <h3>{walk.title}</h3>
          <p>{walk.meetingLocation}</p>
        </div>
      ))}
    </div>
  )
}

export default App