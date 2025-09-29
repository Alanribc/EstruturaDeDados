package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;

/**
 * Representa uma entrada de pontuação no ranking do jogo.
 * Cada instância contém o nome do jogador e a pontuação obtida.
 * Implementa a interface {@link Sorts.SortableScore} para permitir
 * ordenação e busca em rankings.
 */
public class ScoreEntry implements Sorts.SortableScore {

    /** Nome do jogador */
    private final String playerName;

    /** Pontuação do jogador */
    private final int score;

    /**
     * Construtor que cria uma entrada de pontuação.
     *
     * @param playerName nome do jogador
     * @param score pontuação obtida pelo jogador
     */
    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Retorna o nome do jogador.
     *
     * @return nome do jogador
     */
    @Override
    public String getPlayerName() { 
        return playerName; 
    }

    /**
     * Retorna a pontuação do jogador.
     *
     * @return pontuação do jogador
     */
    @Override
    public int getScore() { 
        return score; 
    }

    /**
     * Retorna a representação em string da entrada de pontuação.
     *
     * @return string no formato "nome:score"
     */
    @Override
    public String toString() {
        return String.format("%s:%d", playerName, score);
    }
}
