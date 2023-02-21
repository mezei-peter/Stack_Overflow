package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.User;
import com.codecool.stackoverflowtw.database.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserDaoJdbc implements UserDao {
    private final ConnectionProvider connectionProvider;

    public UserDaoJdbc(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Collection<String> getUsernamesByUserId(Collection<Integer> userIds) {
        Collection<User> foundUsers = getUsersById(userIds);
        return foundUsers.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    private Collection<User> getUsersById(Collection<Integer> userIds) {
        return userIds.stream()
                .map(this::getUserById)
                .collect(Collectors.toList());
    }

    private User getUserById(Integer id) {
        String query = """
                SELECT user_id, is_super_user, name, password, registered
                FROM Users
                    WHERE user_id = ?;
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getInt("user_id"), resultSet.getBoolean("is_super_user"),
                    resultSet.getString("name"), resultSet.getString("password"),
                    resultSet.getTimestamp("registered"));
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
