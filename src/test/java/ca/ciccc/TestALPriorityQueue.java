package ca.ciccc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestALPriorityQueue {

  private VCPriorityQueue alp;

  @Before
  public void before() {
    System.out.println("SET UP");
    alp = new ALPriorityQueue();
    alp.enqueue(1, "test1");
    alp.enqueue(3, "test3");
    alp.enqueue(5, "test5");
    alp.enqueue(7, "test7");
    alp.enqueue(9, "test9");
    alp.enqueue(11, "test11");
    alp.enqueue(13, "test13");
    alp.enqueue(15, "test15");
    alp.enqueue(17, "test17");
    alp.enqueue(19, "test19");
    alp.enqueue(21, "test21");
  }

  @Test
  public void size() {
    System.out.println("TEST: size");
    assertEquals(11, alp.size());

    // boundary test: lower bound
    while (!alp.isEmpty()) {
      alp.dequeueMin();
    }
    assertEquals(0, alp.size());
  }

  @Test
  public void isEmpty() {
    System.out.println("TEST: isEmpty");
    while (!alp.isEmpty()) {
      alp.dequeueMin();
    }
    assertTrue(alp.isEmpty());
  }

  @Test
  public void enqueue() {
    System.out.println("TEST: enqueue");
    Entry e = new Entry(23, "test23");
    assertEquals(e, alp.enqueue(23, "test23"));
  }

  @Test
  public void peek() {
    System.out.println("TEST: peek");
    Entry e = new Entry(1, "test1");
    assertEquals(e, alp.peek());
  }

  @Test
  public void dequeueMin() {
    System.out.println("TEST: dequeueMin");
    int[] priorities = new int[11];
    for (int i = 0; i < priorities.length; i++) {
      priorities[i] = (int) alp.dequeueMin().getKey();
    }
    assertArrayEquals(new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21}, priorities);

    // boundary test: lower bound
    assertNull(alp.dequeueMin());
  }

  @Test
  public void merge() {
    System.out.println("TEST: merge");
    VCPriorityQueue other = new ALPriorityQueue();
    other.enqueue(2, "test2");
    other.enqueue(4, "test4");
    other.enqueue(6, "test6");
    other.enqueue(8, "test8");
    other.enqueue(10, "test10");

    VCPriorityQueue merge = alp.merge(other);
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
