/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

abstract class Icode {
    static final int Icode_DELNAME = 0;
    static final int Icode_DUP = -1;
    static final int Icode_DUP2 = -2;
    static final int Icode_SWAP = -3;
    static final int Icode_POP = -4;
    static final int Icode_POP_RESULT = -5;
    static final int Icode_IFEQ_POP = -6;
    static final int Icode_VAR_INC_DEC = -7;
    static final int Icode_NAME_INC_DEC = -8;
    static final int Icode_PROP_INC_DEC = -9;
    static final int Icode_ELEM_INC_DEC = -10;
    static final int Icode_REF_INC_DEC = -11;
    static final int Icode_SCOPE_LOAD = -12;
    static final int Icode_SCOPE_SAVE = -13;
    static final int Icode_TYPEOFNAME = -14;
    static final int Icode_NAME_AND_THIS = -15;
    static final int Icode_PROP_AND_THIS = -16;
    static final int Icode_ELEM_AND_THIS = -17;
    static final int Icode_VALUE_AND_THIS = -18;
    static final int Icode_CLOSURE_EXPR = -19;
    static final int Icode_CLOSURE_STMT = -20;
    static final int Icode_CALLSPECIAL = -21;
    static final int Icode_RETUNDEF = -22;
    static final int Icode_GOSUB = -23;
    static final int Icode_STARTSUB = -24;
    static final int Icode_RETSUB = -25;
    static final int Icode_LINE = -26;
    static final int Icode_SHORTNUMBER = -27;
    static final int Icode_INTNUMBER = -28;
    static final int Icode_LITERAL_NEW = -29;
    static final int Icode_LITERAL_SET = -30;
    static final int Icode_SPARE_ARRAYLIT = -31;
    static final int Icode_REG_IND_C0 = -32;
    static final int Icode_REG_IND_C1 = -33;
    static final int Icode_REG_IND_C2 = -34;
    static final int Icode_REG_IND_C3 = -35;
    static final int Icode_REG_IND_C4 = -36;
    static final int Icode_REG_IND_C5 = -37;
    static final int Icode_REG_IND1 = -38;
    static final int Icode_REG_IND2 = -39;
    static final int Icode_REG_IND4 = -40;
    static final int Icode_REG_STR_C0 = -41;
    static final int Icode_REG_STR_C1 = -42;
    static final int Icode_REG_STR_C2 = -43;
    static final int Icode_REG_STR_C3 = -44;
    static final int Icode_REG_STR1 = -45;
    static final int Icode_REG_STR2 = -46;
    static final int Icode_REG_STR4 = -47;
    static final int Icode_GETVAR1 = -48;
    static final int Icode_SETVAR1 = -49;
    static final int Icode_UNDEF = -50;
    static final int Icode_ZERO = -51;
    static final int Icode_ONE = -52;
    static final int Icode_ENTERDQ = -53;
    static final int Icode_LEAVEDQ = -54;
    static final int Icode_TAIL_CALL = -55;
    static final int Icode_LOCAL_CLEAR = -56;
    static final int Icode_LITERAL_GETTER = -57;
    static final int Icode_LITERAL_SETTER = -58;
    static final int Icode_SETCONST = -59;
    static final int Icode_SETCONSTVAR = -60;
    static final int Icode_SETCONSTVAR1 = -61;
    static final int Icode_GENERATOR = -62;
    static final int Icode_GENERATOR_END = -63;
    static final int Icode_DEBUGGER = -64;
    static final int MIN_ICODE = -64;

    Icode() {
    }

    static String bytecodeName(int bytecode) {
        if (!Icode.validBytecode(bytecode)) {
            throw new IllegalArgumentException(String.valueOf(bytecode));
        }
        return String.valueOf(bytecode);
    }

    static boolean validIcode(int icode) {
        return -64 <= icode && icode <= 0;
    }

    static boolean validTokenCode(int token) {
        return 2 <= token && token <= 80;
    }

    static boolean validBytecode(int bytecode) {
        return Icode.validIcode(bytecode) || Icode.validTokenCode(bytecode);
    }
}

