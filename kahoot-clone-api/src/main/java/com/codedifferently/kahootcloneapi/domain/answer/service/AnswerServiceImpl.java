package com.codedifferently.kahootcloneapi.domain.answer.service;

import com.codedifferently.kahootcloneapi.domain.answer.exceptions.ResourceNotFoundException;
import com.codedifferently.kahootcloneapi.domain.answer.model.Answer;
import com.codedifferently.kahootcloneapi.domain.answer.repo.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepo answerRepo;

    @Autowired
    public AnswerServiceImpl(AnswerRepo answerRepo) {this.answerRepo = answerRepo;}

    @Override
    public Optional<Answer> getById(UUID id) throws ResourceNotFoundException {
        Optional<Answer> answer = answerRepo.findById(id);
        if (answer.isEmpty()) {
            throw new ResourceNotFoundException("Answer not found with ID: " + id);
        }
        return answer;
    }

    @Override
    public List<Answer> getAll() {
        return answerRepo.findAll();
    }

    @Override
    public Boolean delete(UUID id) throws ResourceNotFoundException {
        Optional<Answer> answer = getById(id);
        if (answer.isPresent()) {
            answerRepo.delete(answer.get());
            return true;
        }
        return false;
    }
}
