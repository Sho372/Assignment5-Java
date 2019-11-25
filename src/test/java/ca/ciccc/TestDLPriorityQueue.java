package ca.ciccc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDLPriorityQueue {

  private VCPriorityQueue dlp;

  @Before
  public void before() {
    System.out.println("SET UP");
    dlp = new DLPriorityQueue();
    dlp.enqueue(1, "test1");
    dlp.enqueue(3, "test3");
    dlp.enqueue(5, "test5");
    dlp.enqueue(7, "test7");
    dlp.enqueue(9, "test9");
    dlp.enqueue(11, "test11");
    dlp.enqueue(13, "test13");
    dlp.enqueue(15, "test15");
    dlp.enqueue(17, "test17");
    dlp.enqueue(19, "test19");
    dlp.enqueue(21, "test21");
  }

  @Test
  public void size() {
    System.out.println("TEST: size");
    assertEquals(11, dlp.size());

    // boundary test: lower bound
    while (!dlp.isEmpty()) {
      dlp.dequeueMin();
    }
    assertEquals(0, dlp.size());
  }

  @Test
  public void isEmpty() {
    System.out.println("TEST: isEmpty");
    while (!dlp.isEmpty()) {
      dlp.dequeueMin();
    }
    assertTrue(dlp.isEmpty());
  }

  @Test
  public void enqueue() {
    System.out.println("TEST: enqueue");
    Entry e = new Entry(23, "test23");
    assertEquals(e, dlp.enqueue(23, "test23"));
  }

  @Test
  public void peek() {
    System.out.println("TEST: peek");
    Entry e = new Entry(1, "test1");
    assertEquals(e, dlp.peek());
  }

  @Test
  public void dequeueMin() {
    System.out.println("TEST: dequeueMin");
    int[] priorities = new int[11];
    for (int i = 0; i < priorities.length; i++) {
      priorities[i] = (int) dlp.dequeueMin().getKey();
    }
    assertArrayEquals(new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21}, priorities);

    // boundary test: lower bound
    assertNull(dlp.dequeueMin());
  }

  @Test
  public void merge() {
    System.out.println("TEST: merge");
    VCPriorityQueue other = new DLPriorityQueue();
    other.enqueue(2, "test2");
    other.enqueue(4, "test4");
    other.enqueue(6, "test6");
    other.enqueue(8, "test8");
    other.enqueue(10, "test10");

    VCPriorityQueue merge = dlp.merge(other);
    Entry[] result = new Entry[merge.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = merge.dequeueMin();
    }
    assertArrayEquals(
        new Entry[] {
          new Entry(1, "test1"),
          new Entry(2, "test2"),
          new Entry(3, "test3"),
          new Entry(4, "test4"),
          new Entry(5, "test5"),
          new Entry(6, "test6"),
          new Entry(7, "test7"),
          new Entry(8, "test8"),
          new Entry(9, "test9"),
          new Entry(10, "test10"),
          new Entry(11, "test11"),
          new Entry(13, "test13"),
          new Entry(15, "test15"),
          new Entry(17, "test17"),
          new Entry(19, "test19"),
          new Entry(21, "test21")
        },
        result);
  }
}
