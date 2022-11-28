import { validations } from "../utilities/validations";
import { useState } from "react";
import { Link } from "react-router-dom";

const SignIn = () => {
  const [touched, setTouched] = useState("");

  const handleBlur = (string) => {
    setTouched(string);
  };

  const [errors, setErrors] = useState({});

  const [values, setValues] = useState({
    email: "",
    dni: "",
    birthdate: "",
    name: "",
    lastname: "",
    password: "",
    confirmPassword: "",

  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setValues({
      ...values,
      [name]: value,
    });
    setErrors(
      validations({
        ...values,
        [name]: value,
      })
    );
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert("datos enviados");
    console.log(values);
  };

  return (
    <div>
      <div className="bg-lightGreen rounded-b-[50px] w-[360px] h-[151px] mx-auto text-3xl text-center">
        <p>logo</p>
      </div>

      <p className="w-[312px] mx-auto mt-[50px] mb-[20px] text-darkGrey">
        Para registrarte, ingresa los siguientes datos por favor
      </p>

      <form
        onSubmit={handleSubmit}
        className=" w-[312px] mx-auto flex flex-col gap-y-[20px]"
      >
        <input
          onBlur={() => handleBlur("email")}
          value={values.email}
          name="email"
          id="email"
          type="email"
          placeholder="Ingresar e-mail"
          className="inputForm w-full"
          onChange={handleInputChange}
        />
        {errors.email && touched == "email" && <p>{errors.email}</p>}
        <input
          value={values.dni}
          name="dni"
          id="dni"
          type="text"
          placeholder="DNI / CUIT"
          className="inputForm w-full"
          onChange={handleInputChange}
        />
        <input
          value={values.birthdate}
          name="birthdate"
          id="birthdate"
          type="date"
          placeholder="Fecha de nacimiento"
          className="inputForm w-full"
          onChange={handleInputChange}
        />

        <span className="flex w-full gap-x-[10px]">
          <span className="flex flex-col">
            <input
              onBlur={() => handleBlur("name")}
              value={values.name}
              name="name"
              id="name"
              type="text"
              placeholder="Nombre"
              className="inputForm w-[150px]"
              onChange={handleInputChange}
            />
            {errors.name && touched == "name" && <p>{errors.name}</p>}
          </span>
          <input
            value={values.lastname}
            name="lastname"
            id="lastname"
            type="text"
            placeholder="Apellido"
            className="inputForm w-[150px]"
            onChange={handleInputChange}
          />
        </span>

        <input
          onBlur={() => handleBlur("password")}
          value={values.password}
          name="password"
          id="password"
          type="password"
          placeholder="Ingresar contraseña"
          className="inputForm w-full"
          onChange={handleInputChange}
        />
        {errors.password && touched == "password" && <p>{errors.password}</p>}
        <input
          value={values.confirmPassword}
          name="confirmPassword"
          id="confirmPassword"
          type="password"
          placeholder="Confirmar contraseña"
          className="inputForm w-full"
          onChange={handleInputChange}
        />
        {errors.confirmPassword && touched == "confirmPassword" && (
          <p>{errors.confirmPassword}</p>
        )}

        <button
          type="submit"
          className="w-full h-[40px] bg-lightGreen text-white font-semibold rounded-[20px] mt-[77px] shadow-lg"
        >
          Confirmar
        </button>
      </form>

      <Link
        to="/"
        className="flex mt-[20px] mb-[50px] text-lightGreen justify-center"
      >
        Volver
      </Link>
    </div>
  );
};

export default SignIn;
