Insert INTO authors (firstname, lastname, email)
value ("Linda", "Belcher", "singinglinda@test.it");

Insert INTO authors (firstname, lastname, email)
value ("Jimmy", "Pesto", "mobkingjimmy@test.it");


INSERT INTO posts (title, body, publish_date, author_id)
SELECT "Lalalala lala la", "I'm singing this song lalalala", null, id
FROM authors
where firstname = "Linda"
and lastname = "Belcher";

INSERT INTO posts (title, body, publish_date, author_id)
SELECT "Ratatatata", "I love mob movies", null, id
FROM authors
where firstname = "Jimmy"
and lastname = "Pesto";


INSERT INTO comments(email, body, date, post_id)
value ("singinglinda@test.it", "Come to our piano bar", "20250608",1);

INSERT INTO comments(email, body, date, post_id)
value ("singinglinda@test.it", "i like pesto coladas", "20250608",2);