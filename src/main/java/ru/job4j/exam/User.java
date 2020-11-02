package ru.job4j.exam;

import java.util.TreeSet;

public class User {
  private String name;
  private TreeSet<String> mails;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TreeSet<String> getMails() {
    return mails;
  }

  public void setMails(TreeSet<String> mails) {
    this.mails = mails;
  }

  public User(String name, TreeSet<String> mails) {
    this.name = name;
    this.mails = mails;
  }

  @Override
  public int hashCode() {
    return 42;
    // т.к. у пользователя может быть сколько угодно почт,
    // а равенство хоть одной будет говорить что это один и тот же пользователь,
    // то придется проверять только по equals?
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    for (String s : ((User) obj).getMails()) {
      if (this.getMails().contains(s)) {
        ((User) obj).getMails().addAll(this.getMails());
        return true;
      }
    }
    return false;
  }
}
