/*
    Query to show all movie titles along with the actors/actresses
    that perform in the movie as well as the character name.
*/
SELECT DISTINCT
    title           AS "Movie",
    name            AS "Starring",
    character_name  AS "As"
FROM
    movie, actor, role
WHERE
    role.movie_id=movie.movie_id	AND
    role.actor_id=actor.actor_id
ORDER BY
    title,
    name;


/*
    Query to view the directors and producers of all
    movies.
*/
SELECT DISTINCT
    title           AS "Movie",
    director.name   AS "Director",
    producer.name   AS "Producer"
FROM
    movie, director, producer
WHERE
    movie.director_id=director.director_id  AND
    movie.producer_id=producer.producer_id
ORDER BY
    title;


/*
    Query to view all movie titles that were released before
    the year 2015
*/
SELECT
    title AS "Title"
FROM
    movie
WHERE
    year=2015;


/*
    Query to view the average ticket price for each theater
*/
SELECT
    theater.name            AS "Theater",
    round(AVG(price), 2)    AS "Average Ticket Price ($)"
FROM
    showtime, ticket, theater
WHERE
    showtime.theater_id=theater.theater_id  AND
    ticket.showtime_id=showtime.showtime_id
GROUP BY
    theater.name;


/*
    Query to view all theaters and their city, state, and zipcode
    location.
*/
SELECT
    name,
    city,
    state,
    zipcode
FROM
    theater;


/*
    Query to view all theaters that are currently playing the
    Avengers movie.
*/
SELECT DISTINCT
    name
FROM
    theater,
    showtime,
    movie
WHERE
    movie.title LIKE '%Avengers%'           AND
    movie.movie_id=showtime.movie_id        AND
    showtime.theater_id=theater.theater_id;


/*
    Query to view the theater name, show start time, show date,
    and ticket price for all theaters playing the Avengers movie.
*/
SELECT
    name,
    start_time,
    show_date,
    price
FROM
    theater,
    movie,
    showtime,
    ticket
WHERE
    movie.title LIKE '%Avengers%'               AND
    showtime.movie_id=movie.movie_id            AND
    showtime.theater_id=theater.theater_id      AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theater.name,
    show_date,
    start_time;


/*
    Query to view the name of all theaters, movie titles,
    show start time, show date, and ticket price for all
    theaters that have any showtimes.
*/
SELECT
    name,
    title,
    start_time,
    show_date,
    price
FROM
    theater,
    movie,
    showtime,
    ticket
WHERE
    showtime.movie_id=movie.movie_id            AND
    showtime.theater_id=theater.theater_id      AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theater.name,
    showtime.show_date,
    showtime.start_time;


/*
    Query to view the title of all movies that were
    produced and/or directed by Steven Spielberg
*/
SELECT DISTINCT
    title   AS "Spielberg Film"
FROM
    movie,
    director,
    producer
WHERE
    movie.director_id=director.director_id      AND
    director.name='Steven Spielberg'            OR
    movie.producer_id=producer.producer_id      AND
    producer.name='Steven Spielberg';


/*
    Query to view all theater names, the types of projectors
    the theater uses, as well as the number of each project
    the theater uses.
*/
SELECT
    name                    AS "Theater",
    projector_type          AS "Projector",
    COUNT(projector_type)   AS "Count"
FROM
    theater,
    showroom
WHERE
    showroom.theater_id=theater.theater_id
GROUP BY
    name,
    projector_type
ORDER BY
    name,
    projector_type;


/*
    Query to view the number of tickets sold, ticket sales, and the number
    of tickets that were redeemed for each theater.
*/
SELECT
    name                                                        AS "Theater",
    COUNT(ticket)                                               AS "Tickets Sold",
    SUM(ticket.price)                                           AS "Sales ($)",
    COUNT(CASE WHEN ticket.wasUsed='t' THEN 1 ELSE NULL END)    AS "Ticket Redeemed"
FROM
    ticket,
    showtime,
    theater
WHERE
    ticket.showtime_id=showtime.showtime_id AND
    showtime.theater_id=theater.theater_id
GROUP BY
    name;


/*
    Query to view the number of tickets sold, the number of tickets available,
    for each showtime.
*/
SELECT
    showtime.showtime_id                AS "Showtime",
    showtime.show_date                  AS "Date",
    showtime.start_time                 AS "Start Time",
    movie.title                         AS "Movie",
    COUNT(ticket)                       AS "Tickets Sold",
    showroom.capacity-COUNT(ticket)     AS "Tickets Available"
FROM
    ticket,
    showtime,
    showroom,
    movie
WHERE
    ticket.showtime_id=showtime.showtime_id     AND
    showtime.showroom_id=showroom.showroom_id   AND
    showtime.movie_id=movie.movie_id
GROUP BY
    showtime.showtime_id,
    showtime.show_date,
    showtime.start_time,
    showroom.capacity,
    movie.title
ORDER BY
    showtime.showtime_id,
    showtime.show_date,
    showtime.start_time;