package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

public class SimpleMapTest {
  @Test
  public void testInsert() {
    SimpleMap<Integer, String> map = new SimpleMap(1);
    map.insert(1, "Apple");
    map.insert(2, "Bpple");
    map.insert(3, "Cpple");
    map.insert(4, "Dpple");
    System.out.println(map.insert(4, "Fpple"));
    Iterator it = map.iterator();
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(it.next());
   // System.out.println(it.next());
  }

  @Test
  public void testGet() {
    SimpleMap<Integer, String> map = new SimpleMap(1);
    map.insert(17, "Apple");
    map.insert(18, "Bpple");
    map.insert(27, "Cpple");
    map.insert(20, "Dpple");
    map.insert(21, "Fpple");
    System.out.println(map.get(27));
  }

  @Test
  public void testDelete() {
  }
}