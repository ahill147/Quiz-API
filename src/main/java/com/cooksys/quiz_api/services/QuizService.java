package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;

public interface QuizService {

	List<QuizResponseDto> getAllQuizzes();
	
//	List<QuestionResponseDto> getAllQuestions();

	QuizResponseDto createQuiz(QuizRequestDto quizRequestDTO);

	QuizResponseDto deleteQuiz(Long id);

	QuizResponseDto renameQuiz(Long id, String newName);
	
	QuestionResponseDto getRandomQuizQuestion(Long id);

	QuizResponseDto addQuestionToQuiz(Long id, QuestionRequestDto questionRequestDto);

	QuestionResponseDto deleteQuestionFromQuiz(Long id, Long questionID);


}
