import React, { useState, useEffect } from "react";
import Map from "../shared/Map";
import SearchFilter from "../shared/SearchFilter";
import classes from "./HeritageContainer.module.css";
import axios from "axios";

const HeritageContainer = () => {
    const [heritageSites, setHeritageSites] = useState([]);
    const [filteredSites, setFilteredSites] = useState([]); // State for filtered sites

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get("https://heritage-macedonia-f654e36c0f1a.herokuapp.com/heritage/all");
                setHeritageSites(response.data);
                setFilteredSites(response.data); // Initialize filtered sites with all sites
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };

        fetchData();
    }, []);

    // Function to handle search
    const handleSearch = (searchTerm) => {
        if (!heritageSites || heritageSites.length === 0) {
            return;
        }

        const normalizedSearchTerm = searchTerm.toLocaleLowerCase();

        const filtered = heritageSites.filter((site) =>
            site.name &&
            site.address &&
            (site.name.toLocaleLowerCase().includes(normalizedSearchTerm) ||
            site.address.toLocaleLowerCase().includes(normalizedSearchTerm))
        );
        setFilteredSites(filtered);
    };

    return (
        <div className={classes.main}>
            <div className={classes.searchBar}>
                <SearchFilter onSearch={handleSearch} />
            </div>
            <div className={classes.heritageContainer}>
                <div className={classes.heritageMap}>
                    {filteredSites.length ? (
                        <Map
                            markers={filteredSites.map((place) => ({
                                ...place,
                                position: {
                                    lat: +place.lat,
                                    lng: +place.lon,
                                },
                            }))}
                        />
                    ) : (
                        <div className={classes.noResults}>
                            <h1>No results found</h1>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default HeritageContainer;