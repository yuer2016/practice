package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.api.Question;

public class PercentileQuestion extends Question {
    public PercentileQuestion(int id, String text, String[] answerChoices) {
        super(id, text, answerChoices);
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected <= actual ;
    }
}
