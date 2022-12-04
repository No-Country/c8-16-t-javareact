import create from 'zustand';
import axios from "axios";
import { devtools } from "zustand/middleware";

const useUserStore = create(
  devtools((set) => ({
    user: {hey: 'jo'},
  }))
);

export default useUserStore;
