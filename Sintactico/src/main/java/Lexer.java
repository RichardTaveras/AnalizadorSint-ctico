import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private List<String> tokens;
    private int currentTokenIndex;

    public Lexer(String input) {
        tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+|[+*/()-]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        currentTokenIndex = 0;
    }

    public String getNextToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex++);
        } else {
            return null;
        }
    }

    public String peekNextToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex);
        } else {
            return null;
        }
    }
}
