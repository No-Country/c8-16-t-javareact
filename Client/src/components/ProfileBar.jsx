import logo from '/greenLogo.svg'
import { BiUserCircle } from "react-icons/bi";
import { Link } from 'react-router-dom';
import { useState } from 'react';
import useUserStore from './../zustand/useUserStore';

const ProfileBar = () => {
  const [isActive, setIsActive] = useState(false);
  const { logout } = useUserStore((state) => state)

  const handleLogout = () => {
    logout();
  };

    return (
      <div className="containerProfileBar flex w-full h-[51px] mx-auto bg-white shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)] items-center justify-between fixed top-0">
        <span className='w-11/12 flex items-center justify-between mx-auto'>
          <img src={logo} alt="logo" className='w-10 md:w-12'/>
          <span className='hidden sm:flex w-3/5 lg:w-5/12 text-center justify-around text-lightGreen'>
            <Link to='/app/dashboard'>Inicio</Link>
            <Link to='/app/transactions'>Transacciones</Link>
            <Link to={'/app/mercado'}>Mercado</Link>
          </span>
          {isActive &&
            <div 
              className='w-screen h-screen top-0 left-0 absolute' 
              onClick={() => setIsActive(false)}
            />
          }
          <div className='relative font-Poppins'>
            <BiUserCircle 
              className='text-3xl text-lightGreen cursor-pointer'
              onClick={() => setIsActive(!isActive)}
            />
            {isActive &&
              <div className='absolute bottom-[-230%] w-[140px] right-0 bg-white text-lightGreen border-2 rounded-lg overflow-hidden' >
                <div 
                  className='px-3 py-1 hover:bg-lightGreen hover:text-white cursor-pointer focus:bg-red-500' 
                  onClick={handleLogout}
                >
                  Cerrar Sesión
                </div>
                <Link to='/app/profile'>
                  <div 
                    className='px-3 py-1 hover:bg-lightGreen hover:text-white cursor-pointer' 
                  >
                    Perfil
                  </div>
                </Link>
              </div>
            }
          </div>
        </span>
      </div>
    )
  }
  
  export default ProfileBar

