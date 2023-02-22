package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record NewAnswerDTO(int questionId, String description, int answerPosterId) {
}
