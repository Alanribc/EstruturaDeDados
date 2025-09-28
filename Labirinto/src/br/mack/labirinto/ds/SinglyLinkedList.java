package br.mack.labirinto.ds;

/**
 * Lista encadeada simples.
 * Usada para armazenar eventos de armadilhas ou ações do jogador.
 */
public class SinglyLinkedList<T> {
    private Node<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * Adiciona um elemento no início da lista.
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Verifica se a lista está vazia.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Percorre a lista e imprime os elementos.
     */
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
