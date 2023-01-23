import { useEffect } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../components/authentication/AuthProvider";
import Header from "../components/header/Header";
import studentService from '../services/students'
import trainerService from '../services/trainers'
import userService from '../services/users'

export const ProtectedLayout = () => {
  const { user, logout } = useAuth();

  useEffect(() => {
    if(user){
      studentService.setToken(user.token)
      trainerService.setToken(user.token)
      userService.setToken(user.token)
    }
  });

  if (!user) {
    return <Navigate to="/" />;
  }

  return (
    <div>
      <Header user={user} logout={logout} />
      <Outlet />
    </div>
  )
};