import React, { useState } from 'react'
import { useEffect } from 'react'
import TrainerDetails from '../components/objectDetails/TrainerDetails';
import teacherServices from '../services/trainers';

const Trainers = () => {
  const [ trainers, setTeachers ] = useState([]);

    useEffect(() => {
      teacherServices
          .getAll()
          .then(initTeachers => {        
            setTeachers(initTeachers)
          })
    }, [])
    return (
        <div>
          <h2>Trainers</h2>
          <ul>{trainers.map(trainer => 
                <TrainerDetails key={trainer.id} trainer={trainer} />
            )}
          </ul>
        </div>
    )
}

export default Trainers
