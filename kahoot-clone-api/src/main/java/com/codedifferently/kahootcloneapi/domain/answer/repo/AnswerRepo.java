package com.codedifferently.kahootcloneapi.domain.answer.repo;

import com.codedifferently.kahootcloneapi.domain.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import java.util.Optional;

public interface AnswerRepo extends JpaRepository<Answer, UUID> {
    Optional<Answer> findById(UUID id);

    void delete(Answer answer);
}
