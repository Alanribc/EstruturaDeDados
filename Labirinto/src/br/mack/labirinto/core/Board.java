package br.mack.labirinto.core;

public class Board {
    private final char[][] grid;
    private final int rows, cols;

    public Board(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public char get(int r, int c) {
        return grid[r][c];
    }

    public void set(int r, int c, char value) {
        grid[r][c] = value;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            System.out.println(new String(grid[i]));
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}
