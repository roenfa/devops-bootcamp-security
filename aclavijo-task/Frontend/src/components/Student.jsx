import React from 'react';

export default function Admin() {
  return (
    <div className="App">
      <header>
        <h1>Welcome Student {localStorage.getItem('username')}</h1>

        <div className="App-intro">

          <a href="/my-subjects">
            <button>My Subjects</button>
          </a>

          <a href="/mydetails">
            <button>My Details</button>
          </a>
        </div>

      </header>

    </div>  
  );   // added missing closing bracket for return statement 
}