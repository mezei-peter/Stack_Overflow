package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.answer.AnswerNotFoundException;
import com.codecool.stackoverflowtw.service.answerService.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/")
    public ResponseEntity addNewAnswer(@RequestBody NewAnswerDTO newAnswerDTO) {
        answerService.createNewAnswer(newAnswerDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAnswerByAnswerId(@PathVariable int id) {
        try {
            answerService.deleteAnswerByAnswerId(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Question with id: " + id + " not found",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successfully deleted question with id: " + id,
                HttpStatus.NO_CONTENT);
    }
}
