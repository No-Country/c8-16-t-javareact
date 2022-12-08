import Container from "../components/Container";
import Loyout from "../components/Layout";
import useUserStore from './../zustand/useUserStore';
// import firebase from '../../firebase'
import { useState, useRef, useEffect } from 'react';
import useLocalStorage from './../hooks/useLocalStorage';
import headers from './../utilities/headers';

let currentOTPIndex = 0;

const VerifyEmail = () => {
  const { verifyEmail } = useUserStore((state) => state);
  const { token, authUser } = useLocalStorage();
  const [otp, setOtp] = useState(new Array(6).fill(""));
  const [activeOTPIndex, setActiveOTPIndex] = useState(0);

  const inputRef = useRef();

  const handleInputChange = ({ target }) => {
    const { value } = target;
    const newOTP = [...otp];
    newOTP[currentOTPIndex] = value.substring(value.length - 1);

    if(!value) setActiveOTPIndex(currentOTPIndex - 1);
    else setActiveOTPIndex(currentOTPIndex + 1);
    setOtp(newOTP)
  };

  const handleOnKeyDown = (e, index) => {
    currentOTPIndex = index;
    if (e.key === 'Backspace') setActiveOTPIndex(currentOTPIndex - 1)
  }

  const handleVerifyEmail = () => {
    let emailOTPJSON = sessionStorage.getItem("otp");
    const emailOTP = JSON.parse(emailOTPJSON)
    if(emailOTP === otp.join('')) {
      const header = headers(token)
      verifyEmail(authUser.email, header)
    } else {
      alert('Codigo Incorrecto')
    }
    console.log(emailOTP)
  }

  useEffect(() => {
    inputRef.current?.focus();
  }, [activeOTPIndex]);
   console.log(otp.join(''), authUser.email, token);

  return (
    <Loyout>
      <Container>
        <p className="mt-[50px] mb-[25px] text-darkGrey">
          Enviamos un codigo de confirmacion a tu mail. Ingresalo acá para
          continuar.
        </p>

        <div className="h-full flex flex-col justify-between md:h-auto">
          <div className="flex gap-4  mt-5">
            {" "}
            {otp.map((_, index) => (
                <input
                  ref={index === activeOTPIndex ? inputRef : null}
                  className="w-10 h-10 shadow-md text-center font-bold"
                  type="number"
                  onChange={handleInputChange}
                  onKeyDown={(e) => handleOnKeyDown(e, index)}
                  value={otp[index]}
                />
            ))}
          </div>
          {/* <a href="https://mail.google.com/mail" target='_blank'> */}
            <button
              className="w-full h-[40px] mt-[50px] mb-[50px] bg-lightGreen text-white font-semibold rounded-[20px]  shadow-lg"
              onClick={handleVerifyEmail}
            >
              Verificar 
            </button>
          {/* </a> */}
        </div>
      </Container>
    </Loyout>
  );
}

export default VerifyEmail;
