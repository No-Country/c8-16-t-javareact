import { validations } from "../utilities/validations";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Layout from '../components/Layout';
import Container from "../components/Container";
import useUserStore from './../zustand/useUserStore';
import { BsFillEyeFill, BsFillEyeSlashFill } from 'react-icons/bs'
import Loader from "../components/Loader";


const SignIn = () => {
  const { signin, loading } = useUserStore((state) => state)
  const [touched, setTouched] = useState("");
  const [isActive, setIsActive] = useState(false);
  const [isActiveDos, setIsActiveDos] = useState(false);

  const handleBlur = (string) => {
    setTouched(string);
  };

  const [errors, setErrors] = useState({});

  const [values, setValues] = useState({
    email: "",
    dni: "",
    birthDate: "",
    firstName: "",
    lastName: "",
    photo: "",
    password: "",
    confirmpassword: ""
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

  const handleSubmit = async(e) => {
    e.preventDefault();
    const user = {
      email: values.email,
      dni: values.dni,
      birthDate: values.birthDate,
      firstName: values.firstName,
      lastName: values.lastName,
      photo: values.photo,
      password: values.password,
    }
    if (Object.values(errors).length === 0) {
      signin(user);
    } else {
      alert('Llenar los campos faltantes')
    }
  };
  

  return (
    <Layout>
      <Container>
        {loading && 
          <div className="absolute w-full h-full bg-white opacity-80 z-10 flex justify-center items-center">
            <Loader />
          </div>
        }
        <p className="mt-[50px] mb-[25px] text-darkGrey">
          Para registrarte, ingresa los siguientes datos por favor
        </p>

        <form
          onSubmit={handleSubmit}
          className="h-full flex flex-col justify-between md:h-auto"
        >
          <div className="mx-auto flex flex-col gap-[18px] ">
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
                  handleBlur("birthDate");
                }}
                // value={values.birthDate}
                name="birthDate"
                id="birthdate"
                type="date"
                placeholder="Fecha de nacimiento"
                className="inputForm w-full"
                onChange={handleInputChange}
              />
              {errors.birthDate && touched == "birthDate" && (
                <p className="errors">{errors.birthDate}</p>
              )}
            </span>

            <span className="flex w-full gap-x-[10px]">
              <span className="flex flex-col">
                <input
                  onBlur={() => handleBlur("firstName")}
                  value={values.firstName}
                  name="firstName"
                  id="name"
                  type="text"
                  placeholder="Nombre"
                  className="inputForm w-full"
                  onChange={handleInputChange}
                />
                {errors.firstName && touched == "firstName" && (
                  <p className="errors">{errors.firstName}</p>
                )}
              </span>
              <span>
                <input
                  onBlur={() => handleBlur("lastName")}
                  value={values.lastName}
                  name="lastName"
                  id="lastname"
                  type="text"
                  placeholder="Apellido"
                  className="inputForm w-full"
                  onChange={handleInputChange}
                />
                {errors.lastName && touched == "lastName" && (
                  <p className="errors">{errors.lastName}</p>
                )}
              </span>
            </span>

            <span className="relative" id="passwordContainer">
              <input
                onBlur={() => handleBlur("password")}
                value={values.password}
                name="password"
                id="password"
                type={isActive ? 'text' : 'password'} 
                placeholder="Ingresar contrase??a"
                className="inputForm w-full"
                onChange={handleInputChange}
              />
              {errors.password && touched == "password" && (
                <p className="errors">{errors.password}</p>
              )}
              <span onClick={() => setIsActive(!isActive)}>
                {isActive 
                  ?
                  <BsFillEyeSlashFill size={22}  className="absolute top-2 right-3 cursor-pointer text-darkGrey" />
                  :
                  <BsFillEyeFill size={22}  className="absolute top-2 right-3 cursor-pointer text-darkGrey "/>
                }
              </span>
            </span>

            <span className="relative" id="confirmPasswordContainer">
              <input
                onBlur={() => handleBlur("confirmpassword")}
                value={values.confirmpassword}
                name="confirmpassword"
                id="confirmpassword"
                type={isActiveDos ? 'text' : 'password'} 
                placeholder="Confirmar contrase??a"
                className="inputForm w-full"
                onChange={handleInputChange}
              />
              {errors.confirmpassword && touched == "confirmpassword" && (
                <p className="errors">{errors.confirmpassword}</p>
              )}
              <span onClick={() => setIsActiveDos(!isActiveDos)}>
                {isActiveDos 
                  ?
                  <BsFillEyeSlashFill size={22}  className="absolute top-2 right-3 cursor-pointer text-darkGrey" />
                  :
                  <BsFillEyeFill size={22}  className="absolute top-2 right-3 cursor-pointer text-darkGrey "/>
                }
              </span>
            </span>
          </div>
          
          <div>
            <button
              type="submit"
              className="w-full h-[40px] bg-lightGreen text-white font-semibold rounded-[20px] mt-[40px] shadow-[1px_2px_4px_2px_rgba(0,0,0,0.25)]"
            >
              Confirmar
            </button>
            <Link
              to="/"
              className="flex mt-[20px] mb-[50px] text-lightGreen justify-center font-semibold"
            >
              Volver
            </Link>
          </div>
        </form>

      </Container>
    </Layout>
  );
};

export default SignIn;
//