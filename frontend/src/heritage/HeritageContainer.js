import React, { useState, useEffect } from "react";
import Map from "../shared/Map";
import SearchFilter from "../shared/SearchFilter";
import classes from "./HeritageContainer.module.css";
import axios from "axios";
import Loader from "../shared/Loader";
const HeritageContainer = () => {
    const [heritageSites, setHeritageSites] = useState([]);
    const [filteredSites, setFilteredSites] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`${process.env.REACT_APP_API_URL}/all`);
                setHeritageSites(response.data);
                setFilteredSites(response.data);
            } catch (error) {
                console.error("Error fetching data:", error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchData();
    }, []);

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
                {isLoading ? (
                    <div className={classes.noResults}>
                        <Loader/>
                    </div>
                ) : (
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
                )}
            </div>
        </div>
    );
};

export default HeritageContainer;
