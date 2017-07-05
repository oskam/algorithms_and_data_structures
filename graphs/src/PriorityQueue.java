

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

public class PriorityQueue<K extends Comparable<K>> {
	private List<K> heap;
	public Map<K, Integer> indexes;

	public PriorityQueue() {
		this.heap = new ArrayList<>();
		this.indexes = new HashMap<>();
	}

	public PriorityQueue(K[] values) {
		this.heap = new ArrayList<>(Arrays.asList(values));
		this.indexes = new HashMap<>();

		for (K value : values) {
			this.indexes.put(value, this.indexes.size());
		}

		// build heap
		for (int i = (int) Math.floor(size() / 2) - 1; i >= 0; i--) {
			heapifyDown(i);
		}


//		 for (K value : values) { insert(value); }

	}

	private int parent(int n) {
		if (n < 0 || n > size())
			throw new IndexOutOfBoundsException(n + " is not a valid index.");

		return n == 0 ? -1 : (n - 1) / 2;
	}

	private int left(int n) {
		if (n < 0 || n > size())
			throw new IndexOutOfBoundsException(n + " is not a valid index.");

		return n * 2 + 1;
	}

	private int right(int n) {
		if (n < 0 || n > size())
			throw new IndexOutOfBoundsException(n + " is not a valid index.");

		return n * 2 + 2;
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return size() == 0 ? true : false;
	}

	public void insert(K k) {
		indexes.put(k, size()); // put(key, value)
		heap.add(k);
		heapify(size() - 1);
	}

	public K peek() {
		return heap.get(0);
	}

	public K extractMin() {

		if (isEmpty())
			throw new NoSuchElementException("Heap is empty!");

		swap(0, size() - 1); // put minimal element on last index
		K removed = heap.remove(size() - 1);
		indexes.remove(removed);
		
		// repair heap, heapify but from top to bottom
		heapifyDown(0);

		return removed;
	}

	// give key on index n from the heap new value k (which is not larger than actual key value)
	public void decreaseKey(int n, K k) {
		if (size() - 1 < n)
			throw new NoSuchElementException("No element with index " + n + " in the heap.");
		if (heap.get(n).compareTo(k) < 0)
			return;

		heap.set(n, k);
		heapify(n);
	}

	private int minChild(int n) {
		if (left(n) > size() - 1)
			return -1;
		if (right(n) > size() - 1)
			return left(n);

		return heap.get(left(n)).compareTo(heap.get(right(n))) <= 0 ? left(n) : right(n);
	}

	private void heapify(int n) {
		int parentId = parent(n);
		while (n > 0 && heap.get(parentId).compareTo(heap.get(n)) > 0) {
			swap(parentId, n);
			n = parentId;
			parentId = parent(n);
		}
	}

	private void heapifyDown(int n) {
		int minChildId = minChild(n); // find index of the smallest element between i, heap[left(i)], heap[right(i)]
		while (minChildId != -1 && heap.get(n).compareTo(heap.get(minChildId)) > 0) {
			swap(n, minChildId);
			n = minChildId;
			minChildId = minChild(n);
		}
	}

	private void swap(int first, int second) {
		indexes.replace(heap.get(first), second);
		indexes.replace(heap.get(second), first);
		Collections.swap(heap, first, second);
	}

	public void print() {
		for (int i = 0; i < size(); i++) {
			System.out.print(heap.get(i).toString() + " ");

			if (i + 2 <= size() && Math.floor(log2(i + 1)) != Math.floor(log2(i + 2))) {
				System.out.println("");
			}
		}
		System.out.println("");
	}

	private double log2(int n) {
		return Math.log(n) / Math.log(2);
	}

	private boolean testHeap() {
		for (int i = 1; i < size(); ++i) {
			if (parent(i) >= 0) {
				if (heap.get(parent(i)).compareTo(heap.get(i)) > 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Integer[] values = { 7, 4, 3, 9, 6, 12, 45, 32, 6, 34, 98, 127, 15, 27, 34, 65, 23, 45, 67, 4, 123, 45, 23, 23,
				54, 23, 99 };

		PriorityQueue<Integer> q = new PriorityQueue<>(values);

		while (q.size() > 0) {
			if (q.testHeap()) {
				q.print();
				System.out.println("");
			} else {
				System.out.println("Not a heap!");
			}

			System.out.println("Minimal element: " + q.extractMin() + "\n");
		}
	}
}
