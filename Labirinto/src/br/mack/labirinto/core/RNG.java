package br.mack.labirinto.core;

import java.util.Random;

public class RNG {
    private final Random rand;

    public RNG(long seed) {
        this.rand = new Random(seed);
    }

    // valores de tesouro (10..50)
    public int treasureValue() {
        return 10 + rand.nextInt(41);
    }

    // penalidade armadilha (20 fixo)
    public int trapPenalty() {
        return 20;
    }
}
