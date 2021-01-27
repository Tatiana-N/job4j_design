package ru.job4j.exam;

import java.util.*;

public class NotMy {
  public Map<String, Set<String>> unionEmails(HashMap<String, Set<String>> emailAccounts) {
    return changeMap(emailAccounts, new HashMap<>());

  }

  public Map<String, Set<String>> changeMap2(Map<String, Set<String>> inPut) {
    Map<String, Set<String>> outPut = new HashMap<>();
    for (Map.Entry<String, Set<String>> inEntry : inPut.entrySet()) {
      String mergeKey = null;
      Set<String> mergeValue = null;
      for (Map.Entry<String, Set<String>> outEntry : outPut.entrySet()) {
        for (String email : inEntry.getValue()) {
          if(outEntry.getValue().contains(email)){
            mergeKey = outEntry.getKey();
            mergeValue = inPut.get(inEntry.getKey());
            break;
          }
        }
if(mergeKey != null){
  break;
}
      }
      if(mergeKey != null){
        outPut.get(mergeKey).addAll(mergeValue);
      } else {
        outPut.put(inEntry.getKey(), new HashSet<>(inEntry.getValue()));

      }


    }
return outPut;
  }
  private Map<String, Set<String>> changeMap(Map<String, Set<String>> map, Map<String, Set<String>> res) {
    boolean wasChanged = false;
    for (String user : res.keySet()) {
      for (String userMap : map.keySet()) {
        if (res.get(user).stream().anyMatch(t -> map.get(userMap).contains(t))) {
          res.get(user).addAll(map.get(userMap));
          map.remove(userMap);
          wasChanged = true;
          break;
        }
      }
    }
    if (map.isEmpty()) {
      return res;
    } else if (!wasChanged) {
      String user = map.keySet().stream().findFirst().get();
      res.put(user, map.get(user));
      map.remove(user);
    }
    return changeMap(map, res);
  }
}
