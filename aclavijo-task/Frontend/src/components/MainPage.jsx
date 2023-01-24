import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function MainPage() {
  const username = localStorage.getItem('username');
  const navigate = useNavigate();
  return (
    <div className="App">
      <header className="Header">
        {username ? <h1>Welcome {username}</h1> & navigate('/role_redirect') : <h1>Welcome please SignIn/SignUp</h1>}
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