package findfiles;

import java.nio.file.Path;
import java.util.Set;

//@RunWith(MockitoJUnitRunner.class)
public class Runner {
    @SuppressWarnings("checkstyle:OperatorWrap")
    public static void main(String[] args) {

        Finder finder = new Finder();//Mockito.spy(new Finder());
        Saver saver = new Saver();//Mockito.spy(new Saver());
        Validait validator = new Validait(args, finder, saver);//Validator validator = new Validator();
        if (validator.isValid(finder, saver)) {
            Set<Path> foundFiles = finder.findIt();
            saver.save(foundFiles);
        }
    }
}
