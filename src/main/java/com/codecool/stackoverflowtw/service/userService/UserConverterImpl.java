package com.codecool.stackoverflowtw.service.userService;

import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.answer.AnswersDao;
import com.codecool.stackoverflowtw.dao.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverterImpl implements UserConverter {
    QuestionsDAO questionsDAO;
    AnswersDao answerDao;

    public UserConverterImpl(QuestionsDAO questionsDAO, AnswersDao answerDao) {
        this.questionsDAO = questionsDAO;
        this.answerDao = answerDao;
    }

    @Override
    public List<UserDTO> convert(List<User> users) {
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDTO(user.getName(), user.getRegistered(), questionsDAO.getNumberOfQuestionsByUserId(user.getUserId()), answerDao.getNumberOfAnswersByUserId(user.getUserId())));
        }
        return result;
    }

    @Override
    public UserDTO convert(User user) {
        return new UserDTO(
                user.getName(),
                user.getRegistered(),
                questionsDAO.getNumberOfQuestionsByUserId(user.getUserId()),
                answerDao.getNumberOfAnswersByUserId(user.getUserId()
                ));
    }

}
