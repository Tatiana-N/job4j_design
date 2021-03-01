package findfiles;

import org.mockito.Mockito;

public class Validator {
    public boolean isValid(Finder finder, Saver saver) throws IllegalArgumentException {
        try {
            Mockito.verify(finder, Mockito.times(1)).setDirectory(Mockito.anyString());
            Mockito.verify(finder, Mockito.times(1)).setLookingFor(Mockito.anyString());
            Mockito.verify(finder, Mockito.times(1)).setHowToSearch(Mockito.anyString());
            Mockito.verify(saver, Mockito.times(1)).setFileOut(Mockito.anyString());
        } catch (Error e) {
            throw new IllegalArgumentException("Неподходящие аргументы");
        }
        return true;
    }
}
