package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record QuestionDTO(int questionId, String title, String description, Timestamp created, int userId,
                          String username, int votes, int numberOfAnswers) {
}
