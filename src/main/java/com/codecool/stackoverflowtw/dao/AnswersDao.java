package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.Collection;

public interface AnswersDao {
    Answer getAnswerByAnswerId(int answerId);
    Collection<Answer> getAnswersByQuestionId(int questionId);
    int getNumberOfAnswersByUserId(int userId);
}
