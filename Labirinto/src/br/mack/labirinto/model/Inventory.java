package br.mack.labirinto.model;

import br.mack.labirinto.ds.Stack;

public class Inventory {
    private final Stack<Character> keys;

    public Inventory(int capacity) {
        keys = new Stack<>(capacity);
    }

    public boolean addKey(char key) {
        if (keys.isFull()) return false;
        keys.push(key);
        return true;
    }

    public boolean canOpen(char door) {
        if (keys.isEmpty()) return false;
        return keys.peek() == Character.toLowerCase(door);
    }

    public void openDoor() {
        if (!keys.isEmpty()) keys.pop();
    }

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

    public int remainingKeys() {
        return keys.size();
    }
}
