package kharon.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author KOTBI Abderrahmane
 */
public class FilesUtility {
    

    /**
     * @param path
     * @return
     */
    public static FileReader read(String path){
        try {
            return new FileReader(new File(path));
        } catch (FileNotFoundException fnfe) {
            StringUtility.getLogger().Logger("ERROR", "Empty reader will be provided.");
            fnfe.printStackTrace();
        }
        return null;        
    }

    /**
     * @param content
     * @param path
     */
    public static void write(String content, String path) {
        try (PrintWriter printWriter = new PrintWriter(new File(path))) {
            Arrays.asList(content.split("\n")).forEach(printWriter::println);
            StringUtility.getLogger().Logger("DONE", "File has been modifiedd sucssefully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
