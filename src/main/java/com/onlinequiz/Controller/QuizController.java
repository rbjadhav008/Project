package com.onlinequiz.Controller;
import com.onlinequiz.Service.QuizServiceImpl;
import com.onlinequiz.dto.QuizDTO;
import com.onlinequiz.models.Quiz;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class QuizController {
    @Autowired
    QuizServiceImpl quizServiceImpl;

    @PostMapping("/createQuiz")
    public ResponseEntity<?>add(@RequestBody @Valid QuizDTO quizDTO){
             Quiz savedQuiz = quizServiceImpl.createQuiz(quizDTO);
                 return ResponseEntity.ok(savedQuiz);

    }

    @GetMapping("/getQuiz")
    ResponseEntity<List<QuizDTO>> getAllQuiz(){

            List<QuizDTO> quizDTOList= quizServiceImpl.getAllQuiz();
            if(quizDTOList!=null){
                return ResponseEntity.ok(quizDTOList);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

    }
    @GetMapping("/getQuiz/{id}")
    ResponseEntity<QuizDTO>getQuizById(@PathVariable("id") int id){
        QuizDTO quizDTO= quizServiceImpl.getById(id);
        if(quizDTO!=null){
            return  ResponseEntity.ok(quizDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateQuiz(@PathVariable("id") int id,@Valid @RequestBody QuizDTO quizDTO){
            boolean isdone = quizServiceImpl.updateQuiz(id, quizDTO);
            if(isdone) {
                return ResponseEntity.ok("Product updated successfully");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }


    @DeleteMapping("delete/{id}")
    ResponseEntity<?>deleteQuiz(@PathVariable("id") int id){
        QuizDTO quiz =  quizServiceImpl.getById(id);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }
        quizServiceImpl.deleteQuiz(id);
        return ResponseEntity.noContent().build();

    }


}
