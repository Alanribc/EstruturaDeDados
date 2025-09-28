package br.mack.labirinto.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.core.Position;

/**
 * Classe responsável por carregar mapas de arquivo .txt e localizar a posição inicial.
 */
public class MapLoader {

    /**
     * Carrega o mapa a partir de um arquivo de texto.
     * @param path Caminho do arquivo do mapa
     * @return Board instanciado com o mapa carregado
     * @throws Exception se ocorrer erro na leitura ou formato inválido
     */
    public static Board load(String path) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(path));

        // Ignora comentários e pega a primeira linha útil: "linhas colunas capacidade"
        String header = null;
        for (String line : lines) {
            line = line.trim();
            if (!line.startsWith(";") && !line.isEmpty()) {
                header = line;
                break;
            }
        }

        if (header == null) throw new Exception("Arquivo de mapa inválido.");

        String[] tokens = header.split("\\s+");
        int rows = Integer.parseInt(tokens[0]);
        int cols = Integer.parseInt(tokens[1]);
        int capacity = Integer.parseInt(tokens[2]);

        Board board = new Board(rows, cols, capacity);

        // Preenche o tabuleiro
        int rowIndex = 0;
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith(";") || line.isEmpty()) continue;

            if (rowIndex == 0) { // pular a linha de header
                rowIndex++;
                continue;
            }

            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                board.setCell(rowIndex, cols, c);(rowIndex - 1, col, c);
            }
            rowIndex++;
        }

        return board;
    }

    /**
     * Localiza a posição inicial "S" no tabuleiro.
     * @param board Tabuleiro carregado
     * @return Position da célula inicial
     * @throws Exception se posição inicial não encontrada
     */
    public static Position findStart(Board board) throws Exception {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                if (board.getCelula(r, c) == 'S') {
                    return new Position(r, c);
                }
            }
        }
        throw new Exception("Posição inicial 'S' não encontrada no mapa.");
    }
}
