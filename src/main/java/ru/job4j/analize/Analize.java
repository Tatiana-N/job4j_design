package ru.job4j.analize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
  public Info diff(List<User> previous, List<User> current) {
Map<Integer, String> prev = new HashMap<>();
    Map<Integer, String> cur = new HashMap<>();
for (User user: previous){
  prev.put(user.id, user.name);
}
    for (User user: current){
      cur.put(user.id, user.name);
    }
Info info = new Info();
    for (Integer id: cur.keySet()) {
      if(prev.containsKey(id)){
        if(!cur.get(id).equals(prev.get(id))){
          info.changed++;
        }
      } else{info.added++;}

    }
    return null;

  }

  public static class User {
   private int id;
   private String name;

    public User(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  public static class Info {

    int added;
    int changed;
    int deleted;

    public Info() {
      this.added = 0;
      this.changed = 0;
      this.deleted = 0;
    }
  }

}