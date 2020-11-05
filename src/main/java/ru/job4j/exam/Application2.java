package ru.job4j.exam;

import java.util.*;

public class Application2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Map<String, String> mapKMailVUser = new HashMap<>();
    Map<String, Set<String>> mapKUserVSetMails = new HashMap<>();
    Set<String> theSameUsers = new HashSet<>();
    String name = scanner.next();
    while (!name.equals("exit")) {
      TreeSet<String> mails = new TreeSet<>(Arrays.asList(scanner.next().split(",")));
      for (String mail : mails) {
          theSameUsers.add(mapKMailVUser.put(mail, name));
      }
        mapKUserVSetMails.put(name, mails);
        for (String n : theSameUsers) {
          if (mapKUserVSetMails.containsKey(n)) {
            mapKUserVSetMails.get(name).addAll(mapKUserVSetMails.get(n));
            mapKUserVSetMails.remove(n);
          }
      }
      name = scanner.next();
    }
    scanner.close();
    for (String f : mapKUserVSetMails.keySet()) {
      System.out.print(f + " " + mapKUserVSetMails.get(f));
      System.out.println();
    }
  }
}
