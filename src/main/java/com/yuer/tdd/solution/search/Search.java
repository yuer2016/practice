package com.yuer.tdd.solution.search;


import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class Search {
    private String searchString;
    private String searchTitle;
    private InputStream input;
    private Exception exception = null;
    private List<Match> matches =  new ArrayList<>();

    private int surroundingCharacterCount = 100;

    public Search(String urlString,String searchString, String searchTitle) throws IOException {
        this(new URL(urlString).openStream(),searchString,searchTitle);
    }

    public Search(InputStream input, String searchString,String searchTitle) {
        this.input = input;
        this.searchString = searchString;
        this.searchTitle = searchTitle;
    }

    public void execute(){
        try {
            search();
        } catch (IOException e) {
            exception = e;
        }
    }

    private void addMatches(String line, Pattern pattern){
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            start -= surroundingCharacterCount;
            if (start < 0){
              start = 0;
            }
            end += surroundingCharacterCount;
            if (end >= line.length()){
                end = line.length();
            }
            matches.add(new Match(searchTitle,searchString,line.substring(start,end)));
        }
    }

    private void search() throws IOException {
        Pattern pattern = Pattern.compile(searchString);
        matches.clear();
        log.info("search matcher for pattern {}",pattern);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
            String line;
            while ((line = reader.readLine())!= null){
                addMatches(line,pattern);
            }
        }

    }

    public List<Match> getMatches() {
        return matches;
    }
    public boolean iserrord(){
        return exception != null;
    }
    public Exception getError(){
        return  exception;
    }
    public void setSurroundingCharacterCount(int count){
        this.surroundingCharacterCount = count;
    }
}
