package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.api.Scoreable;
import com.yuer.tdd.solution.solving.utils.Weight;

public class Criterion implements Scoreable {
    private Answer answer;
    private Weight weight;
    private int score;

    public Criterion(Answer answer, Weight weight) {
        this.answer = answer;
        this.weight = weight;
    }

    public boolean matches(Answer answer){
        return getWeight() == Weight.DontCare ||
              answer.match(getAnswer());
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Weight getWeight() {
        return weight;
    }

    @Override
    public int getScore() {
        return score;
    }
}
