import '../App.css';
import React, { Component } from 'react';

class App extends Component {
  state = {
    students: []
  };

  async componentDidMount() {
    const response = await fetch('/api/trainers/students', {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('idToken')
      }
    });
    const body = await response.json();
    console.log(body);
    this.setState({students: body});
  }


  render () {
    const {students} = this.state;
      return (
          <div className="App">
            <header className="Header">
              <div className="App-intro">
                <h2>Students</h2>
                <table style={{backgroundColor: "black"}}>
                  <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Country</th>
                    </tr>
                  </thead>
                  <tbody>
                    {students.map((student) => (
                      <tr key={student.id}>
                        <td>{student.id}</td>
                        <td>{student.firstName}</td>
                        <td>{student.lastName}</td>
                        <td>{student.email}</td>
                        <td>{student.country}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
                <a href="/role_redirect"><button className='logout'>Back</button></a>
              </div>
            </header>
          </div>
    );
  }
}

export default App;