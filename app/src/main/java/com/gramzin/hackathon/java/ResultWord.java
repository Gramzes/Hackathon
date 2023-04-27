package com.gramzin.hackathon.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultWord {
    public String word;
    public int[] indexes;
    public List<ResultWord> children;

    public ResultWord(String word) {
        this.word = word;
        this.children = new ArrayList<>();
    }

    public void addChild(ResultWord resultWord) {
        children.add(resultWord);
    }

    public void print(String tab) {
        System.out.println(tab + word + ", " + Arrays.toString(indexes));
        for(var c : children)
            c.print("  " + tab);
    }
}
