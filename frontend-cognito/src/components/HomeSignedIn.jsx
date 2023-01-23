import React from 'react'
import { useNavigate } from 'react-router-dom';
import Admin from './Admin';
import Student from './Student';
import Trainer from './Trainer';

const HomeSignedIn = () => {

const navigate = useNavigate();

const handleSubmit = async (e) => {
    e.preventDefault();
    localStorage.clear();
    navigate('/');
}

  const role = localStorage.getItem('role');
  
  let ComponentToImport;

  switch (role) {
    case 'admin':
      ComponentToImport = <Admin />;
      break;

    case 'trainer':
    ComponentToImport = <Trainer />;
    break;

    case 'student':
      ComponentToImport = <Student />;
      break;

    default:
      ComponentToImport = null;
      break;  
  }

  return (
    <div className="App">
    <header className="App-header">
      <div className="App-intro">
        {ComponentToImport ? ComponentToImport : null}
        <button className='logout-btn' onClick={handleSubmit}>Logout</button>

      </div>
    </header>
  </div>
  )
}

export default HomeSignedIn;