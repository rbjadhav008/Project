package com.onlinequiz.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    int indexOfQuestion;
    private String question;
    private List<String> options;
    private int correctAnswerIndex;
    int dificultyLevel;
    String titleOfQuiz;


}