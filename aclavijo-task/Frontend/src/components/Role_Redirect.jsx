import React from 'react'
import { useNavigate } from 'react-router-dom';
import Admin from './Admin';
import Student from './Student';
import Trainer from './Trainer';

const Role_Redirect = () => {

  const navigate = useNavigate();

const handleSubmit = async (e) => {
    e.preventDefault();
    localStorage.clear();
    navigate('/');
}
  const role = localStorage.getItem('role');
  
  let ComponentToImport;

  switch (role) {
    case 'Admin':
      ComponentToImport = <Admin />;
      break;

    case 'Trainer':
    ComponentToImport = <Trainer />;
    break;

    case 'Student':
      ComponentToImport = <Student />;
      break;

    default:
      ComponentToImport = null;
      break;  
  }

  return (
    <div className="App">
    <header className="Header">
      <div className="App-intro">
        {ComponentToImport ? ComponentToImport : null}
        <button className = 'logout' onClick={handleSubmit}>Logout</button>

      </div>
    </header>
  </div>
  )
} 

export default Role_Redirect;