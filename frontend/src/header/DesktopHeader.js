import React from "react";
import "./desktopHeader.css";
import {Link} from "react-router-dom";
import {ReactComponent as LogoSVG} from '../logo.svg'
const Header = (props) => {
    return (
        <div className="main-header">
            <div className="container">
                <Link to="/" className="logo"><LogoSVG/></Link>
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
