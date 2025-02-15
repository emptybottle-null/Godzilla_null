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

public class LatexTokenMaker
extends AbstractJFlexTokenMaker {
    public static final int YYEOF = -1;
    public static final int EOL_COMMENT = 1;
    public static final int YYINITIAL = 0;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0003\u0001\u001a\u0001\u0000\u0001\u0003\u0013\u0000\u0001\u0003\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0007\u0001\u0004\u0007\u0005\u0001\u0002\u0001\u0012\u0001\u0006\n\u0001\u0001\u0010\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0000\u0002\u0005\u001a\u0001\u0001\u0005\u0001\u0013\u0001\u0005\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0001\u0001\u0015\u0001\u0001\u0001\u0019\u0001\u000f\u0001\f\u0001\u0016\u0001\b\u0001\r\u0002\u0001\u0001\u000e\u0001\u0001\u0001\u0017\u0001\u0001\u0001\n\u0002\u0001\u0001\u000b\u0001\t\u0002\u0001\u0001\u0011\u0003\u0001\u0001\u0018\u0001\u0000\u0001\u0014\u0001\u0005\uff81\u0000";
    private static final char[] ZZ_CMAP = LatexTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0003\u0001\u001a\u0001\u0000\u0001\u0003\u0013\u0000\u0001\u0003\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0007\u0001\u0004\u0007\u0005\u0001\u0002\u0001\u0012\u0001\u0006\n\u0001\u0001\u0010\u0001\u0005\u0001\u0000\u0001\u0005\u0001\u0000\u0002\u0005\u001a\u0001\u0001\u0005\u0001\u0013\u0001\u0005\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0001\u0001\u0015\u0001\u0001\u0001\u0019\u0001\u000f\u0001\f\u0001\u0016\u0001\b\u0001\r\u0002\u0001\u0001\u000e\u0001\u0001\u0001\u0017\u0001\u0001\u0001\n\u0002\u0001\u0001\u000b\u0001\t\u0002\u0001\u0001\u0011\u0003\u0001\u0001\u0018\u0001\u0000\u0001\u0014\u0001\u0005\uff81\u0000");
    private static final int[] ZZ_ACTION = LatexTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0001\u0001\u0004\u0001\u0005\u0004\u0006\u0001\u0007\u0001\b\u0001\t\u0002\b\u0004\u0000\u0002\b\u0004\u0000\u0002\b\u0002\u0000\u0001\n\u0001\u0000\u0001\b\u0003\u0000\u0001\b\u0001\u000b\u0002\u0000\u0001\f";
    private static final int[] ZZ_ROWMAP = LatexTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\u001b\u00006\u0000Q\u00006\u00006\u0000l\u00006\u00006\u0000\u0087\u0000\u00a2\u0000\u00bd\u0000\u00d8\u00006\u0000\u00f3\u00006\u0000\u010e\u0000\u0129\u0000\u0144\u0000\u015f\u0000\u017a\u0000\u0195\u0000\u01b0\u0000\u01cb\u0000\u01e6\u0000\u0201\u0000\u021c\u0000\u0237\u0000\u0252\u0000\u026d\u0000\u0288\u0000\u02a3\u0000\u02be\u0000\u02d9\u0000\u02f4\u0000\u030f\u0000\u02be\u0000\u032a\u0000\u0345\u00006\u0000\u0360\u0000\u037b\u00006";
    private static final int[] ZZ_TRANS = LatexTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0003\u0002\u0004\u0001\u0005\u0001\u0006\u0003\u0003\b\u0004\u0001\u0003\u0001\u0004\u0001\u0003\u0001\u0007\u0001\b\u0003\u0004\u0001\b\u0001\u0004\u0001\t\b\n\u0001\u000b\u0003\n\u0001\f\u0004\n\u0001\r\b\n\u0001\u000e\u001c\u0000\u0002\u0004\u0005\u0000\b\u0004\u0001\u0000\u0001\u0004\u0003\u0000\u0003\u0004\u0001\u0000\u0001\u0004\u0002\u0000\u0002\u000f\u0001\u0000\u0001\u0010\u0003\u0000\u0007\u000f\u0001\u0011\u0001\u0000\u0001\u000f\u0003\u0000\u0001\u0012\u0002\u000f\u0001\u0000\u0001\u000f\u0001\u0000\b\n\u0001\u0000\u0003\n\u0001\u0000\u0004\n\u0001\u0000\b\n\n\u0000\u0001\u0013\u001a\u0000\u0001\u0014\u0003\u0000\u0001\u0015\u001e\u0000\u0001\u0016\n\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0003\u000f\u0001\u0000\u0001\u000f\u0002\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0002\u000f\u0001\u0017\u0001\u0000\u0001\u000f\u0002\u0000\u0002\u000f\u0005\u0000\u0007\u000f\u0001\u0018\u0001\u0000\u0001\u000f\u0003\u0000\u0003\u000f\u0001\u0000\u0001\u000f\n\u0000\u0001\u0019\u001b\u0000\u0001\u001a\u001e\u0000\u0001\u001b\u001d\u0000\u0001\u001c\n\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0003\u000f\u0001\u0000\u0001\u001d\u0002\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0001\u000f\u0001\u001e\u0001\u000f\u0001\u0000\u0001\u000f\u000b\u0000\u0001\u001f \u0000\u0001 \u0019\u0000\u0001\u001a\u001d\u0000\u0001!\t\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0003\u000f\u0001\"\u0001\u000f\u0002\u0000\u0002\u000f\u0005\u0000\u0005\u000f\u0001#\u0002\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0003\u000f\u0001\u0000\u0001\u000f\f\u0000\u0001\u001a\u0004\u0000\u0001 \u0010\u0000\u0001$\u0015\u0000\u0001!\u0001%\u0001\u0000\u0002%\n!\u0001%\u0001!\u0001%\u0002\u0000\u0003!\u0001\u0000\u0001!\u0002\u0000\u0002&\u0005\u0000\b&\u0001\u0000\u0001&\u0003\u0000\u0003&\u0001\u0000\u0001&\u0002\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0002\u000f\u0001'\u0001\u0000\u0001\u000f\u0007\u0000\u0001!\u0015\u0000\u0002&\u0005\u0000\b&\u0001\u0000\u0001&\u0002\u0000\u0001(\u0003&\u0001\u0000\u0001&\u0002\u0000\u0002\u000f\u0005\u0000\b\u000f\u0001\u0000\u0001\u000f\u0003\u0000\u0003\u000f\u0001)\u0001\u000f\u0002\u0000\u0002*\u0005\u0000\b*\u0001\u0000\u0001*\u0003\u0000\u0003*\u0001\u0000\u0001*\u0002\u0000\u0002*\u0005\u0000\b*\u0001\u0000\u0001*\u0002\u0000\u0001+\u0003*\u0001\u0000\u0001*\u0001\u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = LatexTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0002\u0000\u0001\t\u0001\u0001\u0002\t\u0001\u0001\u0002\t\u0004\u0001\u0001\t\u0001\u0001\u0001\t\u0002\u0001\u0004\u0000\u0002\u0001\u0004\u0000\u0002\u0001\u0002\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0003\u0000\u0001\u0001\u0001\t\u0002\u0000\u0001\t";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;

    private static int[] zzUnpackAction() {
        int[] result = new int[43];
        int offset = 0;
        offset = LatexTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[43];
        int offset = 0;
        offset = LatexTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[918];
        int offset = 0;
        offset = LatexTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[43];
        int offset = 0;
        offset = LatexTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public LatexTokenMaker() {
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
        this.addToken(this.zzBuffer, start, end, tokenType, so, false);
    }

    @Override
    public void addToken(char[] array, int start, int end, int tokenType, int startOffset, boolean hyperlink) {
        super.addToken(array, start, end, tokenType, startOffset, hyperlink);
        this.zzStartRead = this.zzMarkedPos;
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"%", null};
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        int state = 0;
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

    public LatexTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public LatexTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 112) {
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
        block31: while (true) {
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
                case 1: {
                    this.addToken(20);
                }
                case 13: {
                    continue block31;
                }
                case 8: {
                    this.addToken(8);
                }
                case 14: {
                    continue block31;
                }
                case 2: {
                    this.addToken(21);
                }
                case 15: {
                    continue block31;
                }
                case 12: {
                    int temp = this.zzStartRead;
                    this.addToken(temp, temp + 5, 6);
                    this.addToken(temp + 6, temp + 6, 22);
                    this.addToken(temp + 7, this.zzMarkedPos - 2, 6);
                    this.addToken(this.zzMarkedPos - 1, this.zzMarkedPos - 1, 22);
                }
                case 16: {
                    continue block31;
                }
                case 10: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 1);
                    this.start = this.zzMarkedPos;
                }
                case 17: {
                    continue block31;
                }
                case 3: {
                    this.start = this.zzMarkedPos - 1;
                    this.yybegin(1);
                }
                case 18: {
                    continue block31;
                }
                case 11: {
                    int temp = this.zzStartRead;
                    this.addToken(temp, temp + 3, 6);
                    this.addToken(temp + 4, temp + 4, 22);
                    this.addToken(temp + 5, this.zzMarkedPos - 2, 6);
                    this.addToken(this.zzMarkedPos - 1, this.zzMarkedPos - 1, 22);
                }
                case 19: {
                    continue block31;
                }
                case 5: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 20: {
                    continue block31;
                }
                case 7: {
                    this.addToken(this.start, this.zzStartRead - 1, 1);
                    this.addNullToken();
                    return this.firstToken;
                }
                case 21: {
                    continue block31;
                }
                case 9: {
                    int temp = this.zzStartRead;
                    this.addToken(temp, temp, 22);
                    this.addToken(temp + 1, temp + 1, 20);
                }
                case 22: {
                    continue block31;
                }
                case 6: 
                case 23: {
                    continue block31;
                }
                case 4: {
                    this.addToken(22);
                }
                case 24: {
                    continue block31;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 1);
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 44: {
                        continue block31;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 45: {
                        continue block31;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

