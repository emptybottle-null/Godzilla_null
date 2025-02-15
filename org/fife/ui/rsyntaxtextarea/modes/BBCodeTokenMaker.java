/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;
import org.fife.ui.rsyntaxtextarea.modes.AbstractMarkupTokenMaker;

public class BBCodeTokenMaker
extends AbstractMarkupTokenMaker {
    public static final int YYEOF = -1;
    public static final int INTAG = 1;
    public static final int YYINITIAL = 0;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0001\u0001\u0002\u0001\u0000\u0001\u0001\u0013\u0000\u0001\u0001\u000e\u0000\u0001\u0017\r\u0000\u0001\u0018\u001d\u0000\u0001\u0003\u0001\u0000\u0001\u0004\u0004\u0000\u0001\u0005\u0001\u000b\u0001\u0016\u0001\n\u0001\u0000\u0001\u0013\u0001\u0000\u0001\u0006\u0002\u0000\u0001\r\u0001\u0012\u0001\u000f\u0001\f\u0001\u0000\u0001\u0011\u0001\u000e\u0001\b\u0001\u0010\u0001\u0007\u0001\u0015\u0002\u0000\u0001\u0014\u0001\t\uff85\u0000";
    private static final char[] ZZ_CMAP = BBCodeTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0001\u0001\u0002\u0001\u0000\u0001\u0001\u0013\u0000\u0001\u0001\u000e\u0000\u0001\u0017\r\u0000\u0001\u0018\u001d\u0000\u0001\u0003\u0001\u0000\u0001\u0004\u0004\u0000\u0001\u0005\u0001\u000b\u0001\u0016\u0001\n\u0001\u0000\u0001\u0013\u0001\u0000\u0001\u0006\u0002\u0000\u0001\r\u0001\u0012\u0001\u000f\u0001\f\u0001\u0000\u0001\u0011\u0001\u000e\u0001\b\u0001\u0010\u0001\u0007\u0001\u0015\u0002\u0000\u0001\u0014\u0001\t\uff85\u0000");
    private static final int[] ZZ_ACTION = BBCodeTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0002\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0004\b\u0006\u0005\u0001\t\u0001\n\u0001\u0004\u0013\u0005";
    private static final int[] ZZ_ROWMAP = BBCodeTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\u0019\u00002\u0000K\u0000d\u0000}\u0000\u0096\u0000d\u0000d\u0000\u0096\u0000\u00af\u0000\u00c8\u0000\u00e1\u0000\u00fa\u0000\u0113\u0000\u012c\u0000\u0145\u0000\u015e\u0000\u0177\u0000\u0190\u0000d\u0000d\u0000\u01a9\u0000\u01c2\u0000\u01db\u0000\u01f4\u0000\u020d\u0000\u0226\u0000\u023f\u0000\u0258\u0000\u0271\u0000\u028a\u0000\u02a3\u0000\u02bc\u0000\u02d5\u0000\u02ee\u0000\u0307\u0000\u0320\u0000\u0339\u0000\u0352\u0000\u036b";
    private static final int[] ZZ_TRANS = BBCodeTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0015\u0003\u0001\u0007\u0001\u0004\u0001\u0000\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0002\u0007\u0001\u000e\u0001\u000f\u0001\u0010\u0003\u0007\u0001\u0011\u0001\u0007\u0001\u0012\u0001\u0013\u0002\u0007\u0001\u0014\u0001\u0015\u0001\u0003\u0003\u0000\u0015\u0003\u0001\u0000\u0001\u0004G\u0000\u0001\u0016\u0001\u0000\u0001\u0007\u0004\u0000\u0012\u0007\u0002\u0000\u0001\u0007\u0004\u0000\r\u0007\u0001\u0017\u0004\u0007\u0002\u0000\u0001\u0007\u0004\u0000\b\u0007\u0001\n\u0001\u000f\b\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0001\u0007\u0001\u0018\u0010\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0005\u0007\u0001\u0019\u0001\u0007\u0001\u001a\n\u0007\u0002\u0000\u0001\u0007\u0004\u0000\b\u0007\u0001\n\t\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0001\u0007\u0001\n\u0010\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0002\u0007\u0001\u001b\u000f\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0010\u0007\u0001\u001c\u0001\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0007\u0007\u0001\u001d\n\u0007\u0006\u0000\u0001\t\u0014\u0000\u0001\u0007\u0004\u0000\u000e\u0007\u0001\n\u0003\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0004\u0007\u0001\u001e\r\u0007\u0002\u0000\u0001\u0007\u0004\u0000\n\u0007\u0001\u001f\u0007\u0007\u0002\u0000\u0001\u0007\u0004\u0000\b\u0007\u0001 \t\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0007\u0007\u0001!\n\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0001\u0007\u0001\"\u0010\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0002\u0007\u0001#\u000f\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0005\u0007\u0001\n\f\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u000b\u0007\u0001$\u0006\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0007\u0007\u0001%\n\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u000b\u0007\u0001\u001e\u0006\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0011\u0007\u0001&\u0002\u0000\u0001\u0007\u0004\u0000\u000b\u0007\u0001'\u0006\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0005\u0007\u0001%\f\u0007\u0002\u0000\u0001\u0007\u0004\u0000\t\u0007\u0001\n\b\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0005\u0007\u0001(\f\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0002\u0007\u0001)\u000f\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0007\u0007\u0001\n\n\u0007\u0002\u0000\u0001\u0007\u0004\u0000\u0001\u001e\u0011\u0007\u0002\u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = BBCodeTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0002\u0000\u0002\u0001\u0001\t\u0002\u0001\u0002\t\u000b\u0001\u0002\t\u0013\u0001";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;
    public static final int INTERNAL_INTAG = -1;
    private static boolean completeCloseTags = true;

    private static int[] zzUnpackAction() {
        int[] result = new int[41];
        int offset = 0;
        offset = BBCodeTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[41];
        int offset = 0;
        offset = BBCodeTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[900];
        int offset = 0;
        offset = BBCodeTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[41];
        int offset = 0;
        offset = BBCodeTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public BBCodeTokenMaker() {
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
    public boolean getCompleteCloseTags() {
        return completeCloseTags;
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return null;
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        int state = 0;
        switch (initialTokenType) {
            case -1: {
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

    public static void setCompleteCloseTags(boolean complete) {
        completeCloseTags = complete;
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

    public BBCodeTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public BBCodeTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 80) {
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
        block28: while (true) {
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
                case 11: {
                    continue block28;
                }
                case 9: {
                    this.addToken(25);
                }
                case 12: {
                    continue block28;
                }
                case 2: {
                    this.addToken(21);
                }
                case 13: {
                    continue block28;
                }
                case 10: {
                    this.addToken(23);
                }
                case 14: {
                    continue block28;
                }
                case 8: {
                    this.addToken(26);
                }
                case 15: {
                    continue block28;
                }
                case 4: {
                    this.addToken(25);
                    this.yybegin(1);
                }
                case 16: {
                    continue block28;
                }
                case 6: {
                    this.addToken(20);
                }
                case 17: {
                    continue block28;
                }
                case 5: {
                    this.addToken(27);
                }
                case 18: {
                    continue block28;
                }
                case 3: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 19: {
                    continue block28;
                }
                case 7: {
                    this.yybegin(0);
                    this.addToken(25);
                }
                case 20: {
                    continue block28;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 1: {
                        this.addToken(this.zzMarkedPos, this.zzMarkedPos, -1);
                        return this.firstToken;
                    }
                    case 42: {
                        continue block28;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 43: {
                        continue block28;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

