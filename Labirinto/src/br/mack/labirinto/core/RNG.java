package br.mack.labirinto.core;

import java.util.Random;

/**
 * Gerador de valores pseudoaleatórios para tesouros e armadilhas.
 */
public class RNG {
    private final Random rand;

    public RNG(int seed) {
        this.rand = new Random(seed);
    }

    /**
     * Gera um valor de tesouro entre 10 e 50 baseado na posição.
     */
    public int treasureScore(int row, int col) {
        return 10 + rand.nextInt(41); // 10 a 50
    }

    /**
     * Gera valor de penalidade de armadilha (sempre -20).
     */
    public int trapPenalty() {
        return -20;
    }
}
