import axios from 'axios'
const baseUrl = '/api/auth'

const signIn = async credentials => {
    const response = await axios.post(`${baseUrl}/sign-in`, credentials)
    return response.data
}

const signUp = async newObject => {
    const response = await axios.post(`${baseUrl}/sign-up`, newObject)
    return response.data
}

const exportedOject = { signIn, signUp }

export default exportedOject