/*
    This view automatically stores the locations of all Cinemark theaters.
*/
CREATE TEMP VIEW cinemark_location AS
SELECT name, city, state, zipcode
FROM theater
WHERE name LIKE '%Cinemark%';


/*
    The view stores all show-times for Cinemark theaters including the movie title, show date, start time, and ticket price, and is ordered based on movie title.
*/
CREATE TEMP VIEW cinemark_showtime_by_movie AS
SELECT
    name, title, start_time, show_date, price
FROM 
    theater, movie, showtime, ticket
WHERE
    theater.name LIKE '%Cinemark%'          AND
    theater.theater_id=showtime.theater_id  AND
    showtime.movie_id=movie.movie_id        AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    movie.title ASC,
    theater.theater_id ASC,
    showtime.show_date ASC,
    showtime.start_time ASC;


/*
    The view is identical to the previous with the exception that tuples are ordered by theater name.
*/
CREATE TEMP VIEW cinemark_showtime_by_theater AS
SELECT
    name, title, start_time, show_date, price
FROM 
    theater, movie, showtime, ticket
WHERE
    theater.name LIKE '%Cinemark%'          AND
    theater.theater_id=showtime.theater_id  AND
    showtime.movie_id=movie.movie_id        AND
    ticket.showtime_id=showtime.showtime_id
ORDER BY
    theater.theater_id ASC,
    movie.title ASC,
    showtime.show_date ASC,
    showtime.start_time ASC;