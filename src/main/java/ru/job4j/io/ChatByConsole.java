package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class ChatByConsole {
  private static final String OUT = "закончить";
  private static final String STOP = "стоп";
  private static final String CONTINUE = "продолжить";
  private StringBuilder result;
  private ArgZip argZip;
  private Boolean flagStopChat = true;

  public ChatByConsole(String[] args) {
    result = new StringBuilder();
    String[] newArgs = Arrays.copyOf(args, args.length + 1);
    newArgs[args.length] = "-e=";
    this.argZip = new ArgZip(newArgs);
  }

  public void run() throws IOException {
    Scanner scanner = new Scanner(System.in);
    String answer = "";
    String userText = scanner.nextLine();
    while (!userText.equals(OUT)) {
      answer = this.returnAnswer(userText);
      addString(userText);
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
    if (userText.equals(STOP)) {
      flagStopChat = false;
    }
    if (userText.equals(CONTINUE)) {
      flagStopChat = true;
    }
    String answer = "";
    if (flagStopChat) {
      int rand = 0;
      try (
        Stream<String> lines = Files.lines(new File(argZip.directory()).toPath())
      ) {
        long numOfLines = lines.count();
        rand = (int) (Math.random() * numOfLines); // рандомный номер строки
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
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
    this.result.append(elseString + "\n");
  }

  private String getAllDialog() {
    return result.toString();
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
