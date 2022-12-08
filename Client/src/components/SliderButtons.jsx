import React from "react";
import { Link } from "react-router-dom";

function SliderButtons() {
  return (
    <div className="flex gap-2 items-center  px-2 border-gray-400 font-Poppins text-sm ">
      <Link>
        <div className="rounded-3xl bg-lightGreen text-white border-[1.5px] border-lightGreen flex px-2 py-1 items-center justify-center">
          Todas
        </div>
      </Link>
      <Link>
        <div className="rounded-3xl border-lightGreen text-lightGreen border-[1.5px] border-lightGreen flex px-2 py-1 items-center justify-center">
          Transferencias
        </div>
      </Link>
      <Link>
        <div className="rounded-3xl border-lightGreen text-lightGreen border-[1.5px] border-lightGreen flex px-2 py-1 items-center justify-center">
          Depositos
        </div>
      </Link>
      <Link>
        <div className="rounded-3xl border-lightGreen text-lightGreen border-[1.5px] border-lightGreen flex px-2 py-1 items-center justify-center">
          Cashback
        </div>
      </Link>
    </div>
  );
}

export default SliderButtons;
