//Alan Ribeiro do Carmo 10428496
//Erik Yutaka Takara 10427684

package br.mack.labirinto.core;

import java.util.Random;

/**
 * Classe utilitária para gerar valores aleatórios no jogo,
 * como valores de tesouros e penalidades de armadilhas.
 */
public class RNG {
    private final Random rand;

    /**
     * Construtor que inicializa o gerador de números aleatórios
     * com uma semente fixa.
     *
     * @param seed valor usado para inicializar a aleatoriedade
     */
    public RNG(long seed) {
        this.rand = new Random(seed);
    }

    /**
     * Gera o valor de um tesouro.
     * O valor está entre 10 e 50 pontos.
     *
     * @return valor do tesouro
     */
    public int treasureValue() {
        return 10 + rand.nextInt(41);
    }

    /**
     * Retorna a penalidade de uma armadilha.
     * Valor fixo de 20 pontos perdidos.
     *
     * @return penalidade da armadilha
     */
    public int trapPenalty() {
        return 20;
    }
}
