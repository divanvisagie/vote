import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import UptimeComponent from './components/container/UptimeComponent';
import StepperComponent from './components/container/StepperComponent';


const App = React.createClass({
  render() {
    return (
      <div>
        <h1>Hello World Yo</h1>
        <UptimeComponent />
        <StepperComponent />
      </div>
    );
  }
});

export default App;
