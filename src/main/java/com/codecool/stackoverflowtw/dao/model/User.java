package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class User {
    private final int userId;
    private final boolean isSuperUser;
    private final String name;
    private final String password;
    private final Timestamp registered;

    public User(int userId, boolean isSuperUser, String name, String password, Timestamp registered) {
        this.userId = userId;
        this.isSuperUser = isSuperUser;
        this.name = name;
        this.password = password;
        this.registered = registered;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getRegistered() {
        return registered;
    }
}
