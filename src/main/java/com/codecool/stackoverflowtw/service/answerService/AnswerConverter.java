package com.codecool.stackoverflowtw.service.answerService;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.Collection;

public interface AnswerConverter {

    Collection<AnswerDTO> convertAnswerToAnswerDTO(Collection<Answer> answersFromDatabase);
}
