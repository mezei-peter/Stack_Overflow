package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.answerService.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/")
    public ResponseEntity addNewAnswer(@RequestBody NewAnswerDTO newAnswerDTO) {
        System.out.println(newAnswerDTO.answer_poster_id());
        answerService.createNewAnswer(newAnswerDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
