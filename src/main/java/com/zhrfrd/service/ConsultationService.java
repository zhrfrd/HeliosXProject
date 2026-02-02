package com.zhrfrd.service;

import com.zhrfrd.model.Answer;
import com.zhrfrd.model.Eligibility;
import com.zhrfrd.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsultationService {
    ConsultationProvider consultationProvider;

    public ConsultationService(ConsultationProvider consultationProvider) {
        this.consultationProvider = consultationProvider;
    }

    public Eligibility checkEligibility(List<Answer> answers) {
        Map<String, Answer> mapAnswers = new HashMap<>();

        for (Answer answer : answers) {
            validateAnswer(answer);
            mapAnswers.put(answer.getQuestionId(), answer);
        }

        for (Question question : consultationProvider.getQuestions()) {
            String answerValue = mapAnswers.get(question.getId()).getAnswer();
            validateAnswerType(question, answerValue);

            if (!mapAnswers.containsKey(question.getId())) {
                throw new IllegalArgumentException("Missing answer for question: " + question.getText());
            }
        }

        if ("false".equalsIgnoreCase(mapAnswers.get("2").getAnswer())) {
            return new Eligibility(false, "You must be over 18.");
        }
        if ("true".equalsIgnoreCase(mapAnswers.get("3").getAnswer())) {
            return new Eligibility(false, "You are allergic to the product selected.");
        }
        double weight = Double.parseDouble(mapAnswers.get("4").getAnswer());
        if (weight < 60) {
            return new Eligibility(false, "You must weight more than 60Kg.");
        }

        return new Eligibility(true, "Eligible for prescription");
    }

    public void validateAnswer(Answer answer) {
        if (answer.getQuestionId() == null || "".equals(answer.getQuestionId())) {
            throw new IllegalArgumentException("`questionId` field for answer " + answer.getQuestionId() + " cannot be empty.");
        }
        if (answer.getAnswer() == null || "".equals(answer.getAnswer())) {
            throw new IllegalArgumentException("`answer` field for answer " + answer.getQuestionId() + " cannot be empty.");
        }
    }

    private void validateAnswerType(Question question, String answer) {
        switch (question.getType()) {
            case TEXT:
                if (answer.isBlank()) {
                    throw new IllegalArgumentException("Answer for '" + question.getText() + "' must not be empty");
                }

                break;
            case BOOLEAN:
                if (!answer.equalsIgnoreCase("true") && !answer.equalsIgnoreCase("false")) {
                    throw new IllegalArgumentException("Answer for '" + question.getText() + "' must be true or false");
                }

                break;
            case NUMBER:
                try {
                    double value = Double.parseDouble(answer);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Answer for '" + question.getText() + "' must be a number");
                }

                break;
        }
    }
}
