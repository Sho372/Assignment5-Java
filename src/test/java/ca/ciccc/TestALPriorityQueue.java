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
    alp.enqueue(15, "pikachu");
    alp.enqueue(29, "rukario");
    alp.enqueue(20, "hitokage");
    alp.enqueue(30, "zenigame");
    alp.enqueue(44, "fushigibana");
    alp.enqueue(46, "myu");
    alp.enqueue(33, "genger");
    alp.enqueue(30, "myu");
    alp.enqueue(47, "myu");
    alp.enqueue(45, "myu");
    alp.enqueue(51, "myu");
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
    Entry e = new Entry(25, "darumakka");
    assertEquals(e, alp.enqueue(25, "darumakka"));
  }

  @Test
  public void peek() {
    System.out.println("TEST: peek");
    Entry e = new Entry(15, "pikachu");
    assertEquals(e, alp.peek());
  }

  @Test
  public void dequeueMin() {
    System.out.println("TEST: dequeueMin");
    int[] priorities = new int[11];
    for (int i = 0; i < priorities.length; i++) {
      priorities[i] = (int) alp.dequeueMin().getKey();
    }
    assertArrayEquals(new int[] {15, 20, 29, 30, 30, 33, 44, 45, 46, 47, 51}, priorities);

    // boundary test: lower bound
    assertNull(alp.dequeueMin());
  }

  @Test
  public void merge() {
    System.out.println("TEST: merge");
    VCPriorityQueue other = new ALPriorityQueue();
    other.enqueue(8, "myu");
    other.enqueue(11, "myu");
    other.enqueue(17, "myu");
    other.enqueue(35, "myu");
    other.enqueue(50, "myu");

    VCPriorityQueue merge = alp.merge(other);
    Entry[] result = new Entry[merge.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = merge.dequeueMin();
    }
    assertArrayEquals(
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
        },
        result);
  }
}
