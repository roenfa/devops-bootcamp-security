import React from 'react'

export default function Admin() {
  return (
    <div className="App">
    <header className="App-header">
      <h1>Hello Admin</h1>
      <div className="App-intro">
      <a href="/subjects"><button>Subjects</button></a>
      <a href="/subjects-post"><button>Add Subjects</button></a>
      <a href="/trainers-list"><button>Trainers List</button></a>
      </div>
    </header>
  </div>
  )
}
