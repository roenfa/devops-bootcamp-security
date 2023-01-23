import '../App.css';
import React, { Component } from 'react';

class App extends Component {
  state = {
    trainers: []
  };

  async componentDidMount() {
    const response = await fetch('/api/admin/all', {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('idToken')
      }
    });
    const body = await response.json();
    console.log(body);
    this.setState({trainers: body});
  }


  render () {
    const {trainers} = this.state;
      return (
          <div className="App">
            <header className="Header">
              <div className="App-intro">
                <h2>Trainers List</h2>
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
                    {trainers.map((trainer) => (
                      <tr key={trainer.id}>
                        <td>{trainer.id}</td>
                        <td>{trainer.firstName}</td>
                        <td>{trainer.lastName}</td>
                        <td>{trainer.email}</td>
                        <td>{trainer.country}</td>
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