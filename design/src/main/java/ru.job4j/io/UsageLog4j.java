package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

  private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

  public static void main(String[] args) {
    String name = "Petr Arsentev";
    int age = 33;
    double height = 187.3;
    long dayOfLife = age * 365L;
    boolean isMarried = true;
    float weight = 75.5f;
    byte workingHoursInDay = 8;
    short sleepHoursInDay = 7;
    char gender = 'м';
    LOG.debug("User info name : {}, gender : {}, height : {}, weight : {}, age : {}, dayOfLife : {}, isMarried : {}, workingHoursInDay : {}, sleepHoursInDay {}",
      name, gender, height, weight, age, dayOfLife, isMarried, workingHoursInDay, sleepHoursInDay);

    try {
      throw new Exception("Not supported code");
    } catch (Exception e) {
      LOG.error("Exception in log example", e);
    }
  }
}