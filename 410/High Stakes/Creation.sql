/*
    SQL Script to create the Theater database.
    Domains:
        - GENDER
        - PROJECTOR

    Tables:
        - DIRECTOR
        - PRODUCER
        - MOVIE
        - ACTOR
        - ROLE
        - THEATER
        - SHOWROOM
        - SHOWTIME
        - TICKET
*/


/*
    Encapsulate binary gender values M for Males and F for Female
*/
CREATE DOMAIN GENDER CHAR(1)
    CHECK (
        value IN (
            'M', 'F'
        )
    );


/*
    Encapsulates values for movie projector types
*/
CREATE DOMAIN PROJECTOR VARCHAR(9)
    CHECK (
        value IN (
            'FILM ROLL', 'DIGITAL', 'LASER', 'IMAX'
        )
    );


/*
    Represents a movie director
        - director_id   Unique, incremental identifier for a director
        - name          Name of the director
*/
CREATE TABLE director (
    director_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);


/*
    Represents a movie producer
        - producer_id   Unique, incremental identifier for a director
        - name          Name of the producer
*/
CREATE TABLE producer (
    producer_id     SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);


/*
    Represents a movie
        - movie_id          Unique, incremental identifier for a movie
        - title             Title of the film
        - episode_title     Name of an episode in the case of an Episodic or TV Show
        - year              Year the film was released
        - genre             Genre of the film
        - director_id       Identifier of the film director(s) (Foreign Key)
        - producer_id       Identifier of the film producer(s) (Foreign Key)
*/
CREATE TABLE movie (
    movie_id        SERIAL PRIMARY KEY,
    title           VARCHAR(500) NOT NULL,
    episode_title   VARCHAR(500),
    year            INT NOT NULL,
    genre           VARCHAR(255),
    director_id     INT REFERENCES director(director_id),
    producer_id     INT REFERENCES producer(producer_id)
);


/*
    Represents an actor/actress in a movie
        - actor_id      Unique, incremental identifier for an actor/actress
        - name          Name of the actor/actress
        - gender        Gender of the actor (male) or actress (female)
*/
CREATE TABLE actor (
    actor_id            SERIAL PRIMARY KEY,
    name                VARCHAR(255) NOT NULL,
    gender              GENDER
);


/*
    Represents an actor/actress' role in a movie
        - actor_id          Identifier for an actor/actress (Foreign Key)
        - movie_id          Identifier for a movie (Foreign Key)
        - character_name    Name of the character the actor/actress played
*/
CREATE TABLE role (
    actor_id            INT REFERENCES actor(actor_id),
    movie_id            INT REFERENCES movie(movie_id),
    character_name      VARCHAR(255),
    PRIMARY KEY         (actor_id, movie_id, character_name)
);


/*
    Represents a movie Theater
        - theater_id    Unique, incremental identifier for a Theater
        - name          Name of the Theater
        - city          City in which the Theater exists
        - state         State in which the Theater exists
        - zipcode       Zip code in which the Theater exists
*/
CREATE TABLE theater (
    theater_id      SERIAL PRIMARY KEY,
    name            VARCHAR(500) NOT NULL,
    city            VARCHAR(255),
    state           CHAR(2),
    zipcode         CHAR(5)
);


/*
    Represents a showroom in a Theater
        - showroom_id       Unique identifier for a showroom
        - theater_id        Identifier of the Theater containing the showroom (Foreign Key)
        - projector_type    Type of projector that the showroom uses
        - capacity          Number of people the showroom can hold
*/
CREATE TABLE showroom (
    showroom_id     INT UNIQUE,
    theater_id      INT REFERENCES Theater(theater_id),
    projector_type  PROJECTOR,
    capacity        INT,
    PRIMARY KEY     (showroom_id, theater_id)
);


/*
    Represents a showtime for a film
        - showtime_id   Unique, incremental identifier for a showtime
        - theater_id    Identifier of the Theater playing the showing (Foreign Key)
        - showroom_id   Identifier of the showroom hosting the showing (Foreign Key)
        - movie_id      Identifier of the movie being shown (Foreign Key)
        - start_time    Time in which the showing begins
        - end_time      Time in which the showing ends
        - show_date     Date on which the showing plays
*/
CREATE TABLE showtime (
    showtime_id     SERIAL PRIMARY KEY,
    theater_id      INT REFERENCES Theater(theater_id),
    showroom_id     INT REFERENCES showroom(showroom_id),
    movie_id        INT REFERENCES movie(movie_id),
    start_time      TIME,
    end_time        TIME,
    show_date       DATE
);


/*
    Represents a ticket for a showtime
        - showtime_id   Identifier of the showtime for which the ticket can be used (Foreign Key)
        - seat_number   Seat for the ticket-holder to occupy
        - price         Price of the ticket
        - time_bought   Time in which the ticket was purchased
        - date_bought   Date on which the ticket was purchased
        - wasUsed       True if the ticket was redeemed
*/
CREATE TABLE ticket (
    showtime_id     INT REFERENCES showtime(showtime_id),
    seat_number     SERIAL UNIQUE,
    price           NUMERIC(1000, 2) NOT NULL,
    time_bought     TIME,
    date_bought     DATE,
    wasUsed         BOOLEAN,
    PRIMARY KEY     (showtime_id, seat_number)
);