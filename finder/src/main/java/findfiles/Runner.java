package findfiles;

import java.nio.file.Path;
import java.util.Set;

//@RunWith(MockitoJUnitRunner.class)
public class Runner {
  @SuppressWarnings("checkstyle:OperatorWrap")
  public static void main(String[] args) {

    Finder finder = new Finder();//Mockito.spy(new Finder());
    Saver saver = new Saver();//Mockito.spy(new Saver());
    Validait validator = new Validait();//Validator validator = new Validator();

    if(args.length == 0){
      args = new String[]{"недостаточно параметров","--help"};
    }

    for (String arg : args) {
      String key = arg.split("=")[0];
      String value = arg.split("=")[1];
      switch (key) {
        case "-d" -> finder.setDirectory(value);
        case "-n" -> finder.setLookingFor(value);
        case "-t" -> finder.setHowToSearch(value);
        case "-o" -> saver.setFileOut(value);
        case "--help" ->
                System.out.println("-d - директория, в которой начинать поиск.\n" +
                "-n - имя файла, маска, либо регулярное выражение.\n" +
                "-t - искать по маске\"mask\" полное совпадение имени \"name\" или по регулярному выражению \"regular\".\n" +
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
