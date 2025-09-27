package br.mack.labirinto.ds;

public class SinglyLinkedList<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> head = null;

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) head = newNode;
        else {
            Node<T> current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
    }

    public void printAll() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
