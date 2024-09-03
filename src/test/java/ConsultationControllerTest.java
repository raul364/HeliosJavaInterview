package org.example.medservice.controller;

import org.example.medservice.model.ConsultationResult;
import org.example.medservice.model.Answer;
import org.example.medservice.model.Question;
import org.example.medservice.service.ConsultationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ConsultationController.class)
public class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultationService consultationService;

    @Test
    public void testGetQuestions() throws Exception {
        List<Question> questions = Arrays.asList(
                new Question("1", "Do you have any known allergies?"),
                new Question("2", "Are you currently taking any other medication?")
        );

        Mockito.when(consultationService.getQuestions()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/consultation/questions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].question").value("Do you have any known allergies?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].question").value("Are you currently taking any other medication?"));
    }

    @Test
    public void testSubmitAnswers() throws Exception {
        List<Answer> answers = Arrays.asList(
                new Answer("1", "No"),
                new Answer("2", "Yes")
        );

        ConsultationResult result = new ConsultationResult(true, "You are eligible for the medication.");

        Mockito.when(consultationService.processConsultation(answers)).thenReturn(result);

        mockMvc.perform(post("/api/consultation/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"questionId\":\"1\",\"answer\":\"No\"},{\"questionId\":\"2\",\"answer\":\"Yes\"}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eligible").value(true))
                .andExpect(jsonPath("$.message").value("You are eligible for the medication."));
    }
}
