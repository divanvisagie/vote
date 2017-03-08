import React, { PropTypes } from 'react';
import CounterDisplay from './CounterDisplay';


const Stepper = ({ onClick, text }) => (
  <div>
    <CounterDisplay prefix="Step" text={text}/>
    <button onClick={onClick}>Increment</button>
  </div>
);

export default Stepper;
