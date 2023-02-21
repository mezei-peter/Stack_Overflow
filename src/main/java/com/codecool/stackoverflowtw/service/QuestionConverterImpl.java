package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.UserDao;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionConverterImpl implements QuestionConverter {
    private final UserDao userDao;

    public QuestionConverterImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<QuestionDTO> convertQuestionsToQuestionDTOs(Collection<Question> originalQuestions) {
        return originalQuestions.stream()
                .map(question -> {
                    return new QuestionDTO(
                            question.getQuestionId(),
                            question.getTitle(),
                            question.getDescription(),
                            question.getPosted(),
                            question.getUserId(),
                            userDao.getUsernameByUserId(question.getUserId()),
                            question.getVotes(),
                            69420
                    );
                })
                .collect(Collectors.toList());
    }
}
