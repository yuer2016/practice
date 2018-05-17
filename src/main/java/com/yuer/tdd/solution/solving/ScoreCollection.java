package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.api.Scoreable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable){
        Objects.requireNonNull(scoreable);
        scores.add(scoreable);
    }

    public int arithmeticMean(){
        if (scores.size() == 0){
            return  0;
        }else {
            int total = scores.stream().mapToInt(Scoreable::getScore).sum();
            return total / scores.size();
        }
    }

}
