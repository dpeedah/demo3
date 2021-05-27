import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
    state = {
        actors: []
    };

    async componentDidMount() {
        const response = await fetch('/api/actors/all');
        const body = await response.json();
        this.setState({actors: body});
    }

    render() {
        const {actors} = this.state;
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <div className="App-intro">
                        <h2>Actors</h2>
                        {actors.map(actor =>
                            <div key={actor.id}>
                                Id: {actor.id} Name: {actor.firstName} Last Name: {actor.lastName}
                            </div>
                        )}
                    </div>
                </header>
            </div>
        );
    }
}


export default App;