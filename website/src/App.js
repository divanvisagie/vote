import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import UptimeComponent from './components/container/UptimeComponent';
import StepperComponent from './components/container/StepperComponent';
import Logo from './components/Logo';
import Navbar from './components/Navbar';


const App = React.createClass({
  render() {
    return (
      <main className="wrapper">
        <Navbar>
          <section className="container">
            <Logo />
          </section>
          
        </Navbar>
        <section className="container">
          <h1>Hello World Yo</h1>
          <UptimeComponent />
          <StepperComponent />
        </section>
      </main>
    );
  }
});

export default App;
