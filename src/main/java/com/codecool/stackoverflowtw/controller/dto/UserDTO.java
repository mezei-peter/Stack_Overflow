package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record UserDTO(String username, Timestamp regDate, int noOfQuestions, int noOfAnswers) {
}
