package com.yuer.design.pattern.compose;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Corp {
    private String name;
    private String position;
    private double salary;

    public Corp(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getInfo(){
        return this.toString()+"\n";
    }

}
