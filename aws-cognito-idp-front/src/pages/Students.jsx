import React, { useState } from 'react'
import { useEffect } from 'react'
import StudentDetails from '../components/objectDetails/StudentDetails';
import studentServices from '../services/students';

const Students = () => {
  const [ students, setStudents ] = useState([]);

    useEffect(() => {
      studentServices
          .getAll()
          .then(initStudents => {        
            setStudents(initStudents)      
          })
    }, [])
    return (
        <div>
          <h2>Students</h2>
          <ul>{students.map(s => 
                <StudentDetails key={s.id} student={s} />
            )}
          </ul>
        </div>
    )
}

export default Students
