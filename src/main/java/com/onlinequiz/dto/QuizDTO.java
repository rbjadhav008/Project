package com.onlinequiz.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onlinequiz.models.Question;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class QuizDTO {
    @Id
    @NotNull(message = "quizID id cannot be empty")
    public  int Id;
    @NotEmpty(message = "Quiz title cannot empty")
    private String title;

    @NotEmpty(message = "question cannot empty")
    private List<QuestionDTO> questions;


}
