/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

public class Token {
    public static final boolean printTrees = false;
    static final boolean printICode = false;
    static final boolean printNames = false;
    public static final int ERROR = -1;
    public static final int EOF = 0;
    public static final int EOL = 1;
    public static final int FIRST_BYTECODE_TOKEN = 2;
    public static final int ENTERWITH = 2;
    public static final int LEAVEWITH = 3;
    public static final int RETURN = 4;
    public static final int GOTO = 5;
    public static final int IFEQ = 6;
    public static final int IFNE = 7;
    public static final int SETNAME = 8;
    public static final int BITOR = 9;
    public static final int BITXOR = 10;
    public static final int BITAND = 11;
    public static final int EQ = 12;
    public static final int NE = 13;
    public static final int LT = 14;
    public static final int LE = 15;
    public static final int GT = 16;
    public static final int GE = 17;
    public static final int LSH = 18;
    public static final int RSH = 19;
    public static final int URSH = 20;
    public static final int ADD = 21;
    public static final int SUB = 22;
    public static final int MUL = 23;
    public static final int DIV = 24;
    public static final int MOD = 25;
    public static final int NOT = 26;
    public static final int BITNOT = 27;
    public static final int POS = 28;
    public static final int NEG = 29;
    public static final int NEW = 30;
    public static final int DELPROP = 31;
    public static final int TYPEOF = 32;
    public static final int GETPROP = 33;
    public static final int GETPROPNOWARN = 34;
    public static final int SETPROP = 35;
    public static final int GETELEM = 36;
    public static final int SETELEM = 37;
    public static final int CALL = 38;
    public static final int NAME = 39;
    public static final int NUMBER = 40;
    public static final int STRING = 41;
    public static final int NULL = 42;
    public static final int THIS = 43;
    public static final int FALSE = 44;
    public static final int TRUE = 45;
    public static final int SHEQ = 46;
    public static final int SHNE = 47;
    public static final int REGEXP = 48;
    public static final int BINDNAME = 49;
    public static final int THROW = 50;
    public static final int RETHROW = 51;
    public static final int IN = 52;
    public static final int INSTANCEOF = 53;
    public static final int LOCAL_LOAD = 54;
    public static final int GETVAR = 55;
    public static final int SETVAR = 56;
    public static final int CATCH_SCOPE = 57;
    public static final int ENUM_INIT_KEYS = 58;
    public static final int ENUM_INIT_VALUES = 59;
    public static final int ENUM_INIT_ARRAY = 60;
    public static final int ENUM_NEXT = 61;
    public static final int ENUM_ID = 62;
    public static final int THISFN = 63;
    public static final int RETURN_RESULT = 64;
    public static final int ARRAYLIT = 65;
    public static final int OBJECTLIT = 66;
    public static final int GET_REF = 67;
    public static final int SET_REF = 68;
    public static final int DEL_REF = 69;
    public static final int REF_CALL = 70;
    public static final int REF_SPECIAL = 71;
    public static final int YIELD = 72;
    public static final int STRICT_SETNAME = 73;
    public static final int DEFAULTNAMESPACE = 74;
    public static final int ESCXMLATTR = 75;
    public static final int ESCXMLTEXT = 76;
    public static final int REF_MEMBER = 77;
    public static final int REF_NS_MEMBER = 78;
    public static final int REF_NAME = 79;
    public static final int REF_NS_NAME = 80;
    public static final int LAST_BYTECODE_TOKEN = 80;
    public static final int TRY = 81;
    public static final int SEMI = 82;
    public static final int LB = 83;
    public static final int RB = 84;
    public static final int LC = 85;
    public static final int RC = 86;
    public static final int LP = 87;
    public static final int RP = 88;
    public static final int COMMA = 89;
    public static final int ASSIGN = 90;
    public static final int ASSIGN_BITOR = 91;
    public static final int ASSIGN_BITXOR = 92;
    public static final int ASSIGN_BITAND = 93;
    public static final int ASSIGN_LSH = 94;
    public static final int ASSIGN_RSH = 95;
    public static final int ASSIGN_URSH = 96;
    public static final int ASSIGN_ADD = 97;
    public static final int ASSIGN_SUB = 98;
    public static final int ASSIGN_MUL = 99;
    public static final int ASSIGN_DIV = 100;
    public static final int ASSIGN_MOD = 101;
    public static final int FIRST_ASSIGN = 90;
    public static final int LAST_ASSIGN = 101;
    public static final int HOOK = 102;
    public static final int COLON = 103;
    public static final int OR = 104;
    public static final int AND = 105;
    public static final int INC = 106;
    public static final int DEC = 107;
    public static final int DOT = 108;
    public static final int FUNCTION = 109;
    public static final int EXPORT = 110;
    public static final int IMPORT = 111;
    public static final int IF = 112;
    public static final int ELSE = 113;
    public static final int SWITCH = 114;
    public static final int CASE = 115;
    public static final int DEFAULT = 116;
    public static final int WHILE = 117;
    public static final int DO = 118;
    public static final int FOR = 119;
    public static final int BREAK = 120;
    public static final int CONTINUE = 121;
    public static final int VAR = 122;
    public static final int WITH = 123;
    public static final int CATCH = 124;
    public static final int FINALLY = 125;
    public static final int VOID = 126;
    public static final int RESERVED = 127;
    public static final int EMPTY = 128;
    public static final int BLOCK = 129;
    public static final int LABEL = 130;
    public static final int TARGET = 131;
    public static final int LOOP = 132;
    public static final int EXPR_VOID = 133;
    public static final int EXPR_RESULT = 134;
    public static final int JSR = 135;
    public static final int SCRIPT = 136;
    public static final int TYPEOFNAME = 137;
    public static final int USE_STACK = 138;
    public static final int SETPROP_OP = 139;
    public static final int SETELEM_OP = 140;
    public static final int LOCAL_BLOCK = 141;
    public static final int SET_REF_OP = 142;
    public static final int DOTDOT = 143;
    public static final int COLONCOLON = 144;
    public static final int XML = 145;
    public static final int DOTQUERY = 146;
    public static final int XMLATTR = 147;
    public static final int XMLEND = 148;
    public static final int TO_OBJECT = 149;
    public static final int TO_DOUBLE = 150;
    public static final int GET = 151;
    public static final int SET = 152;
    public static final int LET = 153;
    public static final int CONST = 154;
    public static final int SETCONST = 155;
    public static final int SETCONSTVAR = 156;
    public static final int ARRAYCOMP = 157;
    public static final int LETEXPR = 158;
    public static final int WITHEXPR = 159;
    public static final int DEBUGGER = 160;
    public static final int COMMENT = 161;
    public static final int GENEXPR = 162;
    public static final int LAST_TOKEN = 163;

