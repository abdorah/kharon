package kharon.core;

import java.util.LinkedList;
import java.util.stream.Collectors;

import kharon.model.Token;
import kharon.utilities.ConfigurationUtility;
import kharon.utilities.FilesUtility;

/**
 * @author KOTBI Abderrahmane
 */
public class Binder {

    private static LinkedList<Token> bindToContext(LinkedList<Token> program) {
        return program
                .stream()
                .map(t -> t.withEquivalent(ConfigurationUtility.getProperty("equivalent." + t.type()).get()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static String bindToSyntax(LinkedList<Token> program) {
        var block = new StringBuilder();
        for (Token token : program) {
            switch (token.equivalent()) {
                case "INSTRUCTION":
                    block.append(";");
                    break;
                case "EXPRESSION":
                    block.insert(0, "(").append(")");
                    break;
                case "BLOCK":
                    block.insert(0, "{").append("}");
                    break;
            }
        }
        return block.toString();
    }

    public static void bind(LinkedList<Token> program, String path) {
        FilesUtility.write(bindToSyntax(bindToContext(program)), path);
    }
}
