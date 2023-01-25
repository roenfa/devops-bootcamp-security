import React, { useEffect } from "react";
import TableComponent from "../../components/Table/TableComponent";
import axios from "axios";
import { useParams } from "react-router-dom";

export default function Course () {

//get from route course/:course using useParams
const {course} = useParams();
//get type and token from session storage
const type = sessionStorage.getItem("tokenType");
const token = sessionStorage.getItem("accessToken");

const url = "http://localhost:8080/api/v1/";
axios.defaults.headers.post["Content-Type"] = "text/plain";
axios.defaults.headers.common["Authorization"] = type + " " + token;


const [courses, setCourses] = React.useState([]);

const getCourses = async () => {
    const response = await axios.post(url + "usercourse/getStudentsByCourse", course);
    setCourses(response.data);
};

const columns = [
    {
        id: 1,
        label: "id",
    },
    {
        id: 2,
        label: "nameCourse",
    },
    {
        id: 3,
        label: "note",
    },
    {
        id: 4,
        label: "student",
    }
];



useEffect(() => {
    getCourses();
}, []);
return (
    <div className="student">
      <h1 style={{ color: "black" }}>Curso {course}</h1>
        <TableComponent columns={columns} data={courses} />
    </div>
  );
}
