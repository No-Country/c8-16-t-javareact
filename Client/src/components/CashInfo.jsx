
const CashInfo = ({ data }) => {

  return (
    <div className="flex gap-6 items-center  justify-between mt-4 ml-2">
      <div className="flex gap-6 items-center">
        <img className="w-5 h-5" src={data.image ? data.image : "/Money.svg"} alt="" />

        <div className="flex flex-col gap-0">
          <h4 className="font-Poppins mt-1 text-sm font-normal capitalize">{data.currency}</h4>
          <p className="text-[10px] text-lightViolet font-light -mt-1">{data.name}</p>
        </div>
      </div>
      <h3 className="font-Poppins font-medium text-sm">{data.amount}</h3>
    </div>
  )
}

export default CashInfo
