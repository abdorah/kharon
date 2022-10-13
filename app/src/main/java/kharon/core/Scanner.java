package kharon.core;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.LinkedList;

import kharon.model.Token;
import kharon.utilities.ConfigurationUtility;
import kharon.utilities.FilesUtility;
import kharon.utilities.StringUtility;

/**
 * @author KOTBI Abderrahmane
 */
public class Scanner {

    private StreamTokenizer streamTokenizer;

    /**
     * This method read the code of a program from a file defined by the parameter:
     * 
     * @param path
     * @return
     *         It uses the streamTokenizer after reseting its syntax. This
     *         preparation process can be changed if there was more keys
     *         provided in the properties file.
     */
    private static Scanner beforeProcess(String path) {
        Scanner scanner = new Scanner();
        scanner.streamTokenizer = new StreamTokenizer(FilesUtility.read(path));
        scanner.streamTokenizer.resetSyntax();
        // Actual minimum-required configuration:
        scanner.streamTokenizer.eolIsSignificant(
                Boolean.parseBoolean(ConfigurationUtility.getProperty("scanner.eolIsSignificant").get()));
        scanner.streamTokenizer.slashSlashComments(
                Boolean.parseBoolean(ConfigurationUtility.getProperty("scanner.slashSlashComments").get()));
        scanner.streamTokenizer.slashStarComments(
                Boolean.parseBoolean(ConfigurationUtility.getProperty("scanner.slashStarComments").get()));
        ConfigurationUtility.getProperties("scanner.ordinaryChar").stream().map(s -> s.trim().toCharArray()[0])
                .forEach(c -> scanner.streamTokenizer.ordinaryChar(c));
        return scanner;
    }

    private static LinkedList<Token> processing(Scanner scanner) {

        String type = "";
        String value = "";
        var program = new LinkedList<Token>();

        try {
            scanner.streamTokenizer.nextToken();
            value = scanner.streamTokenizer.sval;
            type = Token.getType(value != null ? value : "hello");
            // resolveValue(value, scanner.streamTokenizer.sval);
            // resolveOrdinaryType(scanner.streamTokenizer.ttype);
            // resolveType(type, scanner.streamTokenizer.ttype);
            program.add(new Token(type, value, ""));
        } catch (IOException ioe) {
            StringUtility.getLogger().Logger("ERROR", "End of file reached without reading anything");
            ioe.printStackTrace();
        }

        while (StreamTokenizer.TT_EOF != scanner.streamTokenizer.ttype) {
            try {
                scanner.streamTokenizer.nextToken();
                value = scanner.streamTokenizer.sval;
                type = Token.getType(value != null ? value : "hello");
                program.add(new Token(type, value, ""));
            } catch (IOException ioe) {
                StringUtility.getLogger().Logger("ERROR", "End of file wasn't reached. Something went wrong while processing.");
                ioe.printStackTrace();
            }
        }
        StringUtility.getLogger().Logger("INFO", "End of file reached. Process is done successfully");
        return program;
    }

    public static LinkedList<Token> scan(String path) {
        return processing(beforeProcess(path));
    }

}
