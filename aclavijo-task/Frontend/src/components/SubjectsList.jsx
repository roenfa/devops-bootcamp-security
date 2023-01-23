import '../App.css';
import React, { Component } from 'react';

class App extends Component {
  state = {
    subjects: []
  };

  async componentDidMount() {
    const response = await fetch('/api/trainers/subjects', {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('idToken')
      }
    });
    const body = await response.json();
    console.log(body);
    this.setState({subjects: body});
  }


  render () {
    const {subjects} = this.state;
      return (
          <div className="App">
            <header className="Header">
              <div className="App-intro">
                <h2>Subjects</h2>
                  <table style={{backgroundColor: "black"}}>
                      <thead>
                          <tr>
                              <th>ID</th>
                              <th>Name</th>
                              <th>TrainerID</th>
                              <th>Trainer Name</th>
                          </tr>
                      </thead>
                      <tbody>
                          {subjects.map((subject) => (
                              <tr key={subject.id}>
                                  <td>{subject.id}</td>
                                  <td>{subject.name}</td>
                                  <td>{subject.trainer.id}</td>
                                  <td>{subject.trainer.firstName + ' ' + subject.trainer.lastName}</td>
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