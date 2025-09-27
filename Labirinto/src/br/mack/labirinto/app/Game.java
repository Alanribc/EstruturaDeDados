package br.mack.labirinto.app;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.io.MapLoader;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        String mapPath = "./mapas/map1.txt"; // por enquanto fixo
        Board board = MapLoader.load(mapPath);

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Labirinto LIFO ===");
        while (true) {
            board.print();
            System.out.print("Movimento (WASD, Q para sair): ");
            char move = sc.nextLine().toUpperCase().charAt(0);
            if (move == 'Q') break;

            // TODO: lógica de movimento, inventário, score...
        }
        sc.close();
    }
}
