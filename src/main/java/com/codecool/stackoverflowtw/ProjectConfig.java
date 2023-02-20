package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.database.DatabasePostgre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc();
    }
    @Bean
    public Database database() {
        return new DatabasePostgre(System.getenv("psql_URL"), System.getenv("psql_username"), System.getenv("psql_pw"));
    }
}
