//Alan Ribeiro do Carmo 10428496
//Erik Yutaka Takara 10427684

package br.mack.labirinto.ds;

/**
 * Implementação simples de uma lista encadeada simples (Singly Linked List).
 * Cada elemento é armazenado em um nó, que aponta para o próximo.
 *
 * @param <T> tipo dos elementos armazenados na lista
 */
public class SinglyLinkedList<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> head = null;

    /**
     * Adiciona um novo elemento ao final da lista.
     *
     * @param value valor a ser adicionado
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) head = newNode;
        else {
            Node<T> current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
    }

    /**
     * Imprime todos os elementos da lista.
     */
    public void printAll() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
