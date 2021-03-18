package ru.job4j.io;

import java.util.Stack;

public class Shell {
    Stack<String> strings;

    public Shell() {
        this.strings = new Stack<>();
    }

    public void cd(String path) {
        String[] split = path.split("/");
        if (path.equals("")) {
            strings.push("/");
        }
        for (String s : split) {
            if (s.contains(".")) {
                while (s.length() != 0) {
                    s = s.replace(".", "");
                    strings.pop();
                }
            } else {
                if (s.length() != 0) {
                    strings.push("/");
                    strings.push(s);
                }
            }
        }


    }

    public String pwd() {
        if (strings.isEmpty()) {
            return "/";
        }
        return String.join("", strings);
    }
}
