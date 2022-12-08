import create from "zustand";
import axios from "axios";
import { devtools } from "zustand/middleware";

const useUserStore = create(
  devtools((set) => ({
    //initial state
    user: { hey: "jo" },
    isLogged: false,
    verified: false,
    error: '',
    loading: false,

    //actions
    signin: async (body) => {
      try {
        set({ loading: true})
        const { data } = await axios.post('https://flux-app.up.railway.app/auth/register', body);
        localStorage.setItem("token", JSON.stringify(data.jwt));
        sessionStorage.setItem("otp", JSON.stringify(data.otp));
        const user = {
          id: data.id,
          email: data.email,
          dni: data.dni,
          photo: data.photo,
          firstName: data.firstName,
          lastName: data.lastName,
          birthDate: data.birthDate,
          verify: false,
        };
        localStorage.setItem("auth", JSON.stringify(user));
        set({ loading: false})
        set({ isLogged: true})
      } catch (error) {
        console.log(error);
      }
    },
    verifyEmail: async (email, headers) => {
      try {
        const { data } = await axios.get(
          `https://flux-app.up.railway.app/auth/verify?email=${email}`,
          headers
        );
        const user = {
          id: data.id,
          email: data.email,
          dni: data.dni,
          photo: data.photo,
          firstName: data.firstName,
          lastName: data.lastName,
          birthDate: data.birthDate,
          verify: data.verify,
        };
        localStorage.setItem("auth", JSON.stringify(user));
        localStorage.setItem("showOn", JSON.stringify(true));
        set({ verified: true });
      } catch (error) {
        console.log(error);
      }
    },
    login: async (body) => {
      try {
        const { data } = await axios.post(
          "https://flux-app.up.railway.app/auth/login",
          body
        );
        const user = {
          id: data.id,
          email: data.email,
          dni: data.dni,
          photo: data.photo,
          firstName: data.firstName,
          lastName: data.lastName,
          birthDate: data.birthDate,
          verify: data.verify,
        };
        localStorage.setItem("auth", JSON.stringify(user));
        localStorage.setItem("token", JSON.stringify(data.jwt));
      } catch (error) {
        set({ error: "Email o contraseña incorrectos" });
      }
    },
    logout: () => {
      localStorage.removeItem('token');
      localStorage.removeItem('auth');
      set({ isLogged: false})
      set({ verified: false})
    }
  }))
);

export default useUserStore;
