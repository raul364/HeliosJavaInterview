package org.example.medservice.service;

import org.example.medservice.model.Answer;
import org.example.medservice.model.Question;
import org.example.medservice.model.ConsultationResult;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService {

    private final List<Question> questions = new ArrayList<>();

    @PostConstruct
    public void init() {
        questions.add(new Question("1", "Do you have any known allergies?"));
        questions.add(new Question("2", "Are you currently taking any other medication?"));
        questions.add(new Question("3", "Have you experienced any adverse reactions to medications in the past?"));
        questions.add(new Question("4", "What is your age?"));
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public ConsultationResult processConsultation(List<Answer> answers) {
        boolean eligible = true;
        String message = "You are eligible for the medication.";

        for (Answer answer : answers) {
            if (answer.getQuestionId().equals("3") && answer.getAnswer().equalsIgnoreCase("yes")) {
                eligible = false;
                message = "You are not eligible for the medication due to a past adverse reaction.";
                break;
            }
        }

        return new ConsultationResult(eligible, message);
    }
}
