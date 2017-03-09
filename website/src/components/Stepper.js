import React, { PropTypes } from 'react';
import CounterDisplay from './CounterDisplay';
import Button from './Button'


const Stepper = ({ onClick, text }) => (
  <div>
    <CounterDisplay prefix='Step' text={text}/>
    <Button onClick={onClick}>Increment</Button>
  </div>
);

export default Stepper;
