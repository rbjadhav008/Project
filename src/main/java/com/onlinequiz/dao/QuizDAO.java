package com.onlinequiz.dao;

import com.onlinequiz.models.Question;
import com.onlinequiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuizDAO extends JpaRepository<Quiz,Integer> {

    boolean existsByTitle(String title);

}
