/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.expression.spel.InternalParseException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.standard.Token;
import org.springframework.expression.spel.standard.TokenKind;

class Tokenizer {
    private static final String[] ALTERNATIVE_OPERATOR_NAMES = new String[]{"DIV", "EQ", "GE", "GT", "LE", "LT", "MOD", "NE", "NOT"};
    private static final byte[] FLAGS = new byte[256];
    private static final byte IS_DIGIT = 1;
    private static final byte IS_HEXDIGIT = 2;
    private static final byte IS_ALPHA = 4;
    private String expressionString;
    private char[] charsToProcess;
    private int pos;
    private int max;
    private List<Token> tokens = new ArrayList<Token>();

    public Tokenizer(String inputData) {
        this.expressionString = inputData;
        this.charsToProcess = (inputData + "\u0000").toCharArray();
        this.max = this.charsToProcess.length;
        this.pos = 0;
    }

    public List<Token> process() {
        block34: while (this.pos < this.max) {
            char ch = this.charsToProcess[this.pos];
            if (this.isAlphabetic(ch)) {
                this.lexIdentifier();
                continue;
            }
            switch (ch) {
                case '+': {
                    if (this.isTwoCharToken(TokenKind.INC)) {
                        this.pushPairToken(TokenKind.INC);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.PLUS);
                    continue block34;
                }
                case '_': {
                    this.lexIdentifier();
                    continue block34;
                }
                case '-': {
                    if (this.isTwoCharToken(TokenKind.DEC)) {
                        this.pushPairToken(TokenKind.DEC);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.MINUS);
                    continue block34;
                }
                case ':': {
                    this.pushCharToken(TokenKind.COLON);
                    continue block34;
                }
                case '.': {
                    this.pushCharToken(TokenKind.DOT);
                    continue block34;
                }
                case ',': {
                    this.pushCharToken(TokenKind.COMMA);
                    continue block34;
                }
                case '*': {
                    this.pushCharToken(TokenKind.STAR);
                    continue block34;
                }
                case '/': {
                    this.pushCharToken(TokenKind.DIV);
                    continue block34;
                }
                case '%': {
                    this.pushCharToken(TokenKind.MOD);
                    continue block34;
                }
                case '(': {
                    this.pushCharToken(TokenKind.LPAREN);
                    continue block34;
                }
                case ')': {
                    this.pushCharToken(TokenKind.RPAREN);
                    continue block34;
                }
                case '[': {
                    this.pushCharToken(TokenKind.LSQUARE);
                    continue block34;
                }
                case '#': {
                    this.pushCharToken(TokenKind.HASH);
                    continue block34;
                }
                case ']': {
                    this.pushCharToken(TokenKind.RSQUARE);
                    continue block34;
                }
                case '{': {
                    this.pushCharToken(TokenKind.LCURLY);
                    continue block34;
                }
                case '}': {
                    this.pushCharToken(TokenKind.RCURLY);
                    continue block34;
                }
                case '@': {
                    this.pushCharToken(TokenKind.BEAN_REF);
                    continue block34;
                }
                case '^': {
                    if (this.isTwoCharToken(TokenKind.SELECT_FIRST)) {
                        this.pushPairToken(TokenKind.SELECT_FIRST);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.POWER);
                    continue block34;
                }
                case '!': {
                    if (this.isTwoCharToken(TokenKind.NE)) {
                        this.pushPairToken(TokenKind.NE);
                        continue block34;
                    }
                    if (this.isTwoCharToken(TokenKind.PROJECT)) {
                        this.pushPairToken(TokenKind.PROJECT);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.NOT);
                    continue block34;
                }
                case '=': {
                    if (this.isTwoCharToken(TokenKind.EQ)) {
                        this.pushPairToken(TokenKind.EQ);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.ASSIGN);
                    continue block34;
                }
                case '&': {
                    if (this.isTwoCharToken(TokenKind.SYMBOLIC_AND)) {
                        this.pushPairToken(TokenKind.SYMBOLIC_AND);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.FACTORY_BEAN_REF);
                    continue block34;
                }
                case '|': {
                    if (!this.isTwoCharToken(TokenKind.SYMBOLIC_OR)) {
                        this.raiseParseException(this.pos, SpelMessage.MISSING_CHARACTER, "|");
                    }
                    this.pushPairToken(TokenKind.SYMBOLIC_OR);
                    continue block34;
                }
                case '?': {
                    if (this.isTwoCharToken(TokenKind.SELECT)) {
                        this.pushPairToken(TokenKind.SELECT);
                        continue block34;
                    }
                    if (this.isTwoCharToken(TokenKind.ELVIS)) {
                        this.pushPairToken(TokenKind.ELVIS);
                        continue block34;
                    }
                    if (this.isTwoCharToken(TokenKind.SAFE_NAVI)) {
                        this.pushPairToken(TokenKind.SAFE_NAVI);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.QMARK);
                    continue block34;
                }
                case '$': {
                    if (this.isTwoCharToken(TokenKind.SELECT_LAST)) {
                        this.pushPairToken(TokenKind.SELECT_LAST);
                        continue block34;
                    }
                    this.lexIdentifier();
                    continue block34;
                }
                case '>': {
                    if (this.isTwoCharToken(TokenKind.GE)) {
                        this.pushPairToken(TokenKind.GE);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.GT);
                    continue block34;
                }
                case '<': {
                    if (this.isTwoCharToken(TokenKind.LE)) {
                        this.pushPairToken(TokenKind.LE);
                        continue block34;
                    }
                    this.pushCharToken(TokenKind.LT);
                    continue block34;
                }
                case '0': 
                case '1': 
                case '2': 
                case '3': 
                case '4': 
                case '5': 
                case '6': 
                case '7': 
                case '8': 
                case '9': {
                    this.lexNumericLiteral(ch == '0');
                    continue block34;
                }
                case '\t': 
                case '\n': 
                case '\r': 
                case ' ': {
                    ++this.pos;
                    continue block34;
                }
                case '\'': {
                    this.lexQuotedStringLiteral();
                    continue block34;
                }
                case '\"': {
                    this.lexDoubleQuotedStringLiteral();
                    continue block34;
                }
                case '\u0000': {
                    ++this.pos;
                    continue block34;
                }
                case '\\': {
                    this.raiseParseException(this.pos, SpelMessage.UNEXPECTED_ESCAPE_CHAR, new Object[0]);
                    continue block34;
                }
            }
            throw new IllegalStateException("Cannot handle (" + ch + ") '" + ch + "'");
        }
        return this.tokens;
    }

