CREATE TABLE heritage_site (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               historic TEXT,
                               "natural" TEXT,
                               tourism TEXT,
                               address TEXT,
                               lat DOUBLE PRECISION,
                               lon DOUBLE PRECISION
);
