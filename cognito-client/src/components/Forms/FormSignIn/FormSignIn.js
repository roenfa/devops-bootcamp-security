import React, { useState } from "react";
import { TextField, Button, Container } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import "./FormSignIn.css";
import axios from "axios";

const FormSignin = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(
      `Iniciando sesión con correo electrónico ${email} y contraseña ${password}`
    );
    const user = {
      username: email,
      password: password,
    };
    axios.defaults.headers.post["Content-Type"] = "application/json";
    axios
      .post("http://localhost:8080/api/users/sign-in", user)
      .then((res) => {
        console.log("Respuesta: ", res);
        sessionStorage.setItem("accessToken", res.data.accessToken);
        sessionStorage.setItem("refreshToken", res.data.refreshToken);
        sessionStorage.setItem("idToken", res.data.idToken);
        sessionStorage.setItem("expiresIn", res.data.expiresIn);
        sessionStorage.setItem("tokenType", res.data.tokenType);
        sessionStorage.setItem("username", email);
        console.log("SessionStorage: ", sessionStorage);
        axios.defaults.headers.common["Authorization"] =
          res.data.tokenType + " " + res.data.accessToken;
      //pasar string email tal cual
        //cambiar json a string application/x-www-form-urlencoded
        axios.defaults.headers.post["Content-Type"] = "text/plain";
        axios
          .post("http://localhost:8080/api/v1/user-roles/getUser", email )
          .then((res) => {
            console.log("Respuesta: ", res);
            console.log("Role: ", res.data.role);
            if (res.data.role === "ADMIN_AGALVISB") {
              sessionStorage.setItem("role", "admin");
              navigate("/admin");
            } else if (res.data.role === "STUDENT_AGALVISB") {
              sessionStorage.setItem("role", "student");
              navigate("/student");
            } else if (res.data.role === "TRAINER_AGALVISB") {
              sessionStorage.setItem("role", "trainer");
              navigate("/trainer");
            }
          });
          axios.defaults.headers.post["Content-Type"] = "application/json";
      })
      .catch((err) => {
        alert("Error al iniciar sesión");
        console.log("Error: ", err);
      });
  };

  return (
    <form onSubmit={handleSubmit} className="form">
      <div className="signin-title">
        <p>Iniciar Sesión</p>
      </div>
      <Container className="form-container">
        <TextField
          label="Email"
          type="email"
          name="email"
          value={email}
          onChange={handleEmailChange}
          variant="outlined"
          className="form-field"
        />

        <TextField
          label="Contraseña"
          type="password"
          name="password"
          value={password}
          onChange={handlePasswordChange}
          variant="outlined"
          className="form-field"
        />
      </Container>
      <div className="button-container ">
        <Button
          type="submit"
          variant="contained"
          color="primary"
          className="signin-button"
          onClick={handleSubmit}
        >
          Iniciar Sesión
        </Button>
        <Button
          type="submit"
          variant="contained"
          color="primary"
          className="signin-button"
          component={Link}
          to="/signup"
        >
          Registrarse
        </Button>
      </div>
    </form>
  );
};

export default FormSignin;
