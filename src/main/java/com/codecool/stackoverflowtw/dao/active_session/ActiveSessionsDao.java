package com.codecool.stackoverflowtw.dao.active_session;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;

public interface ActiveSessionsDao {
    boolean validateNewQuestionDTO(NewQuestionDTO newQuestionDTO);
}
