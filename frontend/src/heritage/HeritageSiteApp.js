import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './HeritageSiteApp.module.css';
import {API_URL} from "../App";

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
                const response = await axios.get(`${API_URL}/all`);
                setHeritageSites(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

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
