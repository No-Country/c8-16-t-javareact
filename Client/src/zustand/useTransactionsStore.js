import create from "zustand";
import axios from "axios";
import { devtools } from "zustand/middleware";

constUseTransactions = create(
  devtools((set) => ({
    transactions: [],

    createTransactions: async (Headers, body) => {
      try {
        const { data } = await axios.post(
          "https://flux-app.up.railway.app/transactions/1",
          body,
          Headers
        );
      } catch (error) {}
    },
  }))
);
