CREATE DOMAIN GENDER CHAR(1)
    CHECK (value IN (
        'M', 'F'
    ));

CREATE DOMAIN PROJECTOR VARCHAR(9)
    CHECK (value IN (
        'FILM ROLL', 'DIGITAL', 'LASER', 'IMAX'
    ));

CREATE TABLE director (
    director_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);

CREATE TABLE producer (
    producer_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);

CREATE TABLE movie (
    movie_id        SERIAL PRIMARY KEY,
    title           VARCHAR(500) NOT NULL,
    episode_title   VARCHAR(500),
    year            INT NOT NULL,
    genre           VARCHAR(255),
    director_id     INT REFERENCES director(director_id),
    producer_id     INT REFERENCES producer(producer_id)
);

CREATE TABLE actor (
    actor_id            SERIAL PRIMARY KEY,
    name                VARCHAR(255) NOT NULL,
    gender              GENDER
);

CREATE TABLE role (
    actor_id            INT REFERENCES actor(actor_id),
    movie_id            INT REFERENCES movie(movie_id),
    character_name      VARCHAR(255),
    PRIMARY KEY         (actor_id, movie_id, character_name)
);

CREATE TABLE theatre (
    theatre_id      SERIAL PRIMARY KEY,
    name            VARCHAR(500) NOT NULL,
    city            VARCHAR(255),
    state           CHAR(2),
    zipcode         CHAR(5)
);

CREATE TABLE showroom (
    showroom_id     SERIAL UNIQUE,
    theatre_id      INT REFERENCES theatre(theatre_id),
    projector_type  PROJECTOR,
    capacity        INT,
    PRIMARY KEY     (showroom_id, theatre_id)
);

CREATE TABLE showtime (
    showtime_id     SERIAL PRIMARY KEY,
    theatre_id      INT REFERENCES theatre(theatre_id),
    showroom_id     INT REFERENCES showroom(showroom_id),
    movie_id        INT REFERENCES movie(movie_id),
    start_time      TIME,
    end_time        TIME,
    show_date       DATE
);

CREATE TABLE ticket (
    showtime_id     INT REFERENCES showtime(showtime_id),
    seat_number     SERIAL UNIQUE,
    price           NUMERIC(1000, 2) NOT NULL,
    time_bought     TIME,
    date_bought     DATE,
    wasUsed         BOOLEAN,
    PRIMARY KEY     (showtime_id, seat_number)
);