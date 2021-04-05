package ru.job4j.exam;

import java.util.*;

import java.util.stream.Collectors;

public class NotMy {
  public static Map<String, Set<String>> inputAllUsers() {
    Map<String, Set<String>> emailAccount = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    while (!line.equals("exit")) {
      String[] s = line.split(" ");
      for (int i = 1; i < s.length; i++) {
        if (!emailAccount.containsKey(s[i])) {
          emailAccount.put(s[i], new HashSet<>());
          emailAccount.get(s[i]).add(s[0]);
        } else {
          emailAccount.get(s[i]).add(s[0]);
        }
      }
      line = scanner.nextLine();
    }
    scanner.close();
    return emailAccount;
  }

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
          if (outEntry.getValue().contains(email)) {
            mergeKey = outEntry.getKey();
            mergeValue = inPut.get(inEntry.getKey());
            break;
          }
        }
        if (mergeKey != null) {
          break;
        }
      }
      if (mergeKey != null) {
        outPut.get(mergeKey).addAll(mergeValue);
      } else {
        outPut.put(inEntry.getKey(), new HashSet<>(inEntry.getValue()));
      }
    }
    return outPut;
  }

  public static Map<String, Set<String>> unionEmailsByMapEmailUsers(Map<String, Set<String>> inPut) {
    Map<String, Set<String>> result = new HashMap<>();
    Map<String, String> newInput = new HashMap<>();
    List<Set<String>> collectAllRepeat = inPut.values().stream().filter(t -> t.size() > 1).collect(Collectors.toList());
    List<Set<String>> collectAllRepeat2 = new ArrayList<>(collectAllRepeat);
    Map<String, String> resultRepeat = new HashMap<>();
    for (Set<String> set : collectAllRepeat) {
      Set<String> r = new HashSet<>();
      collectAllRepeat2.remove(set);
      if (!collectAllRepeat2.isEmpty()) {
        for (String s : set) {
          collectAllRepeat2.stream().filter(t -> t.contains(s)).flatMap(Set::stream).forEach(r::add);
          if (!r.isEmpty()) {
            r.addAll(set);
          }
        }
      } else {
        break;
      }
      if (!r.isEmpty()) {
        String nameRepeater = r.stream().findFirst().get();
        r.forEach(t -> resultRepeat.put(t, nameRepeater));
        List<String> collect = resultRepeat.entrySet().stream().filter(t -> r.contains(t.getValue())).map(t -> t.getKey()).collect(Collectors.toList());
        for (String s : collect) {
          String remove = resultRepeat.remove(s);
          //resultRepeat.put()
        }
        for (String s : inPut.keySet()) {
          // Set<String> set = inPut.get(s);
          String user = set.stream().findAny().get();
          if (resultRepeat.containsKey(user)) {
            newInput.put(s, resultRepeat.get(user));
          } else {
            newInput.put(s, user);
          }
        }
      }
      newInput.values().stream().distinct().forEach(t -> result.put(t, new HashSet<>()));
      for (String s : result.keySet()) {
        result.put(s, newInput.keySet().stream().filter(t -> newInput.get(t).equals(s)).collect(Collectors.toSet()));
      }
      return result;
    }
    return null;
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
