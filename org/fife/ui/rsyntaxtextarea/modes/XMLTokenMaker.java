/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;
import org.fife.ui.rsyntaxtextarea.XmlOccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.modes.AbstractMarkupTokenMaker;

public class XMLTokenMaker
extends AbstractMarkupTokenMaker {
    public static final int YYEOF = -1;
    public static final int INTAG = 4;
    public static final int DTD = 3;
    public static final int INATTR_DOUBLE = 5;
    public static final int YYINITIAL = 0;
    public static final int COMMENT = 1;
    public static final int CDATA = 7;
    public static final int INATTR_SINGLE = 6;
    public static final int PI = 2;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0006\u0001\u0004\u0001\u0000\u0001\u0003\u0013\u0000\u0001\u0006\u0001\n\u0001\t\u0001\u0012\u0001\u0018\u0001\u0012\u0001\u0007\u0001\u0015\u0005\u0012\u0001\u0002\u0001\"\u0001\u0014\n\u0017\u0001\u0013\u0001\b\u0001\u0005\u0001$\u0001\u0011\u0001#\u0001\u0012\u0001\u000e\u0001\u0016\u0001\f\u0001\r\u000f\u0016\u0001\u000f\u0006\u0016\u0001\u000b\u0001\u0000\u0001\u0010\u0001\u0000\u0001\u0001\u0001\u0000\u0004\u0016\u0001 \u0001\u001d\u0001\u0016\u0001\u0019\u0001\u001e\u0002\u0016\u0001\u001f\u0003\u0016\u0001\u001b\u0002\u0016\u0001\u001c\u0001\u001a\u0002\u0016\u0001!\u0003\u0016\u0003\u0000\u0001\u0012\uff81\u0000";
    private static final char[] ZZ_CMAP = XMLTokenMaker.zzUnpackCMap("\t\u0000\u0001\u0006\u0001\u0004\u0001\u0000\u0001\u0003\u0013\u0000\u0001\u0006\u0001\n\u0001\t\u0001\u0012\u0001\u0018\u0001\u0012\u0001\u0007\u0001\u0015\u0005\u0012\u0001\u0002\u0001\"\u0001\u0014\n\u0017\u0001\u0013\u0001\b\u0001\u0005\u0001$\u0001\u0011\u0001#\u0001\u0012\u0001\u000e\u0001\u0016\u0001\f\u0001\r\u000f\u0016\u0001\u000f\u0006\u0016\u0001\u000b\u0001\u0000\u0001\u0010\u0001\u0000\u0001\u0001\u0001\u0000\u0004\u0016\u0001 \u0001\u001d\u0001\u0016\u0001\u0019\u0001\u001e\u0002\u0016\u0001\u001f\u0003\u0016\u0001\u001b\u0002\u0016\u0001\u001c\u0001\u001a\u0002\u0016\u0001!\u0003\u0016\u0003\u0000\u0001\u0012\uff81\u0000");
    private static final int[] ZZ_ACTION = XMLTokenMaker.zzUnpackAction();
    private static final String ZZ_ACTION_PACKED_0 = "\u0005\u0000\u0002\u0001\u0001\u0000\u0002\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0002\u0001\u0001\u0007\u0004\u0001\u0001\b\u0002\u0001\u0001\t\u0001\u0001\u0001\n\u0001\u000b\u0001\f\u0002\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0001\u0001\u0013\u0003\u0001\u0001\u0014\u0001\u0015\u0001\u0004\u0001\u0016\u0001\u0006\u0005\u0000\u0001\u0017\u0004\u0000\u0001\u0018\u0001\u0019\u0005\u0000\u0001\u001a\u0001\u001b\u0003\u0000\u0001\u001c\u0001\u001d\u0006\u0000\u0001\u001e";
    private static final int[] ZZ_ROWMAP = XMLTokenMaker.zzUnpackRowMap();
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000%\u0000J\u0000o\u0000\u0094\u0000\u00b9\u0000\u00de\u0000\u0103\u0000\u0128\u0000\u014d\u0000\u0172\u0000\u0197\u0000\u01bc\u0000\u01e1\u0000\u0206\u0000\u022b\u0000\u0172\u0000\u0250\u0000\u0275\u0000\u029a\u0000\u02bf\u0000\u0172\u0000\u02e4\u0000\u0309\u0000\u0172\u0000\u032e\u0000\u0172\u0000\u0172\u0000\u0172\u0000\u0353\u0000\u0378\u0000\u0172\u0000\u0172\u0000\u039d\u0000\u0172\u0000\u0172\u0000\u03c2\u0000\u0172\u0000\u03e7\u0000\u040c\u0000\u0431\u0000\u0456\u0000\u047b\u0000\u04a0\u0000\u0172\u0000\u0172\u0000\u04c5\u0000\u04ea\u0000\u050f\u0000\u0534\u0000\u0559\u0000\u0172\u0000\u057e\u0000\u05a3\u0000\u05c8\u0000\u05ed\u0000\u0612\u0000\u0172\u0000\u0637\u0000\u065c\u0000\u0681\u0000\u06a6\u0000\u06cb\u0000\u0172\u0000\u0172\u0000\u06f0\u0000\u0715\u0000\u073a\u0000\u075f\u0000\u0172\u0000\u0784\u0000\u07a9\u0000\u075f\u0000\u07ce\u0000\u07f3\u0000\u0818\u0000\u0172";
    private static final int[] ZZ_TRANS = XMLTokenMaker.zzUnpackTrans();
    private static final String ZZ_TRANS_PACKED_0 = "\u0003\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u001d\t\u0002\u000f\u0001\u0010\u0001\u000f\u0001\u0011\u0014\u000f\u0001\u0012\u0003\u000f\u0001\u0013\u0003\u000f\u0001\u0014\u0003\u000f\u0004\u0015\u0001\u0016\u001e\u0015\u0001\u0017\u0001\u0015\u0004\u0018\u0001\u0019\u0001\u001a\u0005\u0018\u0001\u001b\u0004\u0018\u0001\u001c\u0001\u001d\u0013\u0018\u0003\u001e\u0001\u001f\u0001\u0000\u0001\u001e\u0001\r\u0002\u001e\u0001 \u0007\u001e\u0001!\u0002\u001e\u0001\"\u0001#\u000e\u001e\u0001$\t%\u0001&\u001b%\u0015'\u0001&\u000f'\u0010(\u0001)\u0014(\u0004\t\u0004\u0000 \t\u0001\n\u0002\u0000\u0001\r\u0001\u0000\u001d\t&\u0000\u0001*\b\u0000\u0001+\u0001\u0000\u0004*\u0003\u0000\u0001*\u0001,\u0001\u0000\u0001*\u0002\u0000\t*\u0001\u0000\u0001-\u0004\u0000\u0001\r\u0002\u0000\u0001\r\u001e\u0000\u0006\u000e\u0001\u0000\u0001\u000e\u0001.\u001c\u000e\u0002\u000f\u0001\u0000\u0001\u000f\u0001\u0000\u0014\u000f\u0001\u0000\u0003\u000f\u0001\u0000\u0003\u000f\u0001\u0000\u0003\u000f\u0002\u0000\u0001/<\u0000\u00010$\u0000\u00011\u0003\u0000\u00012'\u0000\u00013\u0003\u0000\u0004\u0015\u0001\u0000\u001e\u0015\u0001\u0000\u0001\u0015\u0011\u0000\u00014\u0013\u0000\u0004\u0018\u0002\u0000\u0005\u0018\u0001\u0000\u0004\u0018\u0002\u0000\u0013\u0018\n\u0000\u00015\u001a\u0000\u0004\u001e\u0001\u0000\u0001\u001e\u0001\u0000\u0002\u001e\u0001\u0000\u0007\u001e\u0001\u0000\u0002\u001e\u0002\u0000\u000e\u001e\u0001\u0000\u0003\u001e\u0001\u001f\u0001\u0000\u0001\u001e\u0001\r\u0002\u001e\u0001\u0000\u0007\u001e\u0001\u0000\u0002\u001e\u0002\u0000\u000e\u001e\u0012\u0000\u0001!\u0013\u0000\t%\u0001\u0000\u001b%\u0015'\u0001\u0000\u000f'\u0010(\u0001\u0000\u0014(\u0010\u0000\u00016\u0015\u0000\u0002*\t\u0000\u0004*\u0003\u0000\u0001*\u0002\u0000\u0002*\u0001\u0000\n*\u0004\u0000\u00017\b\u0000\u00018\u001a\u0000\u00019\n\u0000\u00049\u0003\u0000\u00019\u0002\u0000\u00019\u0002\u0000\t9\u0014\u0000\u0001:-\u0000\u0001;%\u0000\u0001<(\u0000\u0001=&\u0000\u0001>\u0005\u0000\u0001?3\u0000\u0001@\u0015\u0000\u0001A.\u0000\u0001B\u0019\u0000\u00029\t\u0000\u00049\u0003\u0000\u00019\u0002\u0000\u00029\u0001\u0000\n9\u001d\u0000\u0001C\u001c\u0000\u0001D1\u0000\u0001<&\u0000\u0001E\u0004\u0000\u0001F/\u0000\u0001G*\u0000\u0001D\b\u0000\u0001<\u001c\u0000\u0001H\u0011\u0000\u0002I\u0004\u0000\u0002I\u0001\u0000\u0002I\u0004E\u0001I\u0001\u0000\u0002I\u0001E\u0001I\fE\u0003I\u000e\u0000\u0001J*\u0000\u0001E\u001f\u0000\u0001K#\u0000\u0001L!\u0000\u0001M\u0019\u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    private static final int[] ZZ_ATTRIBUTE = XMLTokenMaker.zzUnpackAttribute();
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0005\u0000\u0002\u0001\u0001\u0000\u0002\u0001\u0001\t\u0005\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0001\u0001\t\u0001\u0001\u0003\t\u0002\u0001\u0002\t\u0001\u0001\u0002\t\u0001\u0001\u0001\t\u0006\u0001\u0002\t\u0005\u0000\u0001\t\u0004\u0000\u0001\u0001\u0001\t\u0005\u0000\u0002\t\u0003\u0000\u0001\u0001\u0001\t\u0006\u0000\u0001\t";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState = 0;
    private char[] zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtEOF;
    public static final int INTERNAL_ATTR_DOUBLE = -1;
    public static final int INTERNAL_ATTR_SINGLE = -2;
    public static final int INTERNAL_INTAG = -3;
    public static final int INTERNAL_DTD = -4;
    public static final int INTERNAL_DTD_INTERNAL = -5;
    public static final int INTERNAL_IN_XML_COMMENT = -2048;
    private static boolean completeCloseTags = true;
    private boolean inInternalDtd;
    private int prevState;

    private static int[] zzUnpackAction() {
        int[] result = new int[77];
        int offset = 0;
        offset = XMLTokenMaker.zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
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
        int[] result = new int[77];
        int offset = 0;
        offset = XMLTokenMaker.zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
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
        int[] result = new int[2109];
        int offset = 0;
        offset = XMLTokenMaker.zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
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
        int[] result = new int[77];
        int offset = 0;
        offset = XMLTokenMaker.zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
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

    public XMLTokenMaker() {
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
    protected OccurrenceMarker createOccurrenceMarker() {
        return new XmlOccurrenceMarker();
    }

    @Override
    public boolean getCompleteCloseTags() {
        return completeCloseTags;
    }

    public static boolean getCompleteCloseMarkupTags() {
        return completeCloseTags;
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 26;
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();
        this.offsetShift = -text.offset + startOffset;
        this.prevState = 0;
        this.inInternalDtd = false;
        int state = 0;
        switch (initialTokenType) {
            case 29: {
                state = 1;
                break;
            }
            case -4: {
                state = 3;
                break;
            }
            case -5: {
                state = 3;
                this.inInternalDtd = true;
                break;
            }
            case -1: {
                state = 5;
                break;
            }
            case -2: {
                state = 6;
                break;
            }
            case 31: {
                state = 2;
                break;
            }
            case -3: {
                state = 4;
                break;
            }
            case 33: {
                state = 7;
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

    public XMLTokenMaker(Reader in) {
        this.zzReader = in;
    }

    public XMLTokenMaker(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[65536];
        int i = 0;
        int j = 0;
        while (i < 116) {
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
        block79: while (true) {
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
                case 25: {
                    int temp = this.zzMarkedPos;
                    this.addToken(this.start, this.zzStartRead + 2, 29);
                    this.start = temp;
                    this.yybegin(this.prevState);
                }
                case 31: {
                    continue block79;
                }
                case 19: {
                    this.yybegin(4);
                    this.addToken(this.start, this.zzStartRead, 28);
                }
                case 32: {
                    continue block79;
                }
                case 3: {
                    this.addNullToken();
                    return this.firstToken;
                }
                case 33: {
                    continue block79;
                }
                case 29: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 30);
                    this.start = temp;
                    this.prevState = this.zzLexicalState;
                    this.yybegin(1);
                }
                case 34: {
                    continue block79;
                }
                case 11: {
                    this.inInternalDtd = false;
                }
                case 35: {
                    continue block79;
                }
                case 4: {
                    this.addToken(25);
                    this.yybegin(4);
                }
                case 36: {
                    continue block79;
                }
                case 24: {
                    int count = this.yylength();
                    this.addToken(this.zzStartRead, this.zzStartRead + 1, 25);
                    this.addToken(this.zzMarkedPos - (count - 2), this.zzMarkedPos - 1, 26);
                    this.yybegin(4);
                }
                case 37: {
                    continue block79;
                }
                case 9: {
                    this.addToken(this.start, this.zzStartRead - 1, 30);
                    this.addEndToken(this.inInternalDtd ? -5 : -4);
                    return this.firstToken;
                }
                case 38: {
                    continue block79;
                }
                case 16: {
                    this.addToken(25);
                }
                case 39: {
                    continue block79;
                }
                case 7: {
                    this.addToken(this.start, this.zzStartRead - 1, 29);
                    this.addEndToken(-2048 - this.prevState);
                    return this.firstToken;
                }
                case 40: {
                    continue block79;
                }
                case 5: {
                    this.addToken(21);
                }
                case 41: {
                    continue block79;
                }
                case 27: {
                    this.start = this.zzStartRead;
                    this.prevState = this.zzLexicalState;
                    this.yybegin(1);
                }
                case 42: {
                    continue block79;
                }
                case 26: {
                    int temp = this.zzStartRead;
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead - 1, 33);
                    this.addToken(temp, this.zzMarkedPos - 1, 32);
                }
                case 43: {
                    continue block79;
                }
                case 6: {
                    this.addToken(34);
                }
                case 44: {
                    continue block79;
                }
                case 12: {
                    if (!this.inInternalDtd) {
                        this.yybegin(0);
                        this.addToken(this.start, this.zzStartRead, 30);
                    }
                }
                case 45: {
                    continue block79;
                }
                case 2: {
                    this.addToken(20);
                }
                case 46: {
                    continue block79;
                }
                case 10: {
                    this.inInternalDtd = true;
                }
                case 47: {
                    continue block79;
                }
                case 23: {
                    this.yybegin(0);
                    this.addToken(this.start, this.zzStartRead + 1, 31);
                }
                case 48: {
                    continue block79;
                }
                case 21: {
                    this.start = this.zzMarkedPos - 2;
                    this.inInternalDtd = false;
                    this.yybegin(3);
                }
                case 49: {
                    continue block79;
                }
                case 20: {
                    int count = this.yylength();
                    this.addToken(this.zzStartRead, this.zzStartRead, 25);
                    this.addToken(this.zzMarkedPos - (count - 1), this.zzMarkedPos - 1, 26);
                    this.yybegin(4);
                }
                case 50: {
                    continue block79;
                }
                case 22: {
                    this.start = this.zzMarkedPos - 2;
                    this.yybegin(2);
                }
                case 51: {
                    continue block79;
                }
                case 8: {
                    this.addToken(this.start, this.zzStartRead - 1, 31);
                    return this.firstToken;
                }
                case 52: {
                    continue block79;
                }
                case 14: {
                    this.start = this.zzMarkedPos - 1;
                    this.yybegin(5);
                }
                case 53: {
                    continue block79;
                }
                case 28: {
                    int temp = this.zzStartRead;
                    this.addToken(this.start, this.zzStartRead - 1, 29);
                    this.addHyperlinkToken(temp, this.zzMarkedPos - 1, 29);
                    this.start = this.zzMarkedPos;
                }
                case 54: {
                    continue block79;
                }
                case 15: {
                    this.yybegin(0);
                    this.addToken(25);
                }
                case 55: {
                    continue block79;
                }
                case 17: {
                    this.start = this.zzMarkedPos - 1;
                    this.yybegin(6);
                }
                case 56: {
                    continue block79;
                }
                case 18: {
                    this.addToken(23);
                }
                case 57: {
                    continue block79;
                }
                case 30: {
                    this.addToken(32);
                    this.start = this.zzMarkedPos;
                    this.yybegin(7);
                }
                case 58: {
                    continue block79;
                }
                case 13: {
                    this.addToken(27);
                }
                case 59: {
                    continue block79;
                }
                case 1: 
                case 60: {
                    continue block79;
                }
            }
            if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
                this.zzAtEOF = true;
                switch (this.zzLexicalState) {
                    case 4: {
                        this.addToken(this.start, this.zzStartRead - 1, -3);
                        return this.firstToken;
                    }
                    case 78: {
                        continue block79;
                    }
                    case 3: {
                        this.addToken(this.start, this.zzStartRead - 1, 30);
                        this.addEndToken(this.inInternalDtd ? -5 : -4);
                        return this.firstToken;
                    }
                    case 79: {
                        continue block79;
                    }
                    case 5: {
                        this.addToken(this.start, this.zzStartRead - 1, 28);
                        this.addEndToken(-1);
                        return this.firstToken;
                    }
                    case 80: {
                        continue block79;
                    }
                    case 0: {
                        this.addNullToken();
                        return this.firstToken;
                    }
                    case 81: {
                        continue block79;
                    }
                    case 1: {
                        this.addToken(this.start, this.zzStartRead - 1, 29);
                        this.addEndToken(-2048 - this.prevState);
                        return this.firstToken;
                    }
                    case 82: {
                        continue block79;
                    }
                    case 7: {
                        this.addToken(this.start, this.zzStartRead - 1, 33);
                        return this.firstToken;
                    }
                    case 83: {
                        continue block79;
                    }
                    case 6: {
                        this.addToken(this.start, this.zzStartRead - 1, 28);
                        this.addEndToken(-2);
                        return this.firstToken;
                    }
                    case 84: {
                        continue block79;
                    }
                    case 2: {
                        this.addToken(this.start, this.zzStartRead - 1, 31);
                        return this.firstToken;
                    }
                    case 85: {
                        continue block79;
                    }
                }
                return null;
            }
            this.zzScanError(1);
        }
    }
}

