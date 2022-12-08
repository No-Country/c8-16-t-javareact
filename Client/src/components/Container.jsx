
const Container = ({ children }) => {
  return (
    <div className="w-[85vw] sm:w-[312px] mx-auto flex flex-col md:justify-center relative">
      { children }
    </div>
  )
}

export default Container
