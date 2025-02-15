/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import com.kitfox.svg.animation.parser.AnimTimeParserConstants;
import com.kitfox.svg.animation.parser.SimpleCharStream;
import com.kitfox.svg.animation.parser.Token;
import com.kitfox.svg.animation.parser.TokenMgrException;
import java.io.IOException;

public class AnimTimeParserTokenManager
implements AnimTimeParserConstants {
    public static final String[] jjstrLiteralImages = new String[]{"", null, null, null, null, null, null, null, null, null, "indefinite", "mouseover", "whenNotActive", null, null, ";", "+", "-", ":", ".", "(", ")"};
    static final int[] jjnextStates = new int[]{17, 7, 1, 8, 3, 7, 1, 8, 3, 13, 15, 4, 5};
    int curLexState = 0;
    int defaultLexState = 0;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;
    public static final String[] lexStateNames = new String[]{"DEFAULT"};
    public static final int[] jjnewLexState = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    static final long[] jjtoToken = new long[]{4194049L};
    static final long[] jjtoSkip = new long[]{62L};
    static final long[] jjtoSpecial = new long[]{0L};
    static final long[] jjtoMore = new long[]{0L};
    protected SimpleCharStream input_stream;
    private final int[] jjrounds = new int[18];
    private final int[] jjstateSet = new int[36];
    private final StringBuilder jjimage;
    private StringBuilder image = this.jjimage = new StringBuilder();
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;

    private final int jjStopStringLiteralDfa_0(int pos, long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x30000L) != 0L) {
                    return 6;
                }
                if ((active0 & 0x800L) != 0L) {
                    this.jjmatchedKind = 14;
                    return 13;
                }
                if ((active0 & 0x1400L) != 0L) {
                    this.jjmatchedKind = 14;
                    return 11;
                }
                if ((active0 & 0x80000L) != 0L) {
                    return 2;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 1;
                    return 11;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 2;
                    return 11;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 3;
                    return 11;
                }
                return -1;
            }
            case 4: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 4;
                    return 11;
                }
                return -1;
            }
            case 5: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 5;
                    return 11;
                }
                return -1;
            }
            case 6: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 6;
                    return 11;
                }
                return -1;
            }
            case 7: {
                if ((active0 & 0x1C00L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 7;
                    return 11;
                }
                return -1;
            }
            case 8: {
                if ((active0 & 0x800L) != 0L) {
                    return 11;
                }
                if ((active0 & 0x1400L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 8;
                    return 11;
                }
                return -1;
            }
            case 9: {
                if ((active0 & 0x1000L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 9;
                    return 11;
                }
                if ((active0 & 0x400L) != 0L) {
                    return 11;
                }
                return -1;
            }
            case 10: {
                if ((active0 & 0x1000L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 10;
                    return 11;
                }
                return -1;
            }
            case 11: {
                if ((active0 & 0x1000L) != 0L) {
                    this.jjmatchedKind = 14;
                    this.jjmatchedPos = 11;
                    return 11;
                }
                return -1;
            }
        }
        return -1;
    }

    private final int jjStartNfa_0(int pos, long active0) {
        return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    private int jjStopAtPos(int pos, int kind) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        return pos + 1;
    }

    private int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case 40: {
                return this.jjStopAtPos(0, 20);
            }
            case 41: {
                return this.jjStopAtPos(0, 21);
            }
            case 43: {
                return this.jjStartNfaWithStates_0(0, 16, 6);
            }
            case 45: {
                return this.jjStartNfaWithStates_0(0, 17, 6);
            }
            case 46: {
                return this.jjStartNfaWithStates_0(0, 19, 2);
            }
            case 58: {
                return this.jjStopAtPos(0, 18);
            }
            case 59: {
                return this.jjStopAtPos(0, 15);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa1_0(1024L);
            }
            case 109: {
                return this.jjMoveStringLiteralDfa1_0(2048L);
            }
            case 119: {
                return this.jjMoveStringLiteralDfa1_0(4096L);
            }
        }
        return this.jjMoveNfa_0(0, 0);
    }

    private int jjMoveStringLiteralDfa1_0(long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case 104: {
                return this.jjMoveStringLiteralDfa2_0(active0, 4096L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa2_0(active0, 1024L);
            }
            case 111: {
                return this.jjMoveStringLiteralDfa2_0(active0, 2048L);
            }
        }
        return this.jjStartNfa_0(0, active0);
    }

    private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(0, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(1, active0);
            return 2;
        }
        switch (this.curChar) {
            case 100: {
                return this.jjMoveStringLiteralDfa3_0(active0, 1024L);
            }
            case 101: {
                return this.jjMoveStringLiteralDfa3_0(active0, 4096L);
            }
            case 117: {
                return this.jjMoveStringLiteralDfa3_0(active0, 2048L);
            }
        }
        return this.jjStartNfa_0(1, active0);
    }

    private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(1, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(2, active0);
            return 3;
        }
        switch (this.curChar) {
            case 101: {
                return this.jjMoveStringLiteralDfa4_0(active0, 1024L);
            }
            case 110: {
                return this.jjMoveStringLiteralDfa4_0(active0, 4096L);
            }
            case 115: {
                return this.jjMoveStringLiteralDfa4_0(active0, 2048L);
            }
        }
        return this.jjStartNfa_0(2, active0);
    }

    private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(2, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(3, active0);
            return 4;
        }
        switch (this.curChar) {
            case 78: {
                return this.jjMoveStringLiteralDfa5_0(active0, 4096L);
            }
            case 101: {
                return this.jjMoveStringLiteralDfa5_0(active0, 2048L);
            }
            case 102: {
                return this.jjMoveStringLiteralDfa5_0(active0, 1024L);
            }
        }
        return this.jjStartNfa_0(3, active0);
    }

    private int jjMoveStringLiteralDfa5_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(3, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(4, active0);
            return 5;
        }
        switch (this.curChar) {
            case 105: {
                return this.jjMoveStringLiteralDfa6_0(active0, 1024L);
            }
            case 111: {
                return this.jjMoveStringLiteralDfa6_0(active0, 6144L);
            }
        }
        return this.jjStartNfa_0(4, active0);
    }

    private int jjMoveStringLiteralDfa6_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(4, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(5, active0);
            return 6;
        }
        switch (this.curChar) {
            case 110: {
                return this.jjMoveStringLiteralDfa7_0(active0, 1024L);
            }
            case 116: {
                return this.jjMoveStringLiteralDfa7_0(active0, 4096L);
            }
            case 118: {
                return this.jjMoveStringLiteralDfa7_0(active0, 2048L);
            }
        }
        return this.jjStartNfa_0(5, active0);
    }

    private int jjMoveStringLiteralDfa7_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(5, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(6, active0);
            return 7;
        }
        switch (this.curChar) {
            case 65: {
                return this.jjMoveStringLiteralDfa8_0(active0, 4096L);
            }
            case 101: {
                return this.jjMoveStringLiteralDfa8_0(active0, 2048L);
            }
            case 105: {
                return this.jjMoveStringLiteralDfa8_0(active0, 1024L);
            }
        }
        return this.jjStartNfa_0(6, active0);
    }

    private int jjMoveStringLiteralDfa8_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(6, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(7, active0);
            return 8;
        }
        switch (this.curChar) {
            case 99: {
                return this.jjMoveStringLiteralDfa9_0(active0, 4096L);
            }
            case 114: {
                if ((active0 & 0x800L) == 0L) break;
                return this.jjStartNfaWithStates_0(8, 11, 11);
            }
            case 116: {
                return this.jjMoveStringLiteralDfa9_0(active0, 1024L);
            }
        }
        return this.jjStartNfa_0(7, active0);
    }

    private int jjMoveStringLiteralDfa9_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(7, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(8, active0);
            return 9;
        }
        switch (this.curChar) {
            case 101: {
                if ((active0 & 0x400L) == 0L) break;
                return this.jjStartNfaWithStates_0(9, 10, 11);
            }
            case 116: {
                return this.jjMoveStringLiteralDfa10_0(active0, 4096L);
            }
        }
        return this.jjStartNfa_0(8, active0);
    }

    private int jjMoveStringLiteralDfa10_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(8, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(9, active0);
            return 10;
        }
        switch (this.curChar) {
            case 105: {
                return this.jjMoveStringLiteralDfa11_0(active0, 4096L);
            }
        }
        return this.jjStartNfa_0(9, active0);
    }

    private int jjMoveStringLiteralDfa11_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(9, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(10, active0);
            return 11;
        }
        switch (this.curChar) {
            case 118: {
                return this.jjMoveStringLiteralDfa12_0(active0, 4096L);
            }
        }
        return this.jjStartNfa_0(10, active0);
    }

    private int jjMoveStringLiteralDfa12_0(long old0, long active0) {
        if ((active0 &= old0) == 0L) {
            return this.jjStartNfa_0(10, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            this.jjStopStringLiteralDfa_0(11, active0);
            return 12;
        }
        switch (this.curChar) {
            case 101: {
                if ((active0 & 0x1000L) == 0L) break;
                return this.jjStartNfaWithStates_0(12, 12, 11);
            }
        }
        return this.jjStartNfa_0(11, active0);
    }

    private int jjStartNfaWithStates_0(int pos, int kind, int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        } catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_0(state, pos + 1);
    }

    private int jjMoveNfa_0(int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 18;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < 64) {
                long l = 1L << this.curChar;
                block29: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x3FF000000000000L & l) != 0L) {
                                if (kind > 8) {
                                    kind = 8;
                                }
                                this.jjCheckNAddStates(0, 4);
                                break;
                            }
                            if ((0x280000000000L & l) != 0L) {
                                this.jjCheckNAddTwoStates(1, 6);
                                break;
                            }
                            if (this.curChar != 46) break;
                            this.jjCheckNAdd(2);
                            break;
                        }
                        case 6: {
                            if ((0x3FF000000000000L & l) != 0L) {
                                if (kind > 9) {
                                    kind = 9;
                                }
                                this.jjCheckNAddStates(5, 8);
                                break;
                            }
                            if (this.curChar != 46) break;
                            this.jjCheckNAdd(2);
                            break;
                        }
                        case 11: 
                        case 13: {
                            if ((0x3FF200000000000L & l) == 0L) continue block29;
                            if (kind > 14) {
                                kind = 14;
                            }
                            this.jjCheckNAdd(11);
                            break;
                        }
                        case 1: {
                            if (this.curChar != 46) break;
                            this.jjCheckNAdd(2);
                            break;
                        }
                        case 2: {
                            if ((0x3FF000000000000L & l) == 0L) continue block29;
                            if (kind > 9) {
                                kind = 9;
                            }
                            this.jjCheckNAddTwoStates(2, 3);
                            break;
                        }
                        case 4: {
                            if ((0x280000000000L & l) == 0L) break;
                            this.jjCheckNAdd(5);
                            break;
                        }
                        case 5: {
                            if ((0x3FF000000000000L & l) == 0L) continue block29;
                            if (kind > 9) {
                                kind = 9;
                            }
                            this.jjCheckNAdd(5);
                            break;
                        }
                        case 7: {
                            if ((0x3FF000000000000L & l) == 0L) break;
                            this.jjCheckNAddTwoStates(7, 1);
                            break;
                        }
                        case 8: {
                            if ((0x3FF000000000000L & l) == 0L) continue block29;
                            if (kind > 9) {
                                kind = 9;
                            }
                            this.jjCheckNAddTwoStates(8, 3);
                            break;
                        }
                        case 16: {
                            if ((0x3FF000000000000L & l) == 0L) continue block29;
                            if (kind > 8) {
                                kind = 8;
                            }
                            this.jjCheckNAddStates(0, 4);
                            break;
                        }
                        case 17: {
                            if ((0x3FF000000000000L & l) == 0L) continue block29;
                            if (kind > 8) {
                                kind = 8;
                            }
                            this.jjCheckNAdd(17);
                            break;
                        }
                    }
                } while (i != startsAt);
            } else if (this.curChar < 128) {
                long l = 1L << (this.curChar & 0x3F);
                block30: do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x7FFFFFE07FFFFFEL & l) != 0L) {
                                if (kind > 14) {
                                    kind = 14;
                                }
                                this.jjCheckNAdd(11);
                            }
                            if ((0x8010000000000L & l) != 0L) {
                                if (kind <= 13) break;
                                kind = 13;
                                break;
                            }
                            if (this.curChar != 109) break;
                            this.jjAddStates(9, 10);
                            break;
                        }
                        case 13: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0L) {
                                if (kind > 14) {
                                    kind = 14;
                                }
                                this.jjCheckNAdd(11);
                            }
                            if (this.curChar == 105) {
                                this.jjstateSet[this.jjnewStateCnt++] = 14;
                                break;
                            }
                            if (this.curChar != 115 || kind <= 13) continue block30;
                            kind = 13;
                            break;
                        }
                        case 3: {
                            if ((0x2000000020L & l) == 0L) break;
                            this.jjAddStates(11, 12);
                            break;
                        }
                        case 9: {
                            if ((0x8010000000000L & l) == 0L || kind <= 13) continue block30;
                            kind = 13;
                            break;
                        }
                        case 10: {
                            if ((0x7FFFFFE07FFFFFEL & l) == 0L) continue block30;
                            if (kind > 14) {
                                kind = 14;
                            }
                            this.jjCheckNAdd(11);
                            break;
                        }
                        case 11: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0L) continue block30;
                            if (kind > 14) {
                                kind = 14;
                            }
                            this.jjCheckNAdd(11);
                            break;
                        }
                        case 12: {
                            if (this.curChar != 109) break;
                            this.jjAddStates(9, 10);
                            break;
                        }
                        case 14: {
                            if (this.curChar != 110 || kind <= 13) continue block30;
                            kind = 13;
                            break;
                        }
                        case 15: {
                            if (this.curChar != 105) break;
                            this.jjstateSet[this.jjnewStateCnt++] = 14;
                            break;
                        }
                    }
                } while (i != startsAt);
            } else {
                int i2 = (this.curChar & 0xFF) >> 6;
                long l2 = 1L << (this.curChar & 0x3F);
                do {
                    switch (this.jjstateSet[--i]) {
                        default: 
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            i = this.jjnewStateCnt;
            this.jjnewStateCnt = startsAt;
            startsAt = 18 - this.jjnewStateCnt;
            if (i == startsAt) {
                return curPos;
            }
            try {
                this.curChar = this.input_stream.readChar();
            } catch (IOException e) {
                return curPos;
            }
        }
    }

    protected Token jjFillToken() {
        String im = jjstrLiteralImages[this.jjmatchedKind];
        String curTokenImage = im == null ? this.input_stream.getImage() : im;
        int beginLine = this.input_stream.getBeginLine();
        int beginColumn = this.input_stream.getBeginColumn();
        int endLine = this.input_stream.getEndLine();
        int endColumn = this.input_stream.getEndColumn();
        Token t = Token.newToken(this.jjmatchedKind);
        t.kind = this.jjmatchedKind;
        t.image = curTokenImage;
        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;
        return t;
    }

    public Token getNextToken() {
        int curPos;
        block11: {
            curPos = 0;
            while (true) {
                try {
                    this.curChar = this.input_stream.beginToken();
                } catch (Exception e) {
                    this.jjmatchedKind = 0;
                    this.jjmatchedPos = -1;
                    Token matchedToken = this.jjFillToken();
                    return matchedToken;
                }
                try {
                    this.input_stream.backup(0);
                    while (this.curChar <= 32 && (0x100003600L & 1L << this.curChar) != 0L) {
                        this.curChar = this.input_stream.beginToken();
                    }
                } catch (IOException e1) {
                    continue;
                }
                this.jjmatchedKind = Integer.MAX_VALUE;
                this.jjmatchedPos = 0;
                curPos = this.jjMoveStringLiteralDfa0_0();
                if (this.jjmatchedKind == Integer.MAX_VALUE) break block11;
                if (this.jjmatchedPos + 1 < curPos) {
                    this.input_stream.backup(curPos - this.jjmatchedPos - 1);
                }
                if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) break;
            }
            Token matchedToken = this.jjFillToken();
            return matchedToken;
        }
        int error_line = this.input_stream.getEndLine();
        int error_column = this.input_stream.getEndColumn();
        String error_after = null;
        boolean EOFSeen = false;
        try {
            this.input_stream.readChar();
            this.input_stream.backup(1);
        } catch (IOException e1) {
            EOFSeen = true;
            String string = error_after = curPos <= 1 ? "" : this.input_stream.getImage();
            if (this.curChar == 10 || this.curChar == 13) {
                ++error_line;
                error_column = 0;
            }
            ++error_column;
        }
        if (!EOFSeen) {
            this.input_stream.backup(1);
            error_after = curPos <= 1 ? "" : this.input_stream.getImage();
        }
        throw new TokenMgrException(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
    }

    void SkipLexicalActions(Token matchedToken) {
        switch (this.jjmatchedKind) {
            default: 
        }
    }

    void MoreLexicalActions() {
        this.lengthOfMatch = this.jjmatchedPos + 1;
        this.jjimageLen += this.lengthOfMatch;
        switch (this.jjmatchedKind) {
            default: 
        }
    }

    void TokenLexicalActions(Token matchedToken) {
        switch (this.jjmatchedKind) {
            default: 
        }
    }

    private void jjCheckNAdd(int state) {
        if (this.jjrounds[state] != this.jjround) {
            this.jjstateSet[this.jjnewStateCnt++] = state;
            this.jjrounds[state] = this.jjround;
        }
    }

    private void jjAddStates(int start, int end) {
        do {
            this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
        } while (start++ != end);
    }

    private void jjCheckNAddTwoStates(int state1, int state2) {
        this.jjCheckNAdd(state1);
        this.jjCheckNAdd(state2);
    }

    private void jjCheckNAddStates(int start, int end) {
        do {
            this.jjCheckNAdd(jjnextStates[start]);
        } while (start++ != end);
    }

    public AnimTimeParserTokenManager(SimpleCharStream stream) {
        this.input_stream = stream;
    }

    public AnimTimeParserTokenManager(SimpleCharStream stream, int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }

    public void ReInit(SimpleCharStream stream) {
        this.jjnewStateCnt = 0;
        this.jjmatchedPos = 0;
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        this.ReInitRounds();
    }

    private void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 18;
        while (i-- > 0) {
            this.jjrounds[i] = Integer.MIN_VALUE;
        }
    }

    public void ReInit(SimpleCharStream stream, int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }

    public void SwitchTo(int lexState) {
        if (lexState >= 1 || lexState < 0) {
            throw new TokenMgrException("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }
}

