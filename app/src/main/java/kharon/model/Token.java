package kharon.model;

import java.util.Optional;

import kharon.utilities.ConfigurationUtility;
import kharon.utilities.StringUtility;

public record Token(String type, String value, String equivalent) {

    public static String TOKEN_TYPE = "Unknown";

    private static Optional<String> getTokenType(String value) {
        return ConfigurationUtility.getProperties("tokens.type")
                .stream()
                .map(String::toUpperCase)
                .filter(v -> StringUtility.practiclyEqual(v, value))
                .findFirst();
    }

    public static String getType(String value) {
        return TOKEN_TYPE = getTokenType(value).orElse("Unknown");
    }

    Token withType(String type) {
        return new Token(type, this.value(), this.equivalent());
    }

    Token withValue(String value) {
        return new Token(this.type(), value, this.equivalent());
    }

    Token withEquivalent(String equivalent) {
        return new Token(this.type(), this.value(), equivalent);
    }

}
