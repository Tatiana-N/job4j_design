package findfiles;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Pattern;

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
      lookingFor = lookingFor.replaceAll("\\.", "\\.").replaceAll("\\*", ".*");
            return  findByRegular();
    }

    private MySimpleFileVisitor findByRegular() {
        Pattern pattern = Pattern.compile(lookingFor);
        return  new MySimpleFileVisitor(t -> pattern.matcher(t.getFileName().toString()).matches());
    }
}

