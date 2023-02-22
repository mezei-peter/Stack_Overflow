package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionSortType;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;
    private final QuestionConverter questionConverter;

    public QuestionService(QuestionsDAO questionsDAO, QuestionConverter questionConverter) {
        this.questionsDAO = questionsDAO;
        this.questionConverter = questionConverter;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> allQuestions = questionsDAO.getAllQuestions();
        Map<Integer, Integer> answerCountsByQuestionIds = questionsDAO.getAnswerCountsByQuestionIds();
        return questionConverter.convertQuestionsToQuestionDTOs(allQuestions, answerCountsByQuestionIds);
    }

    public List<QuestionDTO> getSortedQuestions(QuestionSortType questionSortType) {
        List<Question> sortedQuestions = questionsDAO.getSortedQuestions(questionSortType);
        Map<Integer, Integer> answerCountsByQuestionIds = questionsDAO.getAnswerCountsByQuestionIds();
        return questionConverter.convertQuestionsToQuestionDTOs(sortedQuestions, answerCountsByQuestionIds);
    }

    public QuestionDTO getQuestionById(int id) {
        int answerCount = questionsDAO.getAnswerCountByQuestionId(id);
        return questionConverter.convertQuestionToQuestionDTO(questionsDAO.getQuestionByQuestionId(id), answerCount);
    }

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        return createdId;
    }
}
