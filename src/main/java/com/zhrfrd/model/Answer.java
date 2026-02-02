package com.zhrfrd.model;

public class Answer {
    private String questionId;
    private String answer;

    public Answer(String questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }
}
