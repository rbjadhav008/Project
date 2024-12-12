package com.onlinequiz.ui;

import org.springframework.web.bind.annotation.*;

@RestController
public class AdminUi {/*
    @Autowired
    QuizService quizService;

    public AdminUi(QuizService quizService) {
        this.quizService = quizService;
    }

    public AdminUi() {
    }

    @PostMapping("/create")
    void add(@RequestBody Quiz quiz){
        quizService.createQuiz(quiz);
    }

    @PostMapping("addQuestion/{id}")
    void addQuestion(@PathVariable("id") int id, @RequestBody List<Question>questions){
        quizService.addQuestion(id,questions);
    }

    @GetMapping("/get")
    List<Quiz> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    @PutMapping("/update/{id}/{newtitle}")
    void updateTitle(@PathVariable("id") int id,@PathVariable("newtitle") String newtitle){
        quizService.updateTitle(id,newtitle);
    }

    @PutMapping("{id}/updateQuestion/{index}")
    void updateQuestion(@PathVariable("id") int id,@PathVariable("index") int index,@RequestBody String question){
        quizService.updateQuestion(id,index,question);
    }

    @PutMapping("{id}/{index}/updateans/{ans}")
    public void updateAnswer(@PathVariable("id") int id,@PathVariable("index") int index,@PathVariable("ans") int ans){
        quizService.updateAnswer(id,index,ans);
    }
    @PutMapping("{id}/updatechoice/{index}")
    public void updateChoice(@PathVariable("id") int id,@PathVariable("index") int index,@RequestBody List<String>choice){
        quizService.updateChoice(id,index,choice);
    }

    @PutMapping("{id}/updateDificulty/{index}")
    public void updateDificulty(@PathVariable("id") int id,@PathVariable("index")int index,@PathVariable int dificultylevel){
        quizService.updateDificulty(id,index,dificultylevel);
    }*/
}
