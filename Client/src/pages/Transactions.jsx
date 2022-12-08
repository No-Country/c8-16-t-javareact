import React from "react";

import ProfileBar from "./ProfileBar";
import MenuBar from "./MenuBar";
import SliderButtons from "../components/SliderButtons";
import ContainerTransactions from "../components/ContainerTransactions";

function Transactions() {
  return (
    <div className="">
      <ProfileBar />

      <h4 className="text-base mt-5  ml-6 font-Poppins font-bold relative  ">
        Transacciones
      </h4>

      <SliderButtons />

      <ContainerTransactions />

      <MenuBar />
    </div>
  );
}

export default Transactions;
