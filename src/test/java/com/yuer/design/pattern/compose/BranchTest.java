package com.yuer.design.pattern.compose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BranchTest {

    private Branch root = null;

    private Branch devOne = null;

    private Branch devTwo = null;

    @BeforeEach
    void init(){
        root   = new Branch("tom","root",10000);
        devOne = new Branch("user1","p6",5000);
        devTwo = new Branch("user2","p6",5000);

        Leaf leafOne = new Leaf("leaf1", "p0", 2500);
        Leaf leafTwo = new Leaf("leaf2", "p0", 2500);
        Leaf leafThree = new Leaf("leaf3", "p0", 2500);

        devTwo.addSubordinate(leafOne);
        devOne.addSubordinate(leafTwo);
        devOne.addSubordinate(leafThree);

        root.addSubordinate(devOne);
        root.addSubordinate(devTwo);

    }

    @Test
    void addSubordinate() {
        assertThat(root.getSubordinate().size()).as("add some position").isEqualTo(2);
    }

    @Test
    void removeSubordinate() {
        root.removeSubordinate(devOne);
        root.removeSubordinate(devTwo);
        assertThat(root.getSubordinate().size()).isEqualTo(0);
    }



    @Test
    void visitGorps() {
        String treeInfo = getTreeInfo(root);
        System.out.println(treeInfo);
    }

    private String getTreeInfo(Branch branch){
        StringBuilder stringBuilder = new StringBuilder();
        List<Corp> subordinate = branch.getSubordinate();
        for (Corp corp: subordinate) {
            if (corp instanceof Leaf){
                stringBuilder.append(corp.getInfo());
            }else {
                stringBuilder.append(corp.getInfo())
                        .append(getTreeInfo((Branch) corp));
            }
        }
        return stringBuilder.toString();
    }
}