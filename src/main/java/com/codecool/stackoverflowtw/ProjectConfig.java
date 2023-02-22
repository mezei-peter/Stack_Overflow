package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.*;
import com.codecool.stackoverflowtw.dao.model.User;
import com.codecool.stackoverflowtw.database.ConnectionProvider;
import com.codecool.stackoverflowtw.database.PostgreConnect;
import com.codecool.stackoverflowtw.service.QuestionConverter;
import com.codecool.stackoverflowtw.service.QuestionConverterImpl;
import com.codecool.stackoverflowtw.service.answerService.AnswerConverter;
import com.codecool.stackoverflowtw.service.answerService.AnswerConverterImpl;
import com.codecool.stackoverflowtw.service.userService.UserConverter;
import com.codecool.stackoverflowtw.service.userService.UserConverterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public QuestionsDAO questionsDAO(ConnectionProvider connectionProvider) {
        return new QuestionsDaoJdbc(connectionProvider);
    }

    @Bean
    public AnswersDao answersDao(ConnectionProvider connectionProvider) {
        return new AnswerDaoJdbc(connectionProvider);
    }

    @Bean
    public AnswerConverter answerConverter(QuestionsDAO questionsDAO, UserDao userDao) {
        return new AnswerConverterImpl(questionsDAO, userDao);
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return new PostgreConnect(System.getenv("psql_URL"), System.getenv("psql_username"), System.getenv("psql_pw"));
    }

    @Bean
    public UserDao userDao(ConnectionProvider connectionProvider) {
        return new UserDaoJdbc(connectionProvider);
    }

    @Bean
    public QuestionConverter questionConverter(UserDao userDao) {
        return new QuestionConverterImpl(userDao);
    }

    @Bean
    public UserConverter userConverter(QuestionsDAO questionsDAO, AnswersDao answersDao) {
        return new UserConverterImpl(questionsDAO,answersDao);
    }
}
