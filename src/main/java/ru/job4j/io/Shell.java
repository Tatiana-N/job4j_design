package ru.job4j.io;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Shell {
Stack<String> strings;

    public Shell() {
        this.strings = new Stack<>();
    }

    public void cd(String path) {
        List<String> collect = Arrays.stream(path.split("/")).collect(Collectors.toList());
        strings.push("/");
        for (String s : collect) {
            if(s.contains(".")){
               int i = s.length();
               while (i>0){
                strings.pop(); i--;}
            } else {
                strings.push(s);
                strings.push("/");
            }
        }
    }
    public String pwd() {
        return     String.join("", strings);
    }
}
