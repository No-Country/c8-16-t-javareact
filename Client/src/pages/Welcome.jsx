import { Link } from "react-router-dom";

const Welcome = () => {
  return (
    <div className="container h-screen flex flex-col justify-start items-center  bg-gradient-to-t from-lightGreen to-darkGreen">
      <div className=" w-40 h-40 text-4xl  rounded-full flex justify-center  items-center   mt-32 ">
        {" "}
        <img src="/Group.svg" alt="logo" />
      </div>

      <div className="flex gap-5 flex-col mt-28 font-Raleway font-semibold  text-base">
        {" "}
        <Link to="auth/Login">
          <div class="  border-none  shadow-xl bg-white   text-darkGrey rounded-2xl px-5 py-2 w-72 flex items-center justify-center">
            {" "}
            Ingresar
          </div>
        </Link>
        <Link to="auth/Signin">
          <div class="border-solid border  border-white  text-white rounded-2xl px-5 py-2 w-72 flex items-center justify-center">
            {" "}
            Abrir cuenta gratis
          </div>
        </Link>
      </div>
    </div>
  );
};

export default Welcome;
