USE kasutajaliidesedApi;

-- Drop tables

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Joke;
DROP TABLE IF EXISTS AuthenticatedUser;
DROP TABLE IF EXISTS Vote;
DROP TABLE IF EXISTS Comment;


SET foreign_key_checks = 1;

-- Create tables

CREATE TABLE User (
  id       BIGINT    AUTO_INCREMENT PRIMARY KEY,
  code     VARCHAR(255) UNIQUE NOT NULL,
  name     VARCHAR(255)        NOT NULL,
  password VARCHAR(255)        NOT NULL,
  role     VARCHAR(255)        NOT NULL,
  created  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Joke (
  id        BIGINT    AUTO_INCREMENT PRIMARY KEY,
  joke      TEXT NOT NULL,
  added     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user      BIGINT,
  upvotes   BIGINT    DEFAULT 0,
  downvotes BIGINT    DEFAULT 0,

  FOREIGN KEY (user)
  REFERENCES User (id)
    ON DELETE RESTRICT
);


CREATE TABLE AuthenticatedUser (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT              NOT NULL,
  token   VARCHAR(255) UNIQUE NOT NULL,

  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE RESTRICT
);

CREATE TABLE Vote (
  id       BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id  BIGINT NOT NULL,
  joke_id  BIGINT NOT NULL,
  isUpvote BOOLEAN,

  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE CASCADE,

  FOREIGN KEY (joke_id)
  REFERENCES Joke (id)
    ON DELETE CASCADE,

  UNIQUE KEY (user_id, joke_id, id)

);

CREATE TABLE Subject (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT              NOT NULL,
  name    VARCHAR(255) UNIQUE NOT NULL,

  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE RESTRICT
);


CREATE TABLE Homework (
  id         BIGINT    AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  user       BIGINT       NOT NULL,
  added      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  subject_id BIGINT       NOT NULL,

  FOREIGN KEY (user)
  REFERENCES User (id)
    ON DELETE CASCADE,

  FOREIGN KEY (subject_id)
  REFERENCES Subject (id)
    ON DELETE CASCADE
);


CREATE TABLE Grade (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id     BIGINT NOT NULL,
  grade       BIGINT,
  homework_id BIGINT NOT NULL,


  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE RESTRICT,

  FOREIGN KEY (homework_id)
  REFERENCES Homework (id)
    ON DELETE RESTRICT
);




