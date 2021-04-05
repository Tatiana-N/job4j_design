package ru.job4j.exam;

import java.util.*;

public class Application {
  public void start() {
    Scanner scanner = new Scanner(System.in);
    Map<String, String> map = new HashMap<>();
    System.out.println("Введите имя пользователя");
    String name = scanner.next();
    while (!name.equals("выход")) {
      System.out.println("Введите почту данного пользователя, если их несколько то введите их через запятую ");
      TreeSet<String> mails = new TreeSet<>(Arrays.asList(scanner.next().split(",")));
      for (String mail : mails) {
        if (!map.containsKey(mail)) {
          map.put(mail, name);
        } else {
          String nameTheSameUser = map.put(mail, name);
          for (String m : map.keySet()) {
            if (map.get(m).equals(nameTheSameUser)) {
              map.put(m, name);
            }
          }
        }
      }
      System.out.println("Введите имя следующего пользователя или \"выход\"");
      name = scanner.next();
    }
    Set<String> users = new HashSet<>(map.values());
    Map<String, Set<String>> result = new HashMap<>();
    for (String user : users) {
      for (String mail : map.keySet()) {
        if (map.get(mail).equals(user)) {
          if (!result.containsKey(user)) {
            result.put(user, new HashSet<>());
          }
          result.get(user).add(mail);
        }
      }
    }
      for (String n : result.keySet()) {
        System.out.print(n + " " + result.get(n));
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Application app = new Application();
    app.start();
  }
}
