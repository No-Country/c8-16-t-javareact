import logo from "/greenLogo.svg";
import { BiUserCircle } from "react-icons/bi";
import { Link } from "react-router-dom";

const ProfileBar = () => {
  return (
    <div className="containerProfileBar flex w-full h-[51px] mx-auto bg-white shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)] items-center justify-between ">
      <span className="w-11/12 flex items-center justify-between mx-auto">
        <img src={logo} alt="logo" className="w-10 md:w-12" />
        <span className="hidden sm:flex w-3/5 lg:w-5/12 text-center justify-around text-lightGreen">
          <Link to={"/dashboard"}>Inicio</Link>
          <Link to={"/investments"}>Mercado</Link>
          <Link to={"/transactions"}>Transacciones</Link>
        </span>
        <Link to="app/profile" className="text-3xl text-lightGreen">
          <BiUserCircle />
        </Link>
      </span>
    </div>
  );
};

export default ProfileBar;
