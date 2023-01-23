import React from 'react'

const StudentDetails = ({student}) => {
  return (
    <li>
      <p>{student.user.name}</p>
      <p>{student.user.username}</p>
      <p>{student.user.role}</p>
    </li>
  )
}

export default StudentDetails