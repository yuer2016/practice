package com.yuer.tdd.solution.solving;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Profile {
    private String id;
    private Map<String,Answer> answers = new HashMap<>();

    public Profile(String id) {
        this.id = id;
    }

    public void add(Answer answer){
        answers.put(answer.getQuestuionText(),answer);
    }

    public MatchSet getMatchSet(Criteria criteria){
        return new MatchSet(id,answers,criteria);
    }


    public List<Answer> find(Predicate<Answer> predicate){
        return answers.values()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return id;
    }

    public String getId() {
        return id;
    }
}
