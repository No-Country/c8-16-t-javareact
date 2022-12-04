import create from 'zustand';
import axios from "axios";
import { devtools } from "zustand/middleware";

const useUserStore = create(
  devtools((set) => ({
    //initial state
    user: {hey: 'jo'},
    isLogged: false,
    verified: false,

    //actions
    signin: async() =>  {
      try {
        localStorage.setItem("token", JSON.stringify('sdfewfefwfwefefwef'))
        set({ isLogged: true})
      } catch (error) {
        console.log(error);
      }
    },
    verifyEmail: () => {
      try {
        const user = {
          verified: true
        }
        localStorage.setItem("auth", JSON.stringify(user))
        localStorage.setItem("showOn", JSON.stringify(true))
        set({ verified: true})
      } catch (error) {
        console.log(error)
      }
    }
  }))
);

export default useUserStore;
