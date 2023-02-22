package com.codecool.stackoverflowtw.service.answerService;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDao;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class AnswerService {

    private final AnswersDao answersDao;
    private final AnswerConverter answerConverter;


    public AnswerService(AnswersDao answersDao, AnswerConverter answerConverter) {
        this.answersDao = answersDao;
        this.answerConverter = answerConverter;
    }

    public Collection<AnswerDTO> getAllAnswers(int answerId) {
        Collection<Answer> answersFromDatabase = answersDao.getAnswersByQuestionId(answerId);
        return answerConverter.convertAnswerToAnswerDTO(answersFromDatabase);
    }
}
