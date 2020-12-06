package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChatByConsole {
  private ArgZip argZip;
  private ArrayList<String> chat;
  private DispatchPattern dp;
  long numOfLines = 0;
  int rand = 0;

  public ChatByConsole(String[] args) {
    chat = new ArrayList<>(50); //список для записи чата
    String[] newArgs = Arrays.copyOf(args, args.length + 1);
    newArgs[args.length] = "-e="; // костыль для использования уже написанного класса ArgZip
    this.argZip = new ArgZip(newArgs);
    try (
      Stream<String> lines = Files.lines(new File(argZip.directory()).toPath())
    ) {
      numOfLines = lines.count(); // посчитаем строки в файле ответов
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    dp = new DispatchPattern();
    dp.init();
  }

  public void run() throws IOException {

    Scanner scanner = new Scanner(System.in);
    String answer = "";
    String userText = scanner.nextLine();
    while (dp.check(userText)) {
      answer = this.returnAnswer(userText);
      this.addString(userText);
      if (!answer.isEmpty()) {
        System.out.println(answer);
        this.addString(answer);
      }
      userText = scanner.nextLine();
    }
    writeDialogInFile();
  }

  public static void main(String[] args) throws IOException {

    ChatByConsole chatByConsole = new ChatByConsole(args);
    chatByConsole.run();
  }

  private String returnAnswer(String userText) {
    String answer = "";
    if (dp.getFlag()) {
      rand = (int) (Math.random() * numOfLines); // рандомный номер строки
      try (BufferedReader bR = new BufferedReader(new FileReader(argZip.directory()))) {
        while (rand-- > 0) {
          answer = bR.readLine();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return answer;
  }

  private void addString(String elseString) {
    this.chat.add(elseString);
  }

  private String getAllDialog() {
    return chat.stream().map(p -> p + "\n").collect(Collectors.joining());
  }

  private boolean writeDialogInFile() throws IOException {
    try (BufferedWriter bW = new BufferedWriter(new FileWriter(argZip.output(), Charset.forName("WINDOWS-1251")))) {
      bW.write(getAllDialog());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }
}
