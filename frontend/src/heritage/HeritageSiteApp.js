import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './HeritageApp.css'; // Import your CSS file

const HeritageSiteList = ({ heritageSites }) => (
    <div>
        <h2>Heritage Sites</h2>
        <ul>
            {heritageSites.map((site) => (
                <li key={site.id}>
                    {site.historic &&
                        <>
                    <strong>{site.name}</strong>
                    <p>{site.address}</p>
                    <p>{site.historic}</p>
                        </>
                    }
                </li>
            ))}
        </ul>
    </div>
);

const HeritageSiteApp = () => {
    const [heritageSites, setHeritageSites] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:9999/heritage/all'); // Update with your backend URL
                setHeritageSites(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []); // Empty dependency array ensures useEffect runs only once (on component mount)

    return (
        <div className="app-container">
            <header>
                <h1>Heritage Site Explorer</h1>
            </header>
            {loading ? (
                <div className="loader">Loading...</div>
            ) : (
                <HeritageSiteList heritageSites={heritageSites} />
            )}
        </div>
    );
};

export default HeritageSiteApp;
