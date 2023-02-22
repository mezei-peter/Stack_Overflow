CREATE TABLE IF NOT EXISTS Questions
(
    question_id SERIAL PRIMARY KEY,
    votes       integer,
    title       varchar(100),
    description TEXT,
    user_id     integer,
    posted      timestamptz default(current_timestamp)
);

CREATE TABLE IF NOT EXISTS Answers
(
    answer_id   SERIAL PRIMARY KEY,
    question_id integer,
    votes       integer,
    description TEXT,
    user_id     integer,
    posted      timestamptz default(current_timestamp)
);

CREATE TABLE IF NOT EXISTS Users
(
    user_id       SERIAL PRIMARY KEY,
    is_super_user boolean,
    name          TEXT,
    password      TEXT,
    registered    timestamptz default(current_timestamp)
);

ALTER TABLE Questions
    DROP CONSTRAINT IF EXISTS fk_user_to_questions;
ALTER TABLE Questions
    ADD CONSTRAINT fk_user_to_questions FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Answers
    DROP CONSTRAINT IF EXISTS fk_user_to_answers;
ALTER TABLE Answers
    ADD CONSTRAINT fk_user_to_answers FOREIGN KEY (user_id) REFERENCES Users (user_id);
ALTER TABLE Answers
    DROP CONSTRAINT IF EXISTS fk_question_to_answers;
ALTER TABLE Answers
    ADD CONSTRAINT fk_question_to_answers FOREIGN KEY (question_id) REFERENCES Questions (question_id);

INSERT INTO Users (is_super_user, name, password)
VALUES (FALSE, 'testName', 'testName');

INSERT INTO Questions (title, votes, description, user_id)
VALUES ('What is the meaning of life?', 2, 'Please guys help', 1);
INSERT INTO Questions (title, votes, description, user_id)
VALUES ('A_TEST', 2, 'Please guys help', 1);
INSERT INTO Questions (title, votes, description, user_id)
VALUES ('B_TEST', 2, 'Please guys help', 1);
INSERT INTO Questions (title, votes, description, user_id)
VALUES ('C_TEST', 2, 'Please guys help', 1);

INSERT INTO Answers (question_id, votes, description, user_id)
VALUES (2, 2, 'Yes', 1);
INSERT INTO Answers (question_id, votes, description, user_id)
VALUES (2, 2, 'Yes', 1);
INSERT INTO Answers (question_id, votes, description, user_id)
VALUES (3, 3, 'Yes', 1);