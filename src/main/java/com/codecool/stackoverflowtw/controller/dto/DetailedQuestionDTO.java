package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;
import java.util.Collection;

public record DetailedQuestionDTO(int questionId, String title, String description, Timestamp created, int userId,
                                  String username, int votes, int numberOfAnswers, Collection<AnswerDTO> answers) {
}
