import { Navigate } from 'react-router-dom';

const PrivateRoutes = ({ isLogged, isVerified, children }) => {
  return (
    <>
      {
        // !(isLogged && isVerified) && <Navigate to='/app/verificateemail' />
          // ? 
          // <Navigate to='/app/home' /> 
          // : 
          // <Navigate to='/app/verificateemail' />
      }
      {
        isLogged ? children : <Navigate to='/auth/login' />
      }
      {
        // !isVerified && <Navigate to='/app/verify-email' />
      }
    </>
  )
}

export default PrivateRoutes
