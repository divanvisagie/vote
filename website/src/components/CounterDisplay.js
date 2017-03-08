import React, { PropTypes } from 'react';

const CounterDisplay = ({ prefix, text }) => (
  <div>
    <h2>{prefix}: {text}</h2>
  </div>
);

CounterDisplay.propTypes = {
  prefix: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired
};

export default CounterDisplay;
