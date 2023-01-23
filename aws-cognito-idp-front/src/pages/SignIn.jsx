import React, { useState } from "react";
import { useAuth } from "../components/authentication/AuthProvider";

const SignIn = () => {
    const { login } = useAuth();

    const [ credentials, setCredentials ] = useState({'username':'', 'password':''});

    const handleInputChange = (event) => {
        const newCredentials = {...credentials};
        newCredentials[event.target.name] = event.target.value;
        setCredentials(newCredentials);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        login({
            username: credentials.username,
            password: credentials.password
        });
    };
  
  
    return (
      <div>
        <h3>Sing-in</h3>
        <form onSubmit={handleSubmit}>
            <input type="text" name="username" placeholder="username" onChange={handleInputChange}/>
            <input type="password" name="password" placeholder="password" onChange={handleInputChange}/>
            <input type="submit" value="submit" />
        </form>
      </div>
    );
}

export default SignIn
