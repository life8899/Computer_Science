/*
	Creates a new theater with 2 showrooms.
*/
BEGIN TRANSACTION;
	INSERT INTO theater VALUES (DEFAULT, 'Marquee Cinemas: Southridge 12', 'Charleston', 'WV', '25309');
	INSERT INTO showroom VALUES (22, lastval(), 'IMAX', 55);
	INSERT INTO showroom VALUES (24, lastval(), 'LASER', 70);
END TRANSACTION;


/*
	Adds a new ticket to a showtime.
*/
BEGIN TRANSACTION;
	INSERT INTO ticket VALUES (lastval(), DEFAULT, 12.50, now(), now(), FALSE);
END TRANSACTION;


/*
	Add a new movie.
*/
BEGIN TRANSACTION;
	INSERT INTO	director VALUES (DEFAULT, "Christopher Nolan");
	INSERT INTO producer VALUES (DEFAULT, "Kevin De La Noy");
	INSERT INTO movie VALUES (DEFAULT, "The Dark Knight Rises", NULL, 2012, "Action", lastval(), lastval());
END TRANSACTION;


/*
	Updates the ticket price for IMAX movies.
*/
BEGIN TRANSACTION;
	UPDATE
		ticket, showtime, showroom
	SET
		ticket.price = 13.75;
	WHERE
		ticket.showtime_id=showtime.showtime_id		AND
		showtime.showroom_id=showroom.showroom_id	AND
		showroom.projector_type="IMAX";
END TRANSACTION;


/*
	Delete all showtimes for Avengers movies.
*/
BEGIN TRANSACTION;
	DELETE FROM
		ticket
	USING
		showtime, movie
	WHERE
		ticket.showtime_id=showtime.showtime_id	AND
		showtime.movie_id=movie.movie_id		AND
		movie.ticket LIKE '%Avengers%'

	DELETE FROM
		showtime
	USING
		movie
	WHERE
		showtime.movie_id=movie.movie_id	AND
		movie.ticket LIKE '%Avengers%'
END TRANSACTION;