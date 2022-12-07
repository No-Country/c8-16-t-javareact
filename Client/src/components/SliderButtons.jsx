import React from "react";
import { Link } from "react-router-dom";

function SliderButtons() {
  return (
    <div className="flex justify-between items-center  px-2 py-6 border-b border-gray-400  border-">
      <Link>
        <div className="font-Poppins text-sm   rounded-3xl bg-lightGreen text-white font-normal flex text-center px-2 py-1 items-center justify-center ">
          Todas
        </div>
      </Link>
      <Link>
        <div className="font-Poppins text-sm  rounded-3xl border-lightGreen border-solid bg-white text-lightGreen font-normal flex text-center  px-2 py-1 items-center justify-center ">
          Transferencias
        </div>
      </Link>
      <Link>
        <div className="font-Poppins text-sm  rounded-3xl border-lightGreen border-solid bg-white text-lightGreen font-normal flex text-center px-2 py-1 items-center justify-center ">
          Depositos
        </div>
      </Link>
      <Link>
        <div className="font-Poppins text-sm  rounded-3xl border-lightGreen border-solid bg-white text-lightGreen  font-normal flex text-center px-2 py-1 items-center justify-center ">
          Cashback
        </div>
      </Link>
    </div>
  );
}

export default SliderButtons;
