package com.codecool.stackoverflowtw.service.answerService;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.UserDao;
import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.Collection;

public class AnswerConverterImpl implements AnswerConverter {

    private final QuestionsDAO questionsDAO;
    private final UserDao userDao;

    public AnswerConverterImpl(QuestionsDAO questionsDAO, UserDao userDao) {
        this.questionsDAO = questionsDAO;
        this.userDao = userDao;
    }

    @Override
    public Collection<AnswerDTO> convertAnswerToAnswerDTO(Collection<Answer> answersFromDatabase) {
        return answersFromDatabase.stream()
                .map(answer -> {
                    int answerPosterId = 1;
                    String username = userDao.getUsernameByUserId(answerPosterId);
                    return new AnswerDTO(answer.getAnswerId(), answer.getQuestionId(),
                            answer.getVotes(), answer.getDescription(), answer.getUserId(),
                            answerPosterId, answer.getPosted(), username);
                        }
                ).toList();
    }

    @Override
    public Answer convertNewAnswerDTOtoAnswer(NewAnswerDTO newAnswers) {
        return new Answer(newAnswers.questionId(), newAnswers.description(),
                newAnswers.answerPosterId());
    }
}
