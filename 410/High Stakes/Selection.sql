SELECT title, name AS starring, character_name AS character, theatre_name AS showing_at
FROM movie, actor, theatre
WHERE
	movie.movie_id=actor.movie_id AND
	theatre_id=1;