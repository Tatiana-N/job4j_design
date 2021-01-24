package ru.job4j.exam;

import java.util.*;

public class NotMy {
  public Map<String, Set<String>> unionEmails(HashMap<String, Set<String>> emailAccounts) {
    return changeMap(emailAccounts, new HashMap<>());

  }

  private Map<String, Set<String>> changeMap(Map<String, Set<String>> map, Map<String, Set<String>> res) {
    if (map.isEmpty()) {
      return res;
    }
    for (String user : res.keySet()) {
      for (String userMap : map.keySet()) {
        if (res.get(user).stream().anyMatch(t -> map.get(userMap).contains(t))) {
          res.get(user).addAll(map.get(userMap));
          map.remove(userMap);
          return changeMap(map, res);
        }
      }
    }
    List<String> users = new ArrayList<>(map.keySet());

    String user = users.get(0);
    res.put(user, map.get(users.get(0)));
      res.put(user, map.get(user));
      map.remove(user);
    return changeMap(map, res);
  }
}
