import LayoutNavigation from './../components/LayoutNavigation';
import { TbArrowBigTop } from 'react-icons/tb';
import { MdOutlineSendToMobile, MdOutlineKeyboardArrowDown } from 'react-icons/md';
import { BsArrowRepeat } from 'react-icons/bs';
import { IoIosArrowDown } from 'react-icons/io';
import Card from '../components/Card';
import CashInfo from '../components/CashInfo';

const Dashboard = () => {
  const buttons = [
    {
      icon: <TbArrowBigTop size={22}/>,
      text: 'Depositar'
    },
    {
      icon: <MdOutlineSendToMobile size={20} />,
      text: 'Enviar'
    },
    {
      icon: <BsArrowRepeat size={22} />,
      text: 'Cambiar'
    },
  ]

  const fakeDataBilletera = [
    {
      currency: 'ARS',
      name: 'Pesos argentinos',
      amount: '5.000 ARS'
    },
    {
      currency: 'USDT',
      name: 'Theter USDT',
      amount: '2,6554852 USDT'
    },
    {
      currency: 'BTC',
      name: 'Bitcoin',
      amount: '1,6550052 BTC'
    },
  ]
  return (
    <LayoutNavigation>
      <div className='bg-white px-[24px] pb-[40px] rounded-b-[30px] font-Poppins shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)]'>
        <h6 className='font-bold'>Balance</h6>
        <div className='mb-2 flex gap-3 pl-3'>
          <span className='text-[40px] font-bold'>262,898</span>
          <div className='flex gap-2 mt-3 font-semibold '>
            <span>ARS</span>
            <IoIosArrowDown size={20}/>
          </div>
        </div>
        <div className='grid grid-cols-3 gap-6 px-3 text-white'>
          {buttons.map(button => (
            <button className='bg-lightGreen rounded-[5px] grid justify-items-center p-[5px] text-[14px]'>
              {button.icon}
              <span>{button.text}</span>
            </button>
          ))}
        </div>
      </div>
      <div className='flex flex-col gap-6 p-[24px] sm:flex-row sm:justify-center sm:gap-8'>
        <Card title={'Tu billetera'}>
          {fakeDataBilletera.map((value, index) => (
            <CashInfo key={value+index} data={value}/>
          ))}

        </Card>
        <Card title={'Ultimos movimientos'} ver >
          {fakeDataBilletera.map((value, index) => (
            <CashInfo key={value+index} data={value}/>
          ))}
        </Card>
      </div>
    </LayoutNavigation>
  )
}

export default Dashboard