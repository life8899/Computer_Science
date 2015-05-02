INSERT INTO director VALUES (DEFAULT, 'Joss Whedon');		-- 1
INSERT INTO director VALUES (DEFAULT, 'Zack Snyder');		-- 2
INSERT INTO director VALUES (DEFAULT, 'Colin Trevorrow');	-- 3
INSERT INTO director VALUES (DEFAULT, 'Steven Spielberg');	-- 4

INSERT INTO producer VALUES (DEFAULT, 'Victoria Alonso');	-- 1
INSERT INTO producer VALUES (DEFAULT, 'Wesley Coller');		-- 2
INSERT INTO producer VALUES (DEFAULT, 'Steven Spielberg');	-- 3
INSERT INTO producer VALUES (DEFAULT, 'Kathleen Kennedy');	-- 4

INSERT INTO movie VALUES (DEFAULT, 'Avengers: Age of Ultron', NULL, 2015, 'Action', 1, 1);				-- 1
INSERT INTO movie VALUES (DEFAULT, 'Batman v Superman: Dawn of Justice', NULL, 2016, 'Action', 2, 2);	-- 2
INSERT INTO movie VALUES (DEFAULT, 'Jurassic World', NULL, 2015, 'Action', 3, 3);						-- 3
INSERT INTO movie VALUES (DEFAULT, 'The Lost World: Jurassic Park', NULL, 1997, 'Action', 4, 4);		-- 4


INSERT INTO actor VALUES (DEFAULT, 'Robert Downey Jr.', 'M'); 	-- 1
INSERT INTO actor VALUES (DEFAULT, 'Chris Hemsworth', 'M'); 	-- 2
INSERT INTO actor VALUES (DEFAULT, 'Mark Ruffalo', 'M'); 		-- 3
INSERT INTO actor VALUES (DEFAULT, 'Chris Evans', 'M'); 		-- 4
INSERT INTO actor VALUES (DEFAULT, 'Scarlett Johansson', 'F'); 	-- 5
INSERT INTO actor VALUES (DEFAULT, 'Jeremy Renner', 'M'); 		-- 6

INSERT INTO actor VALUES (DEFAULT, 'Gal Gadot', 'F'); 			-- 7
INSERT INTO actor VALUES (DEFAULT, 'Jason Mamoa', 'M'); 		-- 8
INSERT INTO actor VALUES (DEFAULT, 'Henry Cavill', 'M'); 		-- 9
INSERT INTO actor VALUES (DEFAULT, 'Ben Affleck', 'M'); 		-- 10
INSERT INTO actor VALUES (DEFAULT, 'Lois Lane', 'F'); 			-- 11
INSERT INTO actor VALUES (DEFAULT, 'Ezra Miller', 'M'); 		-- 12
INSERT INTO actor VALUES (DEFAULT, 'Jesse Eisenburg', 'M'); 	-- 13

INSERT INTO actor VALUES (DEFAULT, 'Vincent DOnofrio', 'M');	-- 14

INSERT INTO actor VALUES (DEFAULT, 'Jeff Goldblum', 'M');		-- 15
INSERT INTO actor VALUES (DEFAULT, 'Julianne Moore', 'F');		-- 16


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


INSERT INTO theatre VALUES (DEFAULT, 'Cinemark: Huntington Mall', 'Barboursville', 'WV', '25504');		-- 1
INSERT INTO theatre VALUES (DEFAULT, 'Cinemark: Cinema 10', 'Ashland', 'KY', '41101');					-- 2
INSERT INTO theatre VALUES (DEFAULT, 'Marquee Cinemas: Pullman Square', 'Huntington', 'WV', '25701');	-- 3


INSERT INTO showroom VALUES (1, 1, 'LASER', 75);		-- 1
INSERT INTO showroom VALUES (2, 1, 'LASER', 75);		-- 2
INSERT INTO showroom VALUES (3, 1, 'LASER', 75);		-- 3
INSERT INTO showroom VALUES (4, 1, 'IMAX', 100);		-- 4
INSERT INTO showroom VALUES (5, 1, 'DIGITAL', 50);	-- 5

