create database MakerSharks;

use database MakerSharks;

CREATE TABLE Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, email, password)
VALUES ('MakerSharks', 'MakerSharks@gmail.com', 'MakerSharks'),
       ('User1', '', 'password1'),
       ('User2', 'user2@example.com', 'short'),
       ('User3', 'invalid-email', 'password3');

      
      