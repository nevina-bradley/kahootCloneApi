package com.codedifferently.kahootcloneapi.answer;

import com.codedifferently.kahootcloneapi.domain.answer.controller.AnswerController;
import com.codedifferently.kahootcloneapi.domain.answer.exceptions.ResourceNotFoundException;
import com.codedifferently.kahootcloneapi.domain.answer.model.Answer;
import com.codedifferently.kahootcloneapi.domain.answer.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    @DisplayName("GET /api/v1/answer/1 - Found")
    public void getByIdValidTest() throws Exception {
        Answer mockAnswer = new Answer();
        mockAnswer.setId(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        mockAnswer.setValue(1);
        mockAnswer.setCorrect(true);

        BDDMockito.when(answerService.getById(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e")))
                .thenReturn(Optional.of(mockAnswer));

        mockMvc.perform(get("/api/v1/answer/{id}", "4d7dc0da-8759-4b25-bf63-396d8b9b986e"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("4d7dc0da-8759-4b25-bf63-396d8b9b986e")))
                .andExpect(jsonPath("$.value", is(1)))
                .andExpect(jsonPath("$.correct", is(true)));
    }

    @Test
    @DisplayName("GET /api/v1/answer/1 - Not Found")
    public void getByIdNotFoundTest() throws Exception {
        BDDMockito.doThrow(new ResourceNotFoundException("Not Found")).when(answerService).getById(UUID.fromString("4d7dc0da-8759-4b25-bf63-396d8b9b986e"));
        mockMvc.perform(get("/api/v1/answer/{id}", "4d7dc0da-8759-4b25-bf63-396d8b9b986e"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v1/answer/1 - Success")
    public void deleteTestSuccess() throws Exception {
        BDDMockito.doReturn(true).when(answerService).delete(any());
        mockMvc.perform(delete("/api/v1/answer/{id}", "4d7dc0da-8759-4b25-bf63-396d8b9b986e"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/v1/answer/1 - Not Found")
    public void deleteTestNotFound() throws Exception {
        BDDMockito.doThrow(new ResourceNotFoundException("Not Found")).when(answerService).delete(any());
        mockMvc.perform(delete("/api/v1/answer/{id}", "4d7dc0da-8759-4b25-bf63-396d8b9b986e"))
                .andExpect(status().isNotFound());
    }
}
