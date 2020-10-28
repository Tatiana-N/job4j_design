package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

public class SimpleMapTest {
  @Test
  public void testInsert() {
    SimpleMap<Integer, String> map = new SimpleMap(0.75);
    map.insert(1, "Apple");
    map.insert(2, "Bpple");
    map.insert(3, "Cpple");
    map.insert(4, "Dpple");
    System.out.println(map.insert(10, "Fpple"));
    Iterator it = map.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }

  @Test
  public void testGet() {
    SimpleMap<Integer, String> map = new SimpleMap(0.75);
    System.out.println(map.insert(17, "Apple"));
    System.out.println(map.insert(18, "Bpple"));
    System.out.println(map.insert(22, "Cpple"));
    System.out.println(map.insert(20, "Dpple"));
    System.out.println(map.insert(21, "Fpple"));
    System.out.println(map.get(22));
  }

  @Test
  public void testDelete() {
    SimpleMap<Integer, String> map = new SimpleMap(0.75);
    map.insert(1, "Apple");
    map.insert(2, "Bpple");
    map.insert(3, "Cpple");
    System.out.println(map.delete(2));
    System.out.println(map.insert(10, "Fpple"));
    Iterator it = map.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}