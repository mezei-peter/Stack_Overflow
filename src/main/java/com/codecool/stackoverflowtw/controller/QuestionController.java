package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.DetailedQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionSortType;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions(@RequestParam("sort_by") Optional<String> sortBy) {
        if (sortBy.isEmpty()) {
            return questionService.getAllQuestions();
        }
        try {
            QuestionSortType sortType = QuestionSortType.valueOf(sortBy.get());
            return questionService.getSortedQuestions(sortType);
        } catch (Exception e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/{id}")
    public DetailedQuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        return questionService.addNewQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public boolean deleteQuestionById(@PathVariable int questionId, @RequestBody String sessionId) {
        return questionService.deleteQuestionById(questionId, sessionId);
    }
}
