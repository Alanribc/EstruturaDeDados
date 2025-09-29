package br.mack.labirinto.core;

/**
 * Representa o tabuleiro do jogo, composto por uma matriz de caracteres.
 * Cada posição do tabuleiro pode conter:
 * - '#' parede
 * - '.' espaço livre
 * - 'S' posição inicial
 * - 'E' saída
 * - letras minúsculas (chaves)
 * - letras maiúsculas (portas)
 * - '$' tesouro
 * - 'T' armadilha
 */
public class Board {
    private final char[][] grid;
    private final int rows, cols;

    /**
     * Construtor que inicializa o tabuleiro.
     *
     * @param grid matriz de caracteres representando o labirinto
     */
    public Board(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    /**
     * Retorna o caractere em uma posição específica.
     *
     * @param r linha
     * @param c coluna
     * @return caractere armazenado na posição
     */
    public char get(int r, int c) {
        return grid[r][c];
    }

    /**
     * Define um valor em uma posição específica do tabuleiro.
     *
     * @param r linha
     * @param c coluna
     * @param value caractere a ser colocado
     */
    public void set(int r, int c, char value) {
        grid[r][c] = value;
    }

    /**
     * Imprime o tabuleiro no console.
     * Cada linha é exibida como uma string.
     */
    public void print() {
        for (int i = 0; i < rows; i++) {
            System.out.println(new String(grid[i]));
        }
    }

    /**
     * @return número de linhas do tabuleiro
     */
    public int getRows() { return rows; }

    /**
     * @return número de colunas do tabuleiro
     */
    public int getCols() { return cols; }
}
