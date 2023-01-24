import React from 'react';


export default function Admin() {
  return (
    <div className="App">
      <header>
        <h1>Welcome Trainer {localStorage.getItem('username')}</h1>

        <div className="App-intro">

          <a href="/subjects-post">
            <button>Add Subjects</button>
          </a>

          <a href="/subjects-list">
            <button>Subjects List</button>
          </a>

          <a href="/students-post">
            <button>Add Students</button>
          </a>

          <a href="/students-list">
            <button>Students List</button>
          </a>

          <a href="/mydetails">
            <button>My Details</button>
          </a>
        </div>

      </header>

    </div>  
  );   // added missing closing bracket for return statement 
}