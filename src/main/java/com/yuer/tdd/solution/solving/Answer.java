package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.api.Question;

public class Answer {
    private int index;
    private Question question;

    public Answer(int index, Question question) {
        this.index = index;
        this.question = question;
    }

    public Answer(Question question,String matchingValue){
        this.question = question;
        this.index = question.indexOf(matchingValue);
    }

    @Override
    public String toString() {
        return String.format("%s %s",question.getText(),question.getAnswerChoice(index));
    }

   public boolean match(int expected){
        return question.match(expected,index);
   }

   public boolean match(Answer otherAnswer){
        return question.match(index,otherAnswer.index);
   }

    public String getQuestuionText(){
        return question.getText();
    }

    public Question getQuestion() {
        return question;
    }
}
