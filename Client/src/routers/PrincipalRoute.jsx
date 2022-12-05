import AplicationRouter from "./AplicationRouter";
import PrivateRoutes from "./PrivateRoutes";
import AuthRouter from "./AuthRouter";
import PublicRoutes from "./PublicRoutes";
import { Routes, Route, Navigate, useNavigate, useLocation } from "react-router-dom";
import Welcome from "../pages/Welcome";
import { useEffect, useState } from "react";
import useLocalStorage from './../hooks/useLocalStorage';
import useUserStore from "../zustand/useUserStore";

const PrincipalRoute = () => {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const [ token, showOn, authUser ] = useLocalStorage();
  const isLogged = token;
  const isVerified= authUser.verified;
  const state = useUserStore((state) => state);
 
  useEffect(() => {
    if (isLogged && !isVerified) {
      navigate("/app/verify-email");
    }
    if(isLogged && isVerified && pathname === '/app/verify-email' && !showOn) {
      navigate("/app/dashboard")
    }
    if(pathname === '/app/verify-email' && showOn) {
      navigate("/app/onboarding")
    } 
  }, [authUser.verified]);

  console.log(token, showOn, isVerified, authUser.verified)
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
