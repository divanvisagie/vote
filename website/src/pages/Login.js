import React from 'react';
import { Link } from 'react-router';

import './Login.css';

const Login = () => (
    <div className="Login">
        <div className="login-box">
            <h3>Login</h3>
            <input type="text" placeholder="Username"></input>
            <input type="password" placeholder="Password"></input>
            <div className="button-box">
                <Link className='button button-outline' to='/ping'>Register</Link>
                <button>Login</button>
            </div>
        </div>
    </div>
);


export default Login;