package findfiles;

public class Validator {
    String[] args;
    Finder finder;
    Saver saver;

    public Validator(String[] args, Finder finder, Saver saver) {
        this.args = args;
        this.finder = finder;
        this.saver = saver;
        splitArguments();
    }

    private void splitArguments() {
        for (String arg : args) {
            String key = arg.split("=")[0];
            String value = arg.split("=")[1];
            switch (key) {
                case "-d":
                     finder.setDirectory(value);
                    break;
                case "-n":
                    finder.setLookingFor(value);
                    break;
                case "-t":
                    finder.setHowToSearch(value);
                    break;
                case "-o":
                    saver.setFileOut(value);
                    break;
                case "--help":
                    System.out.println("-d - директория, в которой начинать поиск.\n"
                            + "-n - имя файла, маска, либо регулярное выражение.\n"
                            + "-t - искать по маске\"mask\" полное совпадение имени \"name\" или по регулярному выражению \"regular\".\n"
                            + "-o - результат записать в файл");
                    break;
                default:
                    System.out.println(key + " is not a find command. See 'find --help'. ");
            }
        }
    }

    public boolean isValid(Finder finder, Saver saver) throws IllegalArgumentException {
        if (finder.getDirectory() != null && finder.getHowToSearch() != null && finder.getLookingFor() != null && saver.getFileOut() != null) {
            return true;
        }
        System.out.println(" Not enough arguments \n  See 'find --help'. ");
        return false;
    }
}
