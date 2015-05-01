CREATE DATABASE THEATRE;

CREATE DOMAIN GENDER CHAR(1)
    CHECK (value IN (
        'M', 'F'
    ));

CREATE DOMAIN PROJECTOR VARCHAR(9)
    CHECK (value IN (
        'FILM ROLL', 'DIGITAL', 'LASER', 'IMAX'
    ));

CREATE TABLE movie (
    movie_id    SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    year        INT NOT NULL
);

CREATE TABLE actor (
    actor_id            SERIAL PRIMARY KEY,
    name                VARCHAR(255),
    gender              GENDER,
    movie_id            INT REFERENCES movie(movie_id),
    character_name      VARCHAR(255)
);

CREATE TABLE producer (
    producer_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    movie_id        INT REFERENCES movie(movie_id),
);

CREATE TABLE director (
    director_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    movie_id        INT REFERENCES movie(movie_id)
);

CREATE TABLE theatre (
    theatre_id      SERIAL PRIMARY KEY,
    theatre_name    VARCHAR(255),
    num_showrooms   INT,
    city            VARCHAR(255),
    state           CHAR(2),
    zipcode         CHAR(5)
);

CREATE TABLE showroom (
    showroom_id     SERIAL,
    theatre_id      INT REFERENCES theatre(theatre_id),
    projector_type  PROJECTOR
    PRIMARY KEY     (showroom_id, theatre_id)
);

CREATE TABLE showtime (
    showtime_id     SERIAL PRIMARY KEY,
    theatre_id      INT REFERENCES theatre(theatre_id),
    movie_id        INT REFERENCES movie(movie_id),
    start_time      TIME,
    end_time        TIME,
    show_date       DATE
);

CREATE TABLE ticket (
    ticket_number   SERIAL,
    showtime        INT REFERENCES showtime(showtime_id),
    price           NUMERIC(1000, 2),
    PRIMARY KEY     (ticket_number, showtime)
);