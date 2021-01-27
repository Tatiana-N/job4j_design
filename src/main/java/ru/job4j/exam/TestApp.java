package ru.job4j.exam;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestApp {

  private static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RESET = "\u001B[0m";

  Set<String> emailsEvgeniy = new HashSet<>();
  Set<String> emailsViktoria = new HashSet<>();
  Set<String> emailsEkaterina = new HashSet<>();
  Set<String> emailsMasha = new HashSet<>();
  Set<String> emailsMary = new HashSet<>();
  HashMap<String, Set<String>> emailsAccount = new HashMap<>();

  HashMap<String, Set<String>> emailsAccount2 = new HashMap<>();


  @Test
  public void get() {
    emailsEvgeniy.add("xxx@ya.ru");
    emailsEvgeniy.add("foo@gmail.com");
    emailsEvgeniy.add("lol@mail.ru");
    emailsViktoria.add("foo@gmail.com");
    emailsViktoria.add("ups@pisem.net");
    emailsEkaterina.add("xyz@pisem.net");
    emailsEkaterina.add("vasya@pupkin.com");
    emailsMasha.add("ups@pisem.net");
    emailsMasha.add("aaa@bbb.ru");
    emailsAccount.put("Evgeny", emailsEvgeniy);
    emailsAccount.put("Viktoria", emailsViktoria);
    emailsAccount.put("Ekaterina", emailsEkaterina);
    emailsAccount.put("Masha", emailsMasha);
    Set<String> s1 = new HashSet<>();
    s1.add("Masha");
    emailsAccount2.put("aaa@bbb.ru", s1);
    Set<String> s2 = new HashSet<>();
    s2.add("Masha");
    s2.add("Viktoria");
    emailsAccount2.put("ups@pisem.net", s2);
    Set<String> s3 = new HashSet<>();
    s3.add("Ekaterina");
    emailsAccount2.put("vasya@pupkin.com", s3);
    Set<String> s4 = new HashSet<>();
    s4.add("Ekaterina");
    emailsAccount2.put("xyz@pisem.net", s4);
    Set<String> s5 = new HashSet<>();
    s5.add("Evgeny");
    emailsAccount2.put("lol@mail.ru", s5);
    Set<String> s6 = new HashSet<>();
    s6.add("Evgeny");
    emailsAccount2.put("xxx@ya.ru", s6);
    Set<String> s7 = new HashSet<>();
    s7.add("Evgeny");
    s7.add("Viktoria");
    emailsAccount2.put("foo@gmail.com", s7);
  }

  @Test
  public void whenSomeUsersHaveSameEmailThenNewSize2variant() {
    get();
    NotMy email = new NotMy();
    System.out.println(ANSI_RESET + ANSI_GREEN + " Начальные данные: ");
    emailsAccount.entrySet().stream().forEach(t -> System.out.println(t.getKey() + "\t" + t.getValue()));
    Map<String, Set<String>> result = email.changeMap2(emailsAccount);
    List<String> users = new ArrayList<>(result.keySet());
    String user = users.get(0);
    System.out.println(ANSI_RESET + ANSI_PURPLE + " Итоговые данные: ");
    result.forEach((key, value) -> System.out.println(key + " " + value));
    assertThat(result.get(user).size(), is(5));
  }

  @Test
  public void change3variant() { //
    get();
    NotMy email = new NotMy();
    System.out.println(ANSI_RESET + ANSI_GREEN + " Начальные данные: ");
    emailsAccount2.entrySet().stream().forEach(t -> System.out.println(t.getKey() + "\t" + t.getValue()));
    Map<String, Set<String>> result = email.unionEmailsByMapEmailUsers(emailsAccount2);
    List<String> users = new ArrayList<>(result.keySet());
    String user = users.get(0);
    System.out.println(ANSI_RESET + ANSI_PURPLE + " Итоговые данные: ");
    result.forEach((key, value) -> System.out.println(key + " " + value));
    assertThat(result.get(user).size(), is(5));

  }

  @Test
  public void whenSomeUsersHaveSameEmailThenSizeTwo1variant() {
    get();
    emailsMary.add("xyz@pisem.net");
    emailsAccount.put("Mary", emailsMary);
    NotMy email = new NotMy();
    System.out.println(ANSI_RESET + ANSI_GREEN + " Начальные данные: ");
    emailsAccount.entrySet().stream().forEach(t -> System.out.println(t.getKey() + "\t" + t.getValue()));
    Map<String, Set<String>> result = email.unionEmails(emailsAccount);
    System.out.println(ANSI_RESET + ANSI_PURPLE + " Итоговые данные: ");
    result.forEach((key, value) -> System.out.println(key + " " + value));
    List<String> users = new ArrayList<>(result.keySet());
    // String user = users.get(0);
    assertThat(result.size(), is(2));

  }

  @Test
  public void whenSomeUsersHaveSameEmailThenSizeTwo3variant() {
    get();
    Set<String> s6 = new HashSet<>();
    s6.add("Mary");
    s6.add("Ekaterina");
    emailsAccount2.put("xyz@pisem.net", s6);
    NotMy email = new NotMy();
    System.out.println(ANSI_RESET + ANSI_GREEN + " Начальные данные: ");
    emailsAccount2.entrySet().stream().forEach(t -> System.out.println(t.getKey() + "\t" + t.getValue()));
    Map<String, Set<String>> result = email.unionEmailsByMapEmailUsers(emailsAccount2);
    System.out.println(ANSI_RESET + ANSI_PURPLE + " Итоговые данные: ");
    result.forEach((key, value) -> System.out.println(key + " " + value));
    List<String> users = new ArrayList<>(result.keySet());
    // String user = users.get(0);
    assertThat(result.size(), is(2));

  }
}