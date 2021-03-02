package findfiles;

import lombok.Getter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
@Getter
public class Saver {
    File fileOut;

    public void setFileOut(String fileOut) {
        this.fileOut = new File(fileOut);
    }

    public void save(Set<Path> it) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))) {
            for (Path path : it) {
                bufferedWriter.write(path.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("saving... ");
    }
}
