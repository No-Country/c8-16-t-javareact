import React from "react";
import CashInfo from './CashInfo';

function ContainerTransactions() {
  const fakeDataBilletera = [
    {
      currency: 'ARS',
      name: 'Pesos argentinos',
      amount: '5.000 ARS'
    },
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
    {
      currency: 'ARS',
      name: 'Pesos argentinos',
      amount: '5.000 ARS'
    },
    {
      currency: 'ARS',
      name: 'Pesos argentinos',
      amount: '5.000 ARS'
    },
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
    }
  ]
  return (
    <div className="w-ful px-10 py-4">
      {fakeDataBilletera.map((value, index) => (
            <CashInfo key={value+index} data={value}/>
      ))}
    </div>
  );
}

export default ContainerTransactions;
