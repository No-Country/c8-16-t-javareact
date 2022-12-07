import logo from '/logo.svg';

const Layout = ({ children }) => {
  return (
    <div className="min-h-screen grid grid-rows-[151px] md:grid-cols-2 md:grid-rows-1">
      <div className="bg-lightGreen rounded-b-[50px] w-full h-[151px] mx-auto flex md:h-full md:rounded-r-[50px] md:rounded-bl-none">
          <img className="mx-auto w-28 md:w-36" src={logo} alt="logo" />
      </div>
      { children }
    </div>
  )
}

export default Layout
