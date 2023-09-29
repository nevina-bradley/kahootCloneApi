package com.codedifferently.kahootcloneapi.domain.answer.service;

import com.codedifferently.kahootcloneapi.domain.answer.exceptions.ResourceNotFoundException;
import com.codedifferently.kahootcloneapi.domain.answer.model.Answer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerService {

    Optional<Answer> getById(UUID id) throws ResourceNotFoundException;

    List<Answer> getAll();

    Boolean delete(UUID id);
}
