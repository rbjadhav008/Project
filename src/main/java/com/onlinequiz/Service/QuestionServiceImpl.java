package com.onlinequiz.Service;

import com.onlinequiz.Exception.ResourceNotFoundException;
import com.onlinequiz.dao.QuestionDAO;
import com.onlinequiz.dao.QuizDAO;
import com.onlinequiz.dto.QuestionDTO;
import com.onlinequiz.models.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl {
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private ModelMapper modelMapper;
    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO questionDTO=modelMapper.map(question,QuestionDTO.class);
       /* return new QuestionDTO(
                question.getIndexOfQuestion(),
                question.getQuestion(),
                question.getOptions(),
                question.getCorrectAnswerIndex(),
                question.getDificultyLevel());

        */
        return questionDTO;
    }
    private Question convertToQuestion(QuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO,Question.class);


        /*question.setIndexOfQuestion(questionDTO.getIndexOfQuestion());
        question.setQuestion(questionDTO.getQuestion());
        question.setOptions(questionDTO.getOptions());
        question.setCorrectAnswerIndex(questionDTO.getCorrectAnswerIndex());
        question.setDificultyLevel(questionDTO.getDificultyLevel());
        return question;*/
        return question;
    }



    public QuestionDTO addQuestion(QuestionDTO questionDTO){
        try{
            Optional<Question> existingQuestion = questionDAO.findByQuestionAndTitleOfQuiz(
                    questionDTO.getQuestion(), questionDTO.getTitleOfQuiz());
            if(existingQuestion.isPresent()){
                throw new Exception("Quiz is already present");
            }
            Question question = convertToQuestion(questionDTO);
            Question question1 = questionDAO.save(question);
            QuestionDTO questionDTO1 = convertToDTO(question1);
            return questionDTO1;
        }catch (Exception ex){
            throw new RuntimeException("The question already exists in the quiz ");
        }


    }
   public List<QuestionDTO> findAllQuestionsByQuizTitle(String titleOfQuiz){
        List<Question> questions = questionDAO.findAllQuestionsByTitleOfQuiz(titleOfQuiz);
        return questions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

  }

    public Optional<QuestionDTO> getQuestionById(int id){
        Question question=questionDAO.findById(id).orElseThrow(()->new ResourceNotFoundException("question is not found with given id"));
        QuestionDTO questionDTO=convertToDTO(question);
        return Optional.of(questionDTO);
    }
   /* public Optional<Question>getQuestionByQuizId(int QuizId){
        return  questionDAO.findById(Q)
    }*/
    public List<QuestionDTO> getAllQuestion(){
        try {
        List<Question> questionList = questionDAO.findAll();
        List<QuestionDTO> questionDTOList = questionList.stream().map(s -> convertToDTO(s)).toList();
        return questionDTOList;
    }catch (RuntimeException e){
        throw new RuntimeException("Failed to fetch questions", e);
    }

    }

    public void updateQuestion(int index, String question) {
        Question question1=questionDAO.findById(index).orElseThrow(()->new ResourceNotFoundException("question with given id is not present"));
        question1.setQuestion(question);
        questionDAO.save(question1);
    }

    public void updateAnswer(int index, int ans) {
        Question question=questionDAO.findById(index).orElseThrow(()->new ResourceNotFoundException("question with given id is not present"));
        question.setCorrectAnswerIndex(ans);
        questionDAO.save(question);

    }

    public void updateOption( int index, List<String> option) {
        Question question1=questionDAO.findById(index).orElseThrow(()->new ResourceNotFoundException("question with given id is not present"));
        question1.setOptions(option);
        questionDAO.save(question1);
    }

    public void updateDificulty( int index, int dificultylevel) {
        Question question=questionDAO.findById(index).orElseThrow(()->new ResourceNotFoundException("question with given id is not present"));
        question.setDificultyLevel(dificultylevel);
        questionDAO.save(question);

    }

    public void deleteQuestion(int id) {
        if(!questionDAO.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        try {
            questionDAO.deleteById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete the question", ex);
        }

    }







}
