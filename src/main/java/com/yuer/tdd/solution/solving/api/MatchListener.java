package com.yuer.tdd.solution.solving.api;

import com.yuer.tdd.solution.solving.MatchSet;
import com.yuer.tdd.solution.solving.Profile;

public interface MatchListener {
    void foundMatch(Profile profile, MatchSet matchSet);
}
