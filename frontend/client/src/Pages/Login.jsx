import React, { useEffect, useState } from 'react';
import ReactDOM from 'react-dom/client';

const LoginForm = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const submitRegistration = (event) => {
        event.preventDefault();
        fetch("/user/login", {
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
            localStorage.setItem("sessionId", msg);
            window.location.reload();
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
            <button onClick={(event) => submitRegistration(event)}>Login</button>
        </form>
        
    </div>);
}

const Login = () => {
    const [isLoggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        const sessionId = localStorage.getItem("sessionId");
        if (sessionId) {
            fetch("/user/session/" + sessionId)
            .then(res => {
                if (res.status != 200) {
                    console.warn("Not logged in");
                    setLoggedIn(false);
                } else {
                    setLoggedIn(true);
                }
                console.log(isLoggedIn)
            })
            .catch(e => {});
        }
    }, []);

    return (
        <div>
            {isLoggedIn && <button style={{"width": "20%", "fontSize": "30px"}}>Logout</button>}
            {!isLoggedIn && <LoginForm/>}
        </div>
    );
}
 
export default Login;