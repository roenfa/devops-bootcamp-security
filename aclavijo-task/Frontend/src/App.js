import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import React from 'react';
import Main from './components/MainPage'
import Login from './components/Login';
import SubjectsList from './components/SubjectsList';
import Register from './components/Register';
import Role_Redirect from './components/Role_Redirect';
import SubjectsPost from './components/SubjectsPost';
import TrainersList from './components/TrainersList';
import StudentList from './components/StudentList';
import TrainersPost from './components/TrainersPost';
import StudentsPost from './components/StudentsPost';


function App() {
  return (
    <BrowserRouter>
    <div>
      <Routes>
        <Route path="/" element= {<Main/>} />
        <Route path="/subjects-post" element= {<SubjectsPost/>} />
        <Route path="/students-post" element= {<StudentsPost/>} />
        <Route path="/trainers-post" element= {<TrainersPost/>} />
        <Route path="/trainers-list" element= {<TrainersList/>} />
        <Route path="/subjects-list" element= {<SubjectsList/>} />
        <Route path="/students-list" element= {<StudentList/>} />
        <Route path="/register" element= {<Register/>} />
        <Route path="/login" element= {<Login/>} />
        <Route path="/role_redirect" element= {<Role_Redirect/>} />
        
      </Routes>
    </div>
    </BrowserRouter>
  );  
}

export default App;
