package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Question {
    private final int questionId;

    private final int votes;
    private final String title;
    private final String description;
    private final int userId;
    private final Timestamp posted;
    public Question(int questionId, int votes, String title, String description, int userId, Timestamp posted) {
        this.questionId = questionId;
        this.votes = votes;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.posted = posted;
    }

    public Question(int votes, String title, String description, int userId) {
        questionId = 0;
        this.votes = votes;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.posted = null;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getVotes() {
        return votes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getPosted() {
        return posted;
    }
}
