public class Parser {
    private Lexer lexer;
    private String currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    private void eat(String tokenType) {
        if (currentToken.equals(tokenType)) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException("Syntax error: expected " + tokenType + ", got " + currentToken);
        }
    }

    private int factor() {
        String token = currentToken;
        if (token.matches("\\d+")) {
            eat(token);
            return Integer.parseInt(token);
        } else if (token.equals("(")) {
            eat("(");
            int result = expr();
            eat(")");
            return result;
        } else {
            throw new RuntimeException("Syntax error: unexpected token " + token);
        }
    }

    private int term() {
        int result = factor();
        while (currentToken != null && (currentToken.equals("*") || currentToken.equals("/"))) {
            String token = currentToken;
            if (token.equals("*")) {
                eat("*");
                result *= factor();
            } else if (token.equals("/")) {
                eat("/");
                result /= factor();
            }
        }
        return result;
    }

    private int expr() {
        int result = term();
        while (currentToken != null && (currentToken.equals("+") || currentToken.equals("-"))) {
            String token = currentToken;
            if (token.equals("+")) {
                eat("+");
                result += term();
            } else if (token.equals("-")) {
                eat("-");
                result -= term();
            }
        }
        return result;
    }

    public int parse() {
        return expr();
    }
}
