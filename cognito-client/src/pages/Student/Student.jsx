import React, { useEffect } from "react";
import TableComponent from "../../components/Table/TableComponent";
import axios from "axios";

export default function Student() {
  const student = sessionStorage.getItem("username");
  const [courses, setCourses] = React.useState([]);
  const url = "http://localhost:8080/api/v1/";
  const type = sessionStorage.getItem("tokenType");
  const token = sessionStorage.getItem("accessToken");
  axios.defaults.headers.post["Content-Type"] = "text/plain";
  axios.defaults.headers.common["Authorization"] = type + " " + token;

  const getMyCourses = () => {
    axios.post(url + "usercourse/courses", student).then((response) => {
      setCourses(response.data);
    });
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
    },
  ];

  useEffect(() => {
    getMyCourses();
  }, []);

  return (
    <div className="student">
      <h5 style={{ color: "black" }}>Student {student}</h5>
      <TableComponent columns={columns} data={courses} />
    </div>
  );
}
