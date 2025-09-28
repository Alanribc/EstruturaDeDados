package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;
import java.util.Arrays;

/**
 * Gerencia o ranking de jogadores.
 */
public class Scoreboard {
    private ScoreEntry[] scores;
    private int count;

    public Scoreboard(int capacity) {
        scores = new ScoreEntry[capacity];
        count = 0;
    }

    /**
     * Adiciona uma entrada ao ranking.
     */
    public void addScore(ScoreEntry entry) {
        if (count >= scores.length) {
            // aumenta a capacidade se necessário
            scores = Arrays.copyOf(scores, scores.length * 2);
        }
        scores[count++] = entry;
    }

    /**
     * Ordena o ranking por pontuação usando QuickSort.
     */
    public void sort() {
        Sorts.quickSort(scores, 0, count - 1);
    }

    /**
     * Mostra os top N jogadores.
     */
    public void showTop(int n) {
        sort();
        System.out.println("=== Top " + n + " ===");
        for (int i = 0; i < Math.min(n, count); i++) {
            System.out.println(scores[i]);
        }
    }

    /**
     * Busca binária pelo nome do jogador.
     */
    public int searchByName(String name) {
        sort();
        return Sorts.binarySearchByName(scores, name);
    }
}
