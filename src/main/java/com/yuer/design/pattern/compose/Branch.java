package com.yuer.design.pattern.compose;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Corp {

    private final List<Corp> corps = new ArrayList<>();

    public Branch(String name, String position, double salary) {
        super(name, position, salary);
    }

    public void addSubordinate(Corp corp){
       this.corps.add(corp);
    }

    public void removeSubordinate(Corp corp){
        this.corps.remove(corp);
    }

    public List<Corp> getSubordinate(){
        return this.corps;
    }

}
