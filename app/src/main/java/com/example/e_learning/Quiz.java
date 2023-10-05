package com.example.e_learning;

public class Quiz {
   String question,firstAnswer,secondAnswer,thirdAnswer,fourAnswer;
    int answer;
    public Quiz(String question, String firstAnswer, String secondAnswer, String thirdAnswer, String fourAnswer, int answer) {
        this.question = question;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourAnswer = fourAnswer;
        this.answer = answer;
    }
    public Quiz() {
    }
    public String getQuestion() {
        return question;
    }
    public String getFirstAnswer() {
        return firstAnswer;
    }
    public String getSecondAnswer() {
        return secondAnswer;
    }
    public String getThirdAnswer() {
        return thirdAnswer;
    }
    public String getFourAnswer() {
        return fourAnswer;
    }
    public int getAnswer() {
        return answer;
    }


}
