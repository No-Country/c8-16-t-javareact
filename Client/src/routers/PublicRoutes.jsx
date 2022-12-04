import { Navigate } from 'react-router-dom';

const PublicRoutes = ({ children, isLogged, isVerified }) => {

  return isLogged ? <Navigate to='/app/dashboard' /> : children;
}

export default PublicRoutes
