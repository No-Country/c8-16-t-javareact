import { useState } from "react";
import reactLogo from "./assets/react.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";

import Welcome from "./pages/Welcome";
import SignIn from "./pages/SignIn";
import LogIn from "./pages/LogIn";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Welcome/>} />
      <Route path="/signin" element={<SignIn/>} />
      <Route path="/login" element={<LogIn/>} />
    </Routes>
  );
}

export default App;
