package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.dao.UserDao;
import com.codecool.stackoverflowtw.dao.UserDaoJdbc;
import com.codecool.stackoverflowtw.database.ConnectionProvider;
import com.codecool.stackoverflowtw.database.PostgreConnect;
import com.codecool.stackoverflowtw.service.QuestionConverter;
import com.codecool.stackoverflowtw.service.QuestionConverterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public QuestionsDAO questionsDAO(ConnectionProvider connectionProvider) {
        return new QuestionsDaoJdbc(connectionProvider);
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
}
