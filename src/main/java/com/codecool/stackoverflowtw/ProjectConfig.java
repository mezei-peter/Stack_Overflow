package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.ConnectionProvider;
import com.codecool.stackoverflowtw.database.PostgreConnect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc();
    }
    @Bean
    public ConnectionProvider connectionProvider() {
        return new PostgreConnect(System.getenv("psql_URL"), System.getenv("psql_username"), System.getenv("psql_pw"));
    }
}
