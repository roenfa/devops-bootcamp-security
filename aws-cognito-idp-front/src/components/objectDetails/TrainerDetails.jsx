import React from 'react'

const TrainerDetails = ({trainer}) => {
  return (
    <li>
      <p>{trainer.user.name}</p>
      <p>{trainer.user.username}</p>
      <p>{trainer.user.role}</p>
    </li>
  )
}

export default TrainerDetails
