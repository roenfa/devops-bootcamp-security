import React from 'react'

export default function Home() {
  return (
    <div className="App">
          <header className="App-header">
            <h1>Hi, welcome</h1>
            <div className="App-intro">
              <a href='/signin'><button>Sign In</button></a>
              <a href='/signup'><button>Sign Up</button></a>
            </div>
          </header>
        </div>
  )
}
