BEGIN;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role; 
 

CREATE TABLE role
(
    Id SMALLSERIAL PRIMARY KEY,
    Name VARCHAR(20) NOT NULL

);

INSERT INTO role(Id, Name) VALUES 
    (2, 'Player'),
    (3, 'Verified'), 
    (1, 'Admin');

CREATE TABLE users(
    Id BIGSERIAL PRIMARY KEY,
    Login VARCHAR(80) NOT NULL,
    Password VARCHAR(60) NOT NULL,
    Email VARCHAR(100) NULL
);

COMMIT;