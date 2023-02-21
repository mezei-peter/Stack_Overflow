package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record AnswerDTO(int answerId, int questionId, int votes, String description,
                        int answerPosterUserId, int questionPosterUserId,
                        Timestamp posted, String username) {
}
