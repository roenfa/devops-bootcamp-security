import React, { useState } from 'react'
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SubjectsPost = () => {

    const [firstName, setfirstName] = useState("");
    const [lastName, setlastName] = useState("");
    const [email, setemail] = useState("");
    const [country, setcountry] = useState("");
    const navigate = useNavigate();

    let config = {
         headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('idToken')
            }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
          await axios.post(`/api/trainers/student`, { firstName, lastName,email ,country }, config);
          navigate('/role_redirect');

        } catch (err) {
          console.error(err);
        }
    
        setfirstName("");
        setlastName("");
        setemail("");
        setcountry("");
    
      };


  return (
    <div className='App'>
    <header className="Header">
    <div className='container'>
    <h2>Add a Student</h2>
        <form onSubmit={handleSubmit}>
        <input type="text" value={firstName} onChange={(e) => setfirstName(e.target.value)} placeholder="First Name" />
        <input type="text" value={lastName} onChange={(e) => setlastName(e.target.value)} placeholder="Last Name" />
        <input type="text" value={email} onChange={(e) => setemail(e.target.value)} placeholder="Email" />
        <input type="text" value={country} onChange={(e) => setcountry(e.target.value)} placeholder="Country" />

        <button type="submit">Add</button>
         
        </form>
        <a href="/role_redirect"><button className='logout'>Back</button></a>
    </div>
    </header>
</div>
  )
}

export default SubjectsPost;