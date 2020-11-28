package ru.job4j.io;

import ru.job4j.search.src.s.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
  public void packFiles(List<Path> sources, File target) throws IOException {
    ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)));
    for (Path source : sources) {
      zip.putNextEntry(new ZipEntry(source.toString()));
      BufferedInputStream out =  new BufferedInputStream(new FileInputStream(source.toFile()));
        zip.write(out.readAllBytes());
      out.close();
    }
    zip.close();

  }

  public static void main(String[] args) throws IOException {
    // new Zip().packSingleFile(
    //    new File("./chapter_005/pom.xml"),
    //     new File("./chapter_005/pom.zip")
    //  );
    ArgZip argZip = new ArgZip(args);
    if (argZip.valid()) {
      Path start = Paths.get(argZip.directory());
      Zip zip = new Zip();
      File fileOut = new File(argZip.output());
      if (!fileOut.getAbsoluteFile().exists()) {
        fileOut.createNewFile();
      }
      List<Path> ourlist = Search.search(start, "");
      ourlist.removeAll(Search.search(start, argZip.exclude()));
      zip.packFiles(ourlist, fileOut);
    }
  }
}
