import "./App.css";
import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Student from "./pages/Student/Student";
import Trainer from "./pages/Trainer/Trainer";
import Course from "./pages/Trainer/Curso";
import Admin from "./pages/Admin";
import { useState } from "react";
import { Button } from "@mui/material";
import ReadTable from "./pages/Admin/ReadTable";

function App() {
  const [role, setRole] = useState(sessionStorage.getItem("role"));

  const cerrarSession = () => {
    sessionStorage.clear();
    setRole(null);
    window.location.href = "/";
  };

  return (
    <div className="">
      <header className="App-header">
        <Router>
          <Routes>
            <Route path="/" element={<SignIn />} />
            <Route path="/signin" element={<SignIn />} />
            <Route path="/signup" element={<SignUp />} />
              <Route path="/student" element={<Student />} />
              <Route path="/trainer" element={<Trainer />} />),
              <Route path="/course/:course" element={<Course />} />
              <Route path="/admin" element={<Admin />} />
              <Route path="/admin/bootcamps" element={<ReadTable entity="bootcamps" />} />
              <Route path="/admin/courses" element={<ReadTable entity="courses" />} />
              <Route path="/admin/students" element={<ReadTable entity="students" />} />
              <Route path="/admin/trainers" element={<ReadTable entity="trainers" />} />
             {
              role === null && (
                <Route path="*" element={<SignIn />} />
              )
             }
          </Routes>
        </Router>
        <Button onClick={cerrarSession}>Cerrar sesión</Button>
      </header>
    </div>
  );
}

export default App;
