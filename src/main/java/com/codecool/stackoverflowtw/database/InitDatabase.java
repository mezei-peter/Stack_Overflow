package com.codecool.stackoverflowtw.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;

@Component
public class InitDatabase implements InitProvider {
    private final ConnectionProvider database;

    @Autowired
    public InitDatabase(ConnectionProvider database) {
        this.database = database;
    }

    @Override
    public void initializeDB() {
        try (Connection connection = database.getConnection();
        Statement statement = connection.createStatement()) {
            connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
