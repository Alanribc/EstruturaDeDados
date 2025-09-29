//Alan Ribeiro do Carmo 10428496
//Erik Yutaka Takara 10427684

package br.mack.labirinto.app;

import br.mack.labirinto.core.*;
import br.mack.labirinto.model.*;
import br.mack.labirinto.io.MapLoader;

import java.util.Scanner;

/**
 * Classe principal que controla a execução do jogo Labirinto LIFO.
 * O jogo consiste em movimentar um jogador dentro de um tabuleiro,
 * coletando chaves, abrindo portas, pegando tesouros e evitando armadilhas,
 * até encontrar a saída.
 */
public class Game {

    /**
     * Método principal responsável por inicializar e rodar o jogo.
     * 
     * Fluxo básico:
     * 1. Carrega o mapa e inicializa os componentes principais.
     * 2. Localiza a posição inicial do jogador.
     * 3. Executa o loop principal onde o jogador pode se mover.
     * 4. Interpreta os símbolos do tabuleiro:
     *    - '#' representa uma parede.
     *    - '.' representa caminho livre.
     *    - 'S' representa a posição inicial.
     *    - 'E' representa a saída do labirinto.
     *    - letras minúsculas representam chaves.
     *    - letras maiúsculas representam portas.
     *    - '$' representa um tesouro.
     *    - 'T' representa uma armadilha.
     * 5. Atualiza pontuação e energia do jogador.
     * 6. Finaliza exibindo a pontuação e o ranking.
     *
     * @param args argumentos da linha de comando (não utilizados no momento)
     * @throws Exception caso ocorra falha ao carregar o mapa
     */
    public static void main(String[] args) throws Exception {
        String mapPath = "Labirinto/mapas/Labirinto.txt";
        String playerName = "Jogador";
        long seed = 3;

        Board board = MapLoader.load(mapPath);
        Inventory inventory = new Inventory(5); // Capacidade inicial da pilha de chaves
        RNG rng = new RNG(seed);
        Scoreboard scoreboard = new Scoreboard();

        Position player = findStart(board);
        int score = 0;
        int energy = 100;

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Labirinto LIFO ===");
        while (true) {
            // Mostra a posição atual do jogador no tabuleiro
            board.set(player.row, player.col, 'P');
            board.print();
            board.set(player.row, player.col, '.');

            // Exibe status atual
            System.out.printf("Energia: %d | Pontos: %d%n", energy, score);
            System.out.print("Movimento (WASD, Q para sair): ");
            char move = sc.nextLine().toUpperCase().charAt(0);

            if (move == 'Q') break;

            // Calcula a próxima posição com base no movimento
            Position next = player.copy();
            switch (move) {
                case 'W': next.row--; break; // cima
                case 'S': next.row++; break; // baixo
                case 'A': next.col--; break; // esquerda
                case 'D': next.col++; break; // direita
                default: continue;
            }

            char cell = board.get(next.row, next.col);
            if (cell == '#') {
                System.out.println("Parede!");
                continue;
            }

            energy--; // cada movimento consome energia

            // Verifica interações com a célula do mapa
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

    /**
     * Procura no tabuleiro a posição inicial do jogador, marcada com 'S'.
     * 
     * Caso seja encontrada, a posição é retornada e o 'S' é substituído por '.',
     * liberando o espaço para movimentação futura.
     *
     * @param board tabuleiro do jogo
     * @return posição inicial do jogador
     * @throws RuntimeException se não houver posição inicial no mapa
     */
    private static Position findStart(Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                if (board.get(r, c) == 'S') {
                    board.set(r, c, '.'); // limpa a marca de início
                    return new Position(r, c);
                }
            }
        }
        throw new RuntimeException("Posição inicial (S) não encontrada!");
    }
}
