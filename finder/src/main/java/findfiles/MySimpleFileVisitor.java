package findfiles;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Getter
@Setter
public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;
    private Set<Path> pathList;

    public MySimpleFileVisitor(Predicate<Path> predicate) {
        this.predicate = predicate;
        this.pathList = new HashSet<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file) && file.toFile().isFile()) {
            pathList.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
