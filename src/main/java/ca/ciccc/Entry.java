package ca.ciccc;

/**
 * Class for the Entry element of VCPriorityQueue
 *
 * @author Derrick Park
 */
public class Entry<K extends Comparable, V> {
  K key;
  V value;

  /**
   * Returns the key stored in this entry.
   *
   * @return the entry's key
   */
  K getKey() {
    return key;
  }

  /**
   * Returns the value stored in this entry.
   *
   * @return the entry's value
   */
  V getValue() {
    return value;
  }

  public Entry(K k, V v) {
    key = k;
    value = v;
  }

  @Override
  public String toString() {
    return "Entry{" + "key=" + key + ", value=" + value + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Entry)) return false;

    Entry<?, ?> entry = (Entry<?, ?>) o;

    if (getKey() != null ? !getKey().equals(entry.getKey()) : entry.getKey() != null) return false;
    return getValue() != null ? getValue().equals(entry.getValue()) : entry.getValue() == null;
  }
}
