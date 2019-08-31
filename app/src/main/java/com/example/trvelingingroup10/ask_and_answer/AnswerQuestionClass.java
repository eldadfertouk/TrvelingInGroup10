package com.example.trvelingingroup10.ask_and_answer;

public class AnswerQuestionClass {
    private String answer;
    private String question;
    private boolean answered;

    public AnswerQuestionClass(String answer, String question, boolean answered) {
        this.answer = answer;
        this.question = question;
        this.answered=answered;
    }

    public AnswerQuestionClass() {
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String qestion) {
        this.question = qestion;
    }
}
