import React from "react";
import "./desktopHeader.css";
import {Link} from "react-router-dom";

const Header = (props) => {
    return (
        <div className="main-header">
            <div className="container">
                <Link to="/" className="logo">Heritage Sites</Link>
            </div>
            <div>
                <nav className="nav">
                    <Link to="/">Home</Link>
                    <Link to="/heritage">Heritage Sites</Link>
                    <Link to="/about">About us</Link>
                    <div className="indicator"></div>
                </nav>
            </div>
        </div>
    );
};

export default Header;
