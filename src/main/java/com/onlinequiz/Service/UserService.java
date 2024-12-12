package com.onlinequiz.Service;
import com.onlinequiz.dao.QuestionDAO;
import com.onlinequiz.dao.QuizDAO;
import com.onlinequiz.dao.UserDAO;
import com.onlinequiz.dto.UserDTO;

import com.onlinequiz.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private QuizServiceImpl quizServiceImpl;
     @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    ModelMapper modelMapper;

    public UserDTO userToUserDTO(User user){
        UserDTO userDTO=modelMapper.map(user,UserDTO.class);
        return  userDTO;
    }

  /*  public QuestionWrapper toQuestionWrapper(Question question) {
        QuestionWrapper questionWrapper= modelMapper.map(question,QuestionWrapper.class);
       QuestionWrapper questionWrapper = new QuestionWrapper();

        questionWrapper.setIndexOfQuestion(question.getIndexOfQuestion());
        questionWrapper.setQuestion(question.getQuestion());
        questionWrapper.setOptions(question.getOptions());
        questionWrapper.setDificultyLevel(question.getDificultyLevel());

        return questionWrapper;
        return questionWrapper;
    }
    public QuizWrapper toQuizWrapper(Quiz quiz) {
        QuizWrapper quizWrapper = modelMapper.map(quiz,QuizWrapper.class);
/*
        quizWrapper.setId(quiz.getId());
        quizWrapper.setTitle(quiz.getTitle());

        List<QuestionWrapper> questionWrappers = quiz.getQuestions().stream()
                .map(UserService::toQuestionWrapper)
                .collect(Collectors.toList());

        quizWrapper.setQuestions(questionWrappers);

        return quizWrapper;
    }*/

    public User userDTOToUser(UserDTO userDTO){
           return new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getRole());
    }

   /* public QuizWrapper takeQuiz(){

    }*/
    public boolean register(UserDTO userDTO){
        User user=userDTOToUser(userDTO);
        if(user.equals(userDAO.findByUsername(user.getUsername()))){
            return false;
        }
        userDAO.save(user);
        return true;

    }

    public boolean login(UserDTO userDTO){

        User user= userDTOToUser(userDTO);
        User foundUser = userDAO.findByUsername(user.getUsername()).get();
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }
    /*
    public List<QuestionDTO>takeQuiz(String tag){

         List<Question>questions=questionDAO.findAllQuestionsByTitleOfQuiz(tag);
          return questions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public int submitQuiz(int quizId, List<Integer> num) {
        Quiz quiz = quizDAO.findById(quizId).get();
        if (quiz == null)
            throw new QuizNotFoundException("Quiz not found");

        int correctAnswers = 0;
        int cnt=0;
        for (Question question : quiz.getQuestions()) {
            if (question.getCorrectAnswerIndex()== num.get(cnt)){
                correctAnswers++;
            }
        }
        int score = correctAnswers;
        return score;
    }*/
}