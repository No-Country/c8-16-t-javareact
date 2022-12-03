import { Route, Routes } from "react-router-dom";
import Onboarding from "../pages/Onboarding";
import MenuBar from "../pages/MenuBar";
import ProfileBar from "../pages/ProfileBar";
import VerifyEmail from '../pages/VerifyEmail';

const AplicationRouter = () => {
  return (
    <>
      <Routes>
        <Route path="/onboarding" element={<Onboarding />} />
        <Route path="/profile" element={"Profile"} />
        <Route path="/menubar" element={<MenuBar/>}/>
        <Route path="/verify-email" element={<VerifyEmail />} />
        <Route path="/profilebar" element={<ProfileBar />} />
        {/* <Route path='/not_found' element={'Not Found'}/>
        <Route path={'/*'} element={<Navigate to='/not_found' replace />}/> */}
      </Routes>
    </>
  );
};

export default AplicationRouter;
