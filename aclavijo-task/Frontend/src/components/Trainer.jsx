import React from 'react';

const Trainer = () => {

  return (
    <div className="App">
          <header className="App-header">
            <h1>Hi Trainer {localStorage.getItem("username")}</h1>
            <div className="App-intro">
              <button onClick={() => window.location.href="/subjects"}>Subjects</button>
              <button onClick={() => window.location.href="/subjects-post"}>Add Subjects</button>
            </div>
          </header>
        </div>
  )
}
export default Trainer; 