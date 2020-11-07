package ru.job4j.exam;

import java.util.*;

public class Application2 {
  public static void main(String[] args) {
    Application2 application2 = new Application2();
    Set<User> userSet = application2.inputAllUsers();
    application2.consolidationMailsOfUsers(userSet);
  }

  public Set<User> inputAllUsers() {
    Set<User> listOfUsers = new HashSet<>();
    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();
    while (!name.equals("exit")) {
      TreeSet<String> mails = new TreeSet<>(Arrays.asList(scanner.next().split(",")));
      User user = new User(name, mails);
      listOfUsers.add(user);
      name = scanner.next();
    }
    scanner.close();
    return listOfUsers;
  }

  public void consolidationMailsOfUsers(Set<User> set) {
    Set<String> theSameUsers = new HashSet<>();
    Map<String, String> mapKMailVUser = new HashMap<>();
    Map<String, Set<String>> mapKUserVSetMails = new HashMap<>();
    Iterator<User> it = set.iterator();
    while (it.hasNext()) {
      User user = it.next();
      for (String mail : user.getMails()) {
        theSameUsers.add(mapKMailVUser.put(mail, user.getName()));
      }
      mapKUserVSetMails.put(user.getName(), user.getMails());
      for (String n : theSameUsers) {
        if (mapKUserVSetMails.containsKey(n)) {
          mapKUserVSetMails.get(user.getName()).addAll(mapKUserVSetMails.get(n));
          mapKUserVSetMails.remove(n);
        }
      }
    }
    for (String f : mapKUserVSetMails.keySet()) {
      System.out.print(f + " " + mapKUserVSetMails.get(f));
      System.out.println();
    }
  }
}