    private void lexQuotedStringLiteral() {
        int start = this.pos;
        boolean terminated = false;
        while (!terminated) {
            ++this.pos;
            char ch = this.charsToProcess[this.pos];
            if (ch == '\'') {
                if (this.charsToProcess[this.pos + 1] == '\'') {
                    ++this.pos;
                } else {
                    terminated = true;
                }
            }
            if (!this.isExhausted()) continue;
            this.raiseParseException(start, SpelMessage.NON_TERMINATING_QUOTED_STRING, new Object[0]);
        }
        ++this.pos;
        this.tokens.add(new Token(TokenKind.LITERAL_STRING, this.subarray(start, this.pos), start, this.pos));
    }

    private void lexDoubleQuotedStringLiteral() {
        int start = this.pos;
        boolean terminated = false;
        while (!terminated) {
            ++this.pos;
            char ch = this.charsToProcess[this.pos];
            if (ch == '\"') {
                if (this.charsToProcess[this.pos + 1] == '\"') {
                    ++this.pos;
                } else {
                    terminated = true;
                }
            }
            if (!this.isExhausted()) continue;
            this.raiseParseException(start, SpelMessage.NON_TERMINATING_DOUBLE_QUOTED_STRING, new Object[0]);
        }
        ++this.pos;
        this.tokens.add(new Token(TokenKind.LITERAL_STRING, this.subarray(start, this.pos), start, this.pos));
    }

