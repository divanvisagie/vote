import React from 'react';
import './Button.css';

const Button = ({ onClick, children }) => (
    <button className='Button' onClick={onClick}>{ children }</button>
);

export default Button;