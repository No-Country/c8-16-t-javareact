import { Link } from "react-router-dom";
import useUserStore from "../zustand/useUserStore";

const Welcome = () => {

  const { user } = useUserStore((state) => state);

  console.log(user)
  return (
    <div className="w-full h-screen flex flex-col justify-start items-center  bg-gradient-to-t from-lightGreen to-darkGreen">
      <div className=" w-40 h-40 text-4xl  rounded-full flex justify-center items-center mt-[15vh] ">
        <img src="/Group.svg" alt="logo" />
      </div>

      <div className="container w-[85%] flex gap-5 flex-col mt-28 font-Raleway font-semibold  text-base sm:w-[312px]">
        {" "}
        <Link to="auth/login">
          <div className="border-none shadow-xl bg-white   text-darkGrey rounded-[80px] px-5 py-2 flex items-center justify-center">
            {" "}
            Ingresar
          </div>
        </Link>
        <Link to="auth/signin">
          <div class="border-solid border-[2px]  border-white  text-white rounded-[80px] px-5 py-2 flex items-center justify-center">
            {" "}
            Abrir cuenta gratis
          </div>
        </Link>
      </div>
    </div>
  );
};

export default Welcome;