    private void lexNumericLiteral(boolean firstCharIsZero) {
        boolean isHex;
        boolean isReal = false;
        int start = this.pos;
        char ch = this.charsToProcess[this.pos + 1];
        boolean bl = isHex = ch == 'x' || ch == 'X';
        if (firstCharIsZero && isHex) {
            ++this.pos;
            do {
                ++this.pos;
            } while (this.isHexadecimalDigit(this.charsToProcess[this.pos]));
            if (this.isChar('L', 'l')) {
                this.pushHexIntToken(this.subarray(start + 2, this.pos), true, start, this.pos);
                ++this.pos;
            } else {
                this.pushHexIntToken(this.subarray(start + 2, this.pos), false, start, this.pos);
            }
            return;
        }
        do {
            ++this.pos;
        } while (this.isDigit(this.charsToProcess[this.pos]));
        ch = this.charsToProcess[this.pos];
        if (ch == '.') {
            isReal = true;
            int dotpos = this.pos;
            do {
                ++this.pos;
            } while (this.isDigit(this.charsToProcess[this.pos]));
            if (this.pos == dotpos + 1) {
                this.pos = dotpos;
                this.pushIntToken(this.subarray(start, this.pos), false, start, this.pos);
                return;
            }
        }
        int endOfNumber = this.pos;
        if (this.isChar('L', 'l')) {
            if (isReal) {
                this.raiseParseException(start, SpelMessage.REAL_CANNOT_BE_LONG, new Object[0]);
            }
            this.pushIntToken(this.subarray(start, endOfNumber), true, start, endOfNumber);
            ++this.pos;
        } else if (this.isExponentChar(this.charsToProcess[this.pos])) {
            isReal = true;
            ++this.pos;
            char possibleSign = this.charsToProcess[this.pos];
            if (this.isSign(possibleSign)) {
                ++this.pos;
            }
            do {
                ++this.pos;
            } while (this.isDigit(this.charsToProcess[this.pos]));
            boolean isFloat = false;
            if (this.isFloatSuffix(this.charsToProcess[this.pos])) {
                isFloat = true;
                endOfNumber = ++this.pos;
            } else if (this.isDoubleSuffix(this.charsToProcess[this.pos])) {
                endOfNumber = ++this.pos;
            }
            this.pushRealToken(this.subarray(start, this.pos), isFloat, start, this.pos);
        } else {
            ch = this.charsToProcess[this.pos];
            boolean isFloat = false;
            if (this.isFloatSuffix(ch)) {
                isReal = true;
                isFloat = true;
                endOfNumber = ++this.pos;
            } else if (this.isDoubleSuffix(ch)) {
                isReal = true;
                endOfNumber = ++this.pos;
            }
            if (isReal) {
                this.pushRealToken(this.subarray(start, endOfNumber), isFloat, start, endOfNumber);
            } else {
                this.pushIntToken(this.subarray(start, endOfNumber), false, start, endOfNumber);
            }
        }
    }

    private void lexIdentifier() {
        String asString;
        int idx;
        int start = this.pos;
        do {
            ++this.pos;
        } while (this.isIdentifier(this.charsToProcess[this.pos]));
        char[] subarray = this.subarray(start, this.pos);
        if ((this.pos - start == 2 || this.pos - start == 3) && (idx = Arrays.binarySearch(ALTERNATIVE_OPERATOR_NAMES, asString = new String(subarray).toUpperCase())) >= 0) {
            this.pushOneCharOrTwoCharToken(TokenKind.valueOf(asString), start, subarray);
            return;
        }
        this.tokens.add(new Token(TokenKind.IDENTIFIER, subarray, start, this.pos));
    }

    private void pushIntToken(char[] data, boolean isLong, int start, int end) {
        if (isLong) {
            this.tokens.add(new Token(TokenKind.LITERAL_LONG, data, start, end));
        } else {
            this.tokens.add(new Token(TokenKind.LITERAL_INT, data, start, end));
        }
    }

