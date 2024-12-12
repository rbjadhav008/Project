package com.onlinequiz.dao;

import com.onlinequiz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionDAO extends JpaRepository<Question,Integer> {
    Optional<Question> findByQuestionAndTitleOfQuiz(String question, String titleOfQuiz);
     List<Question> findAllQuestionsByTitleOfQuiz(String quizTitle);
}
