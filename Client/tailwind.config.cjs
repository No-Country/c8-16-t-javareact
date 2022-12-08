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
        lightViolet: "#8989C7"
      },
      fontFamily: {
        Raleway: "'Raleway', sans-serif;",
        Poppins: "'Poppins', sans-serif;",
      },
    },
  },
  plugins: [],
};
