import React from 'react';
import { createBrowserRouter, defer } from "react-router-dom";
import SignIn from "../pages/SignIn";
import { AuthLayout } from '../components/authentication/AuthLayout';
import Layout from '../pages/Layout';
import SignUp from '../pages/SignUp'
import Home from "../pages/Home";
import Users from '../pages/Users';
import Students from '../pages/Students';
import Trainers from '../pages/Trainers';
import { ProtectedLayout } from '../pages/ProtectedLayout';


const getUserData = () => {
    try {
        new Promise((resolve) =>
            setTimeout(() => {
                const user = window.localStorage.getItem("user");
                resolve(user);
            }, 1000));
    } catch(e) {
        console.log(e)
    }
}

const Router = createBrowserRouter([
    {
        path: "/",
        element: <AuthLayout />,
        loader: () => defer({ userPromise: getUserData() }),
        children: [
            {
                path: "/",
                element: <Layout />,
                children: [
                    {
                        path: "/",
                        element: <Home />
                    },
                    {
                        path: "/sign-in",
                        element: <SignIn />,
                    },
                    {
                        path: "/sign-up",
                        element: <SignUp />
                    }
                ]
            },
            {
                path: "/home",
                element: <ProtectedLayout />,
                children: [
                    {
                        path: "/home",
                        element: <Home />
                    },
                    {
                        path: "users",
                        element: <Users />
                    },
                    {
                        path: "students",
                        element: <Students />
                    },
                    {
                        path: "trainers",
                        element: <Trainers />
                    }
                ]
            }
        ]
    }
]);

export default Router