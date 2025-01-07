package io.github.neonteam10;

import java.io.*;
import java.util.*;

public class Leaderboard {
    Map<String, Integer> scores;
    String filename;
    public Leaderboard(String filename) {
        this.filename = filename;
        readLeaderboard();
        System.out.println(getTopFive().toString());
    }

    // Typical usage after user selected:
    // leaderboard.addEntry("NEON10", (int) (satisfaction * 10));
    // leaderboard.writeLeaderboard();
    // score should be * 10.
    public void writeLeaderboard() {
        try {
            File file = new File(filename);
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(scores);
            objectStream.flush();
            objectStream.close();
            fileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readLeaderboard() {
        try {
            File file = new File(filename);
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            scores = (Map<String, Integer>) objectStream.readObject();

            objectStream.close();
            fileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            scores = new HashMap<>();
            scores.put("BEN", 37000);
        }
    }

    public void addEntry(String username, int score) {
        scores.put(username, score);
    }

    public List<Map.Entry<String, Integer>> getTopFive() {
        Set<Map.Entry<String, Integer>> entries = scores.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<Map.Entry<String, Integer>> topFive = new ArrayList<>();
        for (int i = 0; i < 5 && i < list.size(); i++) {
            topFive.add(list.get(i));
        }
        return topFive;
    }

    @Override
    public String toString() {
        return scores.toString();
    }
}
