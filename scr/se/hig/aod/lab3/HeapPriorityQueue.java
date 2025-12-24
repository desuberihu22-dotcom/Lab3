package se.hig.aod.lab3;

/**
 * En statisk heap-implementation av en prioritetskö för element av typen T.
 *
 * @author Desbele
 *
 * @param <T> Datatypen för elementen som lagras i kön.
 */

public class HeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueue<T> {

	private T[] heap;
	private int size;
	private int maxSize;

	public HeapPriorityQueue(int maxSize) {
		this.maxSize = maxSize;
		clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		heap = (T[]) (new Comparable[maxSize]); 
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Metod som är specifik för en statisk heap-implementation och kontrollerar
	 * om den underliggande arrayen är full.
	 *
	 * @return true om den underliggande arrayen är full
	 */

	public boolean isFull() {
		return size == maxSize;
	}

	@Override
	public int size() {
		return size;
	}

	private int parent(int currentIndex) {
		return (currentIndex - 1) / 2;
	}

	private int leftChild(int currentIndex) {
		return 2 * currentIndex + 1;
	}

	private int rightChild(int currentIndex) {
		return 2 * currentIndex + 2;
	}

	private void reHeapUp(int currentIndex) {
		if (currentIndex <= 0)
			return;
		int parent = parent(currentIndex);
		if (heap[currentIndex].compareTo(heap[parent]) > 0) {
			T tmp = heap[currentIndex];
			heap[currentIndex] = heap[parent];
			heap[parent] = tmp;
			reHeapUp(parent);
		}
	}

	private void reHeapDown(int currentIndex) {
		int left = leftChild(currentIndex);
		int right = rightChild(currentIndex);

		if (left >= size) {
			return; 
		}

		int maxChild = left;

		if (right < size && heap[right].compareTo(heap[left]) > 0) {
			maxChild = right;
		}

		if (heap[currentIndex].compareTo(heap[maxChild]) < 0) {
			T temp = heap[currentIndex];
			heap[currentIndex] = heap[maxChild];
			heap[maxChild] = temp;

			reHeapDown(maxChild);
		}

	}

	@Override
	public void enqueue(T newElement) {
		if (isFull()) {
			throw new PriorityQueueFullException("Heap is full!");
		}
		size++;
		heap[size - 1] = newElement;
		reHeapUp(size - 1);
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new PriorityQueueEmptyException("Cannot dequeue empty Queue");
		} else {
			T dequeuedElement = heap[0];
			T lastElement = heap[size - 1];
			heap[0] = lastElement;
			heap[size - 1] = null;
			size--;
			reHeapDown(0);
			return dequeuedElement;
		}
	}

	@Override
	public T getFront() {
		if (isEmpty()) {
			throw new PriorityQueueEmptyException("Cannot get front of empty Queue!");
		} else {
			return heap[0];
		}
	}

	@Override
	public String toString() {
		String stringRepresentation = "";
		for (int i = 0; i < size; i++) {
			stringRepresentation = stringRepresentation + heap[i] + " ";
		}
		return stringRepresentation;
	}
}
