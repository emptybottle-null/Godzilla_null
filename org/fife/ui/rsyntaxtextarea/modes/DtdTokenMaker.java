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

public class DtdTokenMaker
extends AbstractJFlexTokenMaker {
    public static final int YYEOF = -1;
    public static final int INTAG_START = 2;
    public static final int INTAG_ELEMENT = 3;
    public static final int YYINITIAL = 0;
    public static final int INTAG_ATTLIST = 4;
    public static final int COMMENT = 1;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0001\u0001\u0002\u0001\u0000\u0001\u0001\u0013\u0000\u0001\u0001\u0001\u0014\u0001\u0003\u0001!\u0001\u0007\u0001\u0005\u0001\u0005\u0001\u0004\u0005\u0005\u0001\u0015\u0001\u0012\u0001\u0006\n\u0007\u0001\u0010\u0001\u0005\u0001\u0013\u0001\u0005\u0001\u0016\u0002\u0005\u0001\u001c\u0001\u0007\u0001\u001f\u0001 \u0001\u0017\u0003\u0007\u0001\u001d\u0002\u0007\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u0007\u0001\"\u0001$\u0001#\u0001\u001e\u0001\u001b\u0001%\u0005\u0007\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0000\u0004\u0007\u0001\u000f\u0001\f\u0001\u0007\u0001\b\u0001\r\u0002\u0007\u0001\u000e\u0003\u0007\u0001\n\u0002\u0007\u0001\u000b\u0001\t\u0002\u0007\u0001\u0011\u0003\u0007\u0003\u0000\u0001\u0005\uff81\u0000";
    private static final char[] ZZ_CMAP = DtdTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0001\u0001\u0002\u0001\u0000\u0001\u0001\u0013\u0000\u0001\u0001\u0001\u0014\u0001\u0003\u0001!\u0001\u0007\u0001\u0005\u0001\u0005\u0001\u0004\u0005\u0005\u0001\u0015\u0001\u0012\u0001\u0006\n\u0007\u0001\u0010\u0001\u0005\u0001\u0013\u0001\u0005\u0001\u0016\u0002\u0005\u0001\u001c\u0001\u0007\u0001\u001f\u0001 \u0001\u0017\u0003\u0007\u0001\u001d\u0002\u0007\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u0007\u0001\"\u0001$\u0001#\u0001\u001e\u0001\u001b\u0001%\u0005\u0007\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0000\u0004\u0007\u0001\u000f\u0001\f\u0001\u0007\u0001\b\u0001\r\u0002\u0007\u0001\u000e\u0003\u0007\u0001\n\u0002\u0007\u0001\u000b\u0001\t\u0002\u0007\u0001\u0011\u0003\u0007\u0003\u0000\u0001\u0005\uff81\u0000");
    private static final int[] ZZ_ACTION = DtdTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0005\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0004\u0004\u0001\u0003\u0001\u0006\u0002\u0003\u0002\u0007\u0002\b\u0002\u0007\u0001\t\u0005\u0000\u0002\u0003\u0001\b\u0003\u0007\u0005\u0000\u0001\n\u0002\u0003\u0003\u0007\u0001\u000b\u0002\u0000\u0001\f\u0002\u0003\u0003\u0007\u0002\u0000\u0002\u0003\u0001\r\u0002\u0007\u0002\u0003\u0002\u0007\u0001\u000e\u0001\u000f\u0001\u0007";
    private static final int[] ZZ_ROWMAP = DtdTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000&\u0000L\u0000r\u0000\u0098\u0000\u00be\u0000\u00e4\u0000\u010a\u0000\u0130\u0000\u0156\u0000\u017c\u0000\u01a2\u0000\u01c8\u0000\u01ee\u0000\u0214\u0000\u0156\u0000\u023a\u0000\u0260\u0000\u0286\u0000\u02ac\u0000\u02d2\u0000\u02f8\u0000\u031e\u0000\u0344\u0000\u036a\u0000\u0390\u0000\u03b6\u0000\u03dc\u0000\u0402\u0000\u0428\u0000\u044e\u0000\u0474\u0000\u0156\u0000\u049a\u0000\u04c0\u0000\u04e6\u0000\u050c\u0000\u0532\u0000\u0558\u0000\u057e\u0000\u05a4\u0000\u0156\u0000\u05ca\u0000\u05f0\u0000\u0616\u0000\u063c\u0000\u0662\u0000\u0156\u0000\u0688\u0000\u06ae\u0000\u06d4\u0000\u06fa\u0000\u0720\u0000\u0746\u0000\u076c\u0000\u0792\u0000\u07b8\u0000\u06d4\u0000\u07de\u0000\u0804\u0000\u02ac\u0000\u082a\u0000\u0850\u0000\u0876\u0000\u089c\u0000\u08c2\u0000\u08e8\u0000\u0214\u0000\u0214\u0000\u090e";
    private static final int[] ZZ_TRANS = DtdTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0006\u0001\u0007\u0011\u0006\u0001\b\u0012\u0006\u0002\t\u0001\n\u0005\t\u0001\u000b\u0003\t\u0001\f\u0004\t\u0001\r\u0003\t\u0001\u000e\u0010\t\u0001\u000f\u0001\u0007\u0014\u000f\u0001\u0010\u0001\u0011\u0004\u000f\u0001\u0012\t\u000f\u0001\u0013\u0001\u0007\u0014\u0013\u0001\u0010\u000f\u0013\u0001\u0014\u0001\u0007\u0001\u0014\u0001\u0015\u0001\u0016\u0011\u0014\u0001\u0010\b\u0014\u0001\u0017\u0001\u0014\u0001\u0018\u0004\u0014\u0001\u0006\u0001\u0000\u0011\u0006\u0001\u0000\u0012\u0006\u0001\u0000\u0001\u00078\u0000\u0001\u0019\u0011\u0000\u0002\t\u0001\u0000\u0005\t\u0001\u0000\u0003\t\u0001\u0000\u0004\t\u0001\u0000\u0003\t\u0001\u0000\u0010\t/\u0000\u0001\u001a%\u0000\u0001\u001b\u0003\u0000\u0001\u001c)\u0000\u0001\u001d)\u0000\u0001\u001e\u0010\u0000\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0010\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0001\u000f\u0001\u001f\u000e\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0004\u000f\u0001 \n\u000f\u0001\u0013\u0001\u0000\u0014\u0013\u0001\u0000\u000f\u0013\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u000f\u0014\u0003\u0015\u0001!\"\u0015\u0004\u0016\u0001!!\u0016\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\t\u0014\u0001\"\u0006\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0006\u0014\u0001#\u0005\u0014\u0001$\u0002\u0014\u0015\u0000\u0001%\u0019\u0000\u0001&&\u0000\u0001')\u0000\u0001((\u0000\u0001)*\u0000\u0001*\u000f\u0000\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0001+\u000f\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0004\u000f\u0001,\n\u000f\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0005\u0014\u0001-\n\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0002\u0014\u0001.\r\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0001/\u000e\u0014\u0015\u0000\u00010\u001a\u0000\u00011+\u0000\u00012$\u0000\u0001'(\u0000\u00013\u0013\u0000\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0002\u000f\u00014\r\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0001\u000f\u00015\r\u000f\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0004\u0014\u00016\u000b\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u000b\u0014\u00017\u0004\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\r\u0014\u00018\u0001\u0014\u000b\u0000\u0001'\u0004\u0000\u00012\u001b\u0000\u00019#\u0000\u0002:\n3\u0001:\u00013\u0001:\u0001\u0000\u0002:\u0001\u0000\n3\u0001:\u00043\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0001;\u000f\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0006\u000f\u0001<\b\u000f\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0005\u0014\u0001=\n\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0001\u0014\u0001>\u000e\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u000e\u0014\u0001?\u0006\u0000\u00013\u001f\u0000\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0003\u000f\u0001@\f\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0007\u000f\u0001A\u0007\u000f\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0006\u0014\u0001B\t\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0006\u0014\u0001C\b\u0014\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0004\u000f\u0001D\u000b\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0004\u000f\u0001E\n\u000f\u0001\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\u0001F\u000f\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\f\u0014\u0001B\u0003\u0014\u0001\u0000\u0001\u0014\u0002\u0000\u0011\u0014\u0001\u0000\t\u0014\u0001=\u0005\u0014";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = DtdTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0005\u0000\u0004\u0001\u0001\t\u0005\u0001\u0001\t\t\u0001\u0005\u0000\u0002\u0001\u0001\t\u0003\u0001\u0005\u0000\u0001\t\u0005\u0001\u0001\t\u0002\u0000\u0006\u0001\u0002\u0000\f\u0001";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;
    public static final int INTERNAL_INTAG_START = -1;
    public static final int INTERNAL_INTAG_ELEMENT = -2;
    public static final int INTERNAL_INTAG_ATTLIST = -3;
    public static final int INTERNAL_IN_COMMENT = -2048;
    private int prevState;

    private static int[] zzUnpackAction() {
        int[] result = new int[70];
        int offset = 0;
        offset = DtdTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[70];
        int offset = 0;
        offset = DtdTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[2356];
        int offset = 0;
        offset = DtdTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[70];
        int offset = 0;
        offset = DtdTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public DtdTokenMaker() {
    }

    private void addEndToken(int tokenType) {
        this.addToken(this.zzMarkedPos, this.zzMarkedPos, tokenType);
    }

    private void addHyperlinkToken(int start, int end, int tokenType) {
        int so = start + this.offsetShift;
        this.addToken(this.zzBuffer, start, end, tokenType, so, true);
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
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return false;
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        this.prevState = 0;
        int state = 0;
        switch (initialTokenType) {
            case -1: {
                state = 2;
                break;
            }
            case -2: {
                state = 3;
                break;
            }
            case -3: {
                state = 4;
                break;
            }
            default: {
                if (initialTokenType < -1024) {
                    int main = -(-initialTokenType & 0xFFFFFF00);
                    switch (main) {
                        default: 
                    }
                    state = 1;
                    this.prevState = -initialTokenType & 0xFF;
                    break;
                }
                state = 0;
            }
        }
        this.start = text.offset;
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

    public DtdTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public DtdTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 138) {
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
        block43: while (true) {
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
                case 3: {
                    this.addToken(20);
                }
                case 16: {
                    continue block43;
                }
                case 2: {
                    this.addToken(21);
                }
                case 17: {
                    continue block43;
                }
                case 1: {
                    this.addToken(20);
                }
                case 18: {
                    continue block43;
                }
                case 12: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 29);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 29);
                    this.start = this.zzMarkedPos;
                }
                case 19: {
                    continue block43;
                }
                case 9: {
                    this.addToken(25);
                    this.yybegin(2);
                }
                case 20: {
                    continue block43;
                }
                case 6: {
                    this.addToken(25);
                    this.yybegin(0);
                }
                case 21: {
                    continue block43;
                }
                case 10: {
                    int temp = this.zzMarkedPos;
                    this.addToken(this.start, this.zzStartRead + 2, 29);
                    this.start = temp;
                    this.yybegin(this.prevState);
                }
                case 22: {
                    continue block43;
                }
                case 11: {
                    this.start = this.zzStartRead;
                    this.prevState = this.zzLexicalState;
                    this.yybegin(1);
                }
                case 23: {
                    continue block43;
                }
                case 7: {
                    this.addToken(27);
                }
                case 24: {
                    continue block43;
                }
                case 15: {
                    this.addToken(26);
                    this.yybegin(4);
                }
                case 25: {
                    continue block43;
                }
                case 14: {
                    this.addToken(26);
                    this.yybegin(3);
                }
                case 26: {
                    continue block43;
                }
                case 13: {
                    this.addToken(31);
                }
                case 27: {
                    continue block43;
                }
                case 4: 
                case 28: {
                    continue block43;
                }
                case 5: {
                    this.addToken(this.start, this.zzStartRead - 1, 29);
                    this.addEndToken(-2048 - this.prevState);
                    return this.firstToken;
                }
                case 29: {
                    continue block43;
                }
                case 8: {
                    this.addToken(28);
                }
                case 30: {
                    continue block43;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 2: {
                        this.addEndToken(-1);
                        return this.firstToken;
                    }
                    case 71: {
                        continue block43;
                    }
                    case 3: {
                        this.addEndToken(-2);
                        return this.firstToken;
                    }
                    case 72: {
                        continue block43;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 73: {
                        continue block43;
                    }
                    case 4: {
                        this.addEndToken(-3);
                        return this.firstToken;
                    }
                    case 74: {
                        continue block43;
                    }
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 29);
                        this.addEndToken(-2048 - this.prevState);
                        return this.firstToken;
                    }
                    case 75: {
                        continue block43;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

