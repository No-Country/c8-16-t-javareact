
import { useState } from "react"

const LogIn = () => {
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
console.log(values);

const handleSubmit = () => {
  alert("datos enviados")
}


  return (
    <div >
      <div className="bg-lightGreen rounded-b-[50px] w-[360px] h-[151px] mx-auto text-3xl text-center" >
        <p>LOGO</p>
        </div>
        <form onSubmit={handleSubmit} className=" my-4 flex flex-col items-center">
          <input value={values.email} name="email" id="email" type="email" placeholder="Ingresar e-mail" onChange={handleInputChange} className="inputForm my-2 border-2 border-gray-600 " />
          <input value={values.password} name="password" id="password" type="password" placeholder="Ingrese su contraseña" onChange={handleInputChange} className="inputForm my-2 border-2 border-gray-600" />
          <button className="flex flex-col bg-green-300 hover:bg-green-500 font-bold my-4 py-2 px-4 rounded-full">Enviar</button>

        </form>
      
      
      
      
    </div>
  )
}

export default LogIn
/** comentario */