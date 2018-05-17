package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.utils.Weight;

import java.util.Map;

public class MatchSet implements Comparable<MatchSet> {
    private String profileId;
    private Criteria criteria;
    private int score = Integer.MIN_VALUE;
    private Map<String,Answer> answers ;

    public MatchSet(String profileId, Map<String, Answer> answers,Criteria criteria) {
        this.profileId = profileId;
        this.criteria = criteria;
        this.answers = answers;
    }

    public int getScore() {
        if(score == Integer.MIN_VALUE){
            calculateScore();
        }
        return score;
    }

    private void calculateScore(){
        score = 0;
        criteria.forEach(criterion -> {
           if (criterion.matches(answerMatching(criterion))){
              score += criterion.getWeight().getValue();
           }
        });
    }

    private Answer answerMatching(Criterion criterion){
        return answers.get(criterion.getAnswer().getQuestuionText());
    }

    public boolean matches(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        if (doesNotMeetAnyMustMatchCriteria()){
            return false;
        }
        return anyMatches();
    }

    private boolean doesNotMeetAnyMustMatchCriteria(){
        for (Criterion criterion: criteria) {
            boolean matches = criterion.matches(answerMatching(criterion));
            if(!matches && criterion.getWeight() == Weight.MustMatch ){
                return true;
            }
        }
       return false;
    }

    private boolean anyMatches(){
        boolean anyMatches = false;
        for (Criterion criterion: criteria) {
            anyMatches |= criterion.matches(answerMatching(criterion));
        }
        return anyMatches;
    }


    @Override
    public int compareTo(MatchSet that) {
        return Integer.compare(getScore(), that.getScore());
    }

    public String getProfileId() {
        return profileId;
    }


}
