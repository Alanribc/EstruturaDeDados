package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;
import java.io.*;
import java.util.*;

/**
 * Classe responsável por gerenciar o ranking de pontuações do jogo.
 * Permite carregar e salvar pontuações em arquivo, adicionar novos
 * resultados e exibir o ranking dos melhores jogadores.
 */
public class Scoreboard {

    /** Caminho do arquivo onde o ranking será salvo/carregado */
    private final String filePath = "ranking.csv";

    /** Lista de entradas de pontuação */
    private final List<ScoreEntry> scores = new ArrayList<>();

    /**
     * Construtor que inicializa o ranking e carrega os dados do arquivo.
     */
    public Scoreboard() {
        load();
    }

    /**
     * Carrega o ranking do arquivo.
     * Cada linha do arquivo deve ter o formato "nome,score".
     * Linhas inválidas são ignoradas.
     */
    private void load() {
        File f = new File(filePath);
        if (!f.exists()) return;
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length == 2) {
                    scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ranking: " + e.getMessage());
        }
    }

    /**
     * Salva o ranking atual no arquivo.
     * Cada linha do arquivo segue o formato "nome,score".
     */
    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (ScoreEntry s : scores) {
                pw.println(s.getPlayerName() + "," + s.getScore());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }

    /**
     * Adiciona uma nova pontuação ao ranking e ordena os resultados
     * em ordem decrescente. Em seguida, salva o ranking no arquivo.
     *
     * @param player nome do jogador
     * @param score pontuação do jogador
     */
    public void addScore(String player, int score) {
        scores.add(new ScoreEntry(player, score));
        ScoreEntry[] arr = scores.toArray(new ScoreEntry[0]);
        Sorts.quickSort(arr, 0, arr.length - 1); // ordena decrescente por pontuação
        scores.clear();
        scores.addAll(Arrays.asList(arr));
        save();
    }

    /**
     * Imprime no console as 10 melhores pontuações do ranking.
     * Caso existam menos de 10 pontuações, imprime todas.
     */
    public void printTop10() {
        System.out.println("=== Ranking TOP 10 ===");
        for (int i = 0; i < Math.min(10, scores.size()); i++) {
            System.out.println((i + 1) + ". " + scores.get(i));
        }
    }

    /**
     * Procura a posição de um jogador pelo nome usando busca binária.
     *
     * @param name nome do jogador
     * @return índice do jogador no ranking, ou -1 se não encontrado
     */
    public int findPlayer(String name) {
        ScoreEntry[] arr = scores.toArray(new ScoreEntry[0]);
        return Sorts.binarySearchByName(arr, name);
    }
}
