package manuel.lox;

class Token {
    // which token is it
    final TokenType type;
    // a correct token from a code line
    final String lexeme;
    // is it a number?
    final Object literal;
    // what line number is this at? useful for errors
    final int line;

    Token (TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal= literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
     }
}