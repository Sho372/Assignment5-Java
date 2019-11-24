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
  public Entry enqueue(Comparable key, Object value) throws IllegalArgumentException {

    // null not allowed
    if (value == null) throw new NullPointerException();

    Entry e = new Entry(key, value);

    // if it's empty
    if (isEmpty()) {
      innerArrayList.add(e);
      return e;
    }

    for (int i = 0; i < innerArrayList.size(); ++i) {
      if (innerArrayList.get(i).getKey().compareTo(key) > 0) {
        innerArrayList.add(i, e);
        return e;
      }
    }
    innerArrayList.add(e);
    return e;
  }

  @Override
  public Entry peek() {
    if (!isEmpty()) return innerArrayList.get(0);
    return null;
  }

  @Override
  public Entry dequeueMin() {
    if (!isEmpty()) return innerArrayList.remove(0);
    return null;
  }

  @Override
  public VCPriorityQueue merge(VCPriorityQueue other) {

    VCPriorityQueue alp = new ALPriorityQueue();

    for (int i = 0; i < size(); ++i) {
      alp.enqueue(innerArrayList.get(i).getKey(), innerArrayList.get(i).getValue());
    }

    for (int i = 0; !other.isEmpty(); i++) {
      Entry e = other.dequeueMin();
      alp.enqueue(e.getKey(), e.getValue());
    }
    return alp;
  }
}
