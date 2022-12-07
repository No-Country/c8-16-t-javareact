import { useState } from 'react';
import { useEffect } from 'react';

const useLocalStorage = () => {
  const tokenJSON = localStorage.getItem('token');
  const showOnJSON = localStorage.getItem('showOn');
  const authUserJSON = localStorage.getItem('auth');
  const token = JSON.parse(tokenJSON) || '';
  const showOn = JSON.parse(showOnJSON) || '';
  const authUser = JSON.parse(authUserJSON) || '';

  return { token, showOn, authUser }
}
export default useLocalStorage;