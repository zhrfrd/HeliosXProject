package com.zhrfrd.controller;

import com.zhrfrd.model.Answer;
import com.zhrfrd.model.Eligibility;
import com.zhrfrd.model.Question;
import com.zhrfrd.service.ConsultationService;
import com.zhrfrd.service.ConsultationProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heliosx/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;
    private final ConsultationProvider consultationProvider;

    public ConsultationController(ConsultationService consultationService, ConsultationProvider consultationProvider) {
        this.consultationService = consultationService;
        this.consultationProvider = consultationProvider;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return consultationProvider.getQuestions();
    }

    @PostMapping("/answers")
    public Eligibility submitAnswers(@RequestBody List<Answer> answers) {
        return consultationService.checkEligibility(answers);
    }
}
