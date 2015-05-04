/*
	SQL script to insert sample test data into the Theater database.
*/


/*
	Insert Directors into DIRECTOR table.
	(director_id, name)
*/
INSERT INTO director VALUES (DEFAULT, 'Joss Whedon');
INSERT INTO director VALUES (DEFAULT, 'Zack Snyder');
INSERT INTO director VALUES (DEFAULT, 'Colin Trevorrow');
INSERT INTO director VALUES (DEFAULT, 'Steven Spielberg');


/*
	Insert Producers into PRODUCER table.
	(producer_id, name)
*/
INSERT INTO producer VALUES (DEFAULT, 'Victoria Alonso');
INSERT INTO producer VALUES (DEFAULT, 'Wesley Coller');	
INSERT INTO producer VALUES (DEFAULT, 'Steven Spielberg');
INSERT INTO producer VALUES (DEFAULT, 'Kathleen Kennedy');


/*
	Insert movies into MOVIE table.
	(movie_id, title, episode_title, year, genre, director_id, producer_id)
*/
INSERT INTO movie VALUES (DEFAULT, 'Avengers: Age of Ultron', NULL, 2015, 'Action', 1, 1);
INSERT INTO movie VALUES (DEFAULT, 'Batman v Superman: Dawn of Justice', NULL, 2016, 'Action', 2, 2);
INSERT INTO movie VALUES (DEFAULT, 'Jurassic World', NULL, 2015, 'Action', 3, 3);
INSERT INTO movie VALUES (DEFAULT, 'The Lost World: Jurassic Park', NULL, 1997, 'Action', 4, 4);


/*
	Insert actor into ACTOR table.
	(actor_id, name, gender)
*/
INSERT INTO actor VALUES (DEFAULT, 'Robert Downey Jr.', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Chris Hemsworth', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Mark Ruffalo', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Chris Evans', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Scarlett Johansson', 'F');
INSERT INTO actor VALUES (DEFAULT, 'Jeremy Renner', 'M');

