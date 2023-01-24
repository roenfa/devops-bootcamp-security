import '../App.css';
import React, { Component } from 'react';




let email =localStorage.getItem('email');
class App extends Component {
  state = {
    students: []
  };

  async componentDidMount() {
    const response = await fetch(`/api/students/mydetails/${email}`, {
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
                <h2>My Details</h2>
                <table style={{backgroundColor: "black"}}>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Email</th>
                      <th>Country</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>{students.id}</td>
                      <td>{students.firstName}</td>
                      <td>{students.lastName}</td>
                      <td>{students.email}</td>
                      <td>{students.country}</td>
                    </tr>
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