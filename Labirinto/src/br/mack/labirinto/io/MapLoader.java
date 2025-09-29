package br.mack.labirinto.io;

import br.mack.labirinto.core.Board;
import java.nio.file.*;
import java.util.*;

/**
 * Classe responsável por carregar um mapa a partir de um arquivo texto.
 * O mapa é convertido em uma matriz de caracteres que representa o labirinto
 * e em seguida encapsulado em um objeto {@link Board}.
 *
 * O formato esperado do arquivo é:
 * - Primeira linha: número de linhas, número de colunas e capacidade da pilha (se necessário).
 * - Linhas seguintes: matriz do labirinto representada por caracteres.
 * - Linhas iniciadas com ponto e vírgula (;) são tratadas como comentários e ignoradas.
 */
public class MapLoader {

    /**
     * Lê um arquivo contendo a representação de um labirinto e cria um objeto {@link Board}.
     *
     * @param path caminho do arquivo que contém o mapa
     * @return um objeto {@link Board} construído a partir do conteúdo do arquivo
     * @throws Exception se ocorrer algum erro de leitura ou formatação no arquivo
     */
    public static Board load(String path) throws Exception {
        // Lê todas as linhas do arquivo
        List<String> lines = Files.readAllLines(Paths.get(path));

        // Remove linhas que são comentários
        lines.removeIf(line -> line.startsWith(";"));

        // Primeira linha contém as dimensões e a capacidade
        String[] firstLine = lines.get(0).split(" ");
        int L = Integer.parseInt(firstLine[0]); // número de linhas
        int C = Integer.parseInt(firstLine[1]); // número de colunas
        // capacidade da pilha: firstLine[2] (informação passada para Inventory em outra parte do sistema)

        // Constrói a grade do labirinto
        char[][] grid = new char[L][C];
        for (int i = 0; i < L; i++) {
            grid[i] = lines.get(i + 1).toCharArray();
        }

        // Retorna o tabuleiro pronto
        return new Board(grid);
    }
}
