import { Route, Routes } from "react-router-dom";
import Onboarding from "../pages/Onboarding";
import VerifyEmail from "../pages/VerifyEmail";
import Dashboard from "../pages/Dashboard";
import Transactions from "../pages/Transactions";
import Mercado from './../pages/Mercado';
import Profile from './../pages/Profile';

const AplicationRouter = () => {
  return (
    <>
      <Routes>
        <Route path="/onboarding" element={<Onboarding />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/verify-email" element={<VerifyEmail />} />
        <Route path="/profilebar" element={'hey'} />
        <Route path="/mercado" element={<Mercado />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/transactions" element={<Transactions />} />
        {/* <Route path='/not_found' element={'Not Found'}/>
        <Route path={'/*'} element={<Navigate to='/not_found' replace />}/> */}
      </Routes>
    </>
  );
};

export default AplicationRouter;
