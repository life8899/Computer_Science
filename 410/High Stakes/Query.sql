-- 1) View all Movies playing with stars and their roles
SELECT DISTINCT
    title           AS "Movie",
    name            AS "Starring",
    character_name  AS "As"
FROM
    showtime, movie, actor, role
WHERE
    showtime.movie_id=movie.movie_id    AND
    role.movie_id=movie.movie_id        AND
    role.actor_id=actor.actor_id
ORDER BY
    title,
    name;


-- 2) View producers and directors of films playing
SELECT DISTINCT
    title           AS "Movie",
    director.name   AS "Director",
    producer.name   AS "Producer"
FROM
    showtime, movie, director, producer
WHERE
    showtime.movie_id=movie.movie_id        AND
    movie.director_id=director.director_id  AND
    movie.producer_id=producer.producer_id
ORDER BY
    title;


-- 3) View all movies released in 2015
SELECT
    title AS "Title"
FROM
    movie
WHERE
    year=2015;


-- 4) View average ticket price for each theater
SELECT
    theatre.name            AS "Theater",
    round(AVG(price), 2)    AS "Average Ticket Price ($)"
FROM
    showtime, ticket, theatre
WHERE
    showtime.theatre_id=theatre.theatre_id  AND
    ticket.showtime_id=showtime.showtime_id
GROUP BY
    theatre.name;

-- 5) View all theater locations
SELECT
    name,
    city,
    state,
    zipcode
FROM
    theatre;

-- 6) View all theaters that are playing the Avengers movie
SELECT DISTINCT
    name
FROM
    theatre,
    showtime,
    movie
WHERE
    movie.title LIKE '%Avengers%'           AND
    movie.movie_id=showtime.movie_id        AND
    showtime.theatre_id=theatre.theatre_id;

-- 7) View all show-times for the Avengers
SELECT
    name,
    start_time,
    show_date,
    price
FROM
    theatre,
    movie,
    showtime,
    ticket
WHERE
    movie.title LIKE '%Avengers%'               AND
    showtime.movie_id=movie.movie_id            AND
    showtime.theatre_id=theatre.theatre_id      AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theatre.name,
    show_date,
    start_time;


-- 8) View all show-times
SELECT
    name,
    title,
    start_time,
    show_date,
    price
FROM
    theatre,
    movie,
    showtime,
    ticket
WHERE
    showtime.movie_id=movie.movie_id            AND
    showtime.theatre_id=theatre.theatre_id      AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theatre.name,
    showtime.show_date,
    showtime.start_time;


-- 9) View all Steven Spielberg Films
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


-- 10) View all theaters and their projector types
SELECT
    name                    AS "Theater",
    projector_type          AS "Projector",
    COUNT(projector_type)   AS "Count"
FROM
    theatre,
    showroom
WHERE
    showroom.theatre_id=theatre.theatre_id
GROUP BY
    name,
    projector_type
ORDER BY
    name,
    projector_type;


-- 11) View all tickets sold per theater
SELECT
    name                                                        AS "Theater",
    COUNT(ticket)                                               AS "Tickets Sold",
    SUM(ticket.price)                                           AS "Sales ($)",
    COUNT(CASE WHEN ticket.wasUsed='t' THEN 1 ELSE NULL END)    AS "Ticket Redeemed"
FROM
    ticket,
    showtime,
    theatre
WHERE
    ticket.showtime_id=showtime.showtime_id AND
    showtime.theatre_id=theatre.theatre_id
GROUP BY
    name;


-- 12) View all tickets sold per show-time
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