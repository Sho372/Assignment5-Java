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
    dlp.enqueue(15, "pikachu");
    dlp.enqueue(29, "rukario");
    dlp.enqueue(20, "hitokage");
    dlp.enqueue(30, "zenigame");
    dlp.enqueue(44, "fushigibana");
    dlp.enqueue(46, "myu");
    dlp.enqueue(33, "genger");
    dlp.enqueue(30, "myu");
    dlp.enqueue(47, "myu");
    dlp.enqueue(45, "myu");
    dlp.enqueue(51, "myu");
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
    Entry e = new Entry(25, "darumakka");
    assertEquals(e, dlp.enqueue(25, "darumakka"));
  }

  @Test
  public void peek() {
    System.out.println("TEST: peek");
    Entry e = new Entry(15, "pikachu");
    assertEquals(e, dlp.peek());
  }

  @Test
  public void dequeueMin() {
    System.out.println("TEST: dequeueMin");
    int[] priorities = new int[11];
    for (int i = 0; i < priorities.length; i++) {
      priorities[i] = (int) dlp.dequeueMin().getKey();
    }
    assertArrayEquals(new int[] {15, 20, 29, 30, 30, 33, 44, 45, 46, 47, 51}, priorities);

    // boundary test: lower bound
    assertNull(dlp.dequeueMin());
  }

  @Test
  public void merge() {
    System.out.println("TEST: merge");
    VCPriorityQueue other = new DLPriorityQueue();
    other.enqueue(8, "myu");
    other.enqueue(11, "myu");
    other.enqueue(17, "myu");
    other.enqueue(35, "myu");
    other.enqueue(50, "myu");

    VCPriorityQueue merge = dlp.merge(other);
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
