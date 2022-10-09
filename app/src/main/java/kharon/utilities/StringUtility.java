package kharon.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author KOTBI Abderrahmane
 */
public class StringUtility {

    public static boolean practiclyEqual(String value1, String value2) {
        return value1.trim().equalsIgnoreCase(value2.trim());
    }

    public static void Logger(String level, String log) {
        StringBuilder text = new StringBuilder();
        System.out.println(text.append(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()))
                .append(" [")
                .append(level)
                .append(" ] ")
                .append(log)
                .toString());
    }

    public static String resolveKey(String entry){
        return entry.split(":").length >= 1 ? entry.split(":")[0] : "";
    }

    public static String resolveValue(String entry){
        return entry.split(":").length > 1 ? entry.split(":")[1] : "";
    }

}
