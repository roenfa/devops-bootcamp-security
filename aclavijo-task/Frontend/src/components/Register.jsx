import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";

const SignUp = () => {

   const [email, setEmail] = useState("");
   const [username, setUsername] = useState("");
   const [password, setPassword] = useState("");
   const [role, setRole] = useState("");

  const navigate = useNavigate();


  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post(`/api/users/sign-up`, { email, username, password, role });

    } catch (err) {
      console.error(err);
    }

    navigate('/login')

  };

  return (
    <div className='App'>
        <header className="Header">
        <div className='container'>
        <h2>Sign Up</h2>
            <form onSubmit={handleSubmit}>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" /> 
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" /> 
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" /> 
            <label >
              <input id='btns'
                type="checkbox" 
                value="admin" 
                checked={role === "admin"} 
                onChange={(e) => setRole(e.target.value)}/>
                  Admin
                </label>
              <label>
                <input id='btns'
                  type="checkbox" 
                  value="trainer" 
                  checked={role === "trainer"} 
                  onChange={(e) => setRole(e.target.value)}
                />
                Trainer
              </label>
              <label>
                <input id='btns'
                  type="checkbox" 
                  value="student" 
                  checked={role === "student"} 
                  onChange={(e) => setRole(e.target.value)}
                />
                Student
              </label>
                <button type="submit">Register</button>
            </form>
            <a href='/signin'><button id='btns' >Sign In</button></a>
            <a href='/'><button id='btns' >Back</button></a>
        </div>
        </header>
    </div>
    );

};
      
export default SignUp;