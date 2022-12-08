import LayoutNavigation from "../components/LayoutNavigation"
import { useState, useEffect } from 'react';
import CashInfo from "../components/CashInfo";
import useMercadoStore from '../zustand/useMercadoStore';
import Loader from './../components/Loader';
import { IoIosArrowBack, IoIosArrowForward } from 'react-icons/io'

const Mercado = () => {

  const [active, setActive] = useState(0);
  const text = ['Subieron', 'Bajaron', 'Más compradas'];
  const { cryptos, getCryptos, loading } = useMercadoStore((state) => state);
  const [query, setQuery] = useState('desc');
  const [page, setPage] = useState(1);

  const handleClick = (e, index) => {
    setActive(index)
    if(e === 'Subieron') setQuery('desc');
    if(e === 'Bajaron') setQuery('asc')
  }

  useEffect(() => {
    getCryptos(query, page);
  }, [query, page])


  console.log(cryptos)

  return (
    <LayoutNavigation >
      <div className="px-[24px] py-[20px] font-Poppins border-b-2">
        <h6 className="font-bold">Mercado</h6>
        <p className="my-4">Resumen</p>
        <div className="flex gap-2 text-sm font-Poppins">
          {text.map((e, index) => (
           <button 
            className={`rounded-3xl ${active === index ? 'bg-lightGreen text-white' : 'text-lightGreen'} border-[1.5px] border-lightGreen flex px-2 py-1 items-center justify-center`}
            onClick={() => handleClick(e, index)}
            >
              {e}
            </button>
          ))}
        </div>
      </div>
      {loading 
        ? 
          <Loader />
        :
          <div className="px-[24px] py-[15px]">
            <div className="flex justify-end gap-2">
              <span 
                className={page === 1 ? 'text-lightGrey' : 'text-lightGreen'}
                onClick={() => setPage(page > 1 && page - 1)}
                >
                <IoIosArrowBack size={20} />
              </span>
              <span
                className="text-lightGreen"
                onClick={() => setPage(page + 1)}
              >
                <IoIosArrowForward size={20} />
              </span>
            </div>
            {cryptos.map((crypto) => (
              <CashInfo key={crypto.id} data={crypto} />         
            ))}
          </div>
      }
    </LayoutNavigation>
  )
}

export default Mercado
