package com.codecool.stackoverflowtw.dao.answer;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.Collection;

public interface AnswersDao {
    Answer getAnswerByAnswerId(int answerId);
    Collection<Answer> getAnswersByQuestionId(int questionId);
    int getNumberOfAnswersByUserId(int userId);
    void createNewAnswer(Answer answer);
    boolean deleteAnswerByAnswerId(int answerId);
    boolean deleteAnswersByQuestionId(int questionId);
    boolean updateAnswerDescription(int answerId, String description);
}
