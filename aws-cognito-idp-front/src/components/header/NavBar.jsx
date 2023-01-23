import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const headerNav = (user) => 
    !user ? (
        [
            {
                displayName: 'Home',
                path: '/'
            },
            {
                displayName: 'SignIn',
                path: '/sign-in'
            }
        ]
    ) : (
        [
            {
                displayName: 'Home',
                path: '/home'
            },
            {
                displayName: 'Users',
                path: '/home/users'
            },
            {
                displayName: 'Trainers',
                path: '/home/trainers'
            },
            {
                displayName: 'Students',
                path: '/home/students'
            },
            {
                displayName: 'SignOut',
                path: '/'
            }
        ]
    );

const NavBar = (props) => {
    const { user, logout } = props
    const [isNavExpanded, setIsNavExpanded] = useState(false)

    return (
        <>
            <div>
                <div>React-Java-Api</div>
                <div>
                    {headerNav(user).map((value, index) => (
                        <div key={index} className='button-navbar' color='primary'>                        
                            <Link 
                                to={value.path}
                                onClick={() => {
                                    setIsNavExpanded(!isNavExpanded);
                                    if (value.displayName === 'SignOut') return logout();
                                }}
                            >
                                {value.displayName}
                            </Link>                        
                        </div>
                    ))} 
                </div>
            </div>
        </>
    )
}

export default NavBar
