import { useState } from "react";
import reactLogo from "./assets/react.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";

import Welcome from "./pages/Welcome";
import SignIn from "./pages/SignIn";
import LogIn from "./pages/LogIn";
import PrincipalRoute from "./routers/PrincipalRoute";

function App() {
  return (
    <PrincipalRoute />
  );
}

export default App;
