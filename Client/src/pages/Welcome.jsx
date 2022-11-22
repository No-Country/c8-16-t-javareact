import { Link } from "react-router-dom";

const Welcome = () => {
  return (
    <div className="container  flex flex-col justify-center items-center ">
      <div className=" w-40 h-40 text-4xl bg-Grayexe rounded-full flex justify-center  items-center   top-12 ">
        {" "}
        Logo{" "}
      </div>

      <div className="flex gap-5 flex-col mt-28 font-Lato">
        {" "}
        <Link to="./Login">
          <div className="border-solid border-2 border-black  bg-Grayexe text-black rounded-2xl px-5 py-2 w-72 flex items-center justify-center">
            {" "}
            Ingresar
          </div>
        </Link>
        <Link to="./Signin">
          <div className="border-solid border-2 border-black  bg-white text-black rounded-2xl px-5 py-2 w-72 flex items-center justify-center">
            {" "}
            Abrir cuenta gratis
          </div>
        </Link>
      </div>
    </div>
  );
};

export default Welcome;
