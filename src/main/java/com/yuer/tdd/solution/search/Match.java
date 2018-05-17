package com.yuer.tdd.solution.search;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Match {
    private final String searchTitle;
    private final String searchString;
    private final String surroundingContext;

    public Match(String searchTitle, String searchString, String surroundingContext) {
        this.searchTitle = searchTitle;
        this.searchString = searchString;
        this.surroundingContext = surroundingContext;
    }
}
