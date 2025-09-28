package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;
import java.io.*;
import java.util.*;

public class Scoreboard {
    private final String filePath = "ranking.csv";
    private final List<ScoreEntry> scores = new ArrayList<>();

    public Scoreboard() {
        load();
    }

    private void load() {
        File f = new File(filePath);
        if (!f.exists()) return;
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length == 2) {
                    scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ranking: " + e.getMessage());
        }
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (ScoreEntry s : scores) {
                pw.println(s.getPlayerName() + "," + s.getScore());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }

    public void addScore(String player, int score) {
        scores.add(new ScoreEntry(player, score));
        ScoreEntry[] arr = scores.toArray(new ScoreEntry[0]);
        Sorts.quickSort(arr, 0, arr.length - 1); // ordena decrescente
        scores.clear();
        scores.addAll(Arrays.asList(arr));
        save();
    }

    public void printTop10() {
        System.out.println("=== Ranking TOP 10 ===");
        for (int i = 0; i < Math.min(10, scores.size()); i++) {
            System.out.println((i + 1) + ". " + scores.get(i));
        }
    }

    public int findPlayer(String name) {
        ScoreEntry[] arr = scores.toArray(new ScoreEntry[0]);
        return Sorts.binarySearchByName(arr, name);
    }
}
