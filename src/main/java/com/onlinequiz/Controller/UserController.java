package com.onlinequiz.Controller;

import com.onlinequiz.Service.QuizServiceImpl;

import com.onlinequiz.Service.UserService;
import com.onlinequiz.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private QuizServiceImpl quizServiceImpl;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
   public boolean register(@Valid @RequestBody UserDTO userDTO){
       return userService.register(userDTO);
   }
   @PostMapping("/login")
    public boolean login(@Valid @RequestBody UserDTO userDTO){
         return userService.login(userDTO);

    }

    /*
    @GetMapping("/takeQuiz")
    public List<Question> takeQuiz(@RequestParam String quizName){
        return userService.takeQuiz(quizName);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<?> submitQuiz(@PathVariable int quizId, @RequestBody List<Integer>num) {

        int score = userService.submitQuiz(quizId,num);
        Map<String, Object> result = new HashMap<>();
        result.put("score", score);
        result.put("totalQuestions", 10);
        result.put("correctAnswers", score);

        return ResponseEntity.ok(result);
    }*/
}