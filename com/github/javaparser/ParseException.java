/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Token;
import java.util.TreeSet;

public class ParseException
extends Exception {
    private static final String INDENT = "    ";
    protected static final String EOL = "\n";
    public Token currentToken;
    public int[][] expectedTokenSequences;
    public String[] tokenImage;

    public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal) {
        this(currentTokenVal, expectedTokenSequencesVal, tokenImageVal, null);
    }

    public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal, String lexicalStateName) {
        super(ParseException._initialise(currentTokenVal, expectedTokenSequencesVal, tokenImageVal, lexicalStateName));
        this.currentToken = currentTokenVal;
        this.expectedTokenSequences = expectedTokenSequencesVal;
        this.tokenImage = tokenImageVal;
    }

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    private static String _initialise(Token currentToken, int[][] expectedTokenSequences, String[] tokenImage, String lexicalStateName) {
        StringBuilder expected = new StringBuilder();
        int maxSize = 0;
        TreeSet<String> sortedOptions = new TreeSet<String>();
        for (int i = 0; i < expectedTokenSequences.length; ++i) {
            if (maxSize < expectedTokenSequences[i].length) {
                maxSize = expectedTokenSequences[i].length;
            }
            for (int j = 0; j < expectedTokenSequences[i].length; ++j) {
                sortedOptions.add(tokenImage[expectedTokenSequences[i][j]]);
            }
        }
        for (String option : sortedOptions) {
            expected.append(INDENT).append(option).append(EOL);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Encountered unexpected token:");
        Token tok = currentToken.next;
        for (int i = 0; i < maxSize; ++i) {
            String tokenText = tok.image;
            String escapedTokenText = ParseException.add_escapes(tokenText);
            if (i != 0) {
                sb.append(' ');
            }
            if (tok.kind == 0) {
                sb.append(tokenImage[0]);
                break;
            }
            sb.append(" \"");
            sb.append(escapedTokenText);
            sb.append("\"");
            sb.append(" " + tokenImage[tok.kind]);
            tok = tok.next;
        }
        sb.append(EOL).append(INDENT).append("at line ").append(currentToken.next.beginLine).append(", column ").append(currentToken.next.beginColumn);
        sb.append(".").append(EOL);
        if (expectedTokenSequences.length != 0) {
            sb.append(EOL).append("Was expecting").append(expectedTokenSequences.length == 1 ? ":" : " one of:").append(EOL).append(EOL).append((CharSequence)expected);
        }
        return sb.toString();
    }

    static String add_escapes(String str) {
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
                        retval.append("\\u" + s.substring(s.length() - 4, s.length()));
                        continue block10;
                    }
                    retval.append(ch);
                    continue block10;
                }
            }
        }
        return retval.toString();
    }
}

