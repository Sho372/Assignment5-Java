package ca.ciccc;

import java.util.ArrayList;

public class ALPriorityQueue implements VCPriorityQueue {

  private ArrayList<Entry> innerArrayList;

  public ALPriorityQueue() {
    this.innerArrayList = new ArrayList<>();
  }

  @Override
  public int size() {
    return innerArrayList.size();
  }

  @Override
  public boolean isEmpty() {
    return innerArrayList.isEmpty();
  }

  @Override
  // O(1)
  public Entry enqueue(Comparable key, Object value) throws IllegalArgumentException {

    // null not allowed
    if (value == null) throw new NullPointerException();

    Entry e = new Entry(key, value);

    innerArrayList.add(e);
    return e;
  }

  @Override
  // O(n)
  public Entry peek() {

    if (innerArrayList.isEmpty()) {
      return null;
    }

    if (innerArrayList.size() == 1) {
      return innerArrayList.get(0);
    }

    Entry minE = innerArrayList.get(0);
    if (!isEmpty()) {
      for (int i = 1; i < size(); i++) {
        if (innerArrayList.get(i).getKey().compareTo(minE.getKey()) < 0) {
          minE = innerArrayList.get(i);
        }
      }
    }
    return minE;
  }

  @Override
  // O(n)
  public Entry dequeueMin() {
    Entry e = peek();
    innerArrayList.remove(e);
    return e;
  }

  @Override
  public VCPriorityQueue merge(VCPriorityQueue other) {

    VCPriorityQueue alq = new ALPriorityQueue();

    for (int i = 0; i < size(); ++i) {
      alq.enqueue(innerArrayList.get(i).getKey(), innerArrayList.get(i).getValue());
    }

    if (other.isEmpty()) return alq;

    while (!other.isEmpty()) {
      Entry e = other.dequeueMin();
      alq.enqueue(e.getKey(), e.getValue());
    }
    return alq;
  }
}
