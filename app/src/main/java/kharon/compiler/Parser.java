package kharon.compiler;

import java.util.LinkedList;
import java.util.regex.Pattern;

import kharon.model.Token;
import kharon.utilities.ConfigurationUtility;

public class Parser {
    
    private LinkedList<String> greedyMatcher(LinkedList<Token> program){
        StringBuilder block = new StringBuilder();
        for (Token token : program) {
            block.append(token.value());
            if(ConfigurationUtility.getProperties("regex").stream().anyMatch(regex -> Pattern.compile(regex).matcher(block.toString()).find())){
                
            }
        }
        return null;
    }

    private LinkedList<String> resiliantMatcher(LinkedList<Token> program){
        StringBuilder block = new StringBuilder();
        for (Token token : program) {
            block.append(token.value());
            if(ConfigurationUtility.getProperties("regex").stream().anyMatch(regex -> Pattern.compile(regex).matcher(block.toString()).find())){
                
            }
        }
        return null;
    }

    private LinkedList<String> possessiveMatcher(LinkedList<Token> program){
        StringBuilder block = new StringBuilder();
        for (Token token : program) {
            block.append(token.value());
            if(ConfigurationUtility.getProperties("regex").stream().anyMatch(regex -> Pattern.compile(regex).matcher(block.toString()).find())){
                
            }
        }
        return null;
    }

}