INSERT INTO showroom VALUES (6, 2, 'IMAX', 125);		-- 6
INSERT INTO showroom VALUES (7, 2, 'DIGITAL', 75);	-- 7
INSERT INTO showroom VALUES (8, 2, 'DIGITAL', 75);	-- 8
INSERT INTO showroom VALUES (9, 2, 'DIGITAL', 75);	-- 9
INSERT INTO showroom VALUES (10, 2, 'DIGITAL', 75);	-- 10
INSERT INTO showroom VALUES (11, 2, 'DIGITAL', 75);	-- 11

INSERT INTO showroom VALUES (12, 3, 'LASER', 50);		-- 12
INSERT INTO showroom VALUES (13, 3, 'LASER', 50);		-- 13
INSERT INTO showroom VALUES (14, 3, 'IMAX', 60);		-- 14
INSERT INTO showroom VALUES (15, 3, 'FILM ROLL', 25);	-- 15


INSERT INTO showtime VALUES (DEFAULT, 1, 1, 1, '9:40pm', '12:01am', '5/1/2015');	-- 1
INSERT INTO showtime VALUES (DEFAULT, 1, 5, 1, '10:00pm', '12:21am', '5/1/2015');	-- 2
INSERT INTO showtime VALUES (DEFAULT, 1, 4, 1, '8:00pm', '10:21pm', '5/1/2015');	-- 3

INSERT INTO showtime VALUES (DEFAULT, 2, 6, 1, '7:00pm', '9:21pm', '5/1/2015');		-- 4
INSERT INTO showtime VALUES (DEFAULT, 2, 11, 1, '8:10pm', '10:31pm', '5/1/2015');	-- 5

INSERT INTO showtime VALUES (DEFAULT, 3, 3, 1, '7:10pm', '9:31pm', '5/1/2015');		-- 6

INSERT INTO showtime VALUES (DEFAULT, 1, 4, 2, '8:00pm', '10:00pm', '5/1/2016');	-- 7
INSERT INTO showtime VALUES (DEFAULT, 2, 6, 2, '8:00pm', '10:00pm', '5/1/2016');	-- 8
INSERT INTO showtime VALUES (DEFAULT, 3, 14, 2, '8:00pm', '10:00pm', '5/1/2016');	-- 9


INSERT INTO ticket VALUES (1, DEFAULT, 7.50, NULL, NULL, FALSE);				-- 1
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, NULL, NULL, FALSE);				-- 2
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, NULL, NULL, FALSE);				-- 3
INSERT INTO ticket VALUES (1, DEFAULT, 7.50, '6:25pm', '4/30/2015', TRUE);		-- 4

INSERT INTO ticket VALUES (2, DEFAULT, 7.50, NULL, NULl, FALSE);				-- 5

INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '6:25pm', '4/30/2015', TRUE);		-- 6
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '6:25pm', '4/30/2015', TRUE); 	-- 7
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '6:25pm', '4/30/2015', TRUE);		-- 8
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '11:20am', '5/1/2015', FALSE);	-- 9
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '11:21am', '5/1/2015', FALSE);	-- 10
INSERT INTO ticket VALUES (3, DEFAULT, 10.00, '12:00pm', '5/1/2015', FALSE);	-- 11

INSERT INTO ticket VALUES (4, DEFAULT, 7.50, '6:59pm', '5/1/2015', TRUE);		-- 12

INSERT INTO ticket VALUES (7, DEFAULT, 12.50, NULL, NULL, FALSE);				-- 13
INSERT INTO ticket VALUES (8, DEFAULT, 12.50, NULL, NULL, FALSE);				-- 14
INSERT INTO ticket VALUES (9, DEFAULT, 12.50, NULL, NULL, FALSE);				-- 15