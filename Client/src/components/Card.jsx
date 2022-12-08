
const Card = ({ title, ver, children }) => {
  return (
    <div className="shadow-[0px_3.5px_4px_1px_rgba(0,0,0,0.25)] rounded-[10px] min-h-[218px] sm:w-[312px] p-4">
      <div className="flex justify-between text-lightViolet text-[14px] font-Poppins">
        <p>{title}</p>
        {ver && <p className="font-semibold">Ver todos</p>}
      </div>
      { children }
    </div>
  )
}

export default Card
