package kharon.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author KOTBI Abderrahmane
 */
public class ConfigurationUtility {

    private static String PATH_TO_CONFIGURATION = ClassLoader.getSystemResource("test").getPath().replace("test", "") + "/configuration.properties";

    public static void setPath(String path) {
        PATH_TO_CONFIGURATION = path;
    }

    public static void resetPath() {
        PATH_TO_CONFIGURATION = ClassLoader.getSystemResource("test").getPath().replace("test", "") + "/configuration.properties";

    }

    public static Optional<String> getProperty(String key) {
        StringUtility.getLogger().Logger("DEBUG", "The key reached was: " + key + ".");
        return Optional.of(getProperties().getProperty(key));
    }

    public static LinkedList<String> getProperties(String key) {
        var values = new LinkedList<String>();

        if (getProperty(key).isPresent()) {
            values.addAll(Arrays.asList(getProperty(key).get().split(",")));
        }

        return values;
    }

    public static LinkedList<String> getPropertyChildren(String rootkey) {
        var values = new LinkedList<String>();
        for (String name : getProperties().stringPropertyNames()) {
            if (name.startsWith(rootkey)) {
                values.add(name);
            }
        }
        return values;
    }

    public static Optional<String> getEntry(String key) {
        return Optional.of(key + ":" + getProperty(key).get());
    }

    public static Collection<String> getChildrenEntry(String rootKey) {
        return ConfigurationUtility.getPropertyChildren(rootKey)
                .stream()
                .map(key -> ConfigurationUtility.getEntry(key).get())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static Properties getProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(PATH_TO_CONFIGURATION)) {

            properties.load(inputStream);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return properties;
    }

}