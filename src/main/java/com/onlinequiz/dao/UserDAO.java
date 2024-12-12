package com.onlinequiz.dao;

import com.onlinequiz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
