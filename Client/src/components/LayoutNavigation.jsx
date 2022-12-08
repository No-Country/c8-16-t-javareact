import ProfileBar from './ProfileBar';
import MenuBar from './MenuBar';
import { Fragment } from 'react';

const LayoutNavigation = ({ children }) => {
  return (
    <Fragment>
      <ProfileBar />
      <main className='mt-[51px] mb-[60px]'>
        { children }
      </main>
      <MenuBar />
    </Fragment>
  )
}

export default LayoutNavigation;
