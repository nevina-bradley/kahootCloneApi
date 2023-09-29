package com.codedifferently.kahootcloneapi.answer;

import com.codedifferently.kahootcloneapi.domain.answer.exceptions.ResourceNotFoundException;
import com.codedifferently.kahootcloneapi.domain.answer.model.Answer;
import com.codedifferently.kahootcloneapi.domain.answer.repo.AnswerRepo;
import com.codedifferently.kahootcloneapi.domain.answer.service.AnswerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AnswerServiceImplTest {
    @MockBean
    private AnswerRepo answerRepo;

    @Autowired
    private AnswerService answerService;

    private Answer inputAnswer;

    private Answer mockAnswer1;

    private Answer mockAnswer2;

    @BeforeEach
    public void setUp() {
        mockAnswer1 = new Answer();
        mockAnswer1.setId(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));

        mockAnswer2 = new Answer();
        mockAnswer2.setId(UUID.fromString("f8e6a5c9-2dc8-4e12-9f87-1a3b6f4d5e7a"));

        inputAnswer = new Answer(1);
        inputAnswer.setValue(1);
        inputAnswer.setCorrect(true);
    }

    @Test
    @DisplayName("Answer: Get Answer by Id - Success")
    public void getByIdValidTest() throws ResourceNotFoundException {
        UUID targetId = UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e");
        BDDMockito.doReturn(Optional.of(mockAnswer1)).when(answerRepo).findById(targetId);

        Optional<Answer> foundAnswer = answerService.getById(targetId);

        Assertions.assertTrue(foundAnswer.isPresent(), "Expected an answer to be found");
        Assertions.assertEquals(mockAnswer1, foundAnswer.get());
    }

    @Test
    @DisplayName("Answer: Get Answer by Id - Fail")
    public void getByIdNotFoundTest() {
        BDDMockito.doReturn(Optional.empty()).when(answerRepo).findById(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            answerService.getById(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        });
    }

    @Test
    @DisplayName("Answer: Get All Answers - Success")
    public void getAllTestSuccess() {
        List<Answer> answers = new ArrayList<>();
        Answer.add(mockAnswer1);
        Answer.add(mockAnswer2);

        BDDMockito.doReturn(answers).when(answerRepo).findAll();

        List<Answer> responseAnswers = answerService.getAll();
        Assertions.assertIterableEquals(answers, responseAnswers);
    }

    @Test
    @DisplayName("Answer: Delete Answer - Success")
    public void deleteSuccessTest() throws ResourceNotFoundException {
        BDDMockito.doReturn(Optional.of(mockAnswer1)).when(answerRepo).findById(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        Boolean actualResponse = answerService.delete(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        Assertions.assertTrue(actualResponse);
    }

    @Test
    @DisplayName("Answer: Delete Answer - Fail")
    public void deleteNotFoundTest() {
        BDDMockito.doReturn(Optional.empty()).when(answerRepo).findById(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            answerService.delete(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        });
    }
}
