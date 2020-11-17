package com.example.loginreactredux.Repository.Quiz;

import com.example.loginreactredux.Model.quiz.TopicVoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicVocabRepository extends JpaRepository<TopicVoc,Long> {
}
