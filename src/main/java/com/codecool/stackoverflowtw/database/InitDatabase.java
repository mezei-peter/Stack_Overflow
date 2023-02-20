package com.codecool.stackoverflowtw.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase implements InitProvider {
    private final ConnectionProvider database;

    public InitDatabase(ConnectionProvider database) {
        this.database = database;
    }

    @Override
    public void initializeDB() {
        try (Connection connection = database.getConnection();
        Statement statement = connection.createStatement()) {
            createTables(statement);
            addConstraintsToTables(statement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createTables(Statement statement) throws SQLException {
        try {
            for (String createStatement : TableStatement.STATEMENTS) {
                statement.execute(createStatement);
            }
            System.out.println("Postgre tables created!");
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void addConstraintsToTables(Statement statement) throws SQLException {
        try {
            for (String constraint : TableConstraint.CONSTRAINTS) {
                statement.execute(constraint);
            }
            System.out.println("Postgre tables created!");
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
