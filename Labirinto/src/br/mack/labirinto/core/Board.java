package br.mack.labirinto.core;

/**
 * Representa o tabuleiro do jogo como uma matriz de caracteres.
 * Cada célula pode conter parede, piso, chaves, portas, tesouro ou armadilha.
 */
public class Board {
    private final char[][] cells;
    private final int capacity;

    /**
     * Cria um tabuleiro a partir de uma matriz e define a capacidade da pilha de chaves.
     */
    public Board(char[][] cells, int capacity) {
        this.cells = cells;
        this.capacity = capacity;
    }

    /**
     * Retorna a célula na posição (linha, coluna).
     */
    public char getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Altera o conteúdo da célula na posição (linha, coluna).
     */
    public void setCell(int row, int col, char value) {
        cells[row][col] = value;
    }

    /**
     * Retorna o número de linhas do tabuleiro.
     */
    public int getRows() {
        return cells.length;
    }

    /**
     * Retorna o número de colunas do tabuleiro.
     */
    public int getCols() {
        return cells[0].length;
    }

    /**
     * Retorna a capacidade máxima da pilha de chaves do jogador.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Imprime o tabuleiro no terminal.
     */
    public void printBoard() {
        for (char[] row : cells) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
