
import { useState } from "react"
import Container from "../components/Container"
import Layout from "../components/Layout"
import { Link } from 'react-router-dom';
import useUserStore from './../zustand/useUserStore';

const LogIn = () => {
  const { login } = useUserStore((state) => state);
  const [values, setValues] = useState({
    email:"",
    password:""
  })
  const handleInputChange = (e) => {
    const {name, value} = e.target 
    setValues({
      ...values,
      [name] : value
    })
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    login();
  }


  return (
    <Layout>
      <Container>
        <form 
          onSubmit={handleSubmit} 
          className="h-full flex flex-col justify-between md:h-auto mt-[50px]"
        >
          <div className="flex flex-col gap-[18px] ">
            <input 
              value={values.email} 
              name="email" 
              id="email" 
              type="email" 
              placeholder="Ingresar e-mail" 
              onChange={handleInputChange} 
              className="inputForm w-full" 
            />
            <input 
              value={values.password} 
              name="password" id="password" 
              type="password" placeholder="Ingrese su contraseña" 
              onChange={handleInputChange} 
              className="inputForm w-full"
            />
          </div>
          <button
            type="submit"
            className="w-full h-[40px] bg-lightGreen text-white font-semibold rounded-[20px] mt-[40px] shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)]"
          >
            Confirmar
          </button>

        </form>
        <Link
          to="/"
          className="flex mt-[20px] mb-[50px] text-lightGreen justify-center font-semibold"
        >
          Volver
        </Link>
      </Container>
    </Layout>
  )
}

export default LogIn
/** comentario */