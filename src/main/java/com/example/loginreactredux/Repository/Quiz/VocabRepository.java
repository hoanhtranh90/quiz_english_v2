package com.example.loginreactredux.Repository.Quiz;

import com.example.loginreactredux.Model.quiz.Quiz;
import com.example.loginreactredux.Model.quiz.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabRepository extends JpaRepository<Vocabulary, Long> {
    @Query(nativeQuery=true, value="SELECT * FROM vocabulary ORDER BY RAND() LIMIT 10")
    List<Vocabulary> listVocabulary();

    @Query(nativeQuery=true, value="SELECT * FROM vocabulary WHERE topicvoc_id=:id LIMIT 10")
    List<Vocabulary> listVocabularyTopic(long id);
}
