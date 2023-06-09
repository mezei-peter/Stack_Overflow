package com.codecool.stackoverflowtw.dao.answer;

import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.database.ConnectionProvider;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AnswerDaoJdbc implements AnswersDao {

    ConnectionProvider connectionProvider;

    public AnswerDaoJdbc(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Answer getAnswerByAnswerId(int answerId) {
        return null;
    }

    @Override
    public Collection<Answer> getAnswersByQuestionId(int questionId) {
        String query = "Select answer_id, question_id, votes, description, user_id, posted from " +
                "answers where question_id = ?;";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, questionId);
            Set<Answer> answerSet = new HashSet<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                answerSet.add(new Answer(rs.getInt("answer_id"), rs.getInt("question_id"),
                        rs.getInt("votes"), rs.getString("description"), rs.getInt("user_id"),
                        rs.getTimestamp("posted")));
            }
            return answerSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfAnswersByUserId(int userId) {
        int result = 0;
        String query = "SELECT COUNT(answer_id) FROM Answers WHERE user_id = ?;";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            result = resultSet.getInt("count");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int getAnswerCountByQuestionId(int quesionId) {
        String query = "Select count(*) as answer_count from answers where question_id = 1;";
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement();
        ){
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt("answer_count");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createNewAnswer(Answer answer) {
        String query = "Insert into answers (question_id, votes, description, user_id) " +
                "Values(?, ?, ?, ?);";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, answer.getQuestionId());
            ps.setInt(2, answer.getVotes());
            ps.setString(3, answer.getDescription());
            ps.setInt(4, answer.getUserId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteAnswerByAnswerId(int answerId) {
        String query = "Delete from answers where answer_id = ?";

        try (Connection connection = connectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, answerId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteAnswersByQuestionId(int questionId) {
        if (numberOfAnswersByQuestionId(questionId) == 0) {
            return true;
        }

        String query = "Delete from answers where question_id = ?";

        try(Connection connection = connectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, questionId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateAnswerDescription(int answerId, String description) {
        String query = "Update answers Set description = ? where answer_id = ?";

        try(Connection connection = connectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setString(1, description);
            ps.setInt(2, answerId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int numberOfAnswersByQuestionId(int questionId) {
        String query = "Select count(*) as number_of_answers from answers where question_id = ?";

        try(Connection connection = connectionProvider.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, questionId);

            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getInt("number_of_answers");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
