import React, { useState } from 'react'
import { useEffect } from 'react'
import userServices from '../services/users';

const Users = () => {
    const [ users, setUsers ] = useState([]);

    useEffect(() => {
        userServices
          .getAll()
          .then(initUsers => {        
            setUsers(initUsers)
          })
    }, [])
    return (
        <div>{users}</div>
    )
}

export default Users
