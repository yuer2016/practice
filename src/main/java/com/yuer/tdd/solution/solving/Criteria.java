package com.yuer.tdd.solution.solving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Criteria implements Iterable<Criterion> {
    private List<Criterion> criteria = new ArrayList<>();

    public void add(Criterion criterion){
        criteria.add(criterion);
    }

    private int arithmeticMean(){
        return 0;
    }

    private double geometicMean(int[] numbers){
        int totalProduct = Arrays.stream(numbers).reduce(1, (product, number) -> product * number);
        return Math.pow(totalProduct,1.0 / numbers.length);
    }

    @Override
    public Iterator<Criterion> iterator() {
        return criteria.iterator();
    }
}
