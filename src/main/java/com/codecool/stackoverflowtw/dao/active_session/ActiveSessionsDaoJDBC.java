package com.codecool.stackoverflowtw.dao.active_session;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.database.ConnectionProvider;

public class ActiveSessionsDaoJDBC implements ActiveSessionsDao {
    private final ConnectionProvider connectionProvider;

    public ActiveSessionsDaoJDBC(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean validateNewQuestionDTO(NewQuestionDTO newQuestionDTO) {
        return false;
    }
}
