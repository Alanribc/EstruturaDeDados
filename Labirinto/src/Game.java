

import br.mack.labirinto.core.*;
import br.mack.labirinto.model.*;
import br.mack.labirinto.io.MapLoader;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        String mapPath = "./mapas/map1.txt";
        String playerName = "Jogador";
        long seed = 3;

        Board board = MapLoader.load(mapPath);
        Inventory inventory = new Inventory(5); // capacidade default, pode vir do mapa
        RNG rng = new RNG(seed);
        Scoreboard scoreboard = new Scoreboard();

        Position player = findStart(board);
        int score = 0;
        int energy = 100;

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Labirinto LIFO ===");
        while (true) {
            board.set(player.row, player.col, 'P'); // mostra player
            board.print();
            board.set(player.row, player.col, '.'); // volta a posição

            System.out.printf("Energia: %d | Pontos: %d%n", energy, score);
            System.out.print("Movimento (WASD, Q para sair): ");
            char move = sc.nextLine().toUpperCase().charAt(0);

            if (move == 'Q') break;

            Position next = player.copy();
            switch (move) {
                case 'W': next.row--; break;
                case 'S': next.row++; break;
                case 'A': next.col--; break;
                case 'D': next.col++; break;
                default: continue;
            }

            char cell = board.get(next.row, next.col);
            if (cell == '#') {
                System.out.println("Parede!");
                continue;
            }

            energy--;

            if (cell == '.') {
                player = next;
            } else if (cell == 'E') {
                score += 100 + (inventory.remainingKeys() * 5);
                System.out.println("Você saiu do labirinto!");
                break;
            } else if (cell >= 'a' && cell <= 'z') {
                if (inventory.addKey(cell)) {
                    System.out.println("Pegou chave: " + cell);
                    player = next;
                    board.set(next.row, next.col, '.');
                } else {
                    System.out.println("Inventário cheio!");
                }
            } else if (cell >= 'A' && cell <= 'Z') {
                if (inventory.canOpen(cell)) {
                    System.out.println("Porta " + cell + " aberta!");
                    inventory.openDoor();
                    score += 15;
                    player = next;
                    board.set(next.row, next.col, '.');
                } else {
                    System.out.println("Não tem a chave certa!");
                }
            } else if (cell == '$') {
                int value = rng.treasureValue();
                score += value;
                System.out.println("Tesouro +" + value + " pontos!");
                player = next;
                board.set(next.row, next.col, '.');
            } else if (cell == 'T') {
                int penalty = rng.trapPenalty();
                score -= penalty;
                System.out.println("Armadilha! -" + penalty + " pontos!");
                player = next;
                board.set(next.row, next.col, '.');
            }
        }

        System.out.println("Pontuação final: " + score);
        scoreboard.addScore(playerName, score);
        scoreboard.printTop10();

        sc.close();
    }

    private static Position findStart(Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                if (board.get(r, c) == 'S') {
                    board.set(r, c, '.'); // limpa start
                    return new Position(r, c);
                }
            }
        }
        throw new RuntimeException("Posição inicial (S) não encontrada!");
    }
}
