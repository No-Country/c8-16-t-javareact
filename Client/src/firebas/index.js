import { initializeApp } from "firebase/app";
import { getAuth } from 'firebase/auth';

const config = {
  apiKey: "AIzaSyCtBvSLwfCi6Em-7DHvpWBoYesvAaVj9xw",
  authDomain: "fir-flux.firebaseapp.com",
  projectId: "fir-flux",
  storageBucket: "fir-flux.appspot.com",
  messagingSenderId: "671309240763",
  appId: "1:671309240763:web:dce2161e8a7bdf5da50c9e"
}
export const app = initializeApp(config);

export const auth = getAuth(app);