import React, { useState } from 'react'
import axios from "axios";

const SubjectsPost = () => {

    const [studentId, setStudentId] = useState("");
    const [subjectName, setSubjectName] = useState("");
    const [value, setValue] = useState("");

    let config = {
         headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('idToken')
            }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
          const response = await axios.post(`/api/students/results`, { studentId, subjectName, value }, config);

        } catch (err) {
          console.error(err);
        }
    
        setStudentId("");
        setSubjectName("");
        setValue("");
    
      };


  return (
    <div className='App'>
    <header className="App-header">
    <div className='container'>
    <h2>Add a Subject</h2>
        <form onSubmit={handleSubmit}>
        <input type="text" value={studentId} onChange={(e) => setStudentId(e.target.value)} placeholder="Id of Student" />
        <input type="text" value={subjectName} onChange={(e) => setSubjectName(e.target.value)} placeholder="Subject Name" />
        <input type="text" value={value} onChange={(e) => setValue(e.target.value)} placeholder="Grade" />

            <button type="submit">Add</button>
         
        </form>
        <a href="/home-signed-in"><button className='logout-btn'>Back</button></a>
    </div>
    </header>
</div>
  )
}

export default SubjectsPost;