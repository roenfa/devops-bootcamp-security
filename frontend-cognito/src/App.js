import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import React from 'react';
import Home from './components/Home'
import Subjects from './components/Subjects';
import SignUp from './components/SignUp';
import SignIn from './components/SignIn';
import HomeSignedIn from './components/HomeSignedIn';
import SubjectsPost from './components/SubjectsPost';
import TrainersList from './components/TrainersList';

function App() {
  return (
    <BrowserRouter>
    <div>
      <Routes>
        <Route path="/" element= {<Home/>} />
        <Route path="/subjects" element= {<Subjects/>} />
        <Route path="/subjects-post" element= {<SubjectsPost/>} />
        <Route path="/trainers-list" element= {<TrainersList/>} />
        <Route path="/signup" element= {<SignUp/>} />
        <Route path="/signin" element= {<SignIn/>} />
        <Route path="/home-signed-in" element= {<HomeSignedIn/>} />
      </Routes>
    </div>
    </BrowserRouter>
  );  
}

export default App;
