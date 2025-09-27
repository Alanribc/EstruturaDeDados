package br.mack.labirinto.io;

import br.mack.labirinto.core.Board;
import java.nio.file.*;
import java.util.*;

public class MapLoader {
    public static Board load(String path) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(path));
        lines.removeIf(line -> line.startsWith(";")); // ignora comentários

        String[] firstLine = lines.get(0).split(" ");
        int L = Integer.parseInt(firstLine[0]);
        int C = Integer.parseInt(firstLine[1]);
        // capacidade da pilha: firstLine[2] (passa para Inventory)

        char[][] grid = new char[L][C];
        for (int i = 0; i < L; i++) {
            grid[i] = lines.get(i + 1).toCharArray();
        }

        return new Board(grid);
    }
}
