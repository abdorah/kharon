package kharon.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author KOTBI Abderrahmane
 */
public class StringUtility {

    public static StringUtility logger;
    private static boolean loggerState;

    static {
        logger = new StringUtility(true);
    }

    private StringUtility(boolean loggerState) {
        StringUtility.loggerState = loggerState;
    }

    public static StringUtility getLogger() {
        return StringUtility.logger;
    }

    public static boolean practiclyEqual(String value1, String value2) {
        return value1.trim().equalsIgnoreCase(value2.trim());
    }

    public void Logger(String level, String log) {
        if(loggerState){
        StringBuilder text = new StringBuilder();
        System.out.println(text.append(DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss").format(LocalDateTime.now()))
                .append(" [")
                .append(level)
                .append("] ")
                .append(log)
                .toString());
            }
    }

    public static void desactivateLogger(){
        loggerState = false;
    }

    public static void reactivateLogger(){
        loggerState = true;
    }

    public static String resolveKey(String entry){
        return entry.split(":").length >= 1 ? entry.split(":")[0] : "";
    }

    public static String resolveValue(String entry){
        return entry.split(":").length > 1 ? entry.split(":")[1] : "";
    }

}
