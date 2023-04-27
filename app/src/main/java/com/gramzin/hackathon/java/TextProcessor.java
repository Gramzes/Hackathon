package com.gramzin.hackathon.java;

import static com.gramzin.hackathon.java.aot.WordformMeaning.lookupForMeanings;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {

    BufferedReader txtFile;
    private final RootFinder finder = new RootFinder();

    private static class ProcessingWord implements Comparable<ProcessingWord> {
        private static int lastId = 0;

        final int id;
        final String word;
        final int length;
        final int index;
        String lemma;
        List<RootFinder.Scope> scopes;
        Set<ProcessingWord> children = new HashSet<>();
        boolean hasParent = false;

        ProcessingWord(String word, int index) {
            id = lastId++;
            this.word = word;
            this.index = index;
            this.length = word.length();
        }

        @Override
        public int compareTo(ProcessingWord o) {
            return o.length - length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProcessingWord that = (ProcessingWord) o;

            if (index != that.index) return false;
            return word.equals(that.word);
        }

        @Override
        public int hashCode() {
            int result = word.hashCode();
            result = 31 * result + index;
            return result;
        }

        @Override
        public String toString() {
            return word + "(" + index + ")";
        }
    }

    private static final char[] badChars = new char[]{' ', ',', '.', '?', '!', ':', '\n', '\t', '«', '»'};

    List<ProcessingWord> getWords(String text) {
        List<ProcessingWord> words = new ArrayList<>();

        StringBuilder b = new StringBuilder();
        var arr = text.toLowerCase().toCharArray();
        int i;
        for (i = 0; i < arr.length; i++) {
            var c = arr[i];
            boolean has = false;
            for (var bad : badChars) {
                if (bad == c) {
                    has = true;
                    break;
                }
            }
            if (has) {
                if (b.length() > 0) {
                    words.add(new ProcessingWord(b.toString(), i - b.length()));
                    b.delete(0, b.length());
                }
            } else
                b.append(c);
        }

        if (b.length() > 0) {
            words.add(new ProcessingWord(b.toString(), i - b.length()));
            b.delete(0, b.length());
        }

        return words;
    }

    void unionScopes(Map<String, List<ProcessingWord>> map) {
        for (var key : map.keySet()) {
            var scopeWords = map.get(key);
            scopeWords.sort((a, b) -> a.length - b.length == 0 ? a.id - b.id : b.length - a.length);

            var st = scopeWords.get(0);

            for (int i = 1; i < scopeWords.size(); i++) {
                var nd = scopeWords.get(i);
                nd.hasParent = true;
                st.children.add(nd);

                if (st.word.equals(nd.word)) {
                    st.children.addAll(nd.children);
                    nd.children.clear();
                }
            }
        }
    }

    Map<String, List<ProcessingWord>> sortToScopes(List<ProcessingWord> words) {
        Map<String, List<ProcessingWord>> map = new HashMap<>();

        for (var w : words) {
            List<RootFinder.Scope> scopes = finder.getScopes(w.lemma);
            w.scopes = scopes;

            for (var scope : scopes) {
                if (!map.containsKey(scope.root))
                    map.put(scope.root, new ArrayList<>());
                map.get(scope.root).add(w);
            }
        }

        return map;
    }

    ResultWord buildHierarchy(ProcessingWord word) {
        Set<String> processedChildren = new HashSet<>();

        var indexes = new ArrayList<>(List.of(word.index));
        for (var child : word.children) {
            if (child.lemma.equals(word.lemma))
                indexes.add(child.index);
        }

        Collections.sort(indexes);

        var rw = new ResultWord(word.lemma);

        rw.indexes = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++)
            rw.indexes[i] = indexes.get(i);

        var childrenSorted = new ArrayList<>(word.children.stream().collect(Collectors.toList()));
        childrenSorted.sort((a, b) -> b.children.size() - a.children.size());

        for (var c : childrenSorted) {
            if (c.lemma.equals(word.lemma) || processedChildren.contains(c.lemma))
                continue;
            processedChildren.add(c.lemma);
            rw.addChild(buildHierarchy(c));
        }
        return rw;
    }

    List<ResultWord> buildResult(List<ProcessingWord> words) {
        List<ResultWord> resultWords = new ArrayList<>();

        for (var e : words) {
            if (e.hasParent)
                continue;

            resultWords.add(buildHierarchy(e));
        }

        return resultWords;
    }

    public List<ResultWord> process(String text) {
        text = text.replaceAll("[^а-яА-ЯёЁ]", " ");
        List<ProcessingWord> words = getWords(text);
        System.out.println("\nGOT: " + words.size() + " words \n");
        for (var w : words) {
            var meanings = lookupForMeanings(w.word);
            if (meanings.size() > 0)
                w.lemma = meanings.get(0).getLemma().toString();
            else
                w.lemma = w.word;
        }

        var map = sortToScopes(words);
        unionScopes(map);

        var res = buildResult(words);

        System.out.println("OUT " + res.size() + " roots");

        return res;
    }
}
