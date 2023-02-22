package com.codecool.stackoverflowtw.service.answerService;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.answer.AnswerNotFoundException;
import com.codecool.stackoverflowtw.dao.answer.AnswersDao;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class AnswerService {

    private final AnswersDao answersDao;
    private final AnswerConverter answerConverter;


    public AnswerService(AnswersDao answersDao, AnswerConverter answerConverter) {
        this.answersDao = answersDao;
        this.answerConverter = answerConverter;
    }

    public Collection<AnswerDTO> getAllAnswers(int questionId) {
        Collection<Answer> answersFromDatabase = answersDao.getAnswersByQuestionId(questionId);
        return answerConverter.convertAnswerToAnswerDTO(answersFromDatabase);
    }

    public void createNewAnswer(NewAnswerDTO newAnswerDTO) {
        Answer convertedAnswer = answerConverter.convertNewAnswerDTOtoAnswer(newAnswerDTO);
        answersDao.createNewAnswer(convertedAnswer);
    }

    public void deleteAnswerByAnswerId(int answerId) throws NoSuchElementException {

        if(!answersDao.deleteAnswerByAnswerId(answerId)) throw  new NoSuchElementException(
                "Answer with id: " + answerId + " not found");

    }
}
