package com.onlinequiz.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onlinequiz.dto.QuestionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.lang.model.element.Name;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    public int id;
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    public void setQuestions(@NotEmpty(message = "Questions cannot be empty") List<QuestionDTO> questionDTOs) {
        this.questions = questionDTOs.stream()
                .map(dto -> new Question(
                        dto.getIndexOfQuestion(),
                        dto.getQuestion(),
                        dto.getOptions(),
                        dto.getCorrectAnswerIndex(),
                        dto.getDificultyLevel(),
                        dto.getTitleOfQuiz()
                ))
                .collect(Collectors.toList());
    }
}