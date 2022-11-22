import { useState } from "react"

const signIn = () => {

  const [values, setValues] = useState({
    email:"",
    dni:"",
    birthdate:"",
    name:"",
    lastname:""
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
    alert('datos enviados')
    console.log(values);
  }

  return (
    <div className="h-[682px]">
      <div className="bg-lightGreen rounded-b-[50px] w-[360px] h-[151px] mx-auto text-3xl text-center">
        <p>logo</p>
      </div>

      <form onSubmit={handleSubmit} className=" w-[312px] mx-auto mt-[114px] flex flex-col gap-y-7">
        <input value={values.email} name='email' id="email" type="email" placeholder="Ingresar e-mail" className="inputForm w-full" onChange={handleInputChange}/>
        <input value={values.dni} name='dni' id="dni" type="text" placeholder="DNI / CUIT" className="inputForm w-full" onChange={handleInputChange}/>
        <input value={values.birthdate} name='birthdate' id="birthdate" type="date" placeholder="Fecha de nacimiento" className="inputForm w-full" onChange={handleInputChange}/>

        <span className="flex w-full gap-x-[10px]">
          <input value={values.name} name="name" id="name" type="text" placeholder="Nombre" className="inputForm w-[150px]" onChange={handleInputChange}/>
          <input value={values.lastname} name="lastname" id="lastname" type="text" placeholder="Apellido" className="inputForm w-[150px]" onChange={handleInputChange}/>
        </span>

        <button type="submit" className="w-full h-[40px] bg-gray-500 text-white font-semibold rounded-[20px]">Confirmar</button>

      </form>
    </div>
  )
}

export default signIn