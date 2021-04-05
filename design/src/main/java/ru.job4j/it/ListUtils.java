package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

  public static <T> void addBefore(List<T> list, int index, T value) {
    Objects.checkIndex(index, list.size());
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (i.nextIndex() == index) {
        i.add(value);
        break;
      }
      i.next();
    }
  }

  public static <T> void addAfter(List<T> list, int index, T value) {
    Objects.checkIndex(index, list.size());
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      if (i.nextIndex() == index) {
        i.next();
        i.add(value);
        break;
      }
      i.next();
    }

  }

  public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      T element = i.next();
      if (filter.test(element)) {
        i.remove();
      }
    }
    return list;
  }

  public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
    ListIterator<T> i = list.listIterator();
    while (i.hasNext()) {
      T element = i.next();
      if (filter.test(element)) {
        i.set(value);
      }
    }
    return list;
  }

  public static <T> List<T> removeAll(List<T> list, List<T> elements) {
    ListIterator<T> i = list.listIterator();

    while (i.hasNext()) {
      ListIterator<T> j = elements.listIterator();
      T iNext = i.next();
      while (j.hasNext()) {
        T jNext = j.next();
        if (iNext.equals(jNext)) {
          i.remove();
        }
      }
    }
    return list;
  }

}