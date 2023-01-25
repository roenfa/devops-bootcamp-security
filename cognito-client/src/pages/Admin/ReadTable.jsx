import React, { useEffect, useState } from "react";
import TableComponent from "../../components/Table/TableComponent";
import axios from "axios";

export default function ReadTable(props) {
  const [data, setData] = useState([]);

  //set url api
  const url = "http://localhost:8080/api/v1/";
  //set tokenType and accessToken
  const type = sessionStorage.getItem("tokenType");
  const token = sessionStorage.getItem("accessToken");

  axios.defaults.headers.common["Authorization"] = type + " " + token;
  const getStudent = async () => {
    const response = await axios.get(url + "user-roles/getStudents");
    setData(response.data);
  };

  const getTrainer = async () => {
    const response = await axios.get(url + "user-roles/getTrainers");
    setData(response.data);
  };

  const getCourses = async () => {
    const response = await axios.get(url + "course/all");
    setData(response.data);
  };

  const getBootcamps = async () => {
    const response = await axios.get(url + "bootcamps/all");
    setData(response.data);
  };

  //column for table
  const bootcampColumns = [
    {
      id: 1,
      label: "nameBootcamp",
    },
    {
      id: 2,
      label: "admin",
    },
  ];
  const courseColumns = [
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
  ];
  const trainerColumns = [
    {
      id: 1,
      label: "email",
    },
    {
      id: 2,
      label: "idUsername",
    },
    {
      id: 3,
      label: "role",
    },
  ];

  useEffect(() => {
    if (props.entity === "bootcamps") {
      getBootcamps();
    } else if (props.entity === "courses") {
      getCourses();
    } else if (props.entity === "trainers") {
      getTrainer();
    } else if (props.entity === "students") {
      getStudent();
    }
  }, []);
  useEffect(() => {
    console.log(data);
  }, [data]);

  return (
    <div className="student">
      <h1 style={{ color: "black" }}>Data for {props.entity}</h1>
        <TableComponent
            columns={props.entity === "bootcamps" ? bootcampColumns : props.entity === "courses" ? courseColumns : props.entity === "trainers" ? trainerColumns : props.entity === "students" ? trainerColumns : null}
            data={data}
        />
    </div>
  );
}
