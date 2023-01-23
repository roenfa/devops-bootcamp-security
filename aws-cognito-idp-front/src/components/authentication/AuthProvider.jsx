import { createContext, useContext, useMemo } from "react";
import { useNavigate } from "react-router-dom";
import { useLocalStorage } from "./useLocalStorage";
import loginService from '../../services/login'
import studentService from '../../services/students'
import trainerService from '../../services/trainers'
import userService from '../../services/users'

const AuthContext = createContext();


export const AuthProvider = ({ children }) => {

  const [user, setUser] = useLocalStorage({key:"user", value:null});
  const navigate = useNavigate();


  const login = async ({username, password}) => {

    try {
      const response = await loginService.signIn({
          username, password,
      });

      const newUser = {
        username: username,
        token: response.idToken
      }

      navigate("/home", { replace: true });
      setUser({key: "user", value: newUser});
      studentService.setToken(response.idToken)
      trainerService.setToken(response.idToken)
      userService.setToken(response.idToken)
      console.log('user logged')
    } catch (err) { 

      if(err.code === "UserNotConfirmedException"){
        handleUnconfirmedUser(username);
        return;
      }

      console.log(err.message);
    }

  };

  
  const handleUnconfirmedUser = (username) => {
    try {
      navigate('/sign-up', {
        state: { username, action: 'confirm'}
      }) 
    }catch(err) {
      console.log(err.message);
    }
  }
  
  const logout = async () => {

    setUser({key:"user", value:null});
    navigate("/", { replace: true });

  }


  const value = useMemo(
    () => ({
      user,
      login,
      logout
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps 
    [user]
  );


  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;

};

export const useAuth = () => {

  return useContext(AuthContext);

};