import { Navigate } from 'react-router-dom';

const PublicRoutes = ({ children, isLogged }) => {

  return isLogged ? <Navigate to='/app/home' /> : children;
}

export default PublicRoutes
