import { Route, Routes } from "react-router-dom";
import Onboarding from "../pages/Onboarding";
import VerifyPage from "../pages/VerifyPage";
import ProfileBar from "../pages/ProfileBar";

const AplicationRouter = () => {
  return (
    <>
      <Routes>
        <Route path="/onboarding" element={<Onboarding />} />
        <Route path="/profile" element={"Profile"} />
        <Route path="/verificateemail" element={<VerifyPage />} />
        <Route path="/profilebar" element={<ProfileBar />} />
        {/* <Route path='/not_found' element={'Not Found'}/>
        <Route path={'/*'} element={<Navigate to='/not_found' replace />}/> */}
      </Routes>
    </>
  );
};

export default AplicationRouter;
