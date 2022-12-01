import logo from '/greenLogo.svg'
import { BiUserCircle } from "react-icons/bi";
import { Link } from 'react-router-dom';

const ProfileBar = () => {
    return (
      <div className="containerProfileBar flex w-[360px] h-[51px] mx-auto bg-white shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)] items-center justify-between">
        <span className='w-11/12 flex items-center justify-between mx-auto'>
          <img src={logo} alt="logo" className='w-10'/>
          <Link to='app/profile' className='text-3xl text-lightGreen'><BiUserCircle/></Link>
        </span>
      </div>
    )
  }
  
  export default ProfileBar