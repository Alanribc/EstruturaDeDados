package br.mack.labirinto.ds;

/**
 * Nó de uma lista encadeada simples.
 * Usado para registrar o log de eventos do jogo.
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}
