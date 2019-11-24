package ca.ciccc;

import java.util.Arrays;

public class BHPriorityQueue implements VCPriorityQueue {

  private static final int DEFAULT_CAPACITY = 11;
  private Entry[] heap;
  private int size;

  public BHPriorityQueue() {
    heap = new Entry[DEFAULT_CAPACITY];
  }

  public BHPriorityQueue(int initialCapacity) {
    if (initialCapacity >= 0) {
      heap = new Entry[initialCapacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  public BHPriorityQueue(Entry[] array) {
    size = array.length;
    heap = new Entry[size + 1];
    System.arraycopy(array, 0, heap, 1, array.length);
    buildHeap(heap);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Entry enqueue(Comparable key, Object value) throws IllegalArgumentException {
    if (size == heap.length - 1) {
      heap = grow(size + 1);
    }
    Entry e = new Entry<>(key, value);

    int pos = ++size;
    for (; pos > 1 && key.compareTo(heap[pos / 2].getKey()) < 0; pos = pos / 2) {
      heap[pos] = heap[pos / 2];
    }
    heap[pos] = e;
    return e;
  }

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  private Entry[] grow(int minCapacity) {
    return heap = Arrays.copyOf(heap, newCapacity(minCapacity));
  }

  private int newCapacity(int minCapacity) {
    int oldCapacity = heap.length;
    // bit wise is faster -> oldCapacity * 1.5
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity <= minCapacity) {
      // overflow-conscious code
      if (minCapacity < 0 || minCapacity > MAX_ARRAY_SIZE) {
        throw new OutOfMemoryError("integer overflow");
      }
      return minCapacity; // case1: miniCapacity
    }
    // this is pretty dangerous. (depends on VMs)
    // alternative could be using 'long' type array
    return (newCapacity <= MAX_ARRAY_SIZE)
        ? newCapacity
        : Integer.MAX_VALUE; // case2: newCapacity or case3: Integer.MAX_VALUE
  }

  @Override
  public Entry peek() {
    return heap[1];
  }

  @Override
  public Entry dequeueMin() {

    Entry minEntry = heap[1];
    heap[1] = heap[size];
    heap[size] = null;
    size--;
    percolatingDown(heap, 1);

    return minEntry;
  }

  @Override
  public VCPriorityQueue merge(VCPriorityQueue other) {

    Entry[] array = new Entry[size + other.size()];
    System.arraycopy(heap, 1, array, 0, size);

    int i = size;
    while (!other.isEmpty()) {
      array[i] = other.dequeueMin();
      i++;
    }

    return new BHPriorityQueue(array);
  }

  /**
   * restore the heap property
   *
   * @param array
   */
  private void buildHeap(Entry[] array) {
    for (int k = size / 2; k > 0; k--) {
      percolatingDown(array, k);
    }
  }

  /**
   * heapify tree from k node
   *
   * @param array
   * @param k
   */
  private void percolatingDown(Entry[] array, int k) {
    Entry root = heap[k];
    int child;

    for (; 2 * k <= size; k = child) {
      // start from each left children
      child = 2 * k;

      // compare children nodes which is smaller one
      if (child != size && heap[child].getKey().compareTo(heap[child + 1].getKey()) > 0) child++;

      // compare children nodes and root of subtree which is smaller one. It's smallest one of
      // subtree.
      if (heap[child].getKey().compareTo(root.getKey()) < 0) heap[k] = heap[child];
      else break; // this means root of subtree is smallest one in subtree
    }
    // insert a root of first subtree in last position
    heap[k] = root;
  }

  //  private void minHeapify(Entry[] array, int k) {
  //
  //    int left = 2 * k;
  //    int right = 2 * k + 1;
  //    int smallest = k;
  //
  //    if (left <= size && array[k].getKey().compareTo(array[left].getKey()) > 0) {
  //      smallest = left;
  //    }
  //
  //    if (right <= size && array[smallest].getKey().compareTo(array[right].getKey()) > 0) {
  //      smallest = right;
  //    }
  //
  //    if (smallest != k) {
  //      Entry temp = array[k];
  //      array[k] = array[smallest];
  //      array[smallest] = temp;
  //    }
  //  }

}
