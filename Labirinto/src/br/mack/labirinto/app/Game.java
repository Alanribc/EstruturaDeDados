package br.mack.labirinto.app;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.core.Position;
import br.mack.labirinto.core.RNG;
import br.mack.labirinto.ds.Stack;
import br.mack.labirinto.ds.SinglyLinkedList;
import br.mack.labirinto.io.MapLoader;
import br.mack.labirinto.io.CLI;
import br.mack.labirinto.model.Inventory;
import br.mack.labirinto.model.Scoreboard;
import br.mack.labirinto.model.ScoreEntry;

import java.util.Scanner;

/**
 * Classe principal que roda o jogo Labirinto LIFO.
 */
public class Game {

    public static void main(String[] args) {
        try {
            // Lê parâmetros da linha de comando
            String mapPath = CLI.getArg(args, "--map");
            int seed = CLI.getArgInt(args, "--seed", 1);
            String player = CLI.getArg(args, "--player", "Player");

            // Carrega mapa
            Board board = MapLoader.load(mapPath);

            // Posição inicial
            Position pos = MapLoader.findStart(board);

            // Inventário de chaves
            Inventory inventory = new Inventory(board.getCapacity());

            // Log de eventos
            SinglyLinkedList<String> log = new SinglyLinkedList<>();

            // Random para tesouros e armadilhas
            RNG rng = new RNG(seed);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            int score = 0;

            while (running) {
                // Exibe o tabuleiro e status
                board.printBoard(pos);
                System.out.println("Chaves no inventário: ");
                inventory.printInventory();
                System.out.println("Pontuação: " + score);

                System.out.print("Movimento (W/A/S/D) ou Q para sair: ");
                String input = scanner.nextLine().toUpperCase();
                if (input.equals("Q")) break;

                int dx = 0, dy = 0;
                switch (input) {
                    case "W" -> dx = -1;
                    case "S" -> dx = 1;
                    case "A" -> dy = -1;
                    case "D" -> dy = 1;
                    default -> {
                        System.out.println("Comando inválido!");
                        continue;
                    }
                }

                int newX = pos.getX() + dx;
                int newY = pos.getY() + dy;

                char cell = board.getCell(newX, newY);

                if (cell == '#') {
                    System.out.println("Parede! Não pode andar.");
                    continue;
                }

                // Andar gasta 1 ponto
                score--;

                // Chave
                if (cell >= 'a' && cell <= 'z') {
                    if (inventory.pushKey(cell)) {
                        System.out.println("Pegou a chave " + cell);
                        board.setCell(newX, newY, '.');
                    } else {
                        System.out.println("Inventário cheio! Não pode pegar " + cell);
                    }
                }

                // Porta
                else if (cell >= 'A' && cell <= 'Z') {
                    char top = inventory.peekKey();
                    if (top != 0 && Character.toLowerCase(top) == Character.toLowerCase(cell)) {
                        inventory.popKey();
                        System.out.println("Porta " + cell + " aberta!");
                        board.setCell(newX, newY, '.');
                        score += 15;
                    } else {
                        System.out.println("Precisa da chave correta para abrir " + cell);
                        continue;
                    }
                }

                // Tesouro
                else if (cell == '$') {
                    int tscore = rng.treasureScore(newX, newY);
                    score += tscore;
                    System.out.println("Pegou tesouro! +" + tscore + " pontos.");
                    board.setCell(newX, newY, '.');
                }

                // Armadilha
                else if (cell == 'T') {
                    score -= 20;
                    log.add("Armadilha na posição " + newX + "," + newY);
                    System.out.println("Caiu em armadilha! -20 pontos.");
                    board.setCell(newX, newY, '.');
                }

                // Saída
                else if (cell == 'E') {
                    score += 100;
                    System.out.println("Parabéns! Você saiu do labirinto.");
                    running = false;
                }

                // Atualiza posição
                pos.setX(newX);
                pos.setY(newY);
            }

            System.out.println("Pontuação final: " + score);
            System.out.println("Log de armadilhas:");
            log.printList();

            // Salva no ranking
            Scoreboard sb = new Scoreboard();
            sb.addScore(new ScoreEntry(player, score));
            sb.showTop(10);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
