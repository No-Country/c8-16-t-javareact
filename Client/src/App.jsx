import PrincipalRoute from "./routers/PrincipalRoute";
import useLocalStorage from './hooks/useLocalStorage';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';


const App = () => {
  
  return (
    <PrincipalRoute />
  );
}

export default App;
