import { useState } from "react"

const logIn = () => {
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
      <div className=" flex flex-col items-center" >
        LOGO
        </div>
        <form onSubmit={handleSubmit} className="flex flex-col items-center bg-blue-500">
          <input value={values.email} name="email" id="email" type="email" placeholder="Ingresar e-mail" onChange={handleInputChange} />
          <input value={values.password} name="password" id="password" type="password" placeholder="Ingrese su contraseña" onChange={handleInputChange} />
          <button className="flex flex-col bg-blue-300 hover:bg-blue-500 font-bold py-2 px-4 rounded-full">Enviar</button>

        </form>
      
      
      
      
    </div>
  )
}

export default logIn