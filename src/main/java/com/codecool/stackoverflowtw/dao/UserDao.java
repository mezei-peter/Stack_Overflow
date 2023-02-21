package com.codecool.stackoverflowtw.dao;


import com.codecool.stackoverflowtw.dao.model.User;

import java.util.Collection;

public interface UserDao {
    Collection<String> getUsernamesByUserId(Collection<Integer> userIds);
    String getUsernameByUserId(int userId);
    User getUserByUserId(int userId);
    boolean postNewUser(User user);
    boolean deleteUserByUserId(int userId);
    User getUserByQuestionId(int questionId);
}
