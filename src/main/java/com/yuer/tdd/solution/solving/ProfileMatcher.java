package com.yuer.tdd.solution.solving;

import com.yuer.tdd.solution.solving.api.MatchListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ProfileMatcher {
    private Map<String,Profile> profiles = new HashMap<>();

    public void add(Profile profile){
        profiles.put(profile.getId(),profile);
    }

    public void findMatchingProfiles(Criteria criteria, MatchListener matchListener){
        int DEFAULT_POOL_SIZE = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
        List<MatchSet> matchSets = profiles.values()
                .stream()
                .map(profile -> profile.getMatchSet(criteria))
                .collect(Collectors.toList());

        matchSets.forEach(matchSet -> executorService.execute(() ->{
            if (matchSet.matches()){
              matchListener.foundMatch(profiles.get(matchSet.getProfileId()),matchSet);
            }
        }));
        executorService.shutdown();
    }

}
