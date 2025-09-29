package br.mack.labirinto.core;

/**
 * Representa uma posição no tabuleiro.
 * Contém linha e coluna como coordenadas.
 */
public class Position {
    public int row, col;

    /**
     * Construtor da posição.
     *
     * @param r linha
     * @param c coluna
     */
    public Position(int r, int c) {
        this.row = r;
        this.col = c;
    }

    /**
     * Cria uma cópia da posição atual.
     *
     * @return nova instância de Position com os mesmos valores
     */
    public Position copy() {
        return new Position(row, col);
    }
}
