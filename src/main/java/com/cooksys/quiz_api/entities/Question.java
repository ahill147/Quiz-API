package com.cooksys.quiz_api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Question {

	@Id
	@GeneratedValue
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String text;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Column(nullable=false)
	private List<Answer> answers;

}
