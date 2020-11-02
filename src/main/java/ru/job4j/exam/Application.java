package ru.job4j.exam;

import java.util.*;

public class Application {
  public void start() {
    Scanner scanner = new Scanner(System.in);
    Set<User> userSet = new HashSet<>();
    System.out.println("Введите имя пользователя");
    String str = scanner.next();
    while (!str.equals("выход")) {
      System.out.println("Введите почту данного пользователя, если их несколько то введите их через запятую ");
      User user = new User(str, new TreeSet<String>(Arrays.asList(scanner.next().split(","))));
      userSet.add(user);
      System.out.println("Введите имя следующего пользователя или \"выход\"");
      str = scanner.next();
    }
    for (User user : userSet) {
      System.out.print(user.getName() + " : ");
      for (String s : user.getMails()) {
        System.out.print(s + ",");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Application app = new Application();
    app.start();
  }
}
