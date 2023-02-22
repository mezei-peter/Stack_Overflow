package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.DetailedQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface QuestionConverter {
    List<QuestionDTO> convertQuestionsToQuestionDTOs(Collection<Question> originalQuestions,
                                                     Map<Integer, Integer> answerCountsByQuestionIds);

    QuestionDTO convertQuestionToQuestionDTO(Question question, int answerCount);

    DetailedQuestionDTO convertQuestionToDetailedQuestionDTO(Question questionByQuestionId, int answerCount,
                                                             Collection<AnswerDTO> answerDTOs);
}
