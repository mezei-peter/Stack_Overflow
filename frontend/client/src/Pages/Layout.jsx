import { Link, Outlet, useLocation } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
//import { findCookie } from '../misc';


const Layout = () => {

    return (
        <>
            <nav id="header-nav">
                <Link to="/home">Main page</Link>
                <Link to="/register">Register</Link>
                <Link to="/login">Login/Logout</Link>
                <Link to="/users">Order</Link>
            </nav>

            <Outlet/>

            <div id="footer">© 2022 npm -d. All rights reserved.</div>
        </>
    )
};

export default Layout;