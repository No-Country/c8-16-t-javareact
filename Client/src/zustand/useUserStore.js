import create from 'zustand';
import axios from "axios";
import { devtools } from "zustand/middleware";

const useUserStore = create(
  devtools((set) => ({
    //initial state
    user: {hey: 'jo'},
    isLogged: false,
    verified: false,
    error: '',

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
    },
    login: async() => {
      try {
        const { data } = await axios.post('https://flux-app.up.railway.app/auth/login');
        localStorage.setItem("auth", JSON.stringify(data))
      } catch (error) {
        set({ error: 'Email o contraseña incorrectos'})
      }
    }
  }))
);

export default useUserStore;
