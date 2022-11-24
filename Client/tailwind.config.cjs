/* @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      colors: {
        lightGreen: "#3CAF00",
        darkGreen: "#018B02",
        lightGrey: "#CECECE",
        darkGrey: "#848484",
      },
      fontFamily: {
        Lato: "Lato",
      },
    },
  },
  plugins: [],
};