    public static String name(int token) {
        return String.valueOf(token);
    }

    public static String typeToName(int token) {
        switch (token) {
            case -1: {
                return "ERROR";
            }
            case 0: {
                return "EOF";
            }
            case 1: {
                return "EOL";
            }
            case 2: {
                return "ENTERWITH";
            }
            case 3: {
                return "LEAVEWITH";
            }
            case 4: {
                return "RETURN";
            }
            case 5: {
                return "GOTO";
            }
            case 6: {
                return "IFEQ";
            }
            case 7: {
                return "IFNE";
            }
            case 8: {
                return "SETNAME";
            }
            case 9: {
                return "BITOR";
            }
            case 10: {
                return "BITXOR";
            }
            case 11: {
                return "BITAND";
            }
            case 12: {
                return "EQ";
            }
            case 13: {
                return "NE";
            }
            case 14: {
                return "LT";
            }
            case 15: {
                return "LE";
            }
            case 16: {
                return "GT";
            }
            case 17: {
                return "GE";
            }
            case 18: {
                return "LSH";
            }
            case 19: {
                return "RSH";
            }
            case 20: {
                return "URSH";
            }
            case 21: {
                return "ADD";
            }
            case 22: {
                return "SUB";
            }
            case 23: {
                return "MUL";
            }
            case 24: {
                return "DIV";
            }
            case 25: {
                return "MOD";
            }
            case 26: {
                return "NOT";
            }
            case 27: {
                return "BITNOT";
            }
            case 28: {
                return "POS";
            }
            case 29: {
                return "NEG";
            }
            case 30: {
                return "NEW";
            }
            case 31: {
                return "DELPROP";
            }
            case 32: {
                return "TYPEOF";
            }
            case 33: {
                return "GETPROP";
            }
            case 34: {
                return "GETPROPNOWARN";
            }
            case 35: {
                return "SETPROP";
            }
            case 36: {
                return "GETELEM";
            }
            case 37: {
                return "SETELEM";
            }
            case 38: {
                return "CALL";
            }
            case 39: {
                return "NAME";
            }
            case 40: {
                return "NUMBER";
            }
            case 41: {
                return "STRING";
            }
            case 42: {
                return "NULL";
            }
            case 43: {
                return "THIS";
            }
            case 44: {
                return "FALSE";
            }
            case 45: {
                return "TRUE";
            }
            case 46: {
                return "SHEQ";
            }
            case 47: {
                return "SHNE";
            }
            case 48: {
                return "REGEXP";
            }
            case 49: {
                return "BINDNAME";
            }
            case 50: {
                return "THROW";
            }
            case 51: {
                return "RETHROW";
            }
            case 52: {
                return "IN";
            }
            case 53: {
                return "INSTANCEOF";
            }
            case 54: {
                return "LOCAL_LOAD";
            }
            case 55: {
                return "GETVAR";
            }
            case 56: {
                return "SETVAR";
            }
            case 57: {
                return "CATCH_SCOPE";
            }
            case 58: {
                return "ENUM_INIT_KEYS";
            }
            case 59: {
                return "ENUM_INIT_VALUES";
            }
            case 60: {
                return "ENUM_INIT_ARRAY";
            }
            case 61: {
                return "ENUM_NEXT";
            }
            case 62: {
                return "ENUM_ID";
            }
            case 63: {
                return "THISFN";
            }
            case 64: {
                return "RETURN_RESULT";
            }
            case 65: {
                return "ARRAYLIT";
            }
            case 66: {
                return "OBJECTLIT";
            }
            case 67: {
                return "GET_REF";
            }
            case 68: {
                return "SET_REF";
            }
            case 69: {
                return "DEL_REF";
            }
            case 70: {
                return "REF_CALL";
            }
            case 71: {
                return "REF_SPECIAL";
            }
            case 74: {
                return "DEFAULTNAMESPACE";
            }
            case 76: {
                return "ESCXMLTEXT";
            }
            case 75: {
                return "ESCXMLATTR";
            }
            case 77: {
                return "REF_MEMBER";
            }
            case 78: {
                return "REF_NS_MEMBER";
            }
            case 79: {
                return "REF_NAME";
            }
            case 80: {
                return "REF_NS_NAME";
            }
            case 81: {
                return "TRY";
            }
            case 82: {
                return "SEMI";
            }
            case 83: {
                return "LB";
            }
            case 84: {
                return "RB";
            }
            case 85: {
                return "LC";
            }
            case 86: {
                return "RC";
            }
            case 87: {
                return "LP";
            }
            case 88: {
                return "RP";
            }
            case 89: {
                return "COMMA";
            }
            case 90: {
                return "ASSIGN";
            }
            case 91: {
                return "ASSIGN_BITOR";
            }
            case 92: {
                return "ASSIGN_BITXOR";
            }
            case 93: {
                return "ASSIGN_BITAND";
            }
            case 94: {
                return "ASSIGN_LSH";
            }
            case 95: {
                return "ASSIGN_RSH";
            }
            case 96: {
                return "ASSIGN_URSH";
            }
            case 97: {
                return "ASSIGN_ADD";
            }
            case 98: {
                return "ASSIGN_SUB";
            }
            case 99: {
                return "ASSIGN_MUL";
            }
            case 100: {
                return "ASSIGN_DIV";
            }
            case 101: {
                return "ASSIGN_MOD";
            }
            case 102: {
                return "HOOK";
            }
            case 103: {
                return "COLON";
            }
            case 104: {
                return "OR";
            }
            case 105: {
                return "AND";
            }
            case 106: {
                return "INC";
            }
            case 107: {
                return "DEC";
            }
            case 108: {
                return "DOT";
            }
            case 109: {
                return "FUNCTION";
            }
            case 110: {
                return "EXPORT";
            }
            case 111: {
                return "IMPORT";
            }
            case 112: {
                return "IF";
            }
            case 113: {
                return "ELSE";
            }
            case 114: {
                return "SWITCH";
            }
            case 115: {
                return "CASE";
            }
            case 116: {
                return "DEFAULT";
            }
            case 117: {
                return "WHILE";
            }
            case 118: {
                return "DO";
            }
            case 119: {
                return "FOR";
            }
            case 120: {
                return "BREAK";
            }
            case 121: {
                return "CONTINUE";
            }
            case 122: {
                return "VAR";
            }
            case 123: {
                return "WITH";
            }
            case 124: {
                return "CATCH";
            }
            case 125: {
                return "FINALLY";
            }
            case 126: {
                return "VOID";
            }
            case 127: {
                return "RESERVED";
            }
            case 128: {
                return "EMPTY";
            }
            case 129: {
                return "BLOCK";
            }
            case 130: {
                return "LABEL";
            }
            case 131: {
                return "TARGET";
            }
            case 132: {
                return "LOOP";
            }
            case 133: {
                return "EXPR_VOID";
            }
            case 134: {
                return "EXPR_RESULT";
            }
            case 135: {
                return "JSR";
            }
            case 136: {
                return "SCRIPT";
            }
            case 137: {
                return "TYPEOFNAME";
            }
            case 138: {
                return "USE_STACK";
            }
            case 139: {
                return "SETPROP_OP";
            }
            case 140: {
                return "SETELEM_OP";
            }
            case 141: {
                return "LOCAL_BLOCK";
            }
            case 142: {
                return "SET_REF_OP";
            }
            case 143: {
                return "DOTDOT";
            }
            case 144: {
                return "COLONCOLON";
            }
            case 145: {
                return "XML";
            }
            case 146: {
                return "DOTQUERY";
            }
            case 147: {
                return "XMLATTR";
            }
            case 148: {
                return "XMLEND";
            }
            case 149: {
                return "TO_OBJECT";
            }
            case 150: {
                return "TO_DOUBLE";
            }
            case 151: {
                return "GET";
            }
            case 152: {
                return "SET";
            }
            case 153: {
                return "LET";
            }
            case 72: {
                return "YIELD";
            }
            case 154: {
                return "CONST";
            }
            case 155: {
                return "SETCONST";
            }
            case 157: {
                return "ARRAYCOMP";
            }
            case 159: {
                return "WITHEXPR";
            }
            case 158: {
                return "LETEXPR";
            }
            case 160: {
                return "DEBUGGER";
            }
            case 161: {
                return "COMMENT";
            }
            case 162: {
                return "GENEXPR";
            }
        }
        throw new IllegalStateException(String.valueOf(token));
    }

