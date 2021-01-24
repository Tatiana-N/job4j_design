package ru.job4j.exam;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestApp {
  Set<String> emailsEvgeniy = new HashSet<>();
  Set<String> emailsViktoria = new HashSet<>();
  Set<String> emailsEkaterina = new HashSet<>();
  Set<String> emailsMasha = new HashSet<>();
  Set<String> emailsMary = new HashSet<>();
  HashMap<String, Set<String>> emailsAccount = new HashMap<>();

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
  }

  @Test

  public void whenSomeUsersHaveSameEmailThenNewSize() {
    get();
    NotMy email = new NotMy();
    Map<String, Set<String>> result = email.unionEmails(emailsAccount);
    List<String> users = new ArrayList<>(result.keySet());
    String user = users.get(0);
    assertThat(result.get(user).size(), is(5));

  }

  @Test

  public void whenSomeUsersHaveSameEmailThenSizeTwo() {
    get();
    emailsMary.add("xyz@pisem.net");
    emailsAccount.put("Mary", emailsMary);
    NotMy email = new NotMy();
    Map<String, Set<String>> result = email.unionEmails(emailsAccount);
    List<String> users = new ArrayList<>(result.keySet());
   // String user = users.get(0);
    assertThat(result.size(), is(2));

  }

}