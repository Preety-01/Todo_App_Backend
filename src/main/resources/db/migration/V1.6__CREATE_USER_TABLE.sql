CREATE TABLE person (
    person_id INT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50) UNIQUE,
    phone INT,
    DOB DATE,
    password VARCHAR(50) UNIQUE,
    PRIMARY KEY(person_id)
);