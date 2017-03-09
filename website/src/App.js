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
          {this.props.children}
        </section>
      </main>
    );
  }
});

export default App;
