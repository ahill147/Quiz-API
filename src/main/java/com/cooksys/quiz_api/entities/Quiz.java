package com.cooksys.quiz_api.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Quiz {

  @Id
  @GeneratedValue
  @Column(nullable=false)
  private Long id;

  @Column(nullable=false)
  private String name;

  @Column(nullable=false)
  @OneToMany(mappedBy = "quiz")
  private List<Question> questions;

}
