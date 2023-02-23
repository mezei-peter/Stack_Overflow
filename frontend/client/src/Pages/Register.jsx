import React from 'react';
import { useState } from 'react';
import ReactDOM from 'react-dom/client';

const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [registerResponse, setRegisterResponse] = useState("");

    const submitRegistration = (event) => {
        event.preventDefault();
        fetch("/user/", {
            method: "POST", 
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                "username": username,
                "password": password
            })
        })
        .then(res => res.text())
        .then(msg => {
            console.log(msg);
            setRegisterResponse(msg);
        });
    };

    return (
    <div>
        <form style={{"display": "flex", "flexDirection": "column", "alignItems": "center"}}>
            <label htmlFor="register-name-input">Username: </label>
            <input 
                type="text" id='register-name-input' name='register-name-input' required
                onChange={(e) => setUsername(e.target.value)}
            />
            <label htmlFor="register-password-input">Password: </label>
            <input 
                type="password" id='register-password-input' name='register-password-input' required
                onChange={e => setPassword(e.target.value)}
            />
            <button onClick={(event) => submitRegistration(event)}>Register</button>
            {registerResponse && <div>{registerResponse}</div>}
        </form>
        
    </div>);
}
 
export default Register;