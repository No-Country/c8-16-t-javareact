import { useState } from "react"

import image from "/Group46.png"
import image2 from "/Group49.png"

const Onboarding = () => {
    const [estado, setEstado] = useState(0)
    
    
   
    
    const data = [{
        id:1,
        img: image,
        titulo:"¡Podés tener una cuenta en dólares!",
        parrafo:"USDT es un token de Ethereum sujeto al valor de un dólar estadounidense. El emisor de Tether afirma que cada USDT en circulación está respaldado por reservas bancarias y préstamos por un valor idéntico o superior."}
        ,{
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
        <div className="w-[360px] mx-auto text-3xl text-center">
        <div>
            <div className="bg-lightGreen rounded-b-[50px] w-[360px] h-[151px]" >
        <p>LOGO</p>
        </div>
            <img src={data[estado].img} alt="imagen" />
            <p >{data[estado].titulo}</p>            
            <p>{data[estado].parrafo}</p>
            <button></button>
            <button></button>
            <button onClick={handledClick}>SIGUIENTE</button>
        </div>
        </div>
    )
}

export default Onboarding