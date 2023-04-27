package com.gramzin.hackathon.java;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class MainTih {

    public static void main(String[] args) {
        try {
            Set<String> bad = Set.of("всё-таки из-под из-за".split(" "));
            for (var f : Objects.requireNonNull(new File("tih").listFiles())) {
                var r = new BufferedReader(new FileReader(f));
                var ow = new BufferedWriter(new FileWriter("tih-2/" + f.getName()));

                String line;
                Set<String> strings = new HashSet<>();
                while ((line = r.readLine()) != null) {
                    if(line.matches("(.+?-(таки|то|сям))|((так|такой|тет-а|то)-.+?)")) {
                        System.out.println("SKIP: " + line);
                        continue;
                    }

                    if (line.startsWith("<p>")) {
                        var s = line.replaceAll("(<.+?>)|(\\d+)|([\\[(].+?[])])|(,.+)|(')", "").trim();
                        s = s.replaceAll("/$", "");
                        s = s.replaceAll("(( и )|:|<).+", "");

                        var right = s.indexOf(' ');
                        right = s.indexOf(' ', right + 1);
                        right = s.indexOf(' ', right + 1);

                        if (right > 0)
                            s = s.substring(0, right);

                        if (s.indexOf(' ') > 0 && s.indexOf('|') > 0 && !s.endsWith("|") && !s.endsWith("-") && !s.endsWith("."))
                            strings.add(s);
                        else
                            System.out.println("IGNORED: " + line + " " + s);
                    }
                }
                List<String> ss = new ArrayList<>(strings.stream().collect(Collectors.toList()));
                Collections.sort(ss);

                for (var s : ss)
                    ow.write(s + "\n");
                ow.close();
                r.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}