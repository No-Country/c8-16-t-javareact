import { validations } from "../utilities/validations";
import { useState } from "react";
import { Link } from "react-router-dom";
import logo from "/logo.svg";

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
    confirmpassword: "",
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
    <div className="w-[360px] mx-auto">
      <div className="bg-lightGreen rounded-b-[50px] w-full h-[151px] mx-auto flex">
        <img className="mx-auto w-28" src={logo} alt="logo" />
      </div>

      <p className="w-[312px] mx-auto mt-[30px] mb-[40px] text-darkGrey">
        Para registrarte, ingresa los siguientes datos por favor
      </p>

      <form
        onSubmit={handleSubmit}
        className=" w-[312px] mx-auto flex flex-col gap-y-[10px]"
      >
        <span id="emailContainer">
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
          {errors.email && touched == "email" && (
            <p className="errors">{errors.email}</p>
          )}
        </span>

        <span id="dniContainer">
          <input
            onBlur={() => handleBlur("dni")}
            value={values.dni}
            name="dni"
            id="dni"
            type="text"
            placeholder="DNI"
            className="inputForm w-full"
            onChange={handleInputChange}
          />
          {errors.dni && touched == "dni" && (
            <p className="errors">{errors.dni}</p>
          )}
        </span>

        <span id="dateContainer">
          <input
            onBlur={() => {
              handleBlur("email");
            }}
            value={values.birthdate}
            name="birthdate"
            id="birthdate"
            type="date"
            placeholder="Fecha de nacimiento"
            className="inputForm w-full"
            onChange={handleInputChange}
          />
          {errors.birthdate && touched == "birthdate" && (
            <p className="errors">{errors.birthdate}</p>
          )}
        </span>

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
            {errors.name && touched == "name" && (
              <p className="errors">{errors.name}</p>
            )}
          </span>
          <span>
            <input
              onBlur={() => handleBlur("lastname")}
              value={values.lastname}
              name="lastname"
              id="lastname"
              type="text"
              placeholder="Apellido"
              className="inputForm w-[150px]"
              onChange={handleInputChange}
            />
            {errors.lastname && touched == "lastname" && (
              <p className="errors">{errors.lastname}</p>
            )}
          </span>
        </span>

        <span id="passwordContainer">
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
          {errors.password && touched == "password" && (
            <p className="errors">{errors.password}</p>
          )}
        </span>

        <span id="confirmPasswordContainer">
          <input
            onBlur={() => handleBlur("confirmpassword")}
            value={values.confirmpassword}
            name="confirmpassword"
            id="confirmpassword"
            type="password"
            placeholder="Confirmar contraseña"
            className="inputForm w-full"
            onChange={handleInputChange}
          />
          {errors.confirmpassword && touched == "confirmpassword" && (
            <p className="errors">{errors.confirmpassword}</p>
          )}
        </span>

        <button
          type="submit"
          className="w-full h-[40px] bg-lightGreen text-white font-semibold rounded-[20px] mt-[27px] shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)]"
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
