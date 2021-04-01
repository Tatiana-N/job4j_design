package findfiles;

import org.junit.Assert;
import org.junit.Test;

public class FinderTest {

    @Test
    public void getDirectory() {
        Finder finder = new Finder();
        Assert.assertNotNull(finder);
    }
}