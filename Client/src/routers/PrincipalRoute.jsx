import AplicationRouter from "./AplicationRouter";
import PrivateRoutes from "./PrivateRoutes";
import AuthRouter from "./AuthRouter";
import PublicRoutes from "./PublicRoutes";
import { Routes, Route, Navigate, useNavigate } from "react-router-dom";
import Welcome from "../pages/Welcome";
import { useEffect } from "react";

const PrincipalRoute = () => {
  const isLogged = false;
  const isVerified = false;
  const navigate = useNavigate();
 
  useEffect(() => {
    if (isLogged && !isVerified) {
      navigate("/app/verify-email");
    }
  }, []);

  return (
    <Routes>
      <Route path="/" element={<Welcome />} />
      //Public route management
      <Route
        path="/auth/*"
        element={
          <PublicRoutes isLogged={isLogged} isVerified={isVerified}>
            <AuthRouter />
          </PublicRoutes>
        }
      />
      //Private route management
      <Route
        path="/app/*"
        element={
          <PrivateRoutes isLogged={isLogged} isVerified={isVerified}>
            <AplicationRouter />
          </PrivateRoutes>
        }
      />
      <Route path="/not_found" element={"Not Found"} />
      <Route path={"*"} element={<Navigate to="/not_found" replace />} />
    </Routes>
  );
};

export default PrincipalRoute;
