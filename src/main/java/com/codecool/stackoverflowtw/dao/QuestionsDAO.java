package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();

    List<Question> getAllQuestions();

    Question getQuestionByQuestionId(int questionId);
    boolean postNewQuestion(Question question);
    boolean deleteQuestionByQuestionId(int questionId);
    List<Question> getSortedQuestions(QuestionSortType sortBy);
    int getNumberOfQuestionsByUserId(int userId);
}
