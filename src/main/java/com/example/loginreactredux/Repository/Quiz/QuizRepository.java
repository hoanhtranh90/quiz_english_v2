package com.example.loginreactredux.Repository.Quiz;

import com.example.loginreactredux.Model.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("select q from QuizChoice q where q.id = :id")
    Quiz findVById(Long id);

    @Query(nativeQuery=true, value="SELECT * FROM quiz ORDER BY RAND() LIMIT 10")
    List<Quiz> listQues();
}
