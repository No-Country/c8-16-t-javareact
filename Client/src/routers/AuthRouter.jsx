import { Routes, Route } from 'react-router-dom';
import LogIn from './../pages/LogIn';
import SignIn from './../pages/SignIn';

const AuthRouter = () => {
  return (
    <div>
      <Routes>
        <Route path='/login' element={<LogIn />} />
        <Route path='/signin' element={<SignIn />} />
        {/* <Route path='/reset' element={<ResetPassword />} /> */}
      </Routes>
      <Route path='/not_found' element={'Not Found'}/>
      <Route path={'*'} element={<Navigate to='/not_found' replace />}/>
    </div>
  )
}

export default AuthRouter
