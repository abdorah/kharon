package kharon.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Properties;

/**
 * @author KOTBI Abderrahmane
 */
public class ConfigurationUtility {

    private static String PATH_TO_CONFIGURATION = "/home/kotbi/Documents/Projects/kharon/app/src/main/resources/configuration.properties";

    public static void setPath(String path){
        PATH_TO_CONFIGURATION = path;
    }

    public static void resetPath(){
        PATH_TO_CONFIGURATION = "/home/kotbi/Documents/Projects/kharon/app/src/main/resources/configuration.properties";
    }

    public static Optional<String> getProperty(String key) {

        String value = null;

        try (InputStream inputStream = new FileInputStream(PATH_TO_CONFIGURATION)) {

            Properties properties = new Properties();
            properties.load(inputStream);
            value = properties.getProperty(key);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return Optional.ofNullable(value);
    }

    public static LinkedList<String> getProperties(String key) {
        var values = new LinkedList<String>();

        try (InputStream inputStream = new FileInputStream(PATH_TO_CONFIGURATION)) {

            Properties properties = new Properties();
            properties.load(inputStream);
            if(properties.getProperty(key) != null){
                values.addAll(Arrays.asList(properties.getProperty(key).split(",")));
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return values;
    }

}
