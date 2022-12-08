import create from 'zustand';
import axios from "axios";
import { devtools } from "zustand/middleware";

const useMercadoStore = create(
  devtools((set) => ({
    //initial state
    cryptos: [],
    loading: false,

    //actions
    getCryptos: async(query, page) => {
      try {
        set({ loading: true});
        const { data } = await axios.get(`https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_${query}&per_page=100&page=${page}&sparkline=false`)
        const crypto = data.map(e => ({
          id: e.id,
          currency: e.symbol,
          name: e.name,
          image: e.image,
          amount: `${e.current_price} USD`
        }))
        set({ cryptos: crypto});
        set({ loading: false});
      } catch (error) {
        
      }
    }
  }))
);

export default useMercadoStore;