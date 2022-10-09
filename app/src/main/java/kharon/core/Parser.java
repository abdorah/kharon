package kharon.core;

import java.util.LinkedList;
import java.util.regex.Pattern;

import kharon.model.Token;
import kharon.utilities.ConfigurationUtility;
import kharon.utilities.StringUtility;

/**
 * @author KOTBI Abderrahmane
 */
public class Parser {

    /**
     * This method is an early stage implementation of the parser.
     * Please realy on the {@link kharon.utilities.RegexUtility} to understand the
     * goal of the specification that this class follow.
     * 
     * @param program The scanned code
     * @return
     */
    public static LinkedList<Token> parse(LinkedList<Token> program) {
        StringBuilder block = new StringBuilder();
        var result = new LinkedList<Token>();
        for (Token token : program) {
            var effectivlyFinalBlock = block.append(token.value());
            if (ConfigurationUtility.getChildrenEntry("regex").stream()
                    .anyMatch(entry -> Pattern.compile(StringUtility.resolveValue(entry))
                            .matcher(effectivlyFinalBlock.toString()).find())) {

                result.add(new Token(ConfigurationUtility.getChildrenEntry("regex").stream()
                        .filter(entry -> Pattern.compile(StringUtility.resolveValue(entry))
                                .matcher(effectivlyFinalBlock.toString()).find())
                        .map(StringUtility::resolveKey)
                        .map(key -> key.replace("regex", "scanner"))
                        .map(key -> ConfigurationUtility.getProperty(key).get())
                        .findAny().get(), block.toString(), ""));

                block = new StringBuilder();
            }
        }
        return result;
    }

}
