package com.codecool.stackoverflowtw.service.userService;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.dao.AnswerDaoJdbc;
import com.codecool.stackoverflowtw.dao.AnswersDao;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.UserDao;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    UserDao userDao;
    UserConverter userConverter;
    @Autowired
    public UserService(UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        return userConverter.convert(allUsers);
    }
}
