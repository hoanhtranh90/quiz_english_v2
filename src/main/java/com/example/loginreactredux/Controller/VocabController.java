package com.example.loginreactredux.Controller;

import com.example.loginreactredux.Model.quiz.Vocabulary;
import com.example.loginreactredux.Repository.Quiz.TopicVocabRepository;
import com.example.loginreactredux.Repository.Quiz.VocabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vocab")
public class VocabController {
    @Autowired
    VocabRepository vocabRepository;
    @Autowired
    TopicVocabRepository topicVocabRepository;
    @GetMapping
    public ResponseEntity<?> vocabRandom(){
        return ResponseEntity.ok(vocabRepository.listVocabulary());
    }
    @PostMapping
    public ResponseEntity<?> createVocab(@RequestBody Vocabulary vocabulary){
        vocabRepository.save(vocabulary);
//        Vocabulary vocabulary = new Vocabulary();
        return ResponseEntity.ok(vocabRepository.findById(vocabulary.getId()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> vocabTopic(@PathVariable("id") long id){
        return ResponseEntity.ok(vocabRepository.listVocabularyTopic(id));
    }
}
