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
        MySimpleFileVisitor mySimpleFileVisitor = new MySimpleFileVisitor(t ->
                t.toString().contains(lookingFor.replaceAll("\\*", "")));
        try {
            Files.walkFileTree(root, mySimpleFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mySimpleFileVisitor.getPathList();

    }
}

