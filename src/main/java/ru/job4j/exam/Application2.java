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
        String oldName = mapKMailVUser.put(mail, name);
        if (oldName != null) {
          theSameUsers.add(oldName);
        }
      }
      if (theSameUsers.isEmpty()) {
        mapKUserVSetMails.put(name, mails);
      } else {
        Set<String> mailsNew = new HashSet<>();
        for (String n : theSameUsers) {
          if (mapKUserVSetMails.containsKey(n)) {
            mailsNew.addAll(mapKUserVSetMails.get(n));
            mapKUserVSetMails.remove(n);
          }
        }
        mailsNew.addAll(mails);
        mapKUserVSetMails.put(name, mailsNew);
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
