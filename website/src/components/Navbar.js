import React from 'react';
import './Navbar.css';


const Navbar = ({ children = 'No Content' }) => (
    <nav className='Navbar'>
        {children}
    </nav>
)

export default Navbar;