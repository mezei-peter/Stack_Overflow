import { Link, Outlet, useLocation } from 'react-router-dom';
import React, { useEffect, useState } from 'react';


const Layout = () => {
    const [currentUser, setCurrentUser] = useState({
            username: "",
            numberOfQuestions: 0,
            numberOfAnswers: 0
        });

    useEffect(() => {
        const sessionId = localStorage.getItem("sessionId");
        if (sessionId) {
            fetch("/user/session/" + sessionId)
            .then(res => {
                if (res.status != 200) {
                    console.warn("Not logged in");
                }
                return res;
            })
            .then(res => {
                return res.json();
            })
            .then(user => {
                setCurrentUser({
                    "username": user.username,
                    "numberOfQuestions": user.noOfQuestions,
                    "numberOfAnswers": user.noOfAnswers
                });
            })
            .catch(e => {});
        }
    }, []);

    return (
        <>
            <nav id="header-nav" style={{"display": "flex", "justifyContent": "space-around"}}>
                <Link to="/home">Main page</Link>
                <Link to="/register">Register</Link>
                <Link to="/login">Login/Logout</Link>
                <Link to="/users">List of users</Link>
                <div style={{"display": 'flex', "flexDirection": "column", "textAlign": "center"}}>
                    <div>{!currentUser.username ? "Logged out." : <span>Logged in as <span style={{"color": "blue"}}>{currentUser.username}</span></span>}</div>
                    <div>{!currentUser.username ? "Click on Login/Logout " : `Questions: ${currentUser.numberOfQuestions}`}</div>
                    <div>{!currentUser.username ? "to log in." : `Answers: ${currentUser.numberOfAnswers}`}</div>
                    <div>{!currentUser.username ? "" : <Link to="/question/new">Post new question</Link>}</div>
                </div>
            </nav>

            <Outlet/>

            <div id="footer">© 2023 All rights reserved. The best team is Mind Overflow.</div>
        </>
    )
};

export default Layout;