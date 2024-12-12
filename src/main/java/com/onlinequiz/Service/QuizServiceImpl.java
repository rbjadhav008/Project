package com.onlinequiz.Service;

import com.onlinequiz.Exception.QuizNotFoundException;
import com.onlinequiz.dao.QuestionDAO;
import com.onlinequiz.dao.QuizDAO;
import com.onlinequiz.dto.QuizDTO;
import com.onlinequiz.models.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuizServiceImpl {
    @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    ModelMapper modelMapper;

    private QuizDTO convertToQuizDTO(Quiz quiz) {
        QuizDTO quizDTO=modelMapper.map(quiz,QuizDTO.class);
        /*return new QuizDTO(
                quiz.getId(), quiz.getTitle(), quiz.getQuestions()
        );*/
        return quizDTO;

    }

    private Quiz convertToQuiz(QuizDTO quizDTO1) {
        Quiz quiz=modelMapper.map(quizDTO1,Quiz.class);
        /*Quiz quiz = new Quiz();
        quiz.setId(quizDTO1.getId());
        quiz.setTitle(quizDTO1.getTitle());
        quiz.setQuestions(quizDTO1.getQuestions());
        return quiz;*/
        return quiz;
    }

    public QuizDTO getById(int id) {
        Quiz quiz = quizDAO.findById(id).orElseThrow(
                ()->new QuizNotFoundException("Quiz not found"));
        QuizDTO quizDTO1 = convertToQuizDTO(quiz);
        return quizDTO1;
    }

    public Quiz createQuiz(QuizDTO quizDTO) {
      /*  Quiz quiz=modelMapper.map(quizDTO,Quiz.class);
        if(quizDAO.existsByTitle(quizDTO.getTitle())){
             throw new DataIntegrityViolationException("Quiz with the same title already exists.");
         }
        Quiz quiz1=quizDAO.save(quiz);
        return quiz1;*/
            Quiz quiz = new Quiz();
            quiz.setTitle(quizDTO.getTitle());
            quiz.setQuestions(quizDTO.getQuestions());
            return quizDAO.save(quiz);

    }

    public List<QuizDTO> getAllQuiz() {
            try {
                List<Quiz> quizList = quizDAO.findAll();
                if(quizList==null){
                    return new ArrayList<>();
                }
                List<QuizDTO> ListquizDTO1 = quizList.stream().map(s -> convertToQuizDTO(s)).toList();
                return ListquizDTO1;
            }catch (Exception e){
                System.out.println(" error because no quiz inserted yet");
                return new ArrayList<>();
            }

    }

    public boolean updateQuiz(int id, QuizDTO newQuizDTO) {
        Quiz quiz = quizDAO.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz not found"));
        quiz.setTitle(newQuizDTO.getTitle());
        quiz.setQuestions(newQuizDTO.getQuestions());
        quizDAO.save(quiz);
        return true;
    }

    public void deleteQuiz(int id) {
        try {
            quizDAO.deleteById(id);
        }catch (QuizNotFoundException e){
            new QuizNotFoundException(e.getMessage());
        }
    }


    /* public void updateTitle(int id, String newtitle) {
       Quiz newQuiz=quizDAO.findById(id).orElseThrow(()->new QuizNotFoundException("id not found"));
       newQuiz.setTitle(newtitle);
       quizDAO.save(newQuiz);
    }


   public void addQuestion(int id, List<Question> question) {
        Quiz quiz= quizDAO.findById(id).get();
        //quiz.getQuestions().addAll(question);
        for (Question questions : question) {
            questions.setQuizID(quiz); // setting the back-reference from question to quiz
        }
        quiz.setQuestions(question);
        quizDAO.save(quiz);
    }

    public void updateQuestion( int id,int index, String question) {
        Question question1=questionDAO.findById(index).get();

        if (question1.getQuizID().getId() != id) {
            throw new IllegalArgumentException("Question does not belong to the specified quiz.");
        }
        question1.setQuestion(question);
        questionDAO.save(question1);
    }


    public void updateAnswer(int id, int index, int ans) {
        Question question=questionDAO.findById(index).get();
        if (question.getQuizID().getId() != id) {
            throw new IllegalArgumentException("Question does not belong to the specified quiz.");
        }
        question.setCorrectAnswerIndex(ans);
        questionDAO.save(question);

    }


    public void updateChoice(int id, int index, List<String> option) {
        Question question1=questionDAO.findById(index).get();
        if(question1.getQuizID().getId()!=id){
            throw new IllegalArgumentException("Question not found");
        }
        question1.setOptions(option);
        questionDAO.save(question1);
    }

    public void updateDificulty(int id, int index, int dificultylevel) {
        Question question=questionDAO.findById(index).get();
        if(question.getQuizID().getId()!=id){
            throw new IllegalArgumentException("Question not found");
        }
        question.setDificultyLevel(dificultylevel);
        questionDAO.save(question);

    }*/
}
