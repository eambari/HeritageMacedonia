import React from "react";
import { Link } from "react-router-dom";
import "./Introduction.css";

const Introduction = () => {
    return (
        <div className="introduction-container">
            <div className="introduction">
                <h1>Welcome to Heritage Sites in Macedonia</h1>
                <p>
                    Explore the rich cultural heritage of Macedonia, a country steeped in history and adorned with magnificent heritage sites. From ancient archaeological wonders to medieval marvels, Macedonia offers a journey through time and culture.
                </p>
                <p>
                    Discover the stories behind each site, the architectural wonders that have withstood the test of time, and the vibrant cultural tapestry that makes Macedonia a treasure trove of heritage.
                </p>
                <p>
                    Begin your exploration and immerse yourself in the beauty and significance of Macedonia's heritage sites.
                </p>
                <Link to="/heritage" className="explore-button">Explore Heritage Sites</Link>
            </div>
        </div>
    );
};

export default Introduction;
