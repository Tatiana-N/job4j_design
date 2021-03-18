package ru.job4j.io;

import java.util.Stack;

public class Shell2 {
    Stack<String> strings;

    public Shell2() {
        this.strings = new Stack<>();
    }

    public void cd(String path) {
        String[] split = path.split("");
        StringBuilder stringBuilder = new StringBuilder();
        strings.push("/");
        for (String s : split) {
            if (s.equals("/")) {
                if (!stringBuilder.toString().equals("")) strings.push(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                continue;

            }
            if (s.equals(".")) {
                if (!stringBuilder.toString().equals("")) strings.push(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                strings.pop();
            } else {
                stringBuilder.append(s);
            }

        }
        if (!stringBuilder.toString().equals("")) strings.push(stringBuilder.toString());
    }

    public String pwd() {
        if (strings.isEmpty()) {
            return "/";
        }
        return String.join("", strings);
    }
}
