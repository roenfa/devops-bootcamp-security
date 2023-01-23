import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SignIn = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(`/api/users/sign-in`, { username, password });

      if (response.data.idToken) {
        localStorage.setItem("idToken", response.data.idToken);
        localStorage.setItem("role", response.data.role);
        console.log("Successfully signed in!");
        navigate('/home-signed-in');
      } else {
        console.log("Incorrect username or password.");
      }
    } catch (err) {
      console.error(err);
    }

    setUsername("");
    setPassword("");

  };

  return (
    <div className="App">
          <header className="App-header">
          <div className='container'>
            <h2>Sign In</h2>
              <form onSubmit={handleSubmit}>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" /> <br /> <br /> 

                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" /> <br /> <br /> 

                <button type="submit">Sign In</button>  
              </form>  
              <div>
                <a href='/signup'><button id='btns'>Sign Up</button></a>
                <a href='/'><button id='btns'>Home</button></a>
              </div>
          </div> 
        </header>
      </div>
  );  
};  
export default SignIn;