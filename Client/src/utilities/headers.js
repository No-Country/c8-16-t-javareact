const headers = (token) =>{
  let headers = {
    headers: {
      Authorization : `Bearer ${token}`
    }
  }
  return headers;
}

export default headers;