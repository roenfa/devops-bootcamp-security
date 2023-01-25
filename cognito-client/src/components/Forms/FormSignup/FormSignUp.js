import React, { useState } from 'react';
import { TextField, Button, Select ,Container} from '@mui/material';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
 
import "./FormSignUp.css";

const FormSignup = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('');
  const [option, setOption] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleRoleChange = (event) => {

    const {name, value} = event.target;



    switch (value) {
      case 'admin':
        setRole('ADMIN_AGALVISB');
        setOption('admin');
        break;
      case 'student':
        setRole('STUDENT_AGALVISB');
        setOption('student');
        break;
      case 'trainer':
        setRole('TRAINER_AGALVISB');
        setOption('trainer');
        break;
      default:
        setOption('');
        setRole('');

      
    }  
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (username === '' || email === '' || password === '' || role === '') {
      alert('Por favor, rellene todos los campos');
      return;
    }
    const user = {
      username: username,
      email: email,
      password: password,
      role: role
    };
    axios.post('http://localhost:8080/api/users/sign-up', user).then((res) => {
      alert('Usuario creado con éxito');
      navigate('/signin');
    }
    ).catch((err) => {
      alert('Error al crear el usuario');
      console.log(err);
    }
    );
  };

  return (
    <form onSubmit={handleSubmit} className="form">
      <div className="signup-title">
        <p>Registrarse</p>
      </div>
    <Container className="form-container">
      <TextField
        label="Correo electrónico"
        name="username"
        value={username}
        onChange={handleUsernameChange}
        variant="outlined"
        className="form-field"
      />
      <TextField
        label="Confirmar correo electrónico"
        type="email"
        name="email"
        value={email}
        onChange={handleEmailChange}
        variant="outlined"
        className="form-field"
      />
      <TextField
        label="Contraseña"
        type="password"
        name="password"
        value={password}
        onChange={handlePasswordChange}
        variant="outlined"
        className="form-field"
      />
     
      <Select
        native
        
        onChange={handleRoleChange}
        inputProps={{
          name: 'role',
          id: 'role-select',
        }}
        className="form-field"
        value={option}
        
      >
        <option aria-label="None" value="" />
        <option value="admin">Administrador</option>
        <option value="student">Estudiante</option>
        <option value="trainer">Entrenador</option>
      </Select>
      <Button type="submit" variant="contained" color="primary" className="form-field" onClick={handleSubmit}> Registrarse </Button>
      </Container>
    </form>
  );
};

export default FormSignup;
