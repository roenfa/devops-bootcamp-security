import { Outlet } from 'react-router-dom';
import { useIsAuthenticated } from 'react-auth-kit';
import { Navigate } from 'react-router-dom';

// ==============================|| MINIMAL LAYOUT ||============================== //

const MinimalLayout = () => {
    const isAuthenticated = useIsAuthenticated();

    if (isAuthenticated()) {
        return <Navigate to="/" />;
    }

    return <Outlet />;
};

export default MinimalLayout;
