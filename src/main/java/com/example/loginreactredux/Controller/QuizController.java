package com.example.loginreactredux.Controller;

import com.example.loginreactredux.Model.quiz.Quiz;
import com.example.loginreactredux.Repository.Quiz.QuizChoiceRepository;
import com.example.loginreactredux.Repository.Quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizChoiceRepository quizChoiceRepository;

    @GetMapping
    public ResponseEntity<?> getAllVote(){

        return ResponseEntity.ok(quizRepository.listQues());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVote(@PathVariable("id") Long vode_id) {
        return ResponseEntity.ok(quizRepository.findById(vode_id));
    }
    @PostMapping
    public ResponseEntity<?> createVote(@RequestBody Quiz quiz) {
        quizRepository.save(quiz);
        return ResponseEntity.ok(quiz);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> EditAcc(@PathVariable("id") Long quiz_id,
                                     @RequestBody Quiz quiz){


        //ham viet trong service
//        voteChoiceRepository.deleteByVoteId(vote_id);
        quizChoiceRepository.deleteByVoteId(quiz_id);
        Quiz v = quizRepository.findVById(quiz_id);
        v.setQuestionText(quiz.getQuestionText());
        v.setQuizChoices(quiz.getQuizChoices());
        quizRepository.save(v);

        //
        return new ResponseEntity("Update thanh cong", HttpStatus.OK);
    }



}
