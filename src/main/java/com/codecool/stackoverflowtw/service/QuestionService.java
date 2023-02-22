package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.DetailedQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.UserDao;
import com.codecool.stackoverflowtw.dao.active_session.ActiveSessionsDao;
import com.codecool.stackoverflowtw.dao.answer.AnswersDao;
import com.codecool.stackoverflowtw.dao.QuestionSortType;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.service.answerService.AnswerConverter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;
    private final QuestionConverter questionConverter;
    private final AnswersDao answersDao;
    private final AnswerConverter answerConverter;
    private final ActiveSessionsDao activeSessionsDao;
    private final UserDao userDao;

    public QuestionService(QuestionsDAO questionsDAO, QuestionConverter questionConverter, AnswersDao answersDao,
                           AnswerConverter answerConverter, ActiveSessionsDao activeSessionsDao, UserDao userDao) {
        this.questionsDAO = questionsDAO;
        this.questionConverter = questionConverter;
        this.answersDao = answersDao;
        this.answerConverter = answerConverter;
        this.activeSessionsDao = activeSessionsDao;
        this.userDao = userDao;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> allQuestions = questionsDAO.getAllQuestions();
        Map<Integer, Integer> answerCountsByQuestionIds = questionsDAO.getAnswerCountsByQuestionIds();
        return questionConverter.convertQuestionsToQuestionDTOs(allQuestions, answerCountsByQuestionIds);
    }

    public List<QuestionDTO> getSortedQuestions(QuestionSortType questionSortType) {
        List<Question> sortedQuestions = questionsDAO.getSortedQuestions(questionSortType);
        Map<Integer, Integer> answerCountsByQuestionIds = questionsDAO.getAnswerCountsByQuestionIds();
        return questionConverter.convertQuestionsToQuestionDTOs(sortedQuestions, answerCountsByQuestionIds);
    }

    public DetailedQuestionDTO getQuestionById(int id) {
        int answerCount = questionsDAO.getAnswerCountByQuestionId(id);
        Collection<Answer> answers = answersDao.getAnswersByQuestionId(id);
        Collection<AnswerDTO> answerDTOs = answerConverter.convertAnswerToAnswerDTO(answers);
        return questionConverter.convertQuestionToDetailedQuestionDTO(questionsDAO.getQuestionByQuestionId(id),
                answerCount, answerDTOs);
    }

    public boolean deleteQuestionById(int questionId, String sessionId) {
        int userId = activeSessionsDao.getUserIdBySessionId(sessionId);
        if (userId <= 0) {
            return false;
        }
        if (!userDao.isSuperUserByUserId(userId) && !questionsDAO.isOwnerOfQuestionByIds(questionId, userId)) {
            return false;
        }

        boolean answersDeleted = answersDao.deleteAnswersByQuestionId(questionId);
        if (!answersDeleted) {
            return false;
        }
        return questionsDAO.deleteQuestionByQuestionId(questionId);
    }

    public int addNewQuestion(NewQuestionDTO newQuestionDTO) {
        boolean isValidSession = activeSessionsDao.validateNewQuestionDTO(newQuestionDTO);
        if (!isValidSession) {
            return -1;
        }
        Question newQuestion = questionConverter.convertNewQuestionDtoToQuestion(newQuestionDTO);
        return questionsDAO.postNewQuestion(newQuestion);
    }
}
