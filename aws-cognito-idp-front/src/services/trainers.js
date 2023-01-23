import axios from 'axios'
const baseUrl = '/api/trainers'

let token = null

const setToken = newToken => {  
  token = `Bearer ${newToken}`
}

const getAll = async () => {
  const config = {    
    headers: { 'Authorization': token }  
  }

  const response = await axios.get(baseUrl, config)
  return response.data
}

const create = async newObject => {
  const config = {    
    headers: { 'Authorization': token }  
  }

  const response = await axios.post(baseUrl, newObject, config)
  return response.data
}

const update = async(id, newObject) => {

  const config = {    
    headers: { 'Authorization': token }  
  }

  const response = await axios.put(`${baseUrl}/${id}`, newObject, config)
  return response.data
}

const del = (id) => {
  const request = axios.delete(`${baseUrl}/${id}`)
  return request.then(response => response.data)
}

const exportedObject = { getAll, create, update, del, setToken }

export default exportedObject;