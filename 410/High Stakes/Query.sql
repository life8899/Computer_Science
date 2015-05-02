-- 1) View all Movies playing with stars and their roles
SELECT DISTINCT
    title AS "Movie", name AS "Starring", character_name AS "As"
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
    title AS "Movie", director.name AS "Director", producer.name AS "Producer"
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


-- 4) View average ticket price for each theatre
SELECT
    theatre.name AS "Theatre", round(AVG(price), 2) AS "Average Ticket Price ($)"
FROM
    showtime, ticket, theatre
WHERE
    showtime.theatre_id=theatre.theatre_id  AND
    ticket.showtime_id=showtime.showtime_id
GROUP BY
    theatre.name;

-- 5) View all theatre locations
SELECT
    name, city, state, zipcode
FROM
    theatre;

-- 6) View all theatres that are playing the Avengers movie
SELECT DISTINCT
    name
FROM
    theatre, showtime, movie
WHERE
    movie.title LIKE '%Avengers%'       AND
    movie.movie_id=showtime.movie_id    AND
    showtime.theatre_id=theatre.theatre_id;

-- 7) View all showtimes for the Avengers
SELECT
    name, start_time, show_date, price
FROM
    theatre, movie, showtime, ticket
WHERE
    movie.title LIKE '%Avengers%'           AND
    showtime.movie_id=movie.movie_id        AND
    showtime.theatre_id=theatre.theatre_id  AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theatre.name,
    show_date,
    start_time;


-- 8) View all showtimes
SELECT
    name, title, start_time, show_date, price
FROM
    theatre, movie, showtime, ticket
WHERE
    showtime.movie_id=movie.movie_id        AND
    showtime.theatre_id=theatre.theatre_id  AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theatre.name,
    show_date,
    start_time;


-- 8) View all Steven Spielberg Films
SELECT DISTINCT
    title AS "Spielberg Film"
FROM
    movie, director, producer
WHERE
    movie.director_id=director.director_id  AND
    director.name='Steven Spielberg'        OR
    movie.producer_id=producer.producer_id  AND
    producer.name='Steven Spielberg';


-- 9) View all theatres and their projector types
SELECT
    name AS "Theatre", projector_type AS "Projector", COUNT(projector_type) AS "Count"
FROM
    theatre, showroom
WHERE
    showroom.theatre_id=theatre.theatre_id
GROUP BY
    name,
    projector_type
ORDER BY
    name,
    projector_type;


-- 10) View all tickets sold per theatre
SELECT
    name AS "Theatre", COUNT(ticket) AS "Ticket Count", COUNT(CASE WHEN ticket.wasUsed = 't' THEN 1 ELSE NULL END) AS "Tickets Used"
FROM
    ticket, showtime, theatre
WHERE
    ticket.showtime_id=showtime.showtime_id AND
    showtime.theatre_id=theatre.theatre_id
GROUP BY
    name;