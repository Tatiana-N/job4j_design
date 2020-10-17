package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    throw new NoSuchElementException();
  }

  @Override
  public boolean replace(String id, T model) {
    int a = getindexById(id);
    mem.set(a, model);
    return mem.get(a).getId().equals(model.getId());
  }

  @Override
  public boolean delete(String id) {
    return mem.remove(mem.get(getindexById(id)));
  }

  @Override
  public T findById(String id) {
    return mem.get(getindexById(id));
  }

  public int size() {
    return mem.size();
  }
}
