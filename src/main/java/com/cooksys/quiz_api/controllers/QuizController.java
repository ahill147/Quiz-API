package com.cooksys.quiz_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.quiz_api.dtos.ErrorDto;
import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;
import com.cooksys.quiz_api.exceptions.NotFoundException;

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
  @ResponseStatus(HttpStatus.CREATED)
  public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
    return quizService.createQuiz(quizRequestDto);
  }

  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuiz(@PathVariable Long id) throws javassist.NotFoundException {
    try {
      return quizService.deleteQuiz(id);
    } catch (NotFoundException e) {
      // Handle the exception and return an appropriate error response
      String errorMessage = "Error: " + e.getMessage();
      QuizResponseDto errorResponse = new QuizResponseDto();
      errorResponse.setName(errorMessage);
      return errorResponse;
    }
  }

  @PatchMapping("/{id}/rename/{name}")
  public QuizResponseDto renameQuiz(@PathVariable Long id, @PathVariable String name) throws javassist.NotFoundException {
    try {
      return quizService.renameQuiz(id, name);
    } catch (NotFoundException e) {
      // Handle the exception and return an appropriate error response
      String errorMessage = "Error: " + e.getMessage();
      QuizResponseDto errorResponse = new QuizResponseDto();
      errorResponse.setName(errorMessage);
      return errorResponse;
    }
  }

  @GetMapping("/{id}/random")
  public QuestionResponseDto getQuizQuestion(@PathVariable Long id) throws javassist.NotFoundException {
    try {
      return quizService.getQuizQuestion(id);
    } catch (NotFoundException e) {
      // Handle the exception and return an appropriate error response
      String errorMessage = "Error: " + e.getMessage();
      QuestionResponseDto errorResponse = new QuestionResponseDto();
      errorResponse.setQuestion(errorMessage);
      return errorResponse;
    }
  }

  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestion(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto) throws javassist.NotFoundException {
    try {
      return quizService.addQuestion(id, questionRequestDto);
    } catch (NotFoundException e) {
      // Handle the exception and return an appropriate error response
      String errorMessage = "Error: " + e.getMessage();
      QuizResponseDto errorResponse = new QuizResponseDto();
      errorResponse.setName(errorMessage);
      return errorResponse;
    }
  }

  @DeleteMapping("/{id}/delete/{questionID}")
  public QuestionResponseDto deleteQuestion(@PathVariable Long id, @PathVariable Long questionID) throws javassist.NotFoundException {
    try {
      return quizService.deleteQuestion(id, questionID);
    } catch (NotFoundException e) {
      // Handle the exception and return an appropriate error response
      String errorMessage = "Error: " + e.getMessage();
      QuestionResponseDto errorResponse = new QuestionResponseDto();
      errorResponse.setQuestion(errorMessage);
      return errorResponse;
    }
  }

}