import { Routes, Route, Navigate } from "react-router-dom";
import VerifyPage from "../pages/VerifyPage";
import LogIn from "./../pages/LogIn";
import SignIn from "./../pages/SignIn";

const AuthRouter = () => {
  return (
    <>
      <Routes>
        <Route path="/login" element={<LogIn />} />
        <Route path="/signin" element={<SignIn />} />
        {/* <Route path='/reset' element={<ResetPassword />} /> */}
      </Routes>
    </>
  );
};

export default AuthRouter;
