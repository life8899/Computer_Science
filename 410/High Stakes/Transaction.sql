-- Add new theatre
BEGIN TRANSACTION;
	INSERT INTO theatre VALUES (DEFAULT, 'Marquee Cinemas: Southridge 12', 'Charleston', 'WV', '25309');
	INSERT INTO showroom VALUES (22, lastval(), 'IMAX', 55);
END TRANSACTION;