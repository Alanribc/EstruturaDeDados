package br.mack.labirinto.util;

public class Sorts {

    public interface SortableScore {
        String getPlayerName();
        int getScore();
    }

    // Ordenação Insertion Sort (decrescente por score)
    public static <T extends SortableScore> void insertionSort(T[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getScore() < key.getScore()) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Ordenação QuickSort (decrescente por score)
    public static <T extends SortableScore> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static <T extends SortableScore> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].getScore() >= pivot.getScore()) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Busca binária por nome do jogador
    public static <T extends SortableScore> int binarySearchByName(T[] arr, String playerName) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = arr[mid].getPlayerName().compareToIgnoreCase(playerName);
            if (comparison == 0) return mid;
            else if (comparison < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
