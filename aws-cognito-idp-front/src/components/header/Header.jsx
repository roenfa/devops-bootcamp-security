import React from 'react'
import NavBar from './NavBar';

const Header = (props) => {
    return (
        <NavBar 
            user={props.user}
            logout={props.logout}
        />
    )
}

export default Header
