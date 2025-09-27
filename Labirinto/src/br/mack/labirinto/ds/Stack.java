package br.mack.labirinto.ds;

public class Stack<T> {
    private final T[] data;
    private int top = -1;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == data.length - 1; }

    public void push(T item) {
        if (isFull()) throw new IllegalStateException("Pilha cheia!");
        data[++top] = item;
    }

    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Pilha vazia!");
        return data[top--];
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Pilha vazia!");
        return data[top];
    }

    public int size() { return top + 1; }
    public int capacity() { return data.length; }
}
