package br.mack.labirinto.ds;

/**
 * Pilha genérica (LIFO - Last In, First Out) com capacidade fixa.
 * Permite operações de push, pop, peek e verificação de tamanho.
 *
 * @param <T> tipo dos elementos armazenados na pilha
 */
public class Stack<T> {

    /** Array interno que armazena os elementos da pilha */
    private final T[] data;

    /** Índice do topo da pilha */
    private int top = -1;

    /**
     * Construtor que cria uma pilha com capacidade definida.
     *
     * @param capacity capacidade máxima da pilha
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return true se a pilha estiver vazia, false caso contrário
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Verifica se a pilha está cheia.
     *
     * @return true se a pilha estiver cheia, false caso contrário
     */
    public boolean isFull() {
        return top == data.length - 1;
    }

    /**
     * Adiciona um elemento ao topo da pilha.
     *
     * @param item elemento a ser adicionado
     * @throws IllegalStateException se a pilha estiver cheia
     */
    public void push(T item) {
        if (isFull()) throw new IllegalStateException("Pilha cheia!");
        data[++top] = item;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     *
     * @return elemento removido do topo
     * @throws IllegalStateException se a pilha estiver vazia
     */
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Pilha vazia!");
        return data[top--];
    }

    /**
     * Retorna o elemento do topo da pilha sem removê-lo.
     *
     * @return elemento no topo
     * @throws IllegalStateException se a pilha estiver vazia
     */
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Pilha vazia!");
        return data[top];
    }

    /**
     * Retorna a quantidade de elementos armazenados na pilha.
     *
     * @return número de elementos na pilha
     */
    public int size() {
        return top + 1;
    }

    /**
     * Retorna a capacidade máxima da pilha.
     *
     * @return capacidade da pilha
     */
    public int capacity() {
        return data.length;
    }
}
