package ru.job4j.io;

import java.util.Stack;

public class Shell {
    Stack<String> strings;

    public Shell() {
        this.strings = new Stack<>();
    }

    public void cd(String path) {
        String[] split = path.split("/");
        for (String s : split) {
            if (s.equals("")) {
                continue;
            }
            if (s.equals("..")) {
                strings.pop();
            } else {
                strings.push(s);
            }
        }
    }

    public String pwd() {
        return "/" + String.join("/", strings);
    }
}
