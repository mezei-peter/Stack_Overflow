package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;
import java.util.Map;

public interface QuestionsDAO {
    void sayHi();

    List<Question> getAllQuestions();

    Question getQuestionByQuestionId(int questionId);
    int postNewQuestion(Question question);
    boolean deleteQuestionByQuestionId(int questionId);
    List<Question> getSortedQuestions(QuestionSortType sortBy);
    int getNumberOfQuestionsByUserId(int userId);

    Map<Integer, Integer> getAnswerCountsByQuestionIds();

    int getAnswerCountByQuestionId(int id);

    boolean isOwnerOfQuestionByIds(int questionId, int userId);
}