    private void pushHexIntToken(char[] data, boolean isLong, int start, int end) {
        if (data.length == 0) {
            if (isLong) {
                this.raiseParseException(start, SpelMessage.NOT_A_LONG, this.expressionString.substring(start, end + 1));
            } else {
                this.raiseParseException(start, SpelMessage.NOT_AN_INTEGER, this.expressionString.substring(start, end));
            }
        }
        if (isLong) {
            this.tokens.add(new Token(TokenKind.LITERAL_HEXLONG, data, start, end));
        } else {
            this.tokens.add(new Token(TokenKind.LITERAL_HEXINT, data, start, end));
        }
    }

    private void pushRealToken(char[] data, boolean isFloat, int start, int end) {
        if (isFloat) {
            this.tokens.add(new Token(TokenKind.LITERAL_REAL_FLOAT, data, start, end));
        } else {
            this.tokens.add(new Token(TokenKind.LITERAL_REAL, data, start, end));
        }
    }

    private char[] subarray(int start, int end) {
        return Arrays.copyOfRange(this.charsToProcess, start, end);
    }

    private boolean isTwoCharToken(TokenKind kind) {
        return kind.tokenChars.length == 2 && this.charsToProcess[this.pos] == kind.tokenChars[0] && this.charsToProcess[this.pos + 1] == kind.tokenChars[1];
    }

    private void pushCharToken(TokenKind kind) {
        this.tokens.add(new Token(kind, this.pos, this.pos + 1));
        ++this.pos;
    }

    private void pushPairToken(TokenKind kind) {
        this.tokens.add(new Token(kind, this.pos, this.pos + 2));
        this.pos += 2;
    }

    private void pushOneCharOrTwoCharToken(TokenKind kind, int pos, char[] data) {
        this.tokens.add(new Token(kind, data, pos, pos + kind.getLength()));
    }

    private boolean isIdentifier(char ch) {
        return this.isAlphabetic(ch) || this.isDigit(ch) || ch == '_' || ch == '$';
    }

    private boolean isChar(char a, char b) {
        char ch = this.charsToProcess[this.pos];
        return ch == a || ch == b;
    }

    private boolean isExponentChar(char ch) {
        return ch == 'e' || ch == 'E';
    }

    private boolean isFloatSuffix(char ch) {
        return ch == 'f' || ch == 'F';
    }

    private boolean isDoubleSuffix(char ch) {
        return ch == 'd' || ch == 'D';
    }

    private boolean isSign(char ch) {
        return ch == '+' || ch == '-';
    }

    private boolean isDigit(char ch) {
        if (ch > '\u00ff') {
            return false;
        }
        return (FLAGS[ch] & 1) != 0;
    }

    private boolean isAlphabetic(char ch) {
        if (ch > '\u00ff') {
            return false;
        }
        return (FLAGS[ch] & 4) != 0;
    }

    private boolean isHexadecimalDigit(char ch) {
        if (ch > '\u00ff') {
            return false;
        }
        return (FLAGS[ch] & 2) != 0;
    }

    private boolean isExhausted() {
        return this.pos == this.max - 1;
    }

    private void raiseParseException(int start, SpelMessage msg, Object ... inserts) {
        throw new InternalParseException(new SpelParseException(this.expressionString, start, msg, inserts));
    }

    static {
        int ch = 48;
        while (ch <= 57) {
            int n = ch++;
            FLAGS[n] = (byte)(FLAGS[n] | 3);
        }
        ch = 65;
        while (ch <= 70) {
            int n = ch++;
            FLAGS[n] = (byte)(FLAGS[n] | 2);
        }
        ch = 97;
        while (ch <= 102) {
            int n = ch++;
            FLAGS[n] = (byte)(FLAGS[n] | 2);
        }
        ch = 65;
        while (ch <= 90) {
            int n = ch++;
            FLAGS[n] = (byte)(FLAGS[n] | 4);
        }
        ch = 97;
        while (ch <= 122) {
            int n = ch++;
            FLAGS[n] = (byte)(FLAGS[n] | 4);
        }
    }
}

