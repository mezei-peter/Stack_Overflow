CREATE TABLE IF NOT EXISTS Questions
(
    question_id SERIAL PRIMARY KEY,
    title       varchar(100),
    description TEXT,
    user_id     integer,
    posted      timestamptz
);

CREATE TABLE IF NOT EXISTS Answers
(
    answer_id   SERIAL PRIMARY KEY,
    question_id integer,
    votes       integer,
    description TEXT,
    user_id     integer,
    posted      timestamptz
);

CREATE TABLE IF NOT EXISTS Users
(
    user_id       SERIAL PRIMARY KEY,
    is_super_user boolean,
    name          TEXT,
    password      TEXT,
    registered    timestamptz
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
