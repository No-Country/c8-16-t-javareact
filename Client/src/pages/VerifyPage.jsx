import React from "react";
import { useState } from "react";

function VerifyPage() {
  return (
    <div className="flex  flex-col items-center">
      <div className="bg-lightGreen rounded-b-[50px] w-[360px] h-[151px] mx-auto text-3xl text-center flex justify-center items-center">
        <img className="w-24 h-16" src="/Group.svg" alt="logo" />
      </div>

      <p className="w-[312px] mx-auto mt-[50px] mb-[20px] text-darkGrey text-center">
        Enviamos un codigo de confirmacion a tu mail. Ingresalo acá para
        continuar.
      </p>

      <form className=" flex flex-col items-center ">
        <div className="flex gap-4  mt-5">
          {" "}
          <input
            className="w-10 h-10 shadow-md text-center font-bold"
            type="number"
            name="one"
          />
          <input
            className="w-10 h-10 shadow-md text-center font-bold"
            type="number"
            name="two"
          />
          <input
            className="w-10 h-10 shadow-md text-center font-bold"
            type="number"
            name="three"
          />
          <input
            className="w-10 h-10 shadow-md text-center font-bold"
            type="number"
            name="four"
          />
          <input
            className="w-10 h-10 shadow-md text-center font-bold"
            type="number"
            name="five"
          />
          <input
            className="w-10 h-10 shadow-md text-center font-bold"
            type="number"
            name="six"
          />
        </div>
        <button
          type="submit"
          className="w-72 h-[40px] mt-56 bg-lightGreen text-white font-semibold rounded-[20px]  shadow-lg"
        >
          Abrir mail
        </button>
      </form>
    </div>
  );
}

export default VerifyPage;
