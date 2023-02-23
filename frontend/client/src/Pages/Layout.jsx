import { Link, Outlet, useLocation } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
//import { findCookie } from '../misc';


const Layout = () => {
    const [currentUser, setCurrentUser] = useState({
            username: "",
            numberOfQuestions: 0,
            numberOfAnswers: 0
        });

    return (
        <>
            <nav id="header-nav" style={{"display": "flex", "justifyContent": "space-around"}}>
                <Link to="/home">Main page</Link>
                <Link to="/register">Register</Link>
                <Link to="/login">Login/Logout</Link>
                <Link to="/users">Order</Link>
                <div style={{"display": 'flex', "flexDirection": "column", "textAlign": "center"}}>
                    <div>{!currentUser.username ? "Logged out." : `Logged in as ${currentUser.username}.`}</div>
                    <div>{!currentUser.username ? "Click on Login/Logout " : `Questions: ${currentUser.numberOfQuestions}`}</div>
                    <div>{!currentUser.username ? "to log in." : `Answers: ${currentUser.numberOfAnswers}`}</div>
                </div>
            </nav>

            <Outlet/>

            <div id="footer">© 2022 npm -d. All rights reserved.</div>
        </>
    )
};

export default Layout;