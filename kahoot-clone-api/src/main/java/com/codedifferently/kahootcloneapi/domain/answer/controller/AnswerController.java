package com.codedifferently.kahootcloneapi.domain.answer.controller;

import com.codedifferently.kahootcloneapi.domain.answer.model.Answer;
import com.codedifferently.kahootcloneapi.domain.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {this.answerService = answerService; }

    @GetMapping
    public ResponseEntity<List<Answer>> getAll() {
        List<Answer> answers = answerService.getAll();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Answer> getById(@PathVariable("id") UUID id) {
        Optional<Answer> answer = answerService.getById(id);

        if (answer.isPresent()) {
            return new ResponseEntity<>(answer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        answerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
