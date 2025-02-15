/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.standard;

enum TokenKind {
    LITERAL_INT,
    LITERAL_LONG,
    LITERAL_HEXINT,
    LITERAL_HEXLONG,
    LITERAL_STRING,
    LITERAL_REAL,
    LITERAL_REAL_FLOAT,
    LPAREN("("),
    RPAREN(")"),
    COMMA(","),
    IDENTIFIER,
    COLON(":"),
    HASH("#"),
    RSQUARE("]"),
    LSQUARE("["),
    LCURLY("{"),
    RCURLY("}"),
    DOT("."),
    PLUS("+"),
    STAR("*"),
    MINUS("-"),
    SELECT_FIRST("^["),
    SELECT_LAST("$["),
    QMARK("?"),
    PROJECT("!["),
    DIV("/"),
    GE(">="),
    GT(">"),
    LE("<="),
    LT("<"),
    EQ("=="),
    NE("!="),
    MOD("%"),
    NOT("!"),
    ASSIGN("="),
    INSTANCEOF("instanceof"),
    MATCHES("matches"),
    BETWEEN("between"),
    SELECT("?["),
    POWER("^"),
    ELVIS("?:"),
    SAFE_NAVI("?."),
    BEAN_REF("@"),
    FACTORY_BEAN_REF("&"),
    SYMBOLIC_OR("||"),
    SYMBOLIC_AND("&&"),
    INC("++"),
    DEC("--");

    final char[] tokenChars;
    private final boolean hasPayload;

    private TokenKind(String tokenString) {
        this.tokenChars = tokenString.toCharArray();
        this.hasPayload = this.tokenChars.length == 0;
    }

    private TokenKind() {
        this("");
    }

    public String toString() {
        return this.name() + (this.tokenChars.length != 0 ? "(" + new String(this.tokenChars) + ")" : "");
    }

    public boolean hasPayload() {
        return this.hasPayload;
    }

    public int getLength() {
        return this.tokenChars.length;
    }
}

