import Container from "../components/Container";
import Loyout from "../components/Layout";
import useUserStore from './../zustand/useUserStore';
// import firebase from '../../firebase'

const VerifyEmail = () => {
  const { verifyEmail } = useUserStore((state) => state)
  
  const handleClick = () => {
    let email = 'quispealaya73@gmail.com';
  }

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
            <input
              className="w-10 h-10 shadow-md text-center font-bold"
              type="number"
              name="one"
            />
            <input
              className="w-10 h-10 shadow-md text-center font-bold"
              type="number"
              name="two"
            />
            <input
              className="w-10 h-10 shadow-md text-center font-bold"
              type="number"
              name="three"
            />
            <input
              className="w-10 h-10 shadow-md text-center font-bold"
              type="number"
              name="four"
            />
            <input
              className="w-10 h-10 shadow-md text-center font-bold"
              type="number"
              name="five"
            />
            <input
              className="w-10 h-10 shadow-md text-center font-bold"
              type="number"
              name="six"
            />
          </div>
          {/* <a href="https://mail.google.com/mail" target='_blank'> */}
            <button
              className="w-full h-[40px] mt-[50px] mb-[50px] bg-lightGreen text-white font-semibold rounded-[20px]  shadow-lg"
              onClick={() => verifyEmail()}
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