INSERT INTO actor VALUES (DEFAULT, 'Gal Gadot', 'F');
INSERT INTO actor VALUES (DEFAULT, 'Jason Mamoa', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Henry Cavill', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Ben Affleck', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Lois Lane', 'F');
INSERT INTO actor VALUES (DEFAULT, 'Ezra Miller', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Jesse Eisenburg', 'M');

INSERT INTO actor VALUES (DEFAULT, 'Vincent DOnofrio', 'M');

INSERT INTO actor VALUES (DEFAULT, 'Jeff Goldblum', 'M');
INSERT INTO actor VALUES (DEFAULT, 'Julianne Moore', 'F');


/*
	Insert role into ROLE table.
	(actor_id, movie_id, character_name)
*/
INSERT INTO role VALUES (1, 1, 'Tony Stark / Iron Man');
INSERT INTO role VALUES (2, 1, 'Thor');
INSERT INTO role VALUES (3, 1, 'Bruce Banner / Hulk');
INSERT INTO role VALUES (4, 1, 'Steve Roges / Captain America');
INSERT INTO role VALUES (5, 1, 'Natasha Romanoff / Black Widow');
INSERT INTO role VALUES (6, 1, 'Clint Barton / Hawkeye');

INSERT INTO role VALUES (7, 2, 'Diana Prince / Wonder Woman');
INSERT INTO role VALUES (8, 2, 'Arthur Curry / Aquaman');
INSERT INTO role VALUES (9, 2, 'Clark Kent / Superman');
INSERT INTO role VALUES (10, 2, 'Bruce Wayne / Batman');
INSERT INTO role VALUES (11, 2, 'Lois Lane');
INSERT INTO role VALUES (12, 2, 'Barry Allen / The Flash');
INSERT INTO role VALUES (13, 2, 'Lex Luthor');

INSERT INTO role VALUES (14, 3, 'Vic Hoskins');

INSERT INTO role VALUES (15, 4, 'Ian Malcom');
INSERT INTO role VALUES (16, 4, 'Sarah Harding');


/*
	Insert theater into THEATER table.
	(theater_id, name, city, state, zip-code)
*/
INSERT INTO theater VALUES (DEFAULT, 'Cinemark: Huntington Mall', 'Barboursville', 'WV', '25504');
INSERT INTO theater VALUES (DEFAULT, 'Cinemark: Cinema 10', 'Ashland', 'KY', '41101');
INSERT INTO theater VALUES (DEFAULT, 'Marquee Cinemas: Pullman Square', 'Huntington', 'WV', '25701');


/*
	Insert showroom into SHOWROOM table.
	(showroom_id, theater_id, projector_type, capacity)
*/
INSERT INTO showroom VALUES (1, 1, 'LASER', 75);
INSERT INTO showroom VALUES (2, 1, 'LASER', 75);
INSERT INTO showroom VALUES (3, 1, 'LASER', 75);
INSERT INTO showroom VALUES (4, 1, 'IMAX', 100);
INSERT INTO showroom VALUES (5, 1, 'DIGITAL', 50);

INSERT INTO showroom VALUES (6, 2, 'IMAX', 125);
INSERT INTO showroom VALUES (7, 2, 'DIGITAL', 75);
INSERT INTO showroom VALUES (8, 2, 'DIGITAL', 75);
INSERT INTO showroom VALUES (9, 2, 'DIGITAL', 75);
INSERT INTO showroom VALUES (10, 2, 'DIGITAL', 75);
INSERT INTO showroom VALUES (11, 2, 'DIGITAL', 75);

INSERT INTO showroom VALUES (12, 3, 'LASER', 50);
INSERT INTO showroom VALUES (13, 3, 'LASER', 50);
INSERT INTO showroom VALUES (14, 3, 'IMAX', 60);
INSERT INTO showroom VALUES (15, 3, 'FILM ROLL', 25);


/*
	Insert show-time into SHOWTIME table.
	(showtime_id, theater_id, showroom_id, movie_id, start_time, end_time, show_date)
*/
INSERT INTO showtime VALUES (DEFAULT, 1, 1, 1, '9:40pm', '12:01am', '5/1/2015');
INSERT INTO showtime VALUES (DEFAULT, 1, 5, 1, '10:00pm', '12:21am', '5/1/2015');
INSERT INTO showtime VALUES (DEFAULT, 1, 4, 1, '8:00pm', '10:21pm', '5/1/2015');

INSERT INTO showtime VALUES (DEFAULT, 2, 6, 1, '7:00pm', '9:21pm', '5/1/2015');
INSERT INTO showtime VALUES (DEFAULT, 2, 11, 1, '8:10pm', '10:31pm', '5/1/2015');

INSERT INTO showtime VALUES (DEFAULT, 3, 3, 1, '7:10pm', '9:31pm', '5/1/2015');	

INSERT INTO showtime VALUES (DEFAULT, 1, 4, 2, '8:00pm', '10:00pm', '5/1/2016');
INSERT INTO showtime VALUES (DEFAULT, 2, 6, 2, '8:00pm', '10:00pm', '5/1/2016');
INSERT INTO showtime VALUES (DEFAULT, 3, 14, 2, '8:00pm', '10:00pm', '5/1/2016');


/*
	Insert ticket into TICKET table.
	(showtime_id, seat_number, price, time_bought, date_bought, wasUsed)
*/
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, NULL, NULL, FALSE);
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, NULL, NULL, FALSE);
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, NULL, NULL, FALSE);
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, '6:25pm', '4/30/2015', TRUE);

INSERT INTO ticket VALUES (2, DEFAULT, 7.50, NULL, NULl, FALSE);

INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '6:25pm', '4/30/2015', TRUE);	
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '6:25pm', '4/30/2015', TRUE); 
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '6:25pm', '4/30/2015', TRUE);	
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '11:20am', '5/1/2015', FALSE);
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '11:21am', '5/1/2015', FALSE);
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '12:00pm', '5/1/2015', FALSE);

INSERT INTO ticket VALUES (4, DEFAULT, 7.50, '6:59pm', '5/1/2015', TRUE);

INSERT INTO ticket VALUES (7, DEFAULT, 12.50, NULL, NULL, FALSE);
INSERT INTO ticket VALUES (8, DEFAULT, 12.50, NULL, NULL, FALSE);
INSERT INTO ticket VALUES (9, DEFAULT, 12.50, NULL, NULL, FALSE);