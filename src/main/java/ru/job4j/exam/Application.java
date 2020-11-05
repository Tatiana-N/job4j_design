package ru.job4j.exam;

import java.util.*;

public class Application {
  public void start() {
    Scanner scanner = new Scanner(System.in);
    Map<User, String> map = new HashMap<>();
    //Set<User> userSet = new HashSet<>();
    System.out.println("Введите имя пользователя");
    String name = scanner.next();
    while (!name.equals("выход")) {
      System.out.println("Введите почту данного пользователя, если их несколько то введите их через запятую ");
      TreeSet<String> mails = new TreeSet<>(Arrays.asList(scanner.next().split(",")));
      // userSet.add(user);
      User user = new User(name, mails);
      for (String s : mails) {
       User alreadyExistUser =  map.put(s, user);
       if(alreadyExistUser != null && alreadyExistUser != user){
         alreadyExistUser.getMails().addAll(user.getMails());
         map.put(s, alreadyExistUser);
         user = alreadyExistUser;
       }
      }
      System.out.println("Введите имя следующего пользователя или \"выход\"");
      name = scanner.next();
    }
    for (User user : map.values()) {
      System.out.print(user.getName() + " : ");
      for (String s : user.getMails()){
        System.out.print(s + " ");
      }
      //   for (String s : user.getMails()) {
      //      System.out.print(s + ",");
      //   }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Application app = new Application();
    app.start();
  }
}
