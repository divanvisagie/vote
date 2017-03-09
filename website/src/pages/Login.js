import React from 'react';
import { Link } from 'react-router';

const Login = React.createClass({
    render() {
        return (
            <div>
                <h1>Login Page</h1>
                <Link to='/ping'>Go to Ping</Link>
            </div>
        )
    }
});


export default Login;