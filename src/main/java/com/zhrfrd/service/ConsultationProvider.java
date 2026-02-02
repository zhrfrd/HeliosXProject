package com.zhrfrd.service;

import com.zhrfrd.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

//---Need to use @Component because it's a shared dependency that is injected in Service and Controller---
@Component
public class ConsultationProvider {
    public List<Question> getQuestions() {
        return List.of(
                new Question(
                        "1",
                        "What is your name?",
                        Question.Type.TEXT
                ),
                new Question(
                        "2",
                        "Are you more than 18 years old.",
                        Question.Type.BOOLEAN
                ),
                new Question(
                        "3",
                        "Are you allergic to the product?",
                        Question.Type.BOOLEAN
                ),
                new Question(
                        "4",
                        "How much do you weight?",
                        Question.Type.NUMBER
                )
        );
    }
}
