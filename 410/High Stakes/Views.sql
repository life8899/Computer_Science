CREATE TEMP VIEW cinemark_location AS
SELECT name, city, state, zipcode
FROM theatre
WHERE name LIKE '%Cinemark%';


CREATE TEMP VIEW cinemark_showtime_by_movie AS
SELECT
    name, title, start_time, show_date, price
FROM 
    theatre, movie, showtime, ticket
WHERE
    theatre.name LIKE '%Cinemark%'          AND
    theatre.theatre_id=showtime.theatre_id  AND
    showtime.movie_id=movie.movie_id        AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    movie.title ASC,
    theatre.theatre_id ASC,
    showtime.show_date ASC,
    showtime.start_time ASC;


CREATE TEMP VIEW cinemark_showtime_by_theatre AS
SELECT
    name, title, start_time, show_date, price
FROM 
    theatre, movie, showtime, ticket
WHERE
    theatre.name LIKE '%Cinemark%'          AND
    theatre.theatre_id=showtime.theatre_id  AND
    showtime.movie_id=movie.movie_id        AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theatre.theatre_id ASC,
    movie.title ASC,
    showtime.show_date ASC,
    showtime.start_time ASC;