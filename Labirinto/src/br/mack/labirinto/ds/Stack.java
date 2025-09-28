package br.mack.labirinto.ds;

/**
 * Implementação de uma pilha (LIFO) baseada em array.
 * Usada para o inventário de chaves do jogador.
 */
public class Stack<T> {
    private final T[] data;
    private int top = -1;

    /**
     * Cria uma pilha com capacidade máxima definida.
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
    }

    /**
     * Adiciona um elemento no topo da pilha.
     */
    public void push(T value) {
        if (top + 1 >= data.length) {
            throw new RuntimeException("Pilha cheia");
        }
        data[++top] = value;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     */
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }
        return data[top--];
    }

    /**
     * Retorna o elemento do topo da pilha sem removê-lo.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }
        return data[top];
    }

    /**
     * Verifica se a pilha está vazia.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Retorna a capacidade máxima da pilha.
     */
    public int capacity() {
        return data.length;
    }

    /**
     * Retorna a quantidade de elementos na pilha.
     */
    public int size() {
        return top + 1;
    }
}
