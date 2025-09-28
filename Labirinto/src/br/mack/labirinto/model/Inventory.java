package br.mack.labirinto.model;

import br.mack.labirinto.ds.Stack;

/**
 * Representa o inventário de chaves do jogador usando pilha LIFO.
 */
public class Inventory {
    private final Stack<Character> keys;

    /**
     * Cria um inventário com capacidade máxima de chaves.
     */
    public Inventory(int capacity) {
        keys = new Stack<>(capacity);
    }

    /**
     * Adiciona uma chave no inventário.
     */
    public void pushKey(char key) {
        if (keys.size() >= keys.capacity()) {
            System.out.println("Inventário cheio! Não é possível pegar a chave " + key);
            return;
        }
        keys.push(key);
        System.out.println("Pegou a chave " + key);
    }

    /**
     * Retorna a chave do topo da pilha sem removê-la.
     */
    public char peekKey() {
        return keys.peek();
    }

    /**
     * Remove e retorna a chave do topo da pilha.
     */
    public char popKey() {
        return keys.pop();
    }

    /**
     * Verifica se o inventário está vazio.
     */
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    /**
     * Retorna a quantidade de chaves no inventário.
     */
    public int size() {
        return keys.size();
    }
}
