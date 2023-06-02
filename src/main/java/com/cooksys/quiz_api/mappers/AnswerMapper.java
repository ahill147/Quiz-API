package com.cooksys.quiz_api.mappers;

import java.util.List;

import com.cooksys.quiz_api.dtos.AnswerRequestDto;
import com.cooksys.quiz_api.dtos.AnswerResponseDto;
import com.cooksys.quiz_api.entities.Answer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

  AnswerResponseDto entityToResponseDto(Answer entity);

  List<AnswerResponseDto> entitiesToResponseDtos(List<Answer> entities);

  Answer requestDtoToEntity(AnswerRequestDto answerRequestDto);
  
}