    public static String keywordToName(int token) {
        switch (token) {
            case 120: {
                return "break";
            }
            case 115: {
                return "case";
            }
            case 121: {
                return "continue";
            }
            case 116: {
                return "default";
            }
            case 31: {
                return "delete";
            }
            case 118: {
                return "do";
            }
            case 113: {
                return "else";
            }
            case 44: {
                return "false";
            }
            case 119: {
                return "for";
            }
            case 109: {
                return "function";
            }
            case 112: {
                return "if";
            }
            case 52: {
                return "in";
            }
            case 153: {
                return "let";
            }
            case 30: {
                return "new";
            }
            case 42: {
                return "null";
            }
            case 4: {
                return "return";
            }
            case 114: {
                return "switch";
            }
            case 43: {
                return "this";
            }
            case 45: {
                return "true";
            }
            case 32: {
                return "typeof";
            }
            case 122: {
                return "var";
            }
            case 126: {
                return "void";
            }
            case 117: {
                return "while";
            }
            case 123: {
                return "with";
            }
            case 72: {
                return "yield";
            }
            case 124: {
                return "catch";
            }
            case 154: {
                return "const";
            }
            case 160: {
                return "debugger";
            }
            case 125: {
                return "finally";
            }
            case 53: {
                return "instanceof";
            }
            case 50: {
                return "throw";
            }
            case 81: {
                return "try";
            }
        }
        return null;
    }

    public static boolean isValidToken(int code) {
        return code >= -1 && code <= 163;
    }

    public static enum CommentType {
        LINE,
        BLOCK_COMMENT,
        JSDOC,
        HTML;

    }
}

