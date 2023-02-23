package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.User;
import com.codecool.stackoverflowtw.database.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    @Override
    public String getUsernameByUserId(int userId) {
        return getUserById(userId).getName();
    }

    @Override
    public User getUserByUserId(int userId) {
        return null;
    }

    @Override
    public boolean postNewUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUserByUserId(int userId) {
        return false;
    }

    @Override
    public User getUserByQuestionId(int questionId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String query = """
                SELECT user_id, is_super_user, name, password, registered from Users
                """;
        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement prepSt = conn.prepareStatement(query)) {
            ResultSet result = prepSt.executeQuery();
            while (result.next()) {
                allUsers.add(new User(result.getInt("user_id"), result.getBoolean("is_super_user"), result.getString("name"), result.getString("password"), result.getTimestamp("registered")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    @Override
    public boolean isSuperUserByUserId(int userId) {
        String query = """
                SELECT is_super_user
                FROM users
                WHERE user_id = ?;
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                return false;
            }

            return resultSet.getBoolean("is_super_user");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement prepSt = conn.prepareStatement(query)) {
            prepSt.setInt(1, id);
            ResultSet resultSet = prepSt.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getInt("user_id"), resultSet.getBoolean("is_super_user"),
                    resultSet.getString("name"), resultSet.getString("password"),
                    resultSet.getTimestamp("registered"));
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createUser(User user) {

        if (!checkIfUsernamefree(user.getName())) {
            return false;
        }

        String query =  "Insert into users (is_super_user, name, password) Values(?, ?, ?)";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setBoolean(1, user.isSuperUser());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUsernamefree(String username) {
        String query = "Select name from users where name = ?;";

        try(Connection connection = connectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return !rs.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
