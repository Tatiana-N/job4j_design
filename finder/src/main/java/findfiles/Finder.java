package findfiles;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Getter
@Setter
public class Finder {
    String directory;
    String lookingFor;
    String howToSearch;

    public Set<Path> findIt() {
        Path root = Paths.get(directory);
        MySimpleFileVisitor mySimpleFileVisitor = null;
        switch (howToSearch) {
            case "mask" -> mySimpleFileVisitor = findByMask();
            case "name" -> mySimpleFileVisitor = findByFileName();
            case "regular" -> mySimpleFileVisitor = findByRegular();
        }

        try {
            assert mySimpleFileVisitor != null;
            Files.walkFileTree(root, mySimpleFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mySimpleFileVisitor.getPathList();

    }

    private MySimpleFileVisitor findByFileName() {
        return new MySimpleFileVisitor(t -> t.getFileName().toString().split("\\.")[0].equals(lookingFor));
    }

    private MySimpleFileVisitor findByMask() {
        MySimpleFileVisitor mySimpleFileVisitor;
        if (lookingFor.startsWith("*") && lookingFor.endsWith("*")) {
            mySimpleFileVisitor = new MySimpleFileVisitor(t -> t.getFileName().toString().contains(lookingFor.replaceAll("\\*", "")));
        } else if (lookingFor.startsWith("*")) {
            mySimpleFileVisitor = new MySimpleFileVisitor(t -> t.getFileName().toString().endsWith(lookingFor.replaceAll("\\*", "")));
        } else {
            mySimpleFileVisitor = new MySimpleFileVisitor(t -> t.getFileName().toString().startsWith(lookingFor.replaceAll("\\*", "")));
        }
        return mySimpleFileVisitor;
    }

    private MySimpleFileVisitor findByRegular() {
        return null;
    }
}

