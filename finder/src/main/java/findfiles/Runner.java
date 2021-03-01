package findfiles;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class Runner {
  @SuppressWarnings("checkstyle:OperatorWrap")
  public static void main(String[] args) {

    Finder finder = Mockito.spy(new Finder());
    Saver saver = Mockito.spy(new Saver());
    Validator validator = new Validator();

    if(args.length == 0){
      args = new String[]{"недостаточно параметров","--help"};
    }

    for (String arg : args) {
      String key = arg.split("=")[0];
      String value = arg.split("=")[1];
      switch (key) {
        case "-d" -> finder.setDirectory(value);
        case "-n" -> finder.setLookingFor(value);
        case "-t" -> finder.setHowToSearch("mask");
        case "-o" -> saver.setFileOut(value);
        case "-f" -> finder.setHowToSearch("file name");
        case "-r" -> finder.setHowToSearch("regular");
        case "--help" ->
                System.out.println("-d - директория, в которой начинать поиск.\n" +
                "-n - имя файла, маска, либо регулярное выражение.\n" +
                "-m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.\n" +
                "-o - результат записать в файл");
        default -> System.out.println(key + " is not a find command. See 'find --help'. ");
      }
    }
    try{
    if(validator.isValid(finder,saver)){
      Set<Path> it = finder.findIt();
      saver.save(it);
    }}catch (IllegalArgumentException e){
      System.out.println(e.getMessage() + "\n" + "See 'find --help'. ");
    }
  }
}
