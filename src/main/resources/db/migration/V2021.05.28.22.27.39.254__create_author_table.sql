CREATE TABLE author
(
    id BIGSERIAL NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    year_of_birth INTEGER NOT NULL,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);