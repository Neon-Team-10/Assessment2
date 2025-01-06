package io.github.neonteam10;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Leaderboard {
    Map<String, Integer> scores;
    String filename;
    public Leaderboard(String filename) {
        this.filename = filename;
        readLeaderboard();
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

    @Override
    public String toString() {
        return scores.toString();
    }
}
