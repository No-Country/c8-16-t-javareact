
import SliderButtons from "../components/SliderButtons";
import ContainerTransactions from "../components/ContainerTransactions";
import LayoutNavigation from './../components/LayoutNavigation';

function Transactions() {
  
  return (
    <LayoutNavigation>
      <div className="px-[24px] py-[20px] font-Poppins border-b-2 overflow-hidden ">
        <h4 className="text-base font-bold relative mb-8">
          Transacciones
        </h4>
        <SliderButtons />
      </div>
        <ContainerTransactions />
    </LayoutNavigation>


  );
}

export default Transactions;
