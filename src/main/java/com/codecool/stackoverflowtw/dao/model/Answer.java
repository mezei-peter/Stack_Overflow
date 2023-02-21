package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Answer {

    private final int answerId;
    private final int questionId;
    private final int votes;
    private final String descritpion;
    private final int userId;
    private final Timestamp posted;

    public Answer(int answerId, int questionId, int votes, String descritpion, int userId,
                  Timestamp posted) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.votes = votes;
        this.descritpion = descritpion;
        this.userId = userId;
        this.posted = posted;
    }

    public Answer(int questionId, int votes, String descritpion, int userId, Timestamp posted) {
        this.answerId = 0;
        this.questionId = questionId;
        this.votes = votes;
        this.descritpion = descritpion;
        this.userId = userId;
        this.posted = posted;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getVotes() {
        return votes;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getPosted() {
        return posted;
    }
}
