import { Navigate } from 'react-router-dom';

const PublicRoutes = ({ children, isLogged, isVerified }) => {

  return !isLogged ? children : (isLogged && isVerified) ? <Navigate to='/app/dashboard' /> : <Navigate to='/app/verify-email' />;
}

export default PublicRoutes
