import React, { useEffect } from "react";
import TableComponent from "../../components/Table/TableComponent";
import axios from "axios";
import { Button, FormLabel, Select, Grid } from "@mui/material";
import SignCard from "../../components/Forms/FormCard/SignCard";
import { Container } from "@mui/system";
import { Link, useNavigate } from "react-router-dom";
import "./Trainer.css";
const trainerData = {
  id: 1,
  name: "Nikola Tesla",
  columns: [
    {
      id: 1,
      label: "nameCourse",
    },
    {
      id: 2,
      label: "nameBootcamp",
    },

    {
      id: 3,
      label: "trainer",
    },
  ],
};

export default function Trainer() {
  const navigate = useNavigate();
  const [course, setCourse] = React.useState();
  const [courses, setCourses] = React.useState([]);
  const [student, setStudent] = React.useState();
  const [students, setStudents] = React.useState([]);
  const [generated, setGenerated] = React.useState(false);
  const [trainer, setTrainer] = React.useState(
    sessionStorage.getItem("username")
  );
  const type = sessionStorage.getItem("tokenType");
  const token = sessionStorage.getItem("accessToken");

  //setear url de la api
  const url = "http://localhost:8080/api/v1/";
  //setear axios a plain text
  axios.defaults.headers.post["Content-Type"] = "text/plain";
  //authorization
  axios.defaults.headers.common["Authorization"] = type + " " + token;

  //consultar cursos del trainer
  const getCourses = async () => {
    const response = await axios.post(url + "course/trainer", trainer);
    setCourses(response.data);
  };
  const getStudents = async () => {
    const response = await axios.get(url + "user-roles/getStudents/" + course);
    setStudents(response.data);
    setStudent('');
  };

  const createStudentCourse = async () => {
    const obj = {
        nameCourse: course,
        student: student,
    }
    axios.defaults.headers.post["Content-Type"] = "application/json";
         axios.post(
        url + "usercourse/create", obj
    );
    alert("Estudiante agregado al curso");
    setGenerated(true)
    };
  useEffect(() => {
    if (course)
    getStudents();
    else
    setStudents([]);
    }, [course]);

  const onChanges = (e) => {
    setCourse(e.target.value);
  };
  useEffect(() => {
    if (generated)
    getStudents();
    }, [generated]);

  const goToCourse = (e) => {
    navigate("/course/" + e.nameCourse);
  };

  React.useEffect(() => {
    getCourses();
  }, []);

  return (
    <div className="trainer">
      <h4 style={{ color: "black" }}>Trainer: {trainer}</h4>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <h6>
            Si desea ver la lista de estudiantes en el curso, seleccione la fila
            en la tabla por favor
          </h6>
          <TableComponent
            columns={trainerData.columns}
            data={courses}
            click={goToCourse}
          />
        </Grid>
        <Grid item xs={6} style={{ display: "flex", justifyContent: "center" }}>
          {courses != [] && students != [] && (
            <SignCard
              children={
                <Container
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    margin: "10%",
                  }}
                >
                  <h5 style={{ marginBottom: "5%" }}>
                    Add a student to a course
                  </h5>
                  <FormLabel>Name of the Course</FormLabel>

                  <Select
                    native
                    value={course}
                    onChange={onChanges}
                    style={{ marginBottom: "5%" }}
                  >
                    <option aria-label="None" value="" />
                    {courses.map((course) => (
                      <option value={course.nameCourse}>
                        {course.nameCourse}
                      </option>
                    ))}
                  </Select>

                  <FormLabel>Name of the Student</FormLabel>
                  <Select
                    native
                    value={student}
                    onChange={(e) => setStudent(e.target.value)}
                    style={{ marginBottom: "5%" }}
                  >
                    <option aria-label="None" value="" />
                    {students.map((student) => (
                      <option value={student.idUsername}>
                        {student.idUsername}
                      </option>
                    ))}
                  </Select>

                  <Button
                    type="submit"
                     onClick={createStudentCourse}
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
              height="450px"
            />
          )}
        </Grid>
      </Grid>
    </div>
  );
}
