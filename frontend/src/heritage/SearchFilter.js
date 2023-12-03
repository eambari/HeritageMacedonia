import React, { useState } from "react";
import PropTypes from "prop-types";
import classes from "./SearchFilter.module.css";

const SearchFilter = ({ onSearch }) => {
    const [searchTerm, setSearchTerm] = useState("");

    const handleChange = (event) => {
        setSearchTerm(event.target.value);
        onSearch(event.target.value);
    };

    return (
        <div className={classes.searchContainer}>
            <input
                type="text"
                placeholder="Search by name or address"
                value={searchTerm}
                onChange={handleChange}
                className={classes.searchInput}
            />
        </div>
    );
};

SearchFilter.propTypes = {
    onSearch: PropTypes.func.isRequired,
};

export default SearchFilter;
