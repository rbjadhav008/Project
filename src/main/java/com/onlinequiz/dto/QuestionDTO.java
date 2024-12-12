package com.onlinequiz.dto;

import com.onlinequiz.models.Quiz;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class QuestionDTO {
    @Id
    @NotNull(message = "id cannot be null")
    int indexOfQuestion;
    @NotEmpty(message = ("question value cannot be null"))
    private String question;
    @NotEmpty(message = "option value cannot null")
    private List<String> options;
    @NotNull(message ="we answer of question cannot null")
    @Min(value = 1,message = "value cannot be less than 1")
    @Max(value = 4 ,message = "correct answer cannot be greater than 4")
    private int correctAnswerIndex;
    @NotNull(message = "we cannot give the dificulty level null")
    @Max(value = 3,message = "dificulty level cannot be greater than 3")
    @Min(value = 1,message = "difculty level cannot be less than 1")
    int dificultyLevel;
    @NotEmpty
    String titleOfQuiz;







}
