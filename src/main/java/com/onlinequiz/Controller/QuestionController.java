package com.onlinequiz.Controller;
import com.onlinequiz.Service.QuestionServiceImpl;
import com.onlinequiz.dto.QuestionDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class QuestionController {
    @Autowired
    QuestionServiceImpl questionService;



    @GetMapping("/getQuestion")
    ResponseEntity<List<QuestionDTO>> getAllQuestion(){
        List<QuestionDTO> questions = questionService.getAllQuestion();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/getQuestionByQuizTitle")
    ResponseEntity<List<QuestionDTO>> getAllQuestionByTag(@RequestParam String quizTitle){
        List<QuestionDTO>questionDTOList=questionService.findAllQuestionsByQuizTitle(quizTitle);
        return ResponseEntity.ok(questionDTOList);
    }

    @PostMapping("/addQuestion")
    ResponseEntity<?> addQuestion(@RequestBody @Valid QuestionDTO questionDTO){
         QuestionDTO questionDTO1=questionService.addQuestion(questionDTO);
         return ResponseEntity.ok(questionDTO1);
    }


    @PutMapping("/updateQuestion/{QuestionNo}")
    ResponseEntity<?> updateQuestion(@PathVariable("QuestionNo")  int index,@RequestBody @Valid String question){
         questionService.updateQuestion(index,question);
         return ResponseEntity.ok().build();
    }

    @PutMapping("/UpdateCorrectOption/{QuestionNo}/{Ans}")
    ResponseEntity<?> updateCorrectOption(@PathVariable("QuestionNo")  int index,@PathVariable("Ans")  int correctOption){
        questionService.updateAnswer(index,correctOption);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/UpdateOption/{QuestionNo}")
    ResponseEntity<?> updateOption(@PathVariable("QuestionNo")  int index,@Valid @RequestBody List<String>option){
        questionService.updateOption(index,option);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/UpdateDificulty/{QuestionNo}/{dificultyLevel}")
    ResponseEntity<?> updateDificultyLevel(@PathVariable("QuestionNo")  int index,@PathVariable("dificultyLevel") int dicultyLevel){
        questionService.updateDificulty(index,dicultyLevel);
        return ResponseEntity.ok().build();
    }

    //he run nahi hot beacause of foreignkey
    @DeleteMapping("/deleteQuestion/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") int id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }
}

