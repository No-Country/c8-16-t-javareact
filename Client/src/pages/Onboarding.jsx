import { useState } from "react"

import image from "/onboarding_1.svg"
import image2 from "/Group49.png"
import Layout from './../components/Layout';
import Container from "../components/Container";

const Onboarding = () => {
    const [pagination, setPagination] = useState(0);
    
    const data = [
        {
        id:1,
        img: image,
        titulo:"¡Podés tener una cuenta en dólares!",
        parrafo:"USDT es un token de Ethereum sujeto al valor de un dólar estadounidense. El emisor de Tether afirma que cada USDT en circulación está respaldado por reservas bancarias y préstamos por un valor idéntico o superior."
        },
        {
            id:2,
            img: image2,
            titulo:"Invertí y generá rendimientos diarios",
            parrafo:"Tenemos criptos que podes invertir para generar ganancias de acreditación diaria. Además podés tener un fondo fijo con ganancias diarias en USDT."
        }
    ]
   
   
   const  handledClick = () => {
      if (estado <= 1 ) {
        setEstado(estado + 1) 
      } else {
        alert("alert")
      }
   }
    
    
    
    return(
        <Layout>
            <Container>
                <h4 className="text-lightGreen font-semibold text-xl text-center mt-4">
                    Bienvenid@
                </h4>
                <div className="h-full flex flex-col justify-between md:h-auto">
                    <div className="mt-4">
                        <img className="mx-auto" src={data[pagination].img} alt="imagen" />
                        <p className="text-lightGreen font-semibold mt-4 mb-3">
                            {data[pagination].titulo}
                        </p>            
                        <p className="text-darkGrey" >{data[pagination].parrafo}</p>
                    </div>
                    <div className="flex flex-col items-center mt-6">
                        <div className="flex gap-2">
                            <button className={`w-2 h-2 rounded-full ${pagination === 0 ? 'bg-darkGreen' : 'bg-lightGrey' }`}></button>
                            <button className={`w-2 h-2 rounded-full ${pagination === 1 ? 'bg-darkGreen' : 'bg-lightGrey' }`}></button>
                            <button className={`w-2 h-2 rounded-full ${pagination === 2 ? 'bg-darkGreen' : 'bg-lightGrey' }`}></button>
                        </div>
                        <button 
                            className="flex mt-[30px] mb-[50px] text-lightGreen justify-center font-semibold" 
                            onClick={handledClick}
                        >
                            Saltar
                        </button>
                    </div>
                </div>
            </Container>
        </Layout>
    )
}

export default Onboarding