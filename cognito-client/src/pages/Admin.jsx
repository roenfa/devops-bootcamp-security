import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import {
  Button,
  Container,
  FormLabel,
  Input,
  Select,
  Grid,
} from "@mui/material";
import SignCard from "../components/Forms/FormCard/SignCard";

const Admin = () => {
  //set url for axios
  axios.defaults.baseURL = "http://localhost:8080";
  //set token for axios
  const tokenType = sessionStorage.getItem("tokenType");
  const accessToken = sessionStorage.getItem("accessToken");
  axios.defaults.headers.common[
    "Authorization"
  ] = `${tokenType} ${accessToken}`;

  const [bootcampInput, setBootcampInput] = useState();
  const [bootcampChange, setBootcampChange] = useState();
  const [bootcamps, setBootcamps] = useState([]);
  const [trainers, setTrainers] = useState([]);
  const [trainer, setTrainer] = useState();
  const [course, setCourse] = useState([]);
  const [courses, setCourses] = useState([]);
  const [user, setUser] = useState(sessionStorage.getItem("username"));

  const getBootcamps = async () => {
    const res = await axios.get("/api/v1/bootcamps/all");
    setBootcamps(res.data);
  };
  const getTrainers = async () => {
    const res = await axios.get("/api/v1/user-roles/getTrainers");
    setTrainers(res.data);
  };
  const getCourses = async () => {
    const res = await axios.get("/api/v1/course/all");
    setCourses(res.data);
  };

  useEffect(() => {
    getBootcamps();
    getTrainers();
    getCourses();
  }, []);

  const createBootcamp = async (e) => {
    e.preventDefault();
    const bootcamp2 = {
      nameBootcamp: bootcampInput.nameBootcamp,
      admin: user,
    };
    const res = await axios.post("/api/v1/bootcamps/create", bootcamp2);
    setBootcampInput('');
    alert("Bootcamp created");
    setBootcamps([...bootcamps, res.data]);
    getTrainers();
    getBootcamps();
  };

  const createCourse = async (e) => {
    e.preventDefault();
  const obj = {
    nameCourse: course.nameCourse,
    nameBootcamp: bootcampChange,
    trainer: trainer,
  };
  console.log(obj);
    await axios.post("/api/v1/course/create", obj).then((res) => {
      alert("Course created");
      console.log(res.data);
    });

  };

  return (
    <div style={{ width: "85%", height: "100%" ,margin:"auto"}}>
      <div >
        <h3>Admin Page</h3>
      </div>
      <Grid container spacing={5} style={{ alignItems: "center" }}>
        <Grid item xs={4}>
          <SignCard
            children={
              <Container
                style={{
                  display: "flex",
                  flexDirection: "column",
                  
                }}
              >
                <h5 style={{ marginBottom: "10%" }}>Create a new bootcamp</h5>
                <FormLabel>Name of the bootcamp</FormLabel>
                <Input
                  type="text"
                  value={bootcampInput?.nameBootcamp || ""}
                  onChange={(e) =>
                    setBootcampInput({ ...bootcampInput, nameBootcamp: e.target.value })
                  }
                  
                  style={{ marginBottom: "10%" }}
                />

                <Button
                  type="submit"
                  onClick={createBootcamp}
                  style={{
                    backgroundColor: "#1976D2",
                    color: "white",
                    marginBottom: "10%",
                  }}
                >
                  Create
                </Button>
              </Container>
            }
            height="500px"
          />
        </Grid>
        <Grid item xs={4}>
          {bootcamps != [] && trainers != [] ? (
            
              <SignCard
                children={
                  <Container
                    style={{
                      display: "flex",
                      flexDirection: "column",
                      
                    }}
                  >
                    <h5 style={{ marginBottom: "5%" }}>Create a new course</h5>
                    <FormLabel>Name of the Course</FormLabel>
                    <Input
                      type="text"
                      name="name"
                      id="name"
                      onChange={(e) =>
                        setCourse({ ...course, nameCourse: e.target.value })
                      }
                    />
                    <FormLabel htmlFor="bootcamp">Bootcamp</FormLabel>

                    <Select
                     native
                      value={bootcampChange}
                      onChange={(e) =>
                        setBootcampChange(e.target.value)
                      }
                    >
                      <option aria-label="None" value="" /> 
                      {bootcamps.map((b) => (
                        <option value={b.nameBootcamp}>
                          {b.nameBootcamp}
                        </option>
                      ))}
                    </Select>
                    <FormLabel htmlFor="trainer">Trainer</FormLabel>
                    <Select
                      native
                      value={trainer}
                      onChange={(e) =>
                        setTrainer(e.target.value)
                      }
                      style={{ marginBottom: "3%" }}
                    >
                      <option aria-label="None" value="" />
                      {trainers.map((trainer) => (
                        <option value={trainer.idUsername}>
                          {trainer.idUsername}
                        </option>
                      ))}
                    </Select>
                    <Button
                      type="submit"
                      onClick={createCourse}
                      style={{ backgroundColor: "#1976D2", color: "white" }}
                    >
                      Create
                    </Button>
                  </Container>
                }
                height="500px"
              />
            
          ) : (
            <div style={{ textAlign: "center" }}>
              <p>You need bootcamps to</p>
              <p>Generate Courses</p>
            </div>
          )}
        </Grid>
        <Grid item xs={4}>
          {/* mostrar botones en un card, lista bootcamps, lista cursos, lista estudiantes, lista trainers */}
          <SignCard
            children={
              <Container
                style={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  
                }}
              >

          <h5 style={{ marginBottom: "5%" }}>Mostrar lista</h5>
                <Button style={{ marginBottom: "5%" }}>
                  <Link to="/admin/bootcamps">Bootcamps</Link>
                </Button>
                <Button style={{ marginBottom: "5%" }}>
                  <Link to="/admin/courses">Courses</Link>
                </Button>
                <Button style={{ marginBottom: "5%" }}>
                  <Link to="/admin/students">Students</Link>
                </Button>
                <Button style={{ marginBottom: "5%" }}>
                  <Link to="/admin/trainers">Trainers</Link>
                </Button>
              </Container>
            }
            height="500px"
          />

          </Grid>
      </Grid>
    </div>
  );
};
export default Admin;
