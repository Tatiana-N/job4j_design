package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {

  private final List<T> mem = new ArrayList<>();

  @Override
  public void add(T model) {
    mem.add(model);
  }

  /**
   * Returns the index of the first occurrence of the specified element
   * in this list, or -1 if this list does not contain the element.
   *
   * @param id element to search for
   * @return the index of the first occurrence of the specified element in
   * this list, or -1 if this list does not contain the element
   */
  public int getindexById(String id) {
    for (int i = 0; i < mem.size(); i++) {
      if (mem.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean replace(String id, T model) {
    int a = getindexById(id);
    if (a == -1) {
      return false;
    }
    mem.set(a, model);
    return mem.get(a).getId().equals(model.getId());
  }

  @Override
  public boolean delete(String id) {
    int a = getindexById(id);
    if (a == -1) {
      return false;
    }
    return mem.remove(mem.get(a));
  }

  /**
   * Returns the element whith specified id in this list.
   *
   * @param id of the element
   * @return null if element whith this id not exist in this list
   */
  @Override
  public T findById(String id) {
    int a = getindexById(id);
    if (a == -1) {
      return null;
    }
    return mem.get(a);
  }

  public int size() {
    return mem.size();
  }
}
