import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import UptimeComponent from './components/container/UptimeComponent';


const App = React.createClass({
  render() {
    return (
      <div>
        <h1>Hello World Yo</h1>
        <UptimeComponent />
      </div>
    );
  }
});

export default App;
