package br.mack.labirinto.app;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.core.Position;
import br.mack.labirinto.core.RNG;
import br.mack.labirinto.io.CLI;
import br.mack.labirinto.io.MapLoader;
import br.mack.labirinto.model.Inventory;
import br.mack.labirinto.model.ScoreEntry;
import br.mack.labirinto.model.Scoreboard;

/**
 * Classe principal do jogo Labirinto LIFO – Chaves & Portas.
 * Controla a execução, entrada do jogador, movimentação e pontuação.
 */
public class Game {

    public static void main(String[] args) throws Exception {
        // Caminho do mapa
        String mapPath = "./mapas/Labirinto.txt";
        long seed = 1; // semente default
        String playerName = "Player";

        // Carregar mapa
        char[][] map = MapLoader.load(mapPath);
        Position start = MapLoader.findStart(map);

        Board board = new Board(map, start, seed);
        RNG rng = new RNG(seed);
        Inventory inventory = new Inventory(5); // capacidade 5
        CLI cli = new CLI();
        Scoreboard scoreboard = new Scoreboard();

        int score = 0;
        boolean running = true;

        while (running) {
            // Renderiza o labirinto
            board.printBoard();
            System.out.println("Pontuação: " + score);
            System.out.print("Chaves no inventário: ");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("Digite comando (W/A/S/D ou Q para sair):");

            // Lê comando
            String command = cli.readCommand().toUpperCase();
            Position previousPosition = board.getPlayerPosition();
            int x = previousPosition.getX();
            int y = previousPosition.getY();

            // Atualiza posição
            switch (command) {
                case "W": x--; break;
                case "S": x++; break;
                case "A": y--; break;
                case "D": y++; break;
                case "Q": running = false; continue;
                default:
                    System.out.println("Comando inválido!");
                    continue;
            }

            // Checa se posição é válida
            if (!board.isValidPosition(x, y)) {
                System.out.println("Movimento inválido! Há uma parede.");
                continue;
            }

            board.setPlayerPosition(new Position(x, y));
            char cell = board.getCelula(x, y);

            // Interações
            if (cell >= 'a' && cell <= 'z') { // chave
                if (inventory.addKey(cell)) {
                    System.out.println("Pegou a chave " + cell + "!");
                    board.setCelula(x, y, '.');
                } else {
                    System.out.println("Inventário cheio! Não pegou a chave " + cell);
                }
            } else if (cell >= 'A' && cell <= 'Z') { // porta
                if (!inventory.isEmpty() && Character.toLowerCase(inventory.peekKey()) == Character.toLowerCase(cell)) {
                    inventory.useKey();
                    System.out.println("Porta " + cell + " aberta!");
                    score += 15;
                    board.setCelula(x, y, '.');
                } else {
                    System.out.println("Porta " + cell + " não pode ser aberta!");
                    board.setPlayerPosition(previousPosition); // volta
                }
            } else if (cell == '$') { // tesouro
                int treasure = rng.nextInt(10, 50);
                System.out.println("Tesouro encontrado: +" + treasure + " pontos!");
                score += treasure;
                board.setCelula(x, y, '.');
            } else if (cell == 'T') { // armadilha
                System.out.println("Caiu na armadilha! -20 pontos");
                score -= 20;
                board.setCelula(x, y, '.');
            } else if (cell == 'E') { // saída
                System.out.println("Saída alcançada!");
                score += 100;
                running = false;
            }
        }

        cli.close();

        // Adiciona ao ranking
        scoreboard.addScore(new ScoreEntry(playerName, score));

        System.out.println("Pontuação final: " + score);
        System.out.println("Obrigado por jogar!");
    }
}
