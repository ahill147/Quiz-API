package com.cooksys.quiz_api.repositories;

import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.entities.Question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	Optional<Quiz> findByIdAndDeletedFalse(Long id);

	List<Quiz> findAllByDeletedFalse();

}
