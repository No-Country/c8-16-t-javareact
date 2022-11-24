import { Link } from "react-router-dom";

const Welcome = () => {
  return (
    <div className="container  flex flex-col justify-center items-center ">
      <div className=" w-40 h-40 text-4xl bg-Grayexe rounded-full flex justify-center  items-center   mt-9 ">
        {" "}
        Logo{" "}
      </div>

      <div className="flex gap-5 flex-col mt-28 font-Lato">
        {" "}
        <Link to="auth/Login">
          <div class="  border-none   text-black rounded- px-5 py-2 w-72 flex items-center justify-center">
            {" "}
            Ingresar
          </div>
        </Link>
        <Link to="auth/Signin">
          <div class="border-solid border-2 border-black  bg-white text-black rounded-2xl px-5 py-2 w-72 flex items-center justify-center">
            {" "}
            Abrir cuenta gratis
          </div>
        </Link>
      </div>
    </div>
  );
};

export default Welcome;
