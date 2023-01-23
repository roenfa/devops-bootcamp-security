import '../App.css';
import React, { Component } from 'react';

class App extends Component {
  state = {
    subjects: []
  };

  async componentDidMount() {
    const response = await fetch('/api/students/results', {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('idToken')
      }
    });
    const body = await response.json();
    this.setState({subjects: body});
  }


render () {
  const {subjects} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Subjects</h2>
              {subjects.map(subject =>
                  <table key={subject.id}>
                    {subject.studentId}:[{subject.subjectName}: {subject.value}]
                  </table>
              )}
              <a href="/home-signed-in"><button className='logout-btn'>Back</button></a>
            </div>
          </header>
        </div>
  );
}
}

export default App;
