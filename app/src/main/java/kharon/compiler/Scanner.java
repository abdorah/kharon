package kharon.compiler;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.LinkedList;

import kharon.model.Token;
import kharon.utilities.ConfigurationUtility;
import kharon.utilities.FilesUtility;
import kharon.utilities.StringUtility;

public class Scanner {

    private StreamTokenizer streamTokenizer;

    private Scanner beforeReading(Scanner scanner, String path) {
        scanner.streamTokenizer = new StreamTokenizer(FilesUtility.read(path));
        scanner.streamTokenizer.resetSyntax();
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

    private LinkedList<Token> processing(Scanner scanner) {

        String type = "";
        String value = "";
        var program = new LinkedList<Token>();

        try {
            scanner.streamTokenizer.nextToken();
            value = scanner.streamTokenizer.sval;//resolveValue(value, scanner.streamTokenizer.sval);
            type = resolveType(value);//resolveOrdinaryType(scanner.streamTokenizer.ttype);//resolveType(type, scanner.streamTokenizer.ttype);
            program.add(new Token(type, value, ""));
        } catch (IOException ioe) {
            StringUtility.Logger("ERROR", "End of file reached without reading anything");
            ioe.printStackTrace();
        }

        while (StreamTokenizer.TT_EOF != scanner.streamTokenizer.ttype) {
            try {
                scanner.streamTokenizer.nextToken();
                value = scanner.streamTokenizer.sval;//resolveValue(value, scanner.streamTokenizer.sval);
                type = resolveType(value);//resolveOrdinaryType(scanner.streamTokenizer.ttype);//resolveType(type, scanner.streamTokenizer.ttype);
                program.add(new Token(type, value, ""));
            } catch (IOException ioe) {
                StringUtility.Logger("ERROR", "End of file wasn't reached. Something went wrong while processing.");
                ioe.printStackTrace();
            }
        }
        StringUtility.Logger("INFO", "End of file reached. Process is done successfully");
        return program;
    }

    public LinkedList<Token> afterReading(Scanner scanner, String path) {
        return processing(beforeReading(scanner, path));
    }

    @Deprecated
    private String resolveOrdinaryType(int ttype) {
        switch (ttype) {
            case StreamTokenizer.TT_EOF:
                return "EOF";
            case StreamTokenizer.TT_EOL:
                return "EOL";
            case StreamTokenizer.TT_NUMBER:
                return "NUMBER";
            case StreamTokenizer.TT_WORD:
                return "WORD";
            default:
                return "UNKNOWN";
        }
    }

    private String resolveType(String value) {
        return Token.getType(value);
    }

}
