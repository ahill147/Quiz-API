package com.cooksys.quiz_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }

  @PostMapping
  public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDTO) {
	  return quizService.createQuiz(quizRequestDTO);
  }
  
  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuiz(@PathVariable Long id) {
	  return quizService.deleteQuiz(id);
  }
  
  @PatchMapping("/{id}/rename/{newName}")
  public QuizResponseDto renameQuiz(@PathVariable Long id, @PathVariable String newName) {
	  return quizService.renameQuiz(id, newName);
  }
  
  @GetMapping("/{id}/random")
  public QuestionResponseDto getRandomQuizQuestion(@PathVariable Long id) {
	  return quizService.getRandomQuizQuestion(id);
  }
  
  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestionToQuiz(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto) {
	  return quizService.addQuestionToQuiz(id, questionRequestDto);
  }
  
  @DeleteMapping("/{id}/delete/{questionID}")
  public QuestionResponseDto deleteQuestionFromQuiz(@PathVariable Long id, @PathVariable Long questionID) {
	  return quizService.deleteQuestionFromQuiz(id, questionID);
  }

}