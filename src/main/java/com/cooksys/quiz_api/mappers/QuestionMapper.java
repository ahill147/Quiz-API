package com.cooksys.quiz_api.mappers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AnswerMapper.class })
public interface QuestionMapper {

	QuestionResponseDto entityToDto(Question entity);
	
	QuestionResponseDto entityToDto(Quiz saveAndFlush);

	QuestionResponseDto entityToDto(QuestionResponseDto randomQuestionFromQuiz);

	List<QuestionResponseDto> entitiesToDtos(List<Question> entities);

	Question dtoToEntity(QuestionRequestDto questionRequestDto);

}
