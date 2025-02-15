/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

public class TokenMgrException
extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public static final int LEXICAL_ERROR = 0;
    public static final int STATIC_LEXER_ERROR = 1;
    public static final int INVALID_LEXICAL_STATE = 2;
    public static final int LOOP_DETECTED = 3;
    int errorCode;

    protected static final String addEscapes(String str) {
        StringBuilder retval = new StringBuilder();
        block10: for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            switch (ch) {
                case '\b': {
                    retval.append("\\b");
                    continue block10;
                }
                case '\t': {
                    retval.append("\\t");
                    continue block10;
                }
                case '\n': {
                    retval.append("\\n");
                    continue block10;
                }
                case '\f': {
                    retval.append("\\f");
                    continue block10;
                }
                case '\r': {
                    retval.append("\\r");
                    continue block10;
                }
                case '\"': {
                    retval.append("\\\"");
                    continue block10;
                }
                case '\'': {
                    retval.append("\\'");
                    continue block10;
                }
                case '\\': {
                    retval.append("\\\\");
                    continue block10;
                }
                default: {
                    if (ch < ' ' || ch > '~') {
                        String s = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u").append(s.substring(s.length() - 4, s.length()));
                        continue block10;
                    }
                    retval.append(ch);
                    continue block10;
                }
            }
        }
        return retval.toString();
    }

    protected static String LexicalErr(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, int curChar) {
        char curChar1 = (char)curChar;
        return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: " + (EOFSeen ? "<EOF> " : "\"" + TokenMgrException.addEscapes(String.valueOf(curChar1)) + "\"" + " (" + curChar + "), ") + "after : \"" + TokenMgrException.addEscapes(errorAfter) + "\"";
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrException() {
    }

    public TokenMgrException(String message, int reason) {
        super(message);
        this.errorCode = reason;
    }

    public TokenMgrException(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, int curChar, int reason) {
        this(TokenMgrException.LexicalErr(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
    }
}

