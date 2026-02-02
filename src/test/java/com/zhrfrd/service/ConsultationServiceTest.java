package com.zhrfrd.service;

import com.zhrfrd.model.Answer;
import com.zhrfrd.model.Eligibility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationServiceTest {
    private ConsultationService consultationService;

    @BeforeEach
    void setUp() {
        ConsultationProvider consultationProvider = new ConsultationProvider();
        consultationService = new ConsultationService(consultationProvider);
    }

    @Test
    void shouldBeEligibleWhenAllAnswersAreValid() {
        List<Answer> answers = List.of(
                new Answer("1", "Farid"),
                new Answer("2", "true"),
                new Answer("3", "false"),
                new Answer("4", "70")
        );
        Eligibility eligibility = consultationService.checkEligibility(answers);

        assertTrue(eligibility.isEligible());
        assertEquals("Eligible for prescription", eligibility.getReason());
    }

    @Test
    void shouldNotBeEligibleWhenAnswer2IsFalse() {
        List<Answer> answers = List.of(
                new Answer("1", "Farid"),
                new Answer("2", "false"),
                new Answer("3", "false"),
                new Answer("4", "70")

        );
        Eligibility eligibility = consultationService.checkEligibility(answers);

        assertFalse(eligibility.isEligible());
        assertEquals("You must be over 18.", eligibility.getReason());
    }

    @Test
    void shouldThrowAnExceptionWhenAnAnswerFieldIsEmpty() {
        List<Answer> answers = List.of(
                new Answer("1", ""),
                new Answer("2", "true"),
                new Answer("3", "false")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> consultationService.checkEligibility(answers));

        assertTrue(exception.getMessage().contains("cannot be empty"));
    }

    @Test
    void shouldThrowAnExceptionWhenAQuestionIdFieldIsEmpty() {
        List<Answer> answers = List.of(
                new Answer("1", "Farid"),
                new Answer("", "true"),
                new Answer("3", "false")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> consultationService.checkEligibility(answers));

        assertTrue(exception.getMessage().contains("cannot be empty"));
    }
}