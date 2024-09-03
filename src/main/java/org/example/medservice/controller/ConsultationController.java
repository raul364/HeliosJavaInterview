package org.example.medservice.controller;

import org.example.medservice.service.ConsultationService;
import org.example.medservice.model.ConsultationResult;
import org.example.medservice.model.Answer;
import org.example.medservice.model.Question;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return consultationService.getQuestions();
    }

    @PostMapping("/submit")
    public ConsultationResult submitAnswers(@RequestBody List<Answer> answers) {
        return consultationService.processConsultation(answers);
    }
}
