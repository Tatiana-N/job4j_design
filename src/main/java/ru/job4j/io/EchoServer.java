package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

  public static void main(String[] args) {
    DispatchPatternForServer dss = new DispatchPatternForServer();
    dss.init();
    try (ServerSocket server = new ServerSocket(9000)) {
      while (true) {
        Socket socket = server.accept();
        try (OutputStream out = socket.getOutputStream();
             BufferedReader in = new BufferedReader(
               new InputStreamReader(socket.getInputStream()))) {
          String answer = "";
          String str;
          while (!(str = in.readLine()).isEmpty()) {
            if (str.contains("msg")) {
              answer = dss.check(str.substring(str.lastIndexOf("=") + 1, str.indexOf("HTTP") - 1));
            }
            if (!dss.getFlag()) {
              out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
              server.close();
              return;
            }
            System.out.println(str);
          }

          out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
          out.write(answer.getBytes());
          out.flush();
        }
      }
    } catch (IOException e) {
      LOG.error("java.io.IOException – if an I/O error occurs when opening the socket.", e);
    }
  }
}