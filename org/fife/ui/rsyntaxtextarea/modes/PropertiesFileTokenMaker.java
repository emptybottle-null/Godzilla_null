/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.AbstractJFlexTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

public class PropertiesFileTokenMaker
extends AbstractJFlexTokenMaker {
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int VALUE = 1;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0003\u0001\u0002\u0015\u0000\u0001\u0003\u0001\u0004\u0001\u0000\u0001\u0004\u0003\u0000\u0001\u0005\u0012\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u001e\u0000\u0001\u0006\u001e\u0000\u0001\u0007\u0001\u0000\u0001\b\uff82\u0000";
    private static final char[] ZZ_CMAP = PropertiesFileTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0003\u0001\u0002\u0015\u0000\u0001\u0003\u0001\u0004\u0001\u0000\u0001\u0004\u0003\u0000\u0001\u0005\u0012\u0000\u0001\u0001\u0002\u0000\u0001\u0001\u001e\u0000\u0001\u0006\u001e\u0000\u0001\u0007\u0001\u0000\u0001\b\uff82\u0000");
    private static final int[] ZZ_ACTION = PropertiesFileTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0001\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\u0006\u0001\u0005\u0001\b";
    private static final int[] ZZ_ROWMAP = PropertiesFileTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\t\u0000\u0012\u0000\u001b\u0000$\u0000-\u00006\u0000?\u0000H\u0000Q\u0000\u001b\u0000\u001b\u0000\u001b";
    private static final int[] ZZ_TRANS = PropertiesFileTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0003\u0001\u0004\u0001\u0000\u0001\u0005\u0001\u0006\u0004\u0003\u0005\u0007\u0001\b\u0001\t\u0001\n\u0001\u0007\u0001\u0003\u0004\u0000\u0004\u0003\f\u0000\u0001\u0005\u0005\u0000\u0002\u0006\u0001\u0000\u0006\u0006\u0005\u0007\u0003\u0000\u0001\u0007\u0005\b\u0001\u000b\u0003\b\u0002\f\u0001\u0000\u0006\f\b\n\u0001\r";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = PropertiesFileTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0001\u0001\u0000\u0001\u0001\u0001\t\u0006\u0001\u0003\t";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer = new char[16384];
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;

    private static int[] zzUnpackAction() {
        int[] result = new int[13];
        int offset = 0;
        offset = PropertiesFileTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    private static int[] zzUnpackRowMap() {
        int[] result = new int[13];
        int offset = 0;
        offset = PropertiesFileTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int high = packed.charAt(i++) << 16;
            result[j++] = high | packed.charAt(i++);
        }
        return j;
    }

    private static int[] zzUnpackTrans() {
        int[] result = new int[90];
        int offset = 0;
        offset = PropertiesFileTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackTrans(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            int value = packed.charAt(i++);
            do {
                result[j++] = --value;
            } while (--count > 0);
        }
        return j;
    }

    private static int[] zzUnpackAttribute() {
        int[] result = new int[13];
        int offset = 0;
        offset = PropertiesFileTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int i = 0;
        int j = offset;
        int l = packed.length();
        while (i < l) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                result[j++] = value;
            } while (--count > 0);
        }
        return j;
    }

    public PropertiesFileTokenMaker() {
    }

    private void addToken(int tokenType) {
        this.addToken(this.zzStartRead, this.zzMarkedPos - 1, tokenType);
    }

    private void addToken(int start, int end, int tokenType) {
        int so = start + this.offsetShift;
        this.addToken(this.zzBuffer, start, end, tokenType, so);
    }

    @Override
    public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
        super.addToken(array, start, end, tokenType, startOffset);
        this.zzStartRead = this.zzMarkedPos;
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"#", null};
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        int state = 0;
        switch (initialTokenType) {
            case 13: {
                state = 1;
                this.start = text.offset;
                break;
            }
            default: {
                state = 0;
            }
        }
        this.s = text;
        try {
            this.yyreset(this.zzReader);
            this.yybegin(state);
            return this.yylex();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return new TokenImpl();
        }
    }

    private boolean zzRefill() {
        return this.zzCurrentPos >= this.s.offset + this.s.count;
    }

    public final void yyreset(Reader reader) {
        this.zzBuffer = this.s.array;
        this.zzStartRead = this.s.offset;
        this.zzEndRead = this.zzStartRead + this.s.count - 1;
        this.zzCurrentPos = this.zzMarkedPos = this.s.offset;
        this.zzLexicalState = 0;
        this.zzReader = reader;
        this.zzAtEOF = false;
    }

    public PropertiesFileTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public PropertiesFileTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 42) {
            int count = packed.charAt(i++);
            char value = packed.charAt(i++);
            do {
                map[j++] = value;
            } while (--count > 0);
        }
        return map;
    }

    public final void yyclose() throws IOException {
        this.zzAtEOF = true;
        this.zzEndRead = this.zzStartRead;
        if (this.zzReader != null) {
            this.zzReader.close();
        }
    }

    public final int yystate() {
        return this.zzLexicalState;
    }

    @Override
    public final void yybegin(int newState) {
        this.zzLexicalState = newState;
    }

    public final String yytext() {
        return new String(this.zzBuffer, this.zzStartRead, this.zzMarkedPos - this.zzStartRead);
    }

    public final char yycharat(int pos) {
        return this.zzBuffer[this.zzStartRead + pos];
    }

    public final int yylength() {
        return this.zzMarkedPos - this.zzStartRead;
    }

    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[0];
        }
        throw new Error(message);
    }

    public void yypushback(int number) {
        if (number > this.yylength()) {
            this.zzScanError(2);
        }
        this.zzMarkedPos -= number;
    }

    public Token yylex() throws IOException {
        int zzEndReadL = this.zzEndRead;
        char[] zzBufferL = this.zzBuffer;
        char[] zzCMapL = ZZ_CMAP;
        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;
        block23: while (true) {
            int zzInput;
            int zzMarkedPosL = this.zzMarkedPos;
            int zzAction = -1;
            this.zzCurrentPos = this.zzStartRead = zzMarkedPosL;
            int zzCurrentPosL = this.zzStartRead;
            this.zzState = this.zzLexicalState;
            while (true) {
                if (zzCurrentPosL < zzEndReadL) {
                    zzInput = zzBufferL[zzCurrentPosL++];
                } else {
                    if (this.zzAtEOF) {
                        zzInput = -1;
                        break;
                    }
                    this.zzCurrentPos = zzCurrentPosL;
                    this.zzMarkedPos = zzMarkedPosL;
                    boolean eof = this.zzRefill();
                    zzCurrentPosL = this.zzCurrentPos;
                    zzMarkedPosL = this.zzMarkedPos;
                    zzBufferL = this.zzBuffer;
                    zzEndReadL = this.zzEndRead;
                    if (eof) {
                        zzInput = -1;
                        break;
                    }
                    zzInput = zzBufferL[zzCurrentPosL++];
                }
                int zzNext = zzTransL[zzRowMapL[this.zzState] + zzCMapL[zzInput]];
                if (zzNext == -1) break;
                this.zzState = zzNext;
                int zzAttributes = zzAttrL[this.zzState];
                if ((zzAttributes & 1) != 1) continue;
                zzAction = this.zzState;
                zzMarkedPosL = zzCurrentPosL;
                if ((zzAttributes & 8) == 8) break;
            }
            this.zzMarkedPos = zzMarkedPosL;
            switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
                case 7: {
                    this.addToken(this.start, this.zzEndRead, 13);
                    return this.firstToken;
                }
                case 9: {
                    continue block23;
                }
                case 2: {
                    this.start = this.zzMarkedPos;
                    this.addToken(23);
                    this.yybegin(1);
                }
                case 10: {
                    continue block23;
                }
                case 8: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 13);
                    this.addToken(temp, this.zzMarkedPos - 1, 17);
                    this.start = this.zzMarkedPos;
                }
                case 11: {
                    continue block23;
                }
                case 3: {
                    this.addToken(21);
                }
                case 12: {
                    continue block23;
                }
                case 6: {
                    this.addToken(this.start, this.zzMarkedPos - 1, 13);
                    this.start = this.zzMarkedPos;
                }
                case 13: {
                    continue block23;
                }
                case 1: {
                    this.addToken(6);
                }
                case 14: {
                    continue block23;
                }
                case 5: 
                case 15: {
                    continue block23;
                }
                case 4: {
                    this.addToken(1);
                }
                case 16: {
                    continue block23;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 14: {
                        continue block23;
                    }
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 13);
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 15: {
                        continue block23;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

