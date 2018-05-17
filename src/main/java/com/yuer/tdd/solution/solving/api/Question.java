package com.yuer.tdd.solution.solving;

public abstract class Question {
    private int id;
    private String text;
    private String[] answerChoices;

    public Question(int id, String text, String[] answerChoices) {
        this.id = id;
        this.text = text;
        this.answerChoices = answerChoices;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswerChoice(int index) {
        return answerChoices[index];
    }

    public boolean match(Answer answer){
        return false;
    }

    abstract public boolean match(int expected, int actual);

    public int indexOf(String matchingAnswerChoice){
        int index = -1;
        for (int i = 0; i< answerChoices.length; i++) {
            if (answerChoices[i].equals(matchingAnswerChoice)){
                index = i;
            }
        }
        return index;
    }
}
