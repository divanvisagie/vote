import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { connect } from 'react-redux';
import TimeDisplay from './components/TimeDisplay';



const mapStateToProps = (state) => {
  return {
    time: state.counter
  }
}
const mapDispatchToProps = (dispatch) => {
  return {
    // onTodoClick: (id) => {
    //   dispatch(toggleTodo(id))
    // }
  }
}



const TimeDisplayContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(TimeDisplay)


const App = React.createClass({
  render() {
    return (
      <div>
        <h1>Hello World Yo</h1>
        <TimeDisplayContainer />
      </div>
    );
  }
});

export default App;
