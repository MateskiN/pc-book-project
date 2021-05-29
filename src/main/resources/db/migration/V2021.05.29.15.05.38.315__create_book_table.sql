CREATE TABLE book
(
    id BIGSERIAL NOT NULL,
    title VARCHAR(250) NOT NULL,
    author_id INTEGER NOT NULL,
    isbn VARCHAR(100) NOT NULL,
    year_of_publish INTEGER NOT NULL,
    type VARCHAR(50) NOT NULL,
    number_of_pages INTEGER,
    weight NUMERIC(8,2),
    format VARCHAR(50),
    size INTEGER,
    CONSTRAINT book_pkey PRIMARY KEY (id),
    CONSTRAINT fk_book_author FOREIGN KEY (author_id)
        REFERENCES author (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);