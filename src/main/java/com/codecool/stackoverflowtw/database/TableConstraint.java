package com.codecool.stackoverflowtw.database;

public class TableConstraint {
    static final String QUESTIONS_CONSTRAINT = """
            ALTER TABLE Questions
            DROP CONSTRAINT IF EXISTS fk_user_to_questions;
            ALTER TABLE Questions
            ADD CONSTRAINT fk_user_to_questions FOREIGN KEY(user_id) REFERENCES Users(user_id);
            """;
    static final String ANSWERS_CONSTRAINT = """
            ALTER TABLE Answers
            DROP CONSTRAINT IF EXISTS fk_user_to_answers;
            ALTER TABLE Answers
            ADD CONSTRAINT fk_user_to_answers FOREIGN KEY(user_id) REFERENCES Users(user_id);
            ALTER TABLE Answers
            DROP CONSTRAINT IF EXISTS fk_question_to_answers;
            ALTER TABLE Answers
            ADD CONSTRAINT fk_question_to_answers FOREIGN KEY(question_id) REFERENCES Questions(question_id);
            """;
}
