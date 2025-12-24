package se.hig.aod.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapSorter som sorterar en lista med hjälp av en heapbaserad prioritetskö.
 */
public class HeapSorter {

    /**
     * Sorterar en lista med hjälp av heapsort.
     *
     * @param inputList listan som ska sorteras
     * @param <T> typen måste vara jämförbar
     * @return en ny lista med sorterade element
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> inputList) {
        HeapPriorityQueue<T> heap = new HeapPriorityQueue<T>(inputList.size());

        for (T element : inputList) {
            heap.enqueue(element);
        }

        List<T> sortedList = new ArrayList<T>();

        while (!heap.isEmpty()) {
            sortedList.add(heap.dequeue());
        }

        return sortedList;
    }
}
