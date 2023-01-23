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

    navigate('/signin')

  };

  return (
    <div className='App'>
        <header className="App-header">
        <div className='container'>
        <h2>Sign Up</h2>
            <form onSubmit={handleSubmit}>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" /> 
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" /> 
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" /> 
            <select value={role} onChange={(e) => setRole(e.target.value)}>
                <option selected>Select Role</option>
                <option value="admin">Admin</option>
                <option value="trainer">Trainer</option>
                <option value="student">Student</option>
            </select>

                <button type="submit">Register</button>
                
            </form>
            <a href='/signin'><button id='btns' >Sign In</button></a>
            <a href='/'><button id='btns' >Home</button></a>
        </div>
        </header>
    </div>
    );

};
      
export default SignUp;