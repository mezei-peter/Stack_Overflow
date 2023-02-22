package com.codecool.stackoverflowtw.dao.active_session;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.database.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActiveSessionsDaoJDBC implements ActiveSessionsDao {
    private final ConnectionProvider connectionProvider;

    public ActiveSessionsDaoJDBC(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean validateNewQuestionDTO(NewQuestionDTO newQuestionDTO) {
        String query = """
                SELECT session_id
                FROM active_sessions
                WHERE user_id = ?
                    AND session_id = ?;
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, newQuestionDTO.userId());
            ps.setString(2, newQuestionDTO.sessionId());

            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getUserIdBySessionId(String sessionId) {
        String query = """
                SELECT user_id
                FROM active_sessions
                WHERE session_id = ?;
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, sessionId);

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                return -1;
            }
            return resultSet.getInt("user_id");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
