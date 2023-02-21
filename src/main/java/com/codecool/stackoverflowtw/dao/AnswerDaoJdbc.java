package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.database.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AnswerDaoJdbc implements AnswersDao{

    ConnectionProvider connectionProvider;

    public AnswerDaoJdbc(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Set<Answer> getAllAnswers() {
        String query = "Select answer_id, question_id, votes, description, user_id, posted from " +
                "answers;";
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement();
        ) {
            Set<Answer> answerSet = new HashSet<>();
            ResultSet rs = statement.executeQuery(query);
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
    public Answer getAnswerByAnswerId(int answerId) {
        return null;
    }

    @Override
    public Collection<Answer> getAnswersByQuestionId(int questionId) {
        return null;
    }

    @Override
    public int getNumberOfAnswersByUserId(int userId) {
        return 0;
    }

    public int getAnswerCountByQuestionId(int quesionId) {
        String query = "Select count(*) as answer_count from answers where question_id = 1;";
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement();
        ){
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt("answer_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
