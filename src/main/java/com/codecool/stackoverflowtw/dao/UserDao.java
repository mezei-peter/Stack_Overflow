package com.codecool.stackoverflowtw.dao;


import java.util.Collection;

public interface UserDao {
    Collection<String> getUsernamesByUserId(Collection<Integer> userIds);
    String getUsernameByUserId(int userId);
}
