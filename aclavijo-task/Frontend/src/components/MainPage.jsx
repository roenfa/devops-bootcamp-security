import React from 'react';

export default function MainPage() {
  const username = localStorage.getItem("username");

  return (
    <div className="App">
      <header className="Header">
        {username ? <h1>Welcome {username}</h1> : <h1>Welcome please SignIn/SignUp</h1>}
        {!username && (
          <div className="Intro">
            <a href="/login"><button>Log In</button></a>
            <a href="/register"><button>Register</button></a>
          </div>  
        )}  
      </header>  
    </div>  
  );  
} 