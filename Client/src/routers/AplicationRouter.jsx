import { Route, Routes } from "react-router-dom";
import VerifyPage from "../pages/VerifyPage";

const AplicationRouter = () => {
  return (
    <>
      <Routes>
        <Route path="/home" element={"Home"} />
        <Route path="/profile" element={"Profile"} />
        <Route path="/verificateemail" element={<VerifyPage />} />
        {/* <Route path='/not_found' element={'Not Found'}/>
        <Route path={'/*'} element={<Navigate to='/not_found' replace />}/> */}
      </Routes>
    </>
  );
};

export default AplicationRouter;
