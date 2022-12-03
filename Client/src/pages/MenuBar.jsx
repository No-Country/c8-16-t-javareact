import { Link } from "react-router-dom";
import { MdHomeFilled } from "react-icons/md";
import { BiTrendingUp } from "react-icons/bi";
import { RiMoneyDollarCircleLine } from "react-icons/ri";

const MenuBar = () => {
  return (
    <div className="menuBarContainer w-[360px] h-[60px] bg-lightGreen rounded-t-[5px] flex mx-auto fixed bottom-0">
      <span className="w-10/12 flex justify-between items-center mx-auto">
        <Link to="app/home">
          <MdHomeFilled className="text-white text-[26px]"/>
        </Link>
        <Link to="app/transactions" >
          <BiTrendingUp className="text-white text-[26px]" />
        </Link>
        <Link to="">
          <RiMoneyDollarCircleLine className="text-white text-[26px]" />
        </Link>
      </span>
    </div>
  );
};

export default MenuBar;
