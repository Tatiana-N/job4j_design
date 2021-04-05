package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChatByConsole {
  private ArgZip argZip;
  private ArrayList<String> chat;
  private ArrayList<String> answers;
  private DispatchPattern dp;

  public ChatByConsole(String[] args) {
    chat = new ArrayList<>(50); //список для записи чата
    answers = new ArrayList<>(50);
    String[] newArgs = Arrays.copyOf(args, args.length + 1);
    newArgs[args.length] = "-e="; // костыль для использования уже написанного класса ArgZip
    this.argZip = new ArgZip(newArgs);
    readAnswers();
    dp = new DispatchPattern();
    dp.init();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    String answer;
    String userText = scanner.nextLine();
    while (dp.check(userText)) {
      answer = this.returnAnswer(answers);
      this.addString(userText);
      if (!answer.isEmpty()) {
        System.out.println(answer);
        this.addString(answer);
      }
      userText = scanner.nextLine();
    }
    writeDialogInFile();
  }

  private String returnAnswer(ArrayList<String> answers) {
    if (dp.getFlag()) {
      int rand = (int) (Math.random() * answers.size()); // рандомный номер строки
      return answers.get(rand);
    } else {
      return "";
    }
  }

  public static void main(String[] args) {

    ChatByConsole chatByConsole = new ChatByConsole(args);
    chatByConsole.run();
  }

  private void readAnswers() {
    try (BufferedReader bR = new BufferedReader(new FileReader(argZip.directory()))) {
      while (bR.ready()) {
        answers.add(bR.readLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addString(String elseString) {
    this.chat.add(elseString);
  }

  private boolean writeDialogInFile() {
    try (BufferedWriter bW = new BufferedWriter(new FileWriter(argZip.output(), Charset.forName("WINDOWS-1251")))) {
      for (String chatPhrase : chat) {
        bW.write(chatPhrase);
        bW.newLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
}
