//Alan Ribeiro do Carmo 10428496
//Erik Yutaka Takara 10427684

package br.mack.labirinto.model;

import br.mack.labirinto.ds.Stack;

/**
 * Representa o inventário do jogador no labirinto.
 * O inventário consiste em uma pilha de chaves (LIFO) que podem ser
 * usadas para abrir portas correspondentes.
 */
public class Inventory {

    /** Pilha que armazena as chaves do jogador */
    private final Stack<Character> keys;

    /**
     * Construtor que cria um inventário com capacidade definida.
     *
     * @param capacity capacidade máxima de chaves que podem ser armazenadas
     */
    public Inventory(int capacity) {
        keys = new Stack<>(capacity);
    }

    /**
     * Adiciona uma chave ao inventário.
     *
     * @param key caractere representando a chave
     * @return true se a chave foi adicionada com sucesso, false se o inventário estiver cheio
     */
    public boolean addKey(char key) {
        if (keys.isFull()) return false;
        keys.push(key);
        return true;
    }

    /**
     * Verifica se o jogador possui a chave correta para abrir uma porta.
     *
     * @param door caractere representando a porta (maiúscula)
     * @return true se a chave no topo da pilha corresponde à porta, false caso contrário
     */
    public boolean canOpen(char door) {
        if (keys.isEmpty()) return false;
        return keys.peek() == Character.toLowerCase(door);
    }

    /**
     * Remove a chave do topo da pilha, simulando a abertura de uma porta.
     * Se a pilha estiver vazia, nada é feito.
     */
    public void openDoor() {
        if (!keys.isEmpty()) keys.pop();
    }

    /**
     * Imprime o conteúdo da pilha de chaves no console.
     * Se não houver chaves, exibe "[vazio]".
     */
    public void print() {
        System.out.print("Chaves na pilha: ");
        if (keys.isEmpty()) {
            System.out.println("[vazio]");
        } else {
            for (int i = 0; i < keys.size(); i++) {
                System.out.print(keys.peek() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Retorna a quantidade de chaves restantes no inventário.
     *
     * @return número de chaves na pilha
     */
    public int remainingKeys() {
        return keys.size();
    }
}
