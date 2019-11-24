package ca.ciccc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBHPriorityQueue {

  private VCPriorityQueue bhp;

  @Before
  public void before() {
    System.out.println("SET UP");
    bhp = new BHPriorityQueue();
    bhp.enqueue(15, "pikachu");
    bhp.enqueue(29, "rukario");
    bhp.enqueue(20, "hitokage");
    bhp.enqueue(30, "zenigame");
    bhp.enqueue(44, "fushigibana");
    bhp.enqueue(46, "myu");
    bhp.enqueue(33, "genger");
    bhp.enqueue(30, "myu");
    bhp.enqueue(47, "myu");
    bhp.enqueue(45, "myu");
    bhp.enqueue(51, "myu");
  }

  @Test
  public void size() {
    System.out.println("TEST: size");
    assertEquals(11, bhp.size());

    // boundary test: upper bound
    bhp.enqueue(37, "myu");
    assertEquals(12, bhp.size());

    // boundary test: lower bound
    while (!bhp.isEmpty()) {
      bhp.dequeueMin();
    }
    assertEquals(0, bhp.size());
  }

  @Test
  public void isEmpty() {
    System.out.println("TEST: isEmpty");
    while (!bhp.isEmpty()) {
      bhp.dequeueMin();
    }
    assertTrue(bhp.isEmpty());
  }

  @Test
  public void enqueue() {
    System.out.println("TEST: enqueue");
    Entry e = new Entry(25, "darumakka");
    assertEquals(bhp.enqueue(25, "darumakka"), e);
  }

  @Test
  public void peek() {
    System.out.println("TEST: peek");
    Entry e = new Entry(15, "pikachu");
    assertEquals(bhp.peek(), e);
  }

  @Test
  public void dequeueMin() {
    System.out.println("TEST: dequeueMin");
    int[] priorities = new int[11];
    for (int i = 0; i < priorities.length; i++) {
      priorities[i] = (int) bhp.dequeueMin().getKey();
    }
    assertArrayEquals(priorities, new int[] {15, 20, 29, 30, 30, 33, 44, 45, 46, 47, 51});

    // boundary test: lower bound
    assertNull(bhp.dequeueMin());
  }

  @Test
  public void merge() {
    System.out.println("TEST: merge");
    VCPriorityQueue other = new BHPriorityQueue();
    other.enqueue(8, "myu");
    other.enqueue(11, "myu");
    other.enqueue(17, "myu");
    other.enqueue(35, "myu");
    other.enqueue(50, "myu");

    VCPriorityQueue merge = bhp.merge(other);
    Entry[] result = new Entry[merge.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = merge.dequeueMin();
    }
    assertArrayEquals(
        result,
        new Entry[] {
          new Entry(8, "myu"),
          new Entry(11, "myu"),
          new Entry(15, "pikachu"),
          new Entry(17, "myu"),
          new Entry(20, "hitokage"),
          new Entry(29, "rukario"),
          new Entry(30, "zenigame"),
          new Entry(30, "myu"),
          new Entry(33, "genger"),
          new Entry(35, "myu"),
          new Entry(44, "fushigibana"),
          new Entry(45, "myu"),
          new Entry(46, "myu"),
          new Entry(47, "myu"),
          new Entry(50, "myu"),
          new Entry(51, "myu")
        });
  }
}
