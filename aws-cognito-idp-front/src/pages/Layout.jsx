import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../components/authentication/AuthProvider";
import Header from '../components/header/Header';

const Layout = () => {
  const { user, logout } = useAuth();

  if (user) {
    return <Navigate to="/home" />;
  }

  return (
    <>
      <Header user={user} logout={logout} />
      <div style={{ height: 'calc(100vh - 50px)' }}>
        <Outlet />
      </div>
    </>
  )
}

export default Layout