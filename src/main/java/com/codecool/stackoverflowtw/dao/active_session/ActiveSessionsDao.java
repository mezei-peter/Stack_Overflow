package com.codecool.stackoverflowtw.dao.active_session;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;

public interface ActiveSessionsDao {
    boolean validateNewQuestionDTO(NewQuestionDTO newQuestionDTO);

    int getUserIdBySessionId(String sessionId);

    String createSessionForUserId(int userId);

    boolean deleteSession(String sessionId);
}
