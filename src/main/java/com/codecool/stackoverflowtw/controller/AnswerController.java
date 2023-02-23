package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.UpdateAnswerDto;
import com.codecool.stackoverflowtw.service.answerService.AnswerService;
import org.springframework.http.HttpStatus;
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
        System.out.println(newAnswerDTO.answer_poster_id());
        answerService.createNewAnswer(newAnswerDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswerByAnswerId(@PathVariable int id) {
        try {
            answerService.deleteAnserByAnswerId(id);
            return new ResponseEntity<>("Successfully deleted question with id: " + id,
                    HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No question with id: "+ id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<String> updateAnswerDescription(@RequestBody UpdateAnswerDto updateAnswerDto) {

        try {
            answerService.updateAnswerDescritpionNyAnswerId(updateAnswerDto.answer_id(),
                    updateAnswerDto.description());
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Couldn't update answer (id: " + updateAnswerDto.answer_id() + ")", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Successfully updated answer (id : "+ updateAnswerDto.answer_id() + ") with text: " + updateAnswerDto.description(), HttpStatus.ACCEPTED);
    }
}
