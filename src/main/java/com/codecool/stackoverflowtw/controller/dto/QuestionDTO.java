package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record QuestionDTO(int questionId, String title, String description, LocalDateTime created, int userId,
                          String username, int votes, int numberOfAnswers) {
}
