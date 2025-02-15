/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.Reader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Token;

class TokenStream {
    private static final int EOF_CHAR = -1;
    private static final char BYTE_ORDER_MARK = '\ufeff';
    private boolean dirtyLine;
    String regExpFlags;
    private String string = "";
    private double number;
    private boolean isOctal;
    private boolean isHex;
    private int quoteChar;
    private char[] stringBuffer = new char[128];
    private int stringBufferTop;
    private ObjToIntMap allStrings = new ObjToIntMap(50);
    private final int[] ungetBuffer = new int[3];
    private int ungetCursor;
    private boolean hitEOF = false;
    private int lineStart = 0;
    private int lineEndChar = -1;
    int lineno;
    private String sourceString;
    private Reader sourceReader;
    private char[] sourceBuffer;
    private int sourceEnd;
    int sourceCursor;
    int cursor;
    int tokenBeg;
    int tokenEnd;
    Token.CommentType commentType;
    private boolean xmlIsAttribute;
    private boolean xmlIsTagContent;
    private int xmlOpenTagsCount;
    private Parser parser;
    private String commentPrefix = "";
    private int commentCursor = -1;

    TokenStream(Parser parser, Reader sourceReader, String sourceString, int lineno) {
        this.parser = parser;
        this.lineno = lineno;
        if (sourceReader != null) {
            if (sourceString != null) {
                Kit.codeBug();
            }
            this.sourceReader = sourceReader;
            this.sourceBuffer = new char[512];
            this.sourceEnd = 0;
        } else {
            if (sourceString == null) {
                Kit.codeBug();
            }
            this.sourceString = sourceString;
            this.sourceEnd = sourceString.length();
        }
        this.cursor = 0;
        this.sourceCursor = 0;
    }

    String tokenToString(int token) {
        return "";
    }

