package findfiles;

public class Validait {
    String [] args;
    Finder finder;
    Saver saver;

    public Validait(String[] args, Finder finder, Saver saver) {
        this.args = args;
        this.finder = finder;
        this.saver = saver;
        splitArguments();
    }

    private void splitArguments() {
        if (args.length == 0) {
            args = new String[]{"недостаточно параметров", "--help"};
        }

        for (String arg : args) {
            String key = arg.split("=")[0];
            String value = arg.split("=")[1];
            switch (key) {
                case "-d" -> finder.setDirectory(value);
                case "-n" -> finder.setLookingFor(value);
                case "-t" -> finder.setHowToSearch(value);
                case "-o" -> saver.setFileOut(value);
                case "--help" -> System.out.println("-d - директория, в которой начинать поиск.\n" +
                        "-n - имя файла, маска, либо регулярное выражение.\n" +
                        "-t - искать по маске\"mask\" полное совпадение имени \"name\" или по регулярному выражению \"regular\".\n" +
                        "-o - результат записать в файл");
                default -> System.out.println(key + " is not a find command. See 'find --help'. ");
            }
        }
    }
    public boolean isValid(Finder finder, Saver saver) throws IllegalArgumentException {
        try {
            if (finder.getDirectory() != null && finder.getHowToSearch() != null && finder.getLookingFor() != null && saver.getFileOut() != null) {
                return true;
            }
} catch (IllegalArgumentException e) {
        System.out.println(e.getMessage() + "\n" + "See 'find --help'. ");
        }
        return false;
    }
}
