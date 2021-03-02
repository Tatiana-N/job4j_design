package findfiles;

public class Validait {
    public boolean isValid(Finder finder, Saver saver) throws IllegalArgumentException {
        if (finder.getDirectory() != null && finder.getHowToSearch() != null && finder.getLookingFor() != null && saver.getFileOut() != null) {
            return true;
        }
        return false;
    }
}
