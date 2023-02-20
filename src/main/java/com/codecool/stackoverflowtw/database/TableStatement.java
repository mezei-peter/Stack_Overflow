package com.codecool.stackoverflowtw.database;

import java.util.List;

public abstract class TableStatement {
    private static final String QUESTIONS_CREATE = """
            CREATE TABLE [IF NOT EXISTS] Questions (
                question_id SERIAL PRIMARY KEY,
                title varchar(100),
                description TEXT,
                user_id integer,
                posted timestamptz);
            """;
    private static final String ANSWERS_CREATE = """
            CREATE TABLE [IF NOT EXISTS] Answers (
                answer_id SERIAL PRIMARY KEY,
                question_id integer,
                votes integer,
                description TEXT,
                user_id integer,
                posted timestamptz);
            """;
    private static final String USERS_CREATE = """
            CREATE TABLE [IF NOT EXISTS] Users (
                user_id SERIAL PRIMARY KEY,
                is_super_user boolean,
                name TEXT,
                password TEXT,
                registered timestamptz);
            """;
    static final List<String> STATEMENTS = List.of(QUESTIONS_CREATE, ANSWERS_CREATE, USERS_CREATE);
}
