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
        localStorage.setItem("role", response.data.rol);
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("email", response.data.email);
        navigate('/role_redirect');
      } else {
        console.log("Incorrect username or password.");
      }

    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="App">
          <header className="Header">
          <div className='container'>
            <h2>Sign In</h2>
              <form onSubmit={handleSubmit}>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" /> <br /> <br /> 

                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" /> <br /> <br /> 

                <button type="submit"> Login</button>  
              </form>  
              <div>
              <a href='/Register'><button>Register</button></a>
              </div>
              <a href='/'><button>Back</button></a>
          </div> 
        </header>
      </div>
  );  
};  
export default SignIn;