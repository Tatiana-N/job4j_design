package ru.job4j.io;

import ru.job4j.search.src.files.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
  private Path start;
  private final File fileOut;
  private final ArgZip argZip;

  private void packFiles(List<Path> sources, File target) {
    try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
      for (Path source : sources) {
        zip.putNextEntry(new ZipEntry(source.toString()));
        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
          zip.write(out.readAllBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Zip(String[] args) {
    argZip = new ArgZip(args);
    if (argZip.valid()) {
      start = Paths.get(argZip.directory());
    }
    fileOut = new File(argZip.output());
  }

  public void packingFiles() throws IOException {
    packFiles(this.getList(start, path -> !path.toString().contains(argZip.exclude())), fileOut);
  }

  public static void main(String[] args) throws IOException {
    Zip zip = new Zip(args);
    zip.packingFiles();
  }

  public List<Path> getList(Path start, Predicate<Path> pred) throws IOException {
    SearchFiles<Path> searchFiles = new SearchFiles<>(pred);
    Files.walkFileTree(start, searchFiles);
    return searchFiles.getPaths();
  }

}

