/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.JavaToken;
import com.github.javaparser.utils.LineSeparator;

public class TokenTypes {
    public static boolean isWhitespace(int kind) {
        return TokenTypes.getCategory(kind).isWhitespace();
    }

    public static boolean isEndOfLineToken(int kind) {
        return TokenTypes.getCategory(kind).isEndOfLine();
    }

    public static boolean isWhitespaceOrComment(int kind) {
        return TokenTypes.getCategory(kind).isWhitespaceOrComment();
    }

    @Deprecated
    public static boolean isSpaceOrTab(int kind) {
        return TokenTypes.isWhitespaceButNotEndOfLine(kind);
    }

    public static boolean isWhitespaceButNotEndOfLine(int kind) {
        return TokenTypes.getCategory(kind).isWhitespaceButNotEndOfLine();
    }

    public static boolean isComment(int kind) {
        return TokenTypes.getCategory(kind).isComment();
    }

    public static int eolTokenKind(LineSeparator lineSeparator) {
        if (lineSeparator.equalsString(LineSeparator.LF)) {
            return 3;
        }
        if (lineSeparator.equalsString(LineSeparator.CRLF)) {
            return 2;
        }
        if (lineSeparator.equalsString(LineSeparator.CR)) {
            return 4;
        }
        throw new AssertionError((Object)"Unknown EOL character sequence");
    }

    public static int eolTokenKind() {
        return TokenTypes.eolTokenKind(LineSeparator.SYSTEM);
    }

    public static int spaceTokenKind() {
        return 1;
    }

    public static JavaToken.Category getCategory(int kind) {
        switch (kind) {
            case 2: 
            case 3: 
            case 4: {
                return JavaToken.Category.EOL;
            }
            case 0: 
            case 1: 
            case 146: {
                return JavaToken.Category.WHITESPACE_NO_EOL;
            }
            case 5: 
            case 8: 
            case 9: {
                return JavaToken.Category.COMMENT;
            }
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 29: 
            case 30: 
            case 31: 
            case 32: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 41: 
            case 42: 
            case 44: 
            case 45: 
            case 46: 
            case 47: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: 
            case 58: 
            case 60: 
            case 61: 
            case 62: 
            case 63: 
            case 64: 
            case 65: 
            case 66: 
            case 67: 
            case 68: 
            case 69: 
            case 70: 
            case 71: 
            case 72: 
            case 73: 
            case 74: {
                return JavaToken.Category.KEYWORD;
            }
            case 28: 
            case 43: 
            case 59: 
            case 75: 
            case 76: 
            case 77: 
            case 78: 
            case 79: 
            case 80: 
            case 81: 
            case 82: 
            case 83: 
            case 84: 
            case 85: 
            case 88: 
            case 89: 
            case 91: {
                return JavaToken.Category.LITERAL;
            }
            case 93: {
                return JavaToken.Category.IDENTIFIER;
            }
            case 96: 
            case 97: 
            case 98: 
            case 99: 
            case 100: 
            case 101: 
            case 102: 
            case 103: 
            case 104: 
            case 105: {
                return JavaToken.Category.SEPARATOR;
            }
            case 106: 
            case 107: 
            case 108: 
            case 109: 
            case 110: 
            case 111: 
            case 112: 
            case 113: 
            case 114: 
            case 115: 
            case 116: 
            case 117: 
            case 118: 
            case 119: 
            case 120: 
            case 121: 
            case 122: 
            case 123: 
            case 124: 
            case 125: 
            case 126: 
            case 127: 
            case 128: 
            case 129: 
            case 130: 
            case 131: 
            case 132: 
            case 133: 
            case 134: 
            case 135: 
            case 136: 
            case 137: 
            case 138: 
            case 139: 
            case 140: 
            case 141: 
            case 142: 
            case 143: 
            case 144: 
            case 145: {
                return JavaToken.Category.OPERATOR;
            }
        }
        throw new AssertionError((Object)("Invalid token kind " + kind));
    }
}

