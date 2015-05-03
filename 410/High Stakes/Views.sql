/*
    This view automatically stoes the locations of all Cinemark theatres.
*/
CREATE TEMP VIEW cinemark_location AS
SELECT name, city, state, zipcode
FROM theatre
WHERE name LIKE '%Cinemark%';


/*
    The view stores all showtimes for Cinemark theatres including the movie title, show date, start time, and ticket price, and is ordered based on movie title.
*/
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


/*
    The view is identical to the previous with the exception that tuples are ordered by theatre name.
*/
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