package com.example.loginreactredux.Repository.Quiz;

import com.example.loginreactredux.Model.quiz.QuizChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface QuizChoiceRepository extends JpaRepository<QuizChoice, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM QuizChoice WHERE quiz.id = :quiz_id")
    void deleteByVoteId(Long quiz_id);

    @Query("select q from QuizChoice q where q.id = :answer_id")
    QuizChoice findByAnswerId(Long answer_id);
}