    static boolean isKeyword(String s) {
        return 0 != TokenStream.stringToKeyword(s);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int stringToKeyword(String name) {
        int Id_break = 120;
        int Id_case = 115;
        int Id_continue = 121;
        int Id_default = 116;
        int Id_delete = 31;
        int Id_do = 118;
        int Id_else = 113;
        int Id_export = 127;
        int Id_false = 44;
        int Id_for = 119;
        int Id_function = 109;
        int Id_if = 112;
        int Id_in = 52;
        int Id_let = 153;
        int Id_new = 30;
        int Id_null = 42;
        int Id_return = 4;
        int Id_switch = 114;
        int Id_this = 43;
        int Id_true = 45;
        int Id_typeof = 32;
        int Id_var = 122;
        int Id_void = 126;
        int Id_while = 117;
        int Id_with = 123;
        int Id_yield = 72;
        int Id_abstract = 127;
        int Id_boolean = 127;
        int Id_byte = 127;
        int Id_catch = 124;
        int Id_char = 127;
        int Id_class = 127;
        int Id_const = 154;
        int Id_debugger = 160;
        int Id_double = 127;
        int Id_enum = 127;
        int Id_extends = 127;
        int Id_final = 127;
        int Id_finally = 125;
        int Id_float = 127;
        int Id_goto = 127;
        int Id_implements = 127;
        int Id_import = 127;
        int Id_instanceof = 53;
        int Id_int = 127;
        int Id_interface = 127;
        int Id_long = 127;
        int Id_native = 127;
        int Id_package = 127;
        int Id_private = 127;
        int Id_protected = 127;
        int Id_public = 127;
        int Id_short = 127;
        int Id_static = 127;
        int Id_super = 127;
        int Id_synchronized = 127;
        int Id_throw = 50;
        int Id_throws = 127;
        int Id_transient = 127;
        int Id_try = 81;
        int Id_volatile = 127;
        String s = name;
        int id = 0;
        String X = null;
        block0 : switch (s.length()) {
            case 2: {
                char c = s.charAt(1);
                if (c == 'f') {
                    if (s.charAt(0) != 'i') break;
                    return 112;
                }
                if (c == 'n') {
                    if (s.charAt(0) != 'i') break;
                    return 52;
                }
                if (c != 'o' || s.charAt(0) != 'd') break;
                return 118;
            }
            case 3: {
                switch (s.charAt(0)) {
                    case 'f': {
                        if (s.charAt(2) != 'r' || s.charAt(1) != 'o') break;
                        return 119;
                    }
                    case 'i': {
                        if (s.charAt(2) != 't' || s.charAt(1) != 'n') break;
                        return 127;
                    }
                    case 'l': {
                        if (s.charAt(2) != 't' || s.charAt(1) != 'e') break;
                        return 153;
                    }
                    case 'n': {
                        if (s.charAt(2) != 'w' || s.charAt(1) != 'e') break;
                        return 30;
                    }
                    case 't': {
                        if (s.charAt(2) != 'y' || s.charAt(1) != 'r') break;
                        return 81;
                    }
                    case 'v': {
                        if (s.charAt(2) != 'r' || s.charAt(1) != 'a') break;
                        return 122;
                    }
                }
                break;
            }
            case 4: {
                switch (s.charAt(0)) {
                    case 'b': {
                        X = "byte";
                        id = 127;
                        break;
                    }
                    case 'c': {
                        char c = s.charAt(3);
                        if (c == 'e') {
                            if (s.charAt(2) != 's' || s.charAt(1) != 'a') break;
                            return 115;
                        }
                        if (c != 'r' || s.charAt(2) != 'a' || s.charAt(1) != 'h') break;
                        return 127;
                    }
                    case 'e': {
                        char c = s.charAt(3);
                        if (c == 'e') {
                            if (s.charAt(2) != 's' || s.charAt(1) != 'l') break;
                            return 113;
                        }
                        if (c != 'm' || s.charAt(2) != 'u' || s.charAt(1) != 'n') break;
                        return 127;
                    }
                    case 'g': {
                        X = "goto";
                        id = 127;
                        break;
                    }
                    case 'l': {
                        X = "long";
                        id = 127;
                        break;
                    }
                    case 'n': {
                        X = "null";
                        id = 42;
                        break;
                    }
                    case 't': {
                        char c = s.charAt(3);
                        if (c == 'e') {
                            if (s.charAt(2) != 'u' || s.charAt(1) != 'r') break;
                            return 45;
                        }
                        if (c != 's' || s.charAt(2) != 'i' || s.charAt(1) != 'h') break;
                        return 43;
                    }
                    case 'v': {
                        X = "void";
                        id = 126;
                        break;
                    }
                    case 'w': {
                        X = "with";
                        id = 123;
                        break;
                    }
                }
                break;
            }
            case 5: {
                switch (s.charAt(2)) {
                    case 'a': {
                        X = "class";
                        id = 127;
                        break;
                    }
                    case 'e': {
                        char c = s.charAt(0);
                        if (c == 'b') {
                            X = "break";
                            id = 120;
                            break;
                        }
                        if (c != 'y') break;
                        X = "yield";
                        id = 72;
                        break;
                    }
                    case 'i': {
                        X = "while";
                        id = 117;
                        break;
                    }
                    case 'l': {
                        X = "false";
                        id = 44;
                        break;
                    }
                    case 'n': {
                        char c = s.charAt(0);
                        if (c == 'c') {
                            X = "const";
                            id = 154;
                            break;
                        }
                        if (c != 'f') break;
                        X = "final";
                        id = 127;
                        break;
                    }
                    case 'o': {
                        char c = s.charAt(0);
                        if (c == 'f') {
                            X = "float";
                            id = 127;
                            break;
                        }
                        if (c != 's') break;
                        X = "short";
                        id = 127;
                        break;
                    }
                    case 'p': {
                        X = "super";
                        id = 127;
                        break;
                    }
                    case 'r': {
                        X = "throw";
                        id = 50;
                        break;
                    }
                    case 't': {
                        X = "catch";
                        id = 124;
                        break;
                    }
                }
                break;
            }
            case 6: {
                switch (s.charAt(1)) {
                    case 'a': {
                        X = "native";
                        id = 127;
                        break;
                    }
                    case 'e': {
                        char c = s.charAt(0);
                        if (c == 'd') {
                            X = "delete";
                            id = 31;
                            break;
                        }
                        if (c != 'r') break;
                        X = "return";
                        id = 4;
                        break;
                    }
                    case 'h': {
                        X = "throws";
                        id = 127;
                        break;
                    }
                    case 'm': {
                        X = "import";
                        id = 127;
                        break;
                    }
                    case 'o': {
                        X = "double";
                        id = 127;
                        break;
                    }
                    case 't': {
                        X = "static";
                        id = 127;
                        break;
                    }
                    case 'u': {
                        X = "public";
                        id = 127;
                        break;
                    }
                    case 'w': {
                        X = "switch";
                        id = 114;
                        break;
                    }
                    case 'x': {
                        X = "export";
                        id = 127;
                        break;
                    }
                    case 'y': {
                        X = "typeof";
                        id = 32;
                        break;
                    }
                }
                break;
            }
            case 7: {
                switch (s.charAt(1)) {
                    case 'a': {
                        X = "package";
                        id = 127;
                        break block0;
                    }
                    case 'e': {
                        X = "default";
                        id = 116;
                        break block0;
                    }
                    case 'i': {
                        X = "finally";
                        id = 125;
                        break block0;
                    }
                    case 'o': {
                        X = "boolean";
                        id = 127;
                        break block0;
                    }
                    case 'r': {
                        X = "private";
                        id = 127;
                        break block0;
                    }
                    case 'x': {
                        X = "extends";
                        id = 127;
                        break block0;
                    }
                }
                break;
            }
            case 8: {
                switch (s.charAt(0)) {
                    case 'a': {
                        X = "abstract";
                        id = 127;
                        break block0;
                    }
                    case 'c': {
                        X = "continue";
                        id = 121;
                        break block0;
                    }
                    case 'd': {
                        X = "debugger";
                        id = 160;
                        break block0;
                    }
                    case 'f': {
                        X = "function";
                        id = 109;
                        break block0;
                    }
                    case 'v': {
                        X = "volatile";
                        id = 127;
                        break block0;
                    }
                }
                break;
            }
            case 9: {
                char c = s.charAt(0);
                if (c == 'i') {
                    X = "interface";
                    id = 127;
                    break;
                }
                if (c == 'p') {
                    X = "protected";
                    id = 127;
                    break;
                }
                if (c != 't') break;
                X = "transient";
                id = 127;
                break;
            }
            case 10: {
                char c = s.charAt(1);
                if (c == 'm') {
                    X = "implements";
                    id = 127;
                    break;
                }
                if (c != 'n') break;
                X = "instanceof";
                id = 53;
                break;
            }
            case 12: {
                X = "synchronized";
                id = 127;
            }
        }
        if (X != null && X != s && !X.equals(s)) {
            return 0;
        }
        if (id != 0) return id & 0xFF;
        return 0;
    }

    final String getSourceString() {
        return this.sourceString;
    }

    final int getLineno() {
        return this.lineno;
    }

    final String getString() {
        return this.string;
    }

    final char getQuoteChar() {
        return (char)this.quoteChar;
    }

    final double getNumber() {
        return this.number;
    }

    final boolean isNumberOctal() {
        return this.isOctal;
    }

    final boolean isNumberHex() {
        return this.isHex;
    }

    final boolean eof() {
        return this.hitEOF;
    }

    final int getToken() throws IOException {
        boolean identifierStart;
        int c;
        do {
            if ((c = this.getChar()) == -1) {
                this.tokenBeg = this.cursor - 1;
                this.tokenEnd = this.cursor;
                return 0;
            }
            if (c != 10) continue;
            this.dirtyLine = false;
            this.tokenBeg = this.cursor - 1;
            this.tokenEnd = this.cursor;
            return 1;
        } while (TokenStream.isJSSpace(c));
        if (c != 45) {
            this.dirtyLine = true;
        }
        this.tokenBeg = this.cursor - 1;
        this.tokenEnd = this.cursor;
        if (c == 64) {
            return 147;
        }
        boolean isUnicodeEscapeStart = false;
        if (c == 92) {
            c = this.getChar();
            if (c == 117) {
                identifierStart = true;
                isUnicodeEscapeStart = true;
                this.stringBufferTop = 0;
            } else {
                identifierStart = false;
                this.ungetChar(c);
                c = 92;
            }
        } else {
            identifierStart = Character.isJavaIdentifierStart((char)c);
            if (identifierStart) {
                this.stringBufferTop = 0;
                this.addToString(c);
            }
        }
        if (identifierStart) {
            boolean containsEscape = isUnicodeEscapeStart;
            while (true) {
                if (isUnicodeEscapeStart) {
                    int escapeVal = 0;
                    for (int i = 0; i != 4 && (escapeVal = Kit.xDigitToInt(c = this.getChar(), escapeVal)) >= 0; ++i) {
                    }
                    if (escapeVal < 0) {
                        this.parser.addError("msg.invalid.escape");
                        return -1;
                    }
                    this.addToString(escapeVal);
                    isUnicodeEscapeStart = false;
                    continue;
                }
                c = this.getChar();
                if (c == 92) {
                    c = this.getChar();
                    if (c == 117) {
                        isUnicodeEscapeStart = true;
                        containsEscape = true;
                        continue;
                    }
                    this.parser.addError("msg.illegal.character");
                    return -1;
                }
                if (c == -1 || c == 65279 || !Character.isJavaIdentifierPart((char)c)) break;
                this.addToString(c);
            }
            this.ungetChar(c);
            String str = this.getStringFromBuffer();
            if (!containsEscape) {
                int result = TokenStream.stringToKeyword(str);
                if (result != 0) {
                    if ((result == 153 || result == 72) && this.parser.compilerEnv.getLanguageVersion() < 170) {
                        this.string = result == 153 ? "let" : "yield";
                        result = 39;
                    }
                    this.string = (String)this.allStrings.intern(str);
                    if (result != 127) {
                        return result;
                    }
                    if (!this.parser.compilerEnv.isReservedKeywordAsIdentifier()) {
                        return result;
                    }
                }
            } else if (TokenStream.isKeyword(str)) {
                str = this.convertLastCharToHex(str);
            }
            this.string = (String)this.allStrings.intern(str);
            return 39;
        }
        if (TokenStream.isDigit(c) || c == 46 && TokenStream.isDigit(this.peekChar())) {
            double dval;
            String numString;
            this.isOctal = false;
            this.stringBufferTop = 0;
            int base = 10;
            this.isOctal = false;
            this.isHex = false;
            if (c == 48) {
                c = this.getChar();
                if (c == 120 || c == 88) {
                    base = 16;
                    this.isHex = true;
                    c = this.getChar();
                } else if (TokenStream.isDigit(c)) {
                    base = 8;
                    this.isOctal = true;
                } else {
                    this.addToString(48);
                }
            }
            if (base == 16) {
                while (0 <= Kit.xDigitToInt(c, 0)) {
                    this.addToString(c);
                    c = this.getChar();
                }
            } else {
                while (48 <= c && c <= 57) {
                    if (base == 8 && c >= 56) {
                        this.parser.addWarning("msg.bad.octal.literal", c == 56 ? "8" : "9");
                        base = 10;
                    }
                    this.addToString(c);
                    c = this.getChar();
                }
            }
            boolean isInteger = true;
            if (base == 10 && (c == 46 || c == 101 || c == 69)) {
                isInteger = false;
                if (c == 46) {
                    do {
                        this.addToString(c);
                    } while (TokenStream.isDigit(c = this.getChar()));
                }
                if (c == 101 || c == 69) {
                    this.addToString(c);
                    c = this.getChar();
                    if (c == 43 || c == 45) {
                        this.addToString(c);
                        c = this.getChar();
                    }
                    if (!TokenStream.isDigit(c)) {
                        this.parser.addError("msg.missing.exponent");
                        return -1;
                    }
                    do {
                        this.addToString(c);
                    } while (TokenStream.isDigit(c = this.getChar()));
                }
            }
            this.ungetChar(c);
            this.string = numString = this.getStringFromBuffer();
            if (base == 10 && !isInteger) {
                try {
                    dval = Double.parseDouble(numString);
                } catch (NumberFormatException ex) {
                    this.parser.addError("msg.caught.nfe");
                    return -1;
                }
            } else {
                dval = ScriptRuntime.stringToNumber(numString, 0, base);
            }
            this.number = dval;
            return 40;
        }
        if (c == 34 || c == 39) {
            this.quoteChar = c;
            this.stringBufferTop = 0;
            c = this.getChar(false);
            block46: while (c != this.quoteChar) {
                if (c == 10 || c == -1) {
                    this.ungetChar(c);
                    this.tokenEnd = this.cursor;
                    this.parser.addError("msg.unterminated.string.lit");
                    return -1;
                }
                if (c == 92) {
                    c = this.getChar();
                    switch (c) {
                        case 98: {
                            c = 8;
                            break;
                        }
                        case 102: {
                            c = 12;
                            break;
                        }
                        case 110: {
                            c = 10;
                            break;
                        }
                        case 114: {
                            c = 13;
                            break;
                        }
                        case 116: {
                            c = 9;
                            break;
                        }
                        case 118: {
                            c = 11;
                            break;
                        }
                        case 117: {
                            int escapeStart = this.stringBufferTop;
                            this.addToString(117);
                            int escapeVal = 0;
                            for (int i = 0; i != 4; ++i) {
                                c = this.getChar();
                                escapeVal = Kit.xDigitToInt(c, escapeVal);
                                if (escapeVal < 0) continue block46;
                                this.addToString(c);
                            }
                            this.stringBufferTop = escapeStart;
                            c = escapeVal;
                            break;
                        }
                        case 120: {
                            c = this.getChar();
                            int escapeVal = Kit.xDigitToInt(c, 0);
                            if (escapeVal < 0) {
                                this.addToString(120);
                                continue block46;
                            }
                            int c1 = c;
                            c = this.getChar();
                            escapeVal = Kit.xDigitToInt(c, escapeVal);
                            if (escapeVal < 0) {
                                this.addToString(120);
                                this.addToString(c1);
                                continue block46;
                            }
                            c = escapeVal;
                            break;
                        }
                        case 10: {
                            c = this.getChar();
                            continue block46;
                        }
                        default: {
                            if (48 > c || c >= 56) break;
                            int val = c - 48;
                            c = this.getChar();
                            if (48 <= c && c < 56) {
                                val = 8 * val + c - 48;
                                c = this.getChar();
                                if (48 <= c && c < 56 && val <= 31) {
                                    val = 8 * val + c - 48;
                                    c = this.getChar();
                                }
                            }
                            this.ungetChar(c);
                            c = val;
                        }
                    }
                }
                this.addToString(c);
                c = this.getChar(false);
            }
            String str = this.getStringFromBuffer();
            this.string = (String)this.allStrings.intern(str);
            return 41;
        }
        switch (c) {
            case 59: {
                return 82;
            }
            case 91: {
                return 83;
            }
            case 93: {
                return 84;
            }
            case 123: {
                return 85;
            }
            case 125: {
                return 86;
            }
            case 40: {
                return 87;
            }
            case 41: {
                return 88;
            }
            case 44: {
                return 89;
            }
            case 63: {
                return 102;
            }
            case 58: {
                if (this.matchChar(58)) {
                    return 144;
                }
                return 103;
            }
            case 46: {
                if (this.matchChar(46)) {
                    return 143;
                }
                if (this.matchChar(40)) {
                    return 146;
                }
                return 108;
            }
            case 124: {
                if (this.matchChar(124)) {
                    return 104;
                }
                if (this.matchChar(61)) {
                    return 91;
                }
                return 9;
            }
            case 94: {
                if (this.matchChar(61)) {
                    return 92;
                }
                return 10;
            }
            case 38: {
                if (this.matchChar(38)) {
                    return 105;
                }
                if (this.matchChar(61)) {
                    return 93;
                }
                return 11;
            }
            case 61: {
                if (this.matchChar(61)) {
                    if (this.matchChar(61)) {
                        return 46;
                    }
                    return 12;
                }
                return 90;
            }
            case 33: {
                if (this.matchChar(61)) {
                    if (this.matchChar(61)) {
                        return 47;
                    }
                    return 13;
                }
                return 26;
            }
            case 60: {
                if (this.matchChar(33)) {
                    if (this.matchChar(45)) {
                        if (this.matchChar(45)) {
                            this.tokenBeg = this.cursor - 4;
                            this.skipLine();
                            this.commentType = Token.CommentType.HTML;
                            return 161;
                        }
                        this.ungetCharIgnoreLineEnd(45);
                    }
                    this.ungetCharIgnoreLineEnd(33);
                }
                if (this.matchChar(60)) {
                    if (this.matchChar(61)) {
                        return 94;
                    }
                    return 18;
                }
                if (this.matchChar(61)) {
                    return 15;
                }
                return 14;
            }
            case 62: {
                if (this.matchChar(62)) {
                    if (this.matchChar(62)) {
                        if (this.matchChar(61)) {
                            return 96;
                        }
                        return 20;
                    }
                    if (this.matchChar(61)) {
                        return 95;
                    }
                    return 19;
                }
                if (this.matchChar(61)) {
                    return 17;
                }
                return 16;
            }
            case 42: {
                if (this.matchChar(61)) {
                    return 99;
                }
                return 23;
            }
            case 47: {
                this.markCommentStart();
                if (this.matchChar(47)) {
                    this.tokenBeg = this.cursor - 2;
                    this.skipLine();
                    this.commentType = Token.CommentType.LINE;
                    return 161;
                }
                if (this.matchChar(42)) {
                    boolean lookForSlash = false;
                    this.tokenBeg = this.cursor - 2;
                    if (this.matchChar(42)) {
                        lookForSlash = true;
                        this.commentType = Token.CommentType.JSDOC;
                    } else {
                        this.commentType = Token.CommentType.BLOCK_COMMENT;
                    }
                    while (true) {
                        if ((c = this.getChar()) == -1) {
                            this.tokenEnd = this.cursor - 1;
                            this.parser.addError("msg.unterminated.comment");
                            return 161;
                        }
                        if (c == 42) {
                            lookForSlash = true;
                            continue;
                        }
                        if (c == 47) {
                            if (!lookForSlash) continue;
                            this.tokenEnd = this.cursor;
                            return 161;
                        }
                        lookForSlash = false;
                        this.tokenEnd = this.cursor;
                    }
                }
                if (this.matchChar(61)) {
                    return 100;
                }
                return 24;
            }
            case 37: {
                if (this.matchChar(61)) {
                    return 101;
                }
                return 25;
            }
            case 126: {
                return 27;
            }
            case 43: {
                if (this.matchChar(61)) {
                    return 97;
                }
                if (this.matchChar(43)) {
                    return 106;
                }
                return 21;
            }
            case 45: {
                if (this.matchChar(61)) {
                    c = 98;
                } else if (this.matchChar(45)) {
                    if (!this.dirtyLine && this.matchChar(62)) {
                        this.markCommentStart("--");
                        this.skipLine();
                        this.commentType = Token.CommentType.HTML;
                        return 161;
                    }
                    c = 107;
                } else {
                    c = 22;
                }
                this.dirtyLine = true;
                return c;
            }
        }
        this.parser.addError("msg.illegal.character");
        return -1;
    }

    private static boolean isAlpha(int c) {
        if (c <= 90) {
            return 65 <= c;
        }
        return 97 <= c && c <= 122;
    }

    static boolean isDigit(int c) {
        return 48 <= c && c <= 57;
    }

    static boolean isJSSpace(int c) {
        if (c <= 127) {
            return c == 32 || c == 9 || c == 12 || c == 11;
        }
        return c == 160 || c == 65279 || Character.getType((char)c) == 12;
    }

    private static boolean isJSFormatChar(int c) {
        return c > 127 && Character.getType((char)c) == 16;
    }

    void readRegExp(int startToken) throws IOException {
        int c;
        int start = this.tokenBeg;
        this.stringBufferTop = 0;
        if (startToken == 100) {
            this.addToString(61);
        } else if (startToken != 24) {
            Kit.codeBug();
        }
        boolean inCharSet = false;
        while ((c = this.getChar()) != 47 || inCharSet) {
            if (c == 10 || c == -1) {
                this.ungetChar(c);
                this.tokenEnd = this.cursor - 1;
                this.string = new String(this.stringBuffer, 0, this.stringBufferTop);
                this.parser.reportError("msg.unterminated.re.lit");
                return;
            }
            if (c == 92) {
                this.addToString(c);
                c = this.getChar();
            } else if (c == 91) {
                inCharSet = true;
            } else if (c == 93) {
                inCharSet = false;
            }
            this.addToString(c);
        }
        int reEnd = this.stringBufferTop;
        while (true) {
            if (this.matchChar(103)) {
                this.addToString(103);
                continue;
            }
            if (this.matchChar(105)) {
                this.addToString(105);
                continue;
            }
            if (this.matchChar(109)) {
                this.addToString(109);
                continue;
            }
            if (!this.matchChar(121)) break;
            this.addToString(121);
        }
        this.tokenEnd = start + this.stringBufferTop + 2;
        if (TokenStream.isAlpha(this.peekChar())) {
            this.parser.reportError("msg.invalid.re.flag");
        }
        this.string = new String(this.stringBuffer, 0, reEnd);
        this.regExpFlags = new String(this.stringBuffer, reEnd, this.stringBufferTop - reEnd);
    }

    String readAndClearRegExpFlags() {
        String flags = this.regExpFlags;
        this.regExpFlags = null;
        return flags;
    }

    boolean isXMLAttribute() {
        return this.xmlIsAttribute;
    }

    int getFirstXMLToken() throws IOException {
        this.xmlOpenTagsCount = 0;
        this.xmlIsAttribute = false;
        this.xmlIsTagContent = false;
        if (!this.canUngetChar()) {
            return -1;
        }
        this.ungetChar(60);
        return this.getNextXMLToken();
    }

    int getNextXMLToken() throws IOException {
        this.tokenBeg = this.cursor;
        this.stringBufferTop = 0;
        int c = this.getChar();
        while (c != -1) {
            if (this.xmlIsTagContent) {
                switch (c) {
                    case 62: {
                        this.addToString(c);
                        this.xmlIsTagContent = false;
                        this.xmlIsAttribute = false;
                        break;
                    }
                    case 47: {
                        this.addToString(c);
                        if (this.peekChar() != 62) break;
                        c = this.getChar();
                        this.addToString(c);
                        this.xmlIsTagContent = false;
                        --this.xmlOpenTagsCount;
                        break;
                    }
                    case 123: {
                        this.ungetChar(c);
                        this.string = this.getStringFromBuffer();
                        return 145;
                    }
                    case 34: 
                    case 39: {
                        this.addToString(c);
                        if (this.readQuotedString(c)) break;
                        return -1;
                    }
                    case 61: {
                        this.addToString(c);
                        this.xmlIsAttribute = true;
                        break;
                    }
                    case 9: 
                    case 10: 
                    case 13: 
                    case 32: {
                        this.addToString(c);
                        break;
                    }
                    default: {
                        this.addToString(c);
                        this.xmlIsAttribute = false;
                    }
                }
                if (!this.xmlIsTagContent && this.xmlOpenTagsCount == 0) {
                    this.string = this.getStringFromBuffer();
                    return 148;
                }
            } else {
                block8 : switch (c) {
                    case 60: {
                        this.addToString(c);
                        c = this.peekChar();
                        switch (c) {
                            case 33: {
                                c = this.getChar();
                                this.addToString(c);
                                c = this.peekChar();
                                switch (c) {
                                    case 45: {
                                        c = this.getChar();
                                        this.addToString(c);
                                        c = this.getChar();
                                        if (c == 45) {
                                            this.addToString(c);
                                            if (this.readXmlComment()) break block8;
                                            return -1;
                                        }
                                        this.stringBufferTop = 0;
                                        this.string = null;
                                        this.parser.addError("msg.XML.bad.form");
                                        return -1;
                                    }
                                    case 91: {
                                        c = this.getChar();
                                        this.addToString(c);
                                        if (this.getChar() == 67 && this.getChar() == 68 && this.getChar() == 65 && this.getChar() == 84 && this.getChar() == 65 && this.getChar() == 91) {
                                            this.addToString(67);
                                            this.addToString(68);
                                            this.addToString(65);
                                            this.addToString(84);
                                            this.addToString(65);
                                            this.addToString(91);
                                            if (this.readCDATA()) break block8;
                                            return -1;
                                        }
                                        this.stringBufferTop = 0;
                                        this.string = null;
                                        this.parser.addError("msg.XML.bad.form");
                                        return -1;
                                    }
                                    default: {
                                        if (this.readEntity()) break block8;
                                        return -1;
                                    }
                                }
                            }
                            case 63: {
                                c = this.getChar();
                                this.addToString(c);
                                if (this.readPI()) break block8;
                                return -1;
                            }
                            case 47: {
                                c = this.getChar();
                                this.addToString(c);
                                if (this.xmlOpenTagsCount == 0) {
                                    this.stringBufferTop = 0;
                                    this.string = null;
                                    this.parser.addError("msg.XML.bad.form");
                                    return -1;
                                }
                                this.xmlIsTagContent = true;
                                --this.xmlOpenTagsCount;
                                break;
                            }
                            default: {
                                this.xmlIsTagContent = true;
                                ++this.xmlOpenTagsCount;
                                break;
                            }
                        }
                        break;
                    }
                    case 123: {
                        this.ungetChar(c);
                        this.string = this.getStringFromBuffer();
                        return 145;
                    }
                    default: {
                        this.addToString(c);
                    }
                }
            }
            c = this.getChar();
        }
        this.tokenEnd = this.cursor;
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return -1;
    }

    private boolean readQuotedString(int quote) throws IOException {
        int c = this.getChar();
        while (c != -1) {
            this.addToString(c);
            if (c == quote) {
                return true;
            }
            c = this.getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readXmlComment() throws IOException {
        int c = this.getChar();
        while (c != -1) {
            this.addToString(c);
            if (c == 45 && this.peekChar() == 45) {
                c = this.getChar();
                this.addToString(c);
                if (this.peekChar() != 62) continue;
                c = this.getChar();
                this.addToString(c);
                return true;
            }
            c = this.getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readCDATA() throws IOException {
        int c = this.getChar();
        while (c != -1) {
            this.addToString(c);
            if (c == 93 && this.peekChar() == 93) {
                c = this.getChar();
                this.addToString(c);
                if (this.peekChar() != 62) continue;
                c = this.getChar();
                this.addToString(c);
                return true;
            }
            c = this.getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readEntity() throws IOException {
        int declTags = 1;
        int c = this.getChar();
        while (c != -1) {
            this.addToString(c);
            switch (c) {
                case 60: {
                    ++declTags;
                    break;
                }
                case 62: {
                    if (--declTags != 0) break;
                    return true;
                }
            }
            c = this.getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private boolean readPI() throws IOException {
        int c = this.getChar();
        while (c != -1) {
            this.addToString(c);
            if (c == 63 && this.peekChar() == 62) {
                c = this.getChar();
                this.addToString(c);
                return true;
            }
            c = this.getChar();
        }
        this.stringBufferTop = 0;
        this.string = null;
        this.parser.addError("msg.XML.bad.form");
        return false;
    }

    private String getStringFromBuffer() {
        this.tokenEnd = this.cursor;
        return new String(this.stringBuffer, 0, this.stringBufferTop);
    }

    private void addToString(int c) {
        int N = this.stringBufferTop;
        if (N == this.stringBuffer.length) {
            char[] tmp = new char[this.stringBuffer.length * 2];
            System.arraycopy(this.stringBuffer, 0, tmp, 0, N);
            this.stringBuffer = tmp;
        }
        this.stringBuffer[N] = (char)c;
        this.stringBufferTop = N + 1;
    }

    private boolean canUngetChar() {
        return this.ungetCursor == 0 || this.ungetBuffer[this.ungetCursor - 1] != 10;
    }

    private void ungetChar(int c) {
        if (this.ungetCursor != 0 && this.ungetBuffer[this.ungetCursor - 1] == 10) {
            Kit.codeBug();
        }
        this.ungetBuffer[this.ungetCursor++] = c;
        --this.cursor;
    }

    private boolean matchChar(int test) throws IOException {
        int c = this.getCharIgnoreLineEnd();
        if (c == test) {
            this.tokenEnd = this.cursor;
            return true;
        }
        this.ungetCharIgnoreLineEnd(c);
        return false;
    }

    private int peekChar() throws IOException {
        int c = this.getChar();
        this.ungetChar(c);
        return c;
    }

    private int getChar() throws IOException {
        return this.getChar(true);
    }

    private int getChar(boolean skipFormattingChars) throws IOException {
        int c;
        block12: {
            if (this.ungetCursor != 0) {
                ++this.cursor;
                return this.ungetBuffer[--this.ungetCursor];
            }
            while (true) {
                if (this.sourceString != null) {
                    if (this.sourceCursor == this.sourceEnd) {
                        this.hitEOF = true;
                        return -1;
                    }
                    ++this.cursor;
                    c = this.sourceString.charAt(this.sourceCursor++);
                } else {
                    if (this.sourceCursor == this.sourceEnd && !this.fillSourceBuffer()) {
                        this.hitEOF = true;
                        return -1;
                    }
                    ++this.cursor;
                    c = this.sourceBuffer[this.sourceCursor++];
                }
                if (this.lineEndChar >= 0) {
                    if (this.lineEndChar == 13 && c == 10) {
                        this.lineEndChar = 10;
                        continue;
                    }
                    this.lineEndChar = -1;
                    this.lineStart = this.sourceCursor - 1;
                    ++this.lineno;
                }
                if (c <= 127) {
                    if (c == 10 || c == 13) {
                        this.lineEndChar = c;
                        c = 10;
                    }
                    break block12;
                }
                if (c == 65279) {
                    return c;
                }
                if (!skipFormattingChars || !TokenStream.isJSFormatChar(c)) break;
            }
            if (ScriptRuntime.isJSLineTerminator(c)) {
                this.lineEndChar = c;
                c = 10;
            }
        }
        return c;
    }

    private int getCharIgnoreLineEnd() throws IOException {
        int c;
        block9: {
            if (this.ungetCursor != 0) {
                ++this.cursor;
                return this.ungetBuffer[--this.ungetCursor];
            }
            do {
                if (this.sourceString != null) {
                    if (this.sourceCursor == this.sourceEnd) {
                        this.hitEOF = true;
                        return -1;
                    }
                    ++this.cursor;
                    c = this.sourceString.charAt(this.sourceCursor++);
                } else {
                    if (this.sourceCursor == this.sourceEnd && !this.fillSourceBuffer()) {
                        this.hitEOF = true;
                        return -1;
                    }
                    ++this.cursor;
                    c = this.sourceBuffer[this.sourceCursor++];
                }
                if (c <= 127) {
                    if (c == 10 || c == 13) {
                        this.lineEndChar = c;
                        c = 10;
                    }
                    break block9;
                }
                if (c != 65279) continue;
                return c;
            } while (TokenStream.isJSFormatChar(c));
            if (ScriptRuntime.isJSLineTerminator(c)) {
                this.lineEndChar = c;
                c = 10;
            }
        }
        return c;
    }

    private void ungetCharIgnoreLineEnd(int c) {
        this.ungetBuffer[this.ungetCursor++] = c;
        --this.cursor;
    }

    private void skipLine() throws IOException {
        int c;
        while ((c = this.getChar()) != -1 && c != 10) {
        }
        this.ungetChar(c);
        this.tokenEnd = this.cursor;
    }

    final int getOffset() {
        int n = this.sourceCursor - this.lineStart;
        if (this.lineEndChar >= 0) {
            --n;
        }
        return n;
    }

    private final int charAt(int index) {
        if (index < 0) {
            return -1;
        }
        if (this.sourceString != null) {
            if (index >= this.sourceEnd) {
                return -1;
            }
            return this.sourceString.charAt(index);
        }
        if (index >= this.sourceEnd) {
            int oldSourceCursor = this.sourceCursor;
            try {
                if (!this.fillSourceBuffer()) {
                    return -1;
                }
            } catch (IOException ioe) {
                return -1;
            }
            index -= oldSourceCursor - this.sourceCursor;
        }
        return this.sourceBuffer[index];
    }

    private final String substring(int beginIndex, int endIndex) {
        if (this.sourceString != null) {
            return this.sourceString.substring(beginIndex, endIndex);
        }
        int count = endIndex - beginIndex;
        return new String(this.sourceBuffer, beginIndex, count);
    }

    final String getLine() {
        int lineEnd = this.sourceCursor;
        if (this.lineEndChar >= 0) {
            if (this.lineEndChar == 10 && this.charAt(--lineEnd - 1) == 13) {
                --lineEnd;
            }
        } else {
            int c;
            int lineLength = lineEnd - this.lineStart;
            while ((c = this.charAt(this.lineStart + lineLength)) != -1 && !ScriptRuntime.isJSLineTerminator(c)) {
                ++lineLength;
            }
            lineEnd = this.lineStart + lineLength;
        }
        return this.substring(this.lineStart, lineEnd);
    }

    final String getLine(int position, int[] linep) {
        assert (position >= 0 && position <= this.cursor);
        assert (linep.length == 2);
        int delta = this.cursor + this.ungetCursor - position;
        int cur = this.sourceCursor;
        if (delta > cur) {
            return null;
        }
        int end = 0;
        int lines = 0;
        while (delta > 0) {
            assert (cur > 0);
            int c = this.charAt(cur - 1);
            if (ScriptRuntime.isJSLineTerminator(c)) {
                if (c == 10 && this.charAt(cur - 2) == 13) {
                    --delta;
                    --cur;
                }
                ++lines;
                end = cur - 1;
            }
            --delta;
            --cur;
        }
        int start = 0;
        int offset = 0;
        while (cur > 0) {
            int c = this.charAt(cur - 1);
            if (ScriptRuntime.isJSLineTerminator(c)) {
                start = cur;
                break;
            }
            --cur;
            ++offset;
        }
        linep[0] = this.lineno - lines + (this.lineEndChar >= 0 ? 1 : 0);
        linep[1] = offset;
        if (lines == 0) {
            return this.getLine();
        }
        return this.substring(start, end);
    }

    private boolean fillSourceBuffer() throws IOException {
        int n;
        if (this.sourceString != null) {
            Kit.codeBug();
        }
        if (this.sourceEnd == this.sourceBuffer.length) {
            if (this.lineStart != 0 && !this.isMarkingComment()) {
                System.arraycopy(this.sourceBuffer, this.lineStart, this.sourceBuffer, 0, this.sourceEnd - this.lineStart);
                this.sourceEnd -= this.lineStart;
                this.sourceCursor -= this.lineStart;
                this.lineStart = 0;
            } else {
                char[] tmp = new char[this.sourceBuffer.length * 2];
                System.arraycopy(this.sourceBuffer, 0, tmp, 0, this.sourceEnd);
                this.sourceBuffer = tmp;
            }
        }
        if ((n = this.sourceReader.read(this.sourceBuffer, this.sourceEnd, this.sourceBuffer.length - this.sourceEnd)) < 0) {
            return false;
        }
        this.sourceEnd += n;
        return true;
    }

    public int getCursor() {
        return this.cursor;
    }

    public int getTokenBeg() {
        return this.tokenBeg;
    }

    public int getTokenEnd() {
        return this.tokenEnd;
    }

    public int getTokenLength() {
        return this.tokenEnd - this.tokenBeg;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    private void markCommentStart() {
        this.markCommentStart("");
    }

    private void markCommentStart(String prefix) {
        if (this.parser.compilerEnv.isRecordingComments() && this.sourceReader != null) {
            this.commentPrefix = prefix;
            this.commentCursor = this.sourceCursor - 1;
        }
    }

    private boolean isMarkingComment() {
        return this.commentCursor != -1;
    }

    final String getAndResetCurrentComment() {
        if (this.sourceString != null) {
            if (this.isMarkingComment()) {
                Kit.codeBug();
            }
            return this.sourceString.substring(this.tokenBeg, this.tokenEnd);
        }
        if (!this.isMarkingComment()) {
            Kit.codeBug();
        }
        StringBuilder comment = new StringBuilder(this.commentPrefix);
        comment.append(this.sourceBuffer, this.commentCursor, this.getTokenLength() - this.commentPrefix.length());
        this.commentCursor = -1;
        return comment.toString();
    }

    private String convertLastCharToHex(String str) {
        int lastIndex = str.length() - 1;
        StringBuffer buf = new StringBuffer(str.substring(0, lastIndex));
        buf.append("\\u");
        String hexCode = Integer.toHexString(str.charAt(lastIndex));
        for (int i = 0; i < 4 - hexCode.length(); ++i) {
            buf.append('0');
        }
        buf.append(hexCode);
        return buf.toString();
    }
}

