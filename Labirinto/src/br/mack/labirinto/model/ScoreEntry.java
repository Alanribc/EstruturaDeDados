package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;

/**
 * Entrada de pontuação para ranking.
 */
public class ScoreEntry implements Sorts.SortableScore {
    private final String playerName;
    private final int score;

    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return playerName + ":" + score;
    }
}
