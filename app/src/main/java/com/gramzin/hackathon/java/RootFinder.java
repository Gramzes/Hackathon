package com.gramzin.hackathon.java;

import java.io.*;
import java.util.*;

public class RootFinder {
    private static final Map<String, List<Scope>> wordsMap = new HashMap<>();
    public static BufferedReader txtFile;
    static class Scope {
        final String root;
        final Set<String> words;

        public Scope(String root, Set<String> words) {
            this.root = root;
            this.words = words;
        }

        @Override
        public String toString() {
            return "S{" + root + "}";
        }
    }

    public void initMeth() {
        try (var r = txtFile) {
            String line;
            while ((line = r.readLine()) != null) {
                var rawWords = line.split(" ");
                String root = rawWords[0];
                Set<String> words = new HashSet<>();
                var scope = new Scope(root, words);
                for (int i = 1; i < rawWords.length; i++) {
                    var word = rawWords[i];
                    words.add(word);
                    if (!wordsMap.containsKey(word))
                        wordsMap.put(word, new ArrayList<>(5));
                    wordsMap.get(word).add(scope);
                }
            }
            System.out.println("Loaded " + wordsMap.size() + " words");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Scope> getScopes(String word) {
        initMeth();
        if(wordsMap.containsKey(word))
            return wordsMap.get(word);
        if(!word.matches("^[а-яА-ЯёЁ]+?$"))
            return Collections.emptyList();

        var newScopes = List.of(new Scope(word, Set.of(word)));
        wordsMap.put(word, newScopes);
        return newScopes;
    }

    public boolean isSame(String first, String second) {
        var scopes = wordsMap.get(first);
        if (scopes == null)
            return false;
        for (var scope : scopes) {
            if (scope.words.contains(second))
                return true;
        }
        return false;
    }
}
