import React, { useState } from 'react'
import axios from "axios";
import { useNavigate } from "react-router-dom";
import CryptoJS from 'crypto-js';


const DecryptData = (plaintext, key) => {
  const ciphertext = CryptoJS.AES.decrypt(plaintext, key);
  return ciphertext;
}
const SubjectsPost = () => {

    const [name, setname] = useState("");
    const [trainer, settrainer] = useState({id:""});
    const navigate = useNavigate();

    let config = {
         headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('idToken')
            }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
          // settrainer(Id);
          await axios.post(`/api/trainers/subject`, { name, trainer }, config);
          navigate('/role_redirect');
        } catch (err) {
          console.error(err);
        }
    
        setname("");
        settrainer({id:""});
    
      };


  return (
    <div className='App'>
    <header className="Header">
    <div className='container'>
    <h2>Add a Subject</h2>
        <form onSubmit={handleSubmit}>
        <input type="text" value={name} onChange={(e) => setname(e.target.value)} placeholder="Subject Name" />
        <input type="text" value={trainer.id} onChange={(e) => settrainer({...trainer,id:e.target.value})} placeholder="Trainer Id" />

            <button type="submit">Add</button>
         
        </form>
        <a href="/role_redirect"><button className='logout'>Back</button></a>
    </div>
    </header>
</div>
  )
}

export default SubjectsPost;