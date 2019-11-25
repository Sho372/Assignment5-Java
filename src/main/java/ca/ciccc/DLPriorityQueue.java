package ca.ciccc;

import java.util.LinkedList;

public class DLPriorityQueue implements VCPriorityQueue {

  private LinkedList<Entry> innerLinkedList;

  public DLPriorityQueue() {
    this.innerLinkedList = new LinkedList<>();
  }

  @Override
  public int size() {
    return innerLinkedList.size();
  }

  @Override
  public boolean isEmpty() {
    return innerLinkedList.isEmpty();
  }

  @Override
  // O(n)
  public Entry enqueue(Comparable key, Object value) throws IllegalArgumentException {

    // null not allowed
    if (value == null) throw new NullPointerException();

    Entry e = new Entry(key, value);

    // if it's empty
    if (isEmpty()) {
      innerLinkedList.add(e);
      return e;
    }

    for (int i = 0; i < innerLinkedList.size(); i++) {
      if (innerLinkedList.get(i).getKey().compareTo(key) > 0) {
        innerLinkedList.add(i, e);
        return e;
      }
    }
    innerLinkedList.add(e);
    return e;
  }

  @Override
  // O(1)
  public Entry peek() {
    if (!isEmpty()) return innerLinkedList.peek();
    return null;
  }

  @Override
  // O(1)
  public Entry dequeueMin() {
    if (!isEmpty()) return innerLinkedList.poll();
    return null;
  }

  @Override
  public VCPriorityQueue merge(VCPriorityQueue other) {

    VCPriorityQueue dlp = new DLPriorityQueue();

    for (int i = 0; i < size(); ++i) {
      dlp.enqueue(innerLinkedList.get(i).getKey(), innerLinkedList.get(i).getValue());
    }

    for (int i = 0; !other.isEmpty(); i++) {
      Entry e = other.dequeueMin();
      dlp.enqueue(e.getKey(), e.getValue());
    }
    return dlp;
  }
}
