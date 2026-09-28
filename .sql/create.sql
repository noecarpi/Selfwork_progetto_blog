
CREATE TABLE authors (
    id BIGINT auto_increment PRIMARY KEY,
    firstname VARCHAR(100),
    lastname VARCHAR(100),
    email VARCHAR(100)
);
CREATE TABLE posts(
    id BIGINT auto_increment PRIMARY KEY,
    title VARCHAR(100) not null,
    body VARCHAR(255) not null,
    publish_date CHAR(10),
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
CREATE TABLE comments(
    id BIGINT auto_increment PRIMARY KEY,
    email VARCHAR(100) not null,
    body VARCHAR(255) not null,
    date CHAR(10),
    post_id BIGINT,
    FOREIGN KEY(post_id) REFERENCES posts(id)
);