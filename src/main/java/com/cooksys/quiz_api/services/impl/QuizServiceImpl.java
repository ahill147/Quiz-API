package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import com.cooksys.quiz_api.exceptions.NotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    private Quiz getQuiz(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }

    private Question getQuestion(Long id, Long questionID) {
        Optional<Question> question = questionRepository.findByIdAndQuizId(questionID, id);
        return question.orElse(null);
    }

    @Override
    public List<QuizResponseDto> getAllQuizzes() {
        return quizMapper.entitiesToDtos(quizRepository.findAll());
    }

    @Override
    public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
        Quiz quiz = quizMapper.requestEntity(quizRequestDto);

        for (Question question : quiz.getQuestions()) {
            question.setQuiz(quiz);

            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
        }
        Quiz result = quizRepository.saveAndFlush(quiz);
        return quizMapper.entityToDto(result);
    }

    @Override
    public QuizResponseDto deleteQuiz(Long id) {
        Quiz quiz = getQuiz(id);
        if (quiz != null) {
            quizRepository.delete(quiz);
            return quizMapper.entityToDto(quiz);
        }
        return null;
    }

    @Override
    public QuizResponseDto renameQuiz(Long id, String name) {
        Quiz quiz = getQuiz(id);
        if (quiz != null) {
            quiz.setName(name);
            Quiz result = quizRepository.saveAndFlush(quiz);
            return quizMapper.entityToDto(result);
        }
        return null;
    }

    @Override
    public QuestionResponseDto getQuizQuestion(Long id) {
        Quiz quiz = getQuiz(id);
        if (quiz != null) {
            List<Question> questions = quiz.getQuestions();
            if (!questions.isEmpty()) {
                Question rand = questions.get(new Random().nextInt(questions.size()));
                return questionMapper.entityToDto(rand);
            }
        }
        return null;
    }

    @Override
    public QuizResponseDto addQuestion(Long id, QuestionRequestDto questionRequestDto) throws NotFoundException {
        Quiz quizToUpdate = getQuiz(id);
        if (quizToUpdate != null) {
            Question question = questionMapper.questionDtoToEntity(questionRequestDto);
            question.setQuiz(quizToUpdate);
            quizToUpdate.getQuestions().add(question);
            Quiz result = quizRepository.saveAndFlush(quizToUpdate);
            return quizMapper.entityToDto(result);
        }
        throw new NotFoundException("Quiz not found with id: " + id);
    }

    @Override
	public QuestionResponseDto deleteQuestion(Long id, Long questionID) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}