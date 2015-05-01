CREATE TEMP VIEW cinemark_locations AS
SELECT city, state, zipcode
FROM theatre
WHERE theatre.theatre_name='Cinemark';

CREATE TEMP VIEW cinemark_showtimes AS
SELECT  title, showtime_id AS showing, show_date AS date, start_time, price
FROM    theatre, movie, showtime, ticket
WHERE   theatre_name='Cinemark' AND
        showtime.theatre_id=theatre.theatre_id AND
        movie.movie_id=showtime.movie_id AND
        showtime.showtime_id=ticket.showtime;