package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.api.Question;

public class BooleanQuestion extends Question {

    public BooleanQuestion(int id, String text) {
        super(id, text, new String[]{"Yes","No"});
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected == actual;
    }
}
