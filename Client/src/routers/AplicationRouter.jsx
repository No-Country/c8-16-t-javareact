import { Route, Routes } from "react-router-dom"

const AplicationRouter = () => {
  return (
    <>
      <Routes>
        <Route path='/home' element={'Home'} />
        <Route path='/profile' element={'Profile'} />
        <Route path='/verificateemail' element={'verificate your email'} />
        {/* <Route path='/not_found' element={'Not Found'}/>
        <Route path={'/*'} element={<Navigate to='/not_found' replace />}/> */}
      </Routes>
    </>
  )
}

export default AplicationRouter
