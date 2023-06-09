package com.codecool.stackoverflowtw.service.userService;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserLoginDTO;
import com.codecool.stackoverflowtw.dao.UserDao;
import com.codecool.stackoverflowtw.dao.active_session.ActiveSessionsDao;
import com.codecool.stackoverflowtw.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserDao userDao;
    UserConverter userConverter;
    private final ActiveSessionsDao activeSessionsDao;

    @Autowired
    public UserService(UserDao userDao, UserConverter userConverter, ActiveSessionsDao activeSessionsDao) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.activeSessionsDao = activeSessionsDao;
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        return userConverter.convert(allUsers);
    }

    public void createNewUser(NewUserDTO newUserDTO) throws UserAlreadyExistAuthenticationException {
        User newUser = userConverter.convertNewUserDtoToUser(newUserDTO);
        boolean isUserCreated = userDao.createUser(newUser);

        if (!isUserCreated) {
            throw new UserAlreadyExistAuthenticationException("Username already taken");
        }
    }

    public String createSession(UserLoginDTO userLoginDTO) {
        int userId = userDao.getUserIdByLoginDetails(userLoginDTO.username(), userLoginDTO.password());
        return activeSessionsDao.createSessionForUserId(userId);
    }

    public boolean deleteSession(String sessionId) {
        return activeSessionsDao.deleteSession(sessionId);
    }

    public UserDTO getUserBySessionId(String sessionId) {
        int userId = activeSessionsDao.getUserIdBySessionId(sessionId);
        User user = userDao.getUserByUserId(userId);
        return userConverter.convert(user);
    }

    public int getUserIdBySessionId(String sessionId) {
        return activeSessionsDao.getUserIdBySessionId(sessionId);
    }
}
