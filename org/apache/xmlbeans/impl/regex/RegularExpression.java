/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import java.text.CharacterIterator;
import org.apache.xmlbeans.impl.regex.BMPattern;
import org.apache.xmlbeans.impl.regex.Match;
import org.apache.xmlbeans.impl.regex.Op;
import org.apache.xmlbeans.impl.regex.ParseException;
import org.apache.xmlbeans.impl.regex.ParserForXMLSchema;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.apache.xmlbeans.impl.regex.RangeToken;
import org.apache.xmlbeans.impl.regex.RegexParser;
import org.apache.xmlbeans.impl.regex.Token;

public class RegularExpression
implements Serializable {
    static final boolean DEBUG = false;
    String regex;
    int options;
    int nofparen;
    Token tokentree;
    boolean hasBackReferences = false;
    transient int minlength;
    transient Op operations = null;
    transient int numberOfClosures;
    transient Context context = null;
    transient RangeToken firstChar = null;
    transient String fixedString = null;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable = null;
    transient boolean fixedStringOnly = false;
    static final int IGNORE_CASE = 2;
    static final int SINGLE_LINE = 4;
    static final int MULTIPLE_LINES = 8;
    static final int EXTENDED_COMMENT = 16;
    static final int USE_UNICODE_CATEGORY = 32;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int XMLSCHEMA_MODE = 512;
    static final int SPECIAL_COMMA = 1024;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static final int LINE_FEED = 10;
    static final int CARRIAGE_RETURN = 13;
    static final int LINE_SEPARATOR = 8232;
    static final int PARAGRAPH_SEPARATOR = 8233;

    private synchronized void compile(Token tok) {
        if (this.operations != null) {
            return;
        }
        this.numberOfClosures = 0;
        this.operations = this.compile(tok, null, false);
    }

    private Op compile(Token tok, Op next, boolean reverse) {
        Op ret;
        switch (tok.type) {
            case 11: {
                ret = Op.createDot();
                ret.next = next;
                break;
            }
            case 0: {
                ret = Op.createChar(tok.getChar());
                ret.next = next;
                break;
            }
            case 8: {
                ret = Op.createAnchor(tok.getChar());
                ret.next = next;
                break;
            }
            case 4: 
            case 5: {
                ret = Op.createRange(tok);
                ret.next = next;
                break;
            }
            case 1: {
                ret = next;
                if (!reverse) {
                    for (int i = tok.size() - 1; i >= 0; --i) {
                        ret = this.compile(tok.getChild(i), ret, false);
                    }
                } else {
                    for (int i = 0; i < tok.size(); ++i) {
                        ret = this.compile(tok.getChild(i), ret, true);
                    }
                }
                break;
            }
            case 2: {
                Op.UnionOp uni = Op.createUnion(tok.size());
                for (int i = 0; i < tok.size(); ++i) {
                    uni.addElement(this.compile(tok.getChild(i), next, reverse));
                }
                ret = uni;
                break;
            }
            case 3: 
            case 9: {
                Token child = tok.getChild(0);
                int min = tok.getMin();
                int max = tok.getMax();
                if (min >= 0 && min == max) {
                    ret = next;
                    for (int i = 0; i < min; ++i) {
                        ret = this.compile(child, ret, reverse);
                    }
                } else {
                    if (min > 0 && max > 0) {
                        max -= min;
                    }
                    if (max > 0) {
                        ret = next;
                        for (int i = 0; i < max; ++i) {
                            Op.ChildOp q = Op.createQuestion(tok.type == 9);
                            q.next = next;
                            q.setChild(this.compile(child, ret, reverse));
                            ret = q;
                        }
                    } else {
                        Op.ChildOp op = tok.type == 9 ? Op.createNonGreedyClosure() : (child.getMinLength() == 0 ? Op.createClosure(this.numberOfClosures++) : Op.createClosure(-1));
                        op.next = next;
                        op.setChild(this.compile(child, op, reverse));
                        ret = op;
                    }
                    if (min <= 0) break;
                    for (int i = 0; i < min; ++i) {
                        ret = this.compile(child, ret, reverse);
                    }
                }
                break;
            }
            case 7: {
                ret = next;
                break;
            }
            case 10: {
                ret = Op.createString(tok.getString());
                ret.next = next;
                break;
            }
            case 12: {
                ret = Op.createBackReference(tok.getReferenceNumber());
                ret.next = next;
                break;
            }
            case 6: {
                if (tok.getParenNumber() == 0) {
                    ret = this.compile(tok.getChild(0), next, reverse);
                    break;
                }
                if (reverse) {
                    next = Op.createCapture(tok.getParenNumber(), next);
                    next = this.compile(tok.getChild(0), next, reverse);
                    ret = Op.createCapture(-tok.getParenNumber(), next);
                    break;
                }
                next = Op.createCapture(-tok.getParenNumber(), next);
                next = this.compile(tok.getChild(0), next, reverse);
                ret = Op.createCapture(tok.getParenNumber(), next);
                break;
            }
            case 20: {
                ret = Op.createLook(20, next, this.compile(tok.getChild(0), null, false));
                break;
            }
            case 21: {
                ret = Op.createLook(21, next, this.compile(tok.getChild(0), null, false));
                break;
            }
            case 22: {
                ret = Op.createLook(22, next, this.compile(tok.getChild(0), null, true));
                break;
            }
            case 23: {
                ret = Op.createLook(23, next, this.compile(tok.getChild(0), null, true));
                break;
            }
            case 24: {
                ret = Op.createIndependent(next, this.compile(tok.getChild(0), null, reverse));
                break;
            }
            case 25: {
                ret = Op.createModifier(next, this.compile(tok.getChild(0), null, reverse), ((Token.ModifierToken)tok).getOptions(), ((Token.ModifierToken)tok).getOptionsMask());
                break;
            }
            case 26: {
                Token.ConditionToken ctok = (Token.ConditionToken)tok;
                int ref = ctok.refNumber;
                Op condition = ctok.condition == null ? null : this.compile(ctok.condition, null, reverse);
                Op yes = this.compile(ctok.yes, next, reverse);
                Op no = ctok.no == null ? null : this.compile(ctok.no, next, reverse);
                ret = Op.createCondition(next, ref, condition, yes, no);
                break;
            }
            default: {
                throw new RuntimeException("Unknown token type: " + tok.type);
            }
        }
        return ret;
    }

    public boolean matches(char[] target) {
        return this.matches(target, 0, target.length, (Match)null);
    }

    public boolean matches(char[] target, int start, int end) {
        return this.matches(target, start, end, (Match)null);
    }

    public boolean matches(char[] target, Match match) {
        return this.matches(target, 0, target.length, match);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean matches(char[] target, int start, int end, Match match) {
        int matchStart;
        int o;
        RegularExpression regularExpression = this;
        synchronized (regularExpression) {
            if (this.operations == null) {
                this.prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        Context con = null;
        Context context = this.context;
        synchronized (context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        if (RegularExpression.isSet(this.options, 512)) {
            int matchEnd = this.matchCharArray(con, this.operations, con.start, 1, this.options);
            if (matchEnd == con.limit) {
                if (con.match != null) {
                    con.match.setBeginning(0, con.start);
                    con.match.setEnd(0, matchEnd);
                }
                con.inuse = false;
                return true;
            }
            return false;
        }
        if (this.fixedStringOnly) {
            int o2 = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o2 >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, o2);
                    con.match.setEnd(0, o2 + this.fixedString.length());
                }
                con.inuse = false;
                return true;
            }
            con.inuse = false;
            return false;
        }
        if (this.fixedString != null && (o = this.fixedStringTable.matches(target, con.start, con.limit)) < 0) {
            con.inuse = false;
            return false;
        }
        int limit = con.limit - this.minlength;
        int matchEnd = -1;
        if (this.operations != null && this.operations.type == 7 && this.operations.getChild().type == 0) {
            if (RegularExpression.isSet(this.options, 4)) {
                matchStart = con.start;
                matchEnd = this.matchCharArray(con, this.operations, con.start, 1, this.options);
            } else {
                boolean previousIsEOL = true;
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    char ch = target[matchStart];
                    if (RegularExpression.isEOLChar(ch)) {
                        previousIsEOL = true;
                        continue;
                    }
                    if (!previousIsEOL || 0 > (matchEnd = this.matchCharArray(con, this.operations, matchStart, 1, this.options))) {
                        previousIsEOL = false;
                        continue;
                    }
                    break;
                }
            }
        } else if (this.firstChar != null) {
            RangeToken range = this.firstChar;
            if (RegularExpression.isSet(this.options, 2)) {
                range = this.firstChar.getCaseInsensitiveToken();
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    char ch1;
                    int ch = target[matchStart];
                    if (!REUtil.isHighSurrogate(ch) || matchStart + 1 >= con.limit ? !range.match(ch) && !range.match(ch1 = Character.toUpperCase((char)ch)) && !range.match(Character.toLowerCase(ch1)) : !range.match(ch = REUtil.composeFromSurrogates(ch, target[matchStart + 1]))) continue;
                    matchEnd = this.matchCharArray(con, this.operations, matchStart, 1, this.options);
                    if (0 > matchEnd) {
                        continue;
                    }
                    break;
                }
            } else {
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    int ch = target[matchStart];
                    if (REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
                        ch = REUtil.composeFromSurrogates(ch, target[matchStart + 1]);
                    }
                    if (!range.match(ch) || 0 > (matchEnd = this.matchCharArray(con, this.operations, matchStart, 1, this.options))) {
                        continue;
                    }
                    break;
                }
            }
        } else {
            for (matchStart = con.start; matchStart <= limit && 0 > (matchEnd = this.matchCharArray(con, this.operations, matchStart, 1, this.options)); ++matchStart) {
            }
        }
        if (matchEnd >= 0) {
            if (con.match != null) {
                con.match.setBeginning(0, matchStart);
                con.match.setEnd(0, matchEnd);
            }
            con.inuse = false;
            return true;
        }
        con.inuse = false;
        return false;
    }

    private int matchCharArray(Context con, Op op, int offset, int dx, int opts) {
        char[] target = con.charTarget;
        while (op != null) {
            if (offset > con.limit || offset < con.start) {
                return -1;
            }
            switch (op.type) {
                case 1: {
                    int ch;
                    if (RegularExpression.isSet(opts, 2)) {
                        ch = op.getData();
                        if (dx > 0) {
                            if (offset >= con.limit || !RegularExpression.matchIgnoreCase(ch, target[offset])) {
                                return -1;
                            }
                            ++offset;
                        } else {
                            int o1 = offset - 1;
                            if (o1 >= con.limit || o1 < 0 || !RegularExpression.matchIgnoreCase(ch, target[o1])) {
                                return -1;
                            }
                            offset = o1;
                        }
                    } else {
                        ch = op.getData();
                        if (dx > 0) {
                            if (offset >= con.limit || ch != target[offset]) {
                                return -1;
                            }
                            ++offset;
                        } else {
                            int o1 = offset - 1;
                            if (o1 >= con.limit || o1 < 0 || ch != target[o1]) {
                                return -1;
                            }
                            offset = o1;
                        }
                    }
                    op = op.next;
                    break;
                }
                case 0: {
                    int o1;
                    int ch;
                    if (dx > 0) {
                        if (offset >= con.limit) {
                            return -1;
                        }
                        ch = target[offset];
                        if (RegularExpression.isSet(opts, 4)) {
                            if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                                ++offset;
                            }
                        } else {
                            if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                                ch = REUtil.composeFromSurrogates(ch, target[++offset]);
                            }
                            if (RegularExpression.isEOLChar(ch)) {
                                return -1;
                            }
                        }
                        ++offset;
                    } else {
                        o1 = offset - 1;
                        if (o1 >= con.limit || o1 < 0) {
                            return -1;
                        }
                        int ch2 = target[o1];
                        if (RegularExpression.isSet(opts, 4)) {
                            if (REUtil.isLowSurrogate(ch2) && o1 - 1 >= 0) {
                                // empty if block
                            }
                        } else {
                            if (REUtil.isLowSurrogate(ch2) && o1 - 1 >= 0) {
                                ch2 = REUtil.composeFromSurrogates(target[--o1], ch2);
                            }
                            if (!RegularExpression.isEOLChar(ch2)) {
                                return -1;
                            }
                        }
                        offset = --o1;
                    }
                    op = op.next;
                    break;
                }
                case 3: 
                case 4: {
                    int o1;
                    int ch;
                    if (dx > 0) {
                        if (offset >= con.limit) {
                            return -1;
                        }
                        ch = target[offset];
                        if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target[++offset]);
                        }
                        RangeToken tok = op.getToken();
                        if (RegularExpression.isSet(opts, 2)) {
                            if (!(tok = tok.getCaseInsensitiveToken()).match(ch)) {
                                if (ch >= 65536) {
                                    return -1;
                                }
                                char uch = Character.toUpperCase((char)ch);
                                if (!tok.match(uch) && !tok.match(Character.toLowerCase(uch))) {
                                    return -1;
                                }
                            }
                        } else if (!tok.match(ch)) {
                            return -1;
                        }
                        ++offset;
                    } else {
                        o1 = offset - 1;
                        if (o1 >= con.limit || o1 < 0) {
                            return -1;
                        }
                        int ch3 = target[o1];
                        if (REUtil.isLowSurrogate(ch3) && o1 - 1 >= 0) {
                            ch3 = REUtil.composeFromSurrogates(target[--o1], ch3);
                        }
                        RangeToken tok = op.getToken();
                        if (RegularExpression.isSet(opts, 2)) {
                            if (!(tok = tok.getCaseInsensitiveToken()).match(ch3)) {
                                if (ch3 >= 65536) {
                                    return -1;
                                }
                                char uch = Character.toUpperCase((char)ch3);
                                if (!tok.match(uch) && !tok.match(Character.toLowerCase(uch))) {
                                    return -1;
                                }
                            }
                        } else if (!tok.match(ch3)) {
                            return -1;
                        }
                        offset = o1;
                    }
                    op = op.next;
                    break;
                }
                case 5: {
                    boolean go = false;
                    switch (op.getData()) {
                        case 94: {
                            if (!(RegularExpression.isSet(opts, 8) ? offset != con.start && (offset <= con.start || !RegularExpression.isEOLChar(target[offset - 1])) : offset != con.start)) break;
                            return -1;
                        }
                        case 64: {
                            if (offset == con.start || offset > con.start && RegularExpression.isEOLChar(target[offset - 1])) break;
                            return -1;
                        }
                        case 36: {
                            if (!(RegularExpression.isSet(opts, 8) ? offset != con.limit && (offset >= con.limit || !RegularExpression.isEOLChar(target[offset])) : !(offset == con.limit || offset + 1 == con.limit && RegularExpression.isEOLChar(target[offset]) || offset + 2 == con.limit && target[offset] == '\r' && target[offset + 1] == '\n'))) break;
                            return -1;
                        }
                        case 65: {
                            if (offset == con.start) break;
                            return -1;
                        }
                        case 90: {
                            if (offset == con.limit || offset + 1 == con.limit && RegularExpression.isEOLChar(target[offset]) || offset + 2 == con.limit && target[offset] == '\r' && target[offset + 1] == '\n') break;
                            return -1;
                        }
                        case 122: {
                            if (offset == con.limit) break;
                            return -1;
                        }
                        case 98: {
                            if (con.length == 0) {
                                return -1;
                            }
                            int after = RegularExpression.getWordType(target, con.start, con.limit, offset, opts);
                            if (after == 0) {
                                return -1;
                            }
                            int before = RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts);
                            if (after != before) break;
                            return -1;
                        }
                        case 66: {
                            if (con.length == 0) {
                                go = true;
                            } else {
                                int after = RegularExpression.getWordType(target, con.start, con.limit, offset, opts);
                                boolean bl = go = after == 0 || after == RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts);
                            }
                            if (go) break;
                            return -1;
                        }
                        case 60: {
                            if (con.length == 0 || offset == con.limit) {
                                return -1;
                            }
                            if (RegularExpression.getWordType(target, con.start, con.limit, offset, opts) == 1 && RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts) == 2) break;
                            return -1;
                        }
                        case 62: {
                            if (con.length == 0 || offset == con.start) {
                                return -1;
                            }
                            if (RegularExpression.getWordType(target, con.start, con.limit, offset, opts) == 2 && RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts) == 1) break;
                            return -1;
                        }
                    }
                    op = op.next;
                    break;
                }
                case 16: {
                    int refno = op.getData();
                    if (refno <= 0 || refno >= this.nofparen) {
                        throw new RuntimeException("Internal Error: Reference number must be more than zero: " + refno);
                    }
                    if (con.match.getBeginning(refno) < 0 || con.match.getEnd(refno) < 0) {
                        return -1;
                    }
                    int o2 = con.match.getBeginning(refno);
                    int literallen = con.match.getEnd(refno) - o2;
                    if (!RegularExpression.isSet(opts, 2)) {
                        if (dx > 0) {
                            if (!RegularExpression.regionMatches(target, offset, con.limit, o2, literallen)) {
                                return -1;
                            }
                            offset += literallen;
                        } else {
                            if (!RegularExpression.regionMatches(target, offset - literallen, con.limit, o2, literallen)) {
                                return -1;
                            }
                            offset -= literallen;
                        }
                    } else if (dx > 0) {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset, con.limit, o2, literallen)) {
                            return -1;
                        }
                        offset += literallen;
                    } else {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset - literallen, con.limit, o2, literallen)) {
                            return -1;
                        }
                        offset -= literallen;
                    }
                    op = op.next;
                    break;
                }
                case 6: {
                    String literal = op.getString();
                    int literallen = literal.length();
                    if (!RegularExpression.isSet(opts, 2)) {
                        if (dx > 0) {
                            if (!RegularExpression.regionMatches(target, offset, con.limit, literal, literallen)) {
                                return -1;
                            }
                            offset += literallen;
                        } else {
                            if (!RegularExpression.regionMatches(target, offset - literallen, con.limit, literal, literallen)) {
                                return -1;
                            }
                            offset -= literallen;
                        }
                    } else if (dx > 0) {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset, con.limit, literal, literallen)) {
                            return -1;
                        }
                        offset += literallen;
                    } else {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset - literallen, con.limit, literal, literallen)) {
                            return -1;
                        }
                        offset -= literallen;
                    }
                    op = op.next;
                    break;
                }
                case 7: {
                    int id = op.getData();
                    if (id >= 0) {
                        int previousOffset = con.offsets[id];
                        if (previousOffset < 0 || previousOffset != offset) {
                            con.offsets[id] = offset;
                        } else {
                            con.offsets[id] = -1;
                            op = op.next;
                            break;
                        }
                    }
                    int ret = this.matchCharArray(con, op.getChild(), offset, dx, opts);
                    if (id >= 0) {
                        con.offsets[id] = -1;
                    }
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.next;
                    break;
                }
                case 9: {
                    int ret = this.matchCharArray(con, op.getChild(), offset, dx, opts);
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.next;
                    break;
                }
                case 8: 
                case 10: {
                    int ret = this.matchCharArray(con, op.next, offset, dx, opts);
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.getChild();
                    break;
                }
                case 11: {
                    for (int i = 0; i < op.size(); ++i) {
                        int ret = this.matchCharArray(con, op.elementAt(i), offset, dx, opts);
                        if (ret < 0) continue;
                        return ret;
                    }
                    return -1;
                }
                case 15: {
                    int ret;
                    int refno = op.getData();
                    if (con.match != null && refno > 0) {
                        int save = con.match.getBeginning(refno);
                        con.match.setBeginning(refno, offset);
                        ret = this.matchCharArray(con, op.next, offset, dx, opts);
                        if (ret < 0) {
                            con.match.setBeginning(refno, save);
                        }
                        return ret;
                    }
                    if (con.match != null && refno < 0) {
                        int index = -refno;
                        int save = con.match.getEnd(index);
                        con.match.setEnd(index, offset);
                        int ret2 = this.matchCharArray(con, op.next, offset, dx, opts);
                        if (ret2 < 0) {
                            con.match.setEnd(index, save);
                        }
                        return ret2;
                    }
                    op = op.next;
                    break;
                }
                case 20: {
                    if (0 > this.matchCharArray(con, op.getChild(), offset, 1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 21: {
                    if (0 <= this.matchCharArray(con, op.getChild(), offset, 1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 22: {
                    if (0 > this.matchCharArray(con, op.getChild(), offset, -1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 23: {
                    if (0 <= this.matchCharArray(con, op.getChild(), offset, -1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 24: {
                    int ret = this.matchCharArray(con, op.getChild(), offset, dx, opts);
                    if (ret < 0) {
                        return ret;
                    }
                    offset = ret;
                    op = op.next;
                    break;
                }
                case 25: {
                    int localopts = opts;
                    localopts |= op.getData();
                    int ret = this.matchCharArray(con, op.getChild(), offset, dx, localopts &= ~op.getData2());
                    if (ret < 0) {
                        return ret;
                    }
                    offset = ret;
                    op = op.next;
                    break;
                }
                case 26: {
                    Op.ConditionOp cop = (Op.ConditionOp)op;
                    boolean matchp = false;
                    if (cop.refNumber > 0) {
                        if (cop.refNumber >= this.nofparen) {
                            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + cop.refNumber);
                        }
                        matchp = con.match.getBeginning(cop.refNumber) >= 0 && con.match.getEnd(cop.refNumber) >= 0;
                    } else {
                        boolean bl = matchp = 0 <= this.matchCharArray(con, cop.condition, offset, dx, opts);
                    }
                    if (matchp) {
                        op = cop.yes;
                        break;
                    }
                    if (cop.no != null) {
                        op = cop.no;
                        break;
                    }
                    op = cop.next;
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown operation type: " + op.type);
                }
            }
        }
        return RegularExpression.isSet(opts, 512) && offset != con.limit ? -1 : offset;
    }

    private static final int getPreviousWordType(char[] target, int begin, int end, int offset, int opts) {
        int ret = RegularExpression.getWordType(target, begin, end, --offset, opts);
        while (ret == 0) {
            ret = RegularExpression.getWordType(target, begin, end, --offset, opts);
        }
        return ret;
    }

    private static final int getWordType(char[] target, int begin, int end, int offset, int opts) {
        if (offset < begin || offset >= end) {
            return 2;
        }
        return RegularExpression.getWordType0(target[offset], opts);
    }

    private static final boolean regionMatches(char[] target, int offset, int limit, String part, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = 0;
        while (partlen-- > 0) {
            if (target[offset++] == part.charAt(i++)) continue;
            return false;
        }
        return true;
    }

    private static final boolean regionMatches(char[] target, int offset, int limit, int offset2, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = offset2;
        while (partlen-- > 0) {
            if (target[offset++] == target[i++]) continue;
            return false;
        }
        return true;
    }

    private static final boolean regionMatchesIgnoreCase(char[] target, int offset, int limit, String part, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = 0;
        while (partlen-- > 0) {
            char uch2;
            char uch1;
            char ch2;
            char ch1;
            if ((ch1 = target[offset++]) == (ch2 = part.charAt(i++)) || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) continue;
            return false;
        }
        return true;
    }

    private static final boolean regionMatchesIgnoreCase(char[] target, int offset, int limit, int offset2, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = offset2;
        while (partlen-- > 0) {
            char uch2;
            char uch1;
            char ch2;
            char ch1;
            if ((ch1 = target[offset++]) == (ch2 = target[i++]) || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) continue;
            return false;
        }
        return true;
    }

    public boolean matches(String target) {
        return this.matches(target, 0, target.length(), (Match)null);
    }

    public boolean matches(String target, int start, int end) {
        return this.matches(target, start, end, (Match)null);
    }

    public boolean matches(String target, Match match) {
        return this.matches(target, 0, target.length(), match);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean matches(String target, int start, int end, Match match) {
        int matchStart;
        int o;
        RegularExpression regularExpression = this;
        synchronized (regularExpression) {
            if (this.operations == null) {
                this.prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        Context con = null;
        Context context = this.context;
        synchronized (context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        if (RegularExpression.isSet(this.options, 512)) {
            int matchEnd = this.matchString(con, this.operations, con.start, 1, this.options);
            if (matchEnd == con.limit) {
                if (con.match != null) {
                    con.match.setBeginning(0, con.start);
                    con.match.setEnd(0, matchEnd);
                }
                con.inuse = false;
                return true;
            }
            return false;
        }
        if (this.fixedStringOnly) {
            int o2 = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o2 >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, o2);
                    con.match.setEnd(0, o2 + this.fixedString.length());
                }
                con.inuse = false;
                return true;
            }
            con.inuse = false;
            return false;
        }
        if (this.fixedString != null && (o = this.fixedStringTable.matches(target, con.start, con.limit)) < 0) {
            con.inuse = false;
            return false;
        }
        int limit = con.limit - this.minlength;
        int matchEnd = -1;
        if (this.operations != null && this.operations.type == 7 && this.operations.getChild().type == 0) {
            if (RegularExpression.isSet(this.options, 4)) {
                matchStart = con.start;
                matchEnd = this.matchString(con, this.operations, con.start, 1, this.options);
            } else {
                boolean previousIsEOL = true;
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    char ch = target.charAt(matchStart);
                    if (RegularExpression.isEOLChar(ch)) {
                        previousIsEOL = true;
                        continue;
                    }
                    if (!previousIsEOL || 0 > (matchEnd = this.matchString(con, this.operations, matchStart, 1, this.options))) {
                        previousIsEOL = false;
                        continue;
                    }
                    break;
                }
            }
        } else if (this.firstChar != null) {
            RangeToken range = this.firstChar;
            if (RegularExpression.isSet(this.options, 2)) {
                range = this.firstChar.getCaseInsensitiveToken();
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    char ch1;
                    int ch = target.charAt(matchStart);
                    if (!REUtil.isHighSurrogate(ch) || matchStart + 1 >= con.limit ? !range.match(ch) && !range.match(ch1 = Character.toUpperCase((char)ch)) && !range.match(Character.toLowerCase(ch1)) : !range.match(ch = REUtil.composeFromSurrogates(ch, target.charAt(matchStart + 1)))) continue;
                    matchEnd = this.matchString(con, this.operations, matchStart, 1, this.options);
                    if (0 > matchEnd) {
                        continue;
                    }
                    break;
                }
            } else {
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    int ch = target.charAt(matchStart);
                    if (REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
                        ch = REUtil.composeFromSurrogates(ch, target.charAt(matchStart + 1));
                    }
                    if (!range.match(ch) || 0 > (matchEnd = this.matchString(con, this.operations, matchStart, 1, this.options))) {
                        continue;
                    }
                    break;
                }
            }
        } else {
            for (matchStart = con.start; matchStart <= limit && 0 > (matchEnd = this.matchString(con, this.operations, matchStart, 1, this.options)); ++matchStart) {
            }
        }
        if (matchEnd >= 0) {
            if (con.match != null) {
                con.match.setBeginning(0, matchStart);
                con.match.setEnd(0, matchEnd);
            }
            con.inuse = false;
            return true;
        }
        con.inuse = false;
        return false;
    }

    private int matchString(Context con, Op op, int offset, int dx, int opts) {
        String target = con.strTarget;
        while (op != null) {
            if (offset > con.limit || offset < con.start) {
                return -1;
            }
            switch (op.type) {
                case 1: {
                    int ch;
                    if (RegularExpression.isSet(opts, 2)) {
                        ch = op.getData();
                        if (dx > 0) {
                            if (offset >= con.limit || !RegularExpression.matchIgnoreCase(ch, target.charAt(offset))) {
                                return -1;
                            }
                            ++offset;
                        } else {
                            int o1 = offset - 1;
                            if (o1 >= con.limit || o1 < 0 || !RegularExpression.matchIgnoreCase(ch, target.charAt(o1))) {
                                return -1;
                            }
                            offset = o1;
                        }
                    } else {
                        ch = op.getData();
                        if (dx > 0) {
                            if (offset >= con.limit || ch != target.charAt(offset)) {
                                return -1;
                            }
                            ++offset;
                        } else {
                            int o1 = offset - 1;
                            if (o1 >= con.limit || o1 < 0 || ch != target.charAt(o1)) {
                                return -1;
                            }
                            offset = o1;
                        }
                    }
                    op = op.next;
                    break;
                }
                case 0: {
                    int o1;
                    int ch;
                    if (dx > 0) {
                        if (offset >= con.limit) {
                            return -1;
                        }
                        ch = target.charAt(offset);
                        if (RegularExpression.isSet(opts, 4)) {
                            if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                                ++offset;
                            }
                        } else {
                            if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                                ch = REUtil.composeFromSurrogates(ch, target.charAt(++offset));
                            }
                            if (RegularExpression.isEOLChar(ch)) {
                                return -1;
                            }
                        }
                        ++offset;
                    } else {
                        o1 = offset - 1;
                        if (o1 >= con.limit || o1 < 0) {
                            return -1;
                        }
                        int ch2 = target.charAt(o1);
                        if (RegularExpression.isSet(opts, 4)) {
                            if (REUtil.isLowSurrogate(ch2) && o1 - 1 >= 0) {
                                // empty if block
                            }
                        } else {
                            if (REUtil.isLowSurrogate(ch2) && o1 - 1 >= 0) {
                                ch2 = REUtil.composeFromSurrogates(target.charAt(--o1), ch2);
                            }
                            if (!RegularExpression.isEOLChar(ch2)) {
                                return -1;
                            }
                        }
                        offset = --o1;
                    }
                    op = op.next;
                    break;
                }
                case 3: 
                case 4: {
                    int o1;
                    int ch;
                    if (dx > 0) {
                        if (offset >= con.limit) {
                            return -1;
                        }
                        ch = target.charAt(offset);
                        if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target.charAt(++offset));
                        }
                        RangeToken tok = op.getToken();
                        if (RegularExpression.isSet(opts, 2)) {
                            if (!(tok = tok.getCaseInsensitiveToken()).match(ch)) {
                                if (ch >= 65536) {
                                    return -1;
                                }
                                char uch = Character.toUpperCase((char)ch);
                                if (!tok.match(uch) && !tok.match(Character.toLowerCase(uch))) {
                                    return -1;
                                }
                            }
                        } else if (!tok.match(ch)) {
                            return -1;
                        }
                        ++offset;
                    } else {
                        o1 = offset - 1;
                        if (o1 >= con.limit || o1 < 0) {
                            return -1;
                        }
                        int ch3 = target.charAt(o1);
                        if (REUtil.isLowSurrogate(ch3) && o1 - 1 >= 0) {
                            ch3 = REUtil.composeFromSurrogates(target.charAt(--o1), ch3);
                        }
                        RangeToken tok = op.getToken();
                        if (RegularExpression.isSet(opts, 2)) {
                            if (!(tok = tok.getCaseInsensitiveToken()).match(ch3)) {
                                if (ch3 >= 65536) {
                                    return -1;
                                }
                                char uch = Character.toUpperCase((char)ch3);
                                if (!tok.match(uch) && !tok.match(Character.toLowerCase(uch))) {
                                    return -1;
                                }
                            }
                        } else if (!tok.match(ch3)) {
                            return -1;
                        }
                        offset = o1;
                    }
                    op = op.next;
                    break;
                }
                case 5: {
                    boolean go = false;
                    switch (op.getData()) {
                        case 94: {
                            if (!(RegularExpression.isSet(opts, 8) ? offset != con.start && (offset <= con.start || !RegularExpression.isEOLChar(target.charAt(offset - 1))) : offset != con.start)) break;
                            return -1;
                        }
                        case 64: {
                            if (offset == con.start || offset > con.start && RegularExpression.isEOLChar(target.charAt(offset - 1))) break;
                            return -1;
                        }
                        case 36: {
                            if (!(RegularExpression.isSet(opts, 8) ? offset != con.limit && (offset >= con.limit || !RegularExpression.isEOLChar(target.charAt(offset))) : !(offset == con.limit || offset + 1 == con.limit && RegularExpression.isEOLChar(target.charAt(offset)) || offset + 2 == con.limit && target.charAt(offset) == '\r' && target.charAt(offset + 1) == '\n'))) break;
                            return -1;
                        }
                        case 65: {
                            if (offset == con.start) break;
                            return -1;
                        }
                        case 90: {
                            if (offset == con.limit || offset + 1 == con.limit && RegularExpression.isEOLChar(target.charAt(offset)) || offset + 2 == con.limit && target.charAt(offset) == '\r' && target.charAt(offset + 1) == '\n') break;
                            return -1;
                        }
                        case 122: {
                            if (offset == con.limit) break;
                            return -1;
                        }
                        case 98: {
                            if (con.length == 0) {
                                return -1;
                            }
                            int after = RegularExpression.getWordType(target, con.start, con.limit, offset, opts);
                            if (after == 0) {
                                return -1;
                            }
                            int before = RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts);
                            if (after != before) break;
                            return -1;
                        }
                        case 66: {
                            if (con.length == 0) {
                                go = true;
                            } else {
                                int after = RegularExpression.getWordType(target, con.start, con.limit, offset, opts);
                                boolean bl = go = after == 0 || after == RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts);
                            }
                            if (go) break;
                            return -1;
                        }
                        case 60: {
                            if (con.length == 0 || offset == con.limit) {
                                return -1;
                            }
                            if (RegularExpression.getWordType(target, con.start, con.limit, offset, opts) == 1 && RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts) == 2) break;
                            return -1;
                        }
                        case 62: {
                            if (con.length == 0 || offset == con.start) {
                                return -1;
                            }
                            if (RegularExpression.getWordType(target, con.start, con.limit, offset, opts) == 2 && RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts) == 1) break;
                            return -1;
                        }
                    }
                    op = op.next;
                    break;
                }
                case 16: {
                    int refno = op.getData();
                    if (refno <= 0 || refno >= this.nofparen) {
                        throw new RuntimeException("Internal Error: Reference number must be more than zero: " + refno);
                    }
                    if (con.match.getBeginning(refno) < 0 || con.match.getEnd(refno) < 0) {
                        return -1;
                    }
                    int o2 = con.match.getBeginning(refno);
                    int literallen = con.match.getEnd(refno) - o2;
                    if (!RegularExpression.isSet(opts, 2)) {
                        if (dx > 0) {
                            if (!RegularExpression.regionMatches(target, offset, con.limit, o2, literallen)) {
                                return -1;
                            }
                            offset += literallen;
                        } else {
                            if (!RegularExpression.regionMatches(target, offset - literallen, con.limit, o2, literallen)) {
                                return -1;
                            }
                            offset -= literallen;
                        }
                    } else if (dx > 0) {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset, con.limit, o2, literallen)) {
                            return -1;
                        }
                        offset += literallen;
                    } else {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset - literallen, con.limit, o2, literallen)) {
                            return -1;
                        }
                        offset -= literallen;
                    }
                    op = op.next;
                    break;
                }
                case 6: {
                    String literal = op.getString();
                    int literallen = literal.length();
                    if (!RegularExpression.isSet(opts, 2)) {
                        if (dx > 0) {
                            if (!RegularExpression.regionMatches(target, offset, con.limit, literal, literallen)) {
                                return -1;
                            }
                            offset += literallen;
                        } else {
                            if (!RegularExpression.regionMatches(target, offset - literallen, con.limit, literal, literallen)) {
                                return -1;
                            }
                            offset -= literallen;
                        }
                    } else if (dx > 0) {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset, con.limit, literal, literallen)) {
                            return -1;
                        }
                        offset += literallen;
                    } else {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset - literallen, con.limit, literal, literallen)) {
                            return -1;
                        }
                        offset -= literallen;
                    }
                    op = op.next;
                    break;
                }
                case 7: {
                    int id = op.getData();
                    if (id >= 0) {
                        int previousOffset = con.offsets[id];
                        if (previousOffset < 0 || previousOffset != offset) {
                            con.offsets[id] = offset;
                        } else {
                            con.offsets[id] = -1;
                            op = op.next;
                            break;
                        }
                    }
                    int ret = this.matchString(con, op.getChild(), offset, dx, opts);
                    if (id >= 0) {
                        con.offsets[id] = -1;
                    }
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.next;
                    break;
                }
                case 9: {
                    int ret = this.matchString(con, op.getChild(), offset, dx, opts);
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.next;
                    break;
                }
                case 8: 
                case 10: {
                    int ret = this.matchString(con, op.next, offset, dx, opts);
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.getChild();
                    break;
                }
                case 11: {
                    for (int i = 0; i < op.size(); ++i) {
                        int ret = this.matchString(con, op.elementAt(i), offset, dx, opts);
                        if (ret < 0) continue;
                        return ret;
                    }
                    return -1;
                }
                case 15: {
                    int ret;
                    int refno = op.getData();
                    if (con.match != null && refno > 0) {
                        int save = con.match.getBeginning(refno);
                        con.match.setBeginning(refno, offset);
                        ret = this.matchString(con, op.next, offset, dx, opts);
                        if (ret < 0) {
                            con.match.setBeginning(refno, save);
                        }
                        return ret;
                    }
                    if (con.match != null && refno < 0) {
                        int index = -refno;
                        int save = con.match.getEnd(index);
                        con.match.setEnd(index, offset);
                        int ret2 = this.matchString(con, op.next, offset, dx, opts);
                        if (ret2 < 0) {
                            con.match.setEnd(index, save);
                        }
                        return ret2;
                    }
                    op = op.next;
                    break;
                }
                case 20: {
                    if (0 > this.matchString(con, op.getChild(), offset, 1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 21: {
                    if (0 <= this.matchString(con, op.getChild(), offset, 1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 22: {
                    if (0 > this.matchString(con, op.getChild(), offset, -1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 23: {
                    if (0 <= this.matchString(con, op.getChild(), offset, -1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 24: {
                    int ret = this.matchString(con, op.getChild(), offset, dx, opts);
                    if (ret < 0) {
                        return ret;
                    }
                    offset = ret;
                    op = op.next;
                    break;
                }
                case 25: {
                    int localopts = opts;
                    localopts |= op.getData();
                    int ret = this.matchString(con, op.getChild(), offset, dx, localopts &= ~op.getData2());
                    if (ret < 0) {
                        return ret;
                    }
                    offset = ret;
                    op = op.next;
                    break;
                }
                case 26: {
                    Op.ConditionOp cop = (Op.ConditionOp)op;
                    boolean matchp = false;
                    if (cop.refNumber > 0) {
                        if (cop.refNumber >= this.nofparen) {
                            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + cop.refNumber);
                        }
                        matchp = con.match.getBeginning(cop.refNumber) >= 0 && con.match.getEnd(cop.refNumber) >= 0;
                    } else {
                        boolean bl = matchp = 0 <= this.matchString(con, cop.condition, offset, dx, opts);
                    }
                    if (matchp) {
                        op = cop.yes;
                        break;
                    }
                    if (cop.no != null) {
                        op = cop.no;
                        break;
                    }
                    op = cop.next;
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown operation type: " + op.type);
                }
            }
        }
        return RegularExpression.isSet(opts, 512) && offset != con.limit ? -1 : offset;
    }

    private static final int getPreviousWordType(String target, int begin, int end, int offset, int opts) {
        int ret = RegularExpression.getWordType(target, begin, end, --offset, opts);
        while (ret == 0) {
            ret = RegularExpression.getWordType(target, begin, end, --offset, opts);
        }
        return ret;
    }

    private static final int getWordType(String target, int begin, int end, int offset, int opts) {
        if (offset < begin || offset >= end) {
            return 2;
        }
        return RegularExpression.getWordType0(target.charAt(offset), opts);
    }

    private static final boolean regionMatches(String text, int offset, int limit, String part, int partlen) {
        if (limit - offset < partlen) {
            return false;
        }
        return text.regionMatches(offset, part, 0, partlen);
    }

    private static final boolean regionMatches(String text, int offset, int limit, int offset2, int partlen) {
        if (limit - offset < partlen) {
            return false;
        }
        return text.regionMatches(offset, text, offset2, partlen);
    }

    private static final boolean regionMatchesIgnoreCase(String text, int offset, int limit, String part, int partlen) {
        return text.regionMatches(true, offset, part, 0, partlen);
    }

    private static final boolean regionMatchesIgnoreCase(String text, int offset, int limit, int offset2, int partlen) {
        if (limit - offset < partlen) {
            return false;
        }
        return text.regionMatches(true, offset, text, offset2, partlen);
    }

    public boolean matches(CharacterIterator target) {
        return this.matches(target, (Match)null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean matches(CharacterIterator target, Match match) {
        int matchStart;
        int o;
        int start = target.getBeginIndex();
        int end = target.getEndIndex();
        RegularExpression regularExpression = this;
        synchronized (regularExpression) {
            if (this.operations == null) {
                this.prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        Context con = null;
        Context context = this.context;
        synchronized (context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        if (RegularExpression.isSet(this.options, 512)) {
            int matchEnd = this.matchCharacterIterator(con, this.operations, con.start, 1, this.options);
            if (matchEnd == con.limit) {
                if (con.match != null) {
                    con.match.setBeginning(0, con.start);
                    con.match.setEnd(0, matchEnd);
                }
                con.inuse = false;
                return true;
            }
            return false;
        }
        if (this.fixedStringOnly) {
            int o2 = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o2 >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(0, o2);
                    con.match.setEnd(0, o2 + this.fixedString.length());
                }
                con.inuse = false;
                return true;
            }
            con.inuse = false;
            return false;
        }
        if (this.fixedString != null && (o = this.fixedStringTable.matches(target, con.start, con.limit)) < 0) {
            con.inuse = false;
            return false;
        }
        int limit = con.limit - this.minlength;
        int matchEnd = -1;
        if (this.operations != null && this.operations.type == 7 && this.operations.getChild().type == 0) {
            if (RegularExpression.isSet(this.options, 4)) {
                matchStart = con.start;
                matchEnd = this.matchCharacterIterator(con, this.operations, con.start, 1, this.options);
            } else {
                boolean previousIsEOL = true;
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    char ch = target.setIndex(matchStart);
                    if (RegularExpression.isEOLChar(ch)) {
                        previousIsEOL = true;
                        continue;
                    }
                    if (!previousIsEOL || 0 > (matchEnd = this.matchCharacterIterator(con, this.operations, matchStart, 1, this.options))) {
                        previousIsEOL = false;
                        continue;
                    }
                    break;
                }
            }
        } else if (this.firstChar != null) {
            RangeToken range = this.firstChar;
            if (RegularExpression.isSet(this.options, 2)) {
                range = this.firstChar.getCaseInsensitiveToken();
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    char ch1;
                    int ch = target.setIndex(matchStart);
                    if (!REUtil.isHighSurrogate(ch) || matchStart + 1 >= con.limit ? !range.match(ch) && !range.match(ch1 = Character.toUpperCase((char)ch)) && !range.match(Character.toLowerCase(ch1)) : !range.match(ch = REUtil.composeFromSurrogates(ch, target.setIndex(matchStart + 1)))) continue;
                    matchEnd = this.matchCharacterIterator(con, this.operations, matchStart, 1, this.options);
                    if (0 > matchEnd) {
                        continue;
                    }
                    break;
                }
            } else {
                for (matchStart = con.start; matchStart <= limit; ++matchStart) {
                    int ch = target.setIndex(matchStart);
                    if (REUtil.isHighSurrogate(ch) && matchStart + 1 < con.limit) {
                        ch = REUtil.composeFromSurrogates(ch, target.setIndex(matchStart + 1));
                    }
                    if (!range.match(ch) || 0 > (matchEnd = this.matchCharacterIterator(con, this.operations, matchStart, 1, this.options))) {
                        continue;
                    }
                    break;
                }
            }
        } else {
            for (matchStart = con.start; matchStart <= limit && 0 > (matchEnd = this.matchCharacterIterator(con, this.operations, matchStart, 1, this.options)); ++matchStart) {
            }
        }
        if (matchEnd >= 0) {
            if (con.match != null) {
                con.match.setBeginning(0, matchStart);
                con.match.setEnd(0, matchEnd);
            }
            con.inuse = false;
            return true;
        }
        con.inuse = false;
        return false;
    }

    private int matchCharacterIterator(Context con, Op op, int offset, int dx, int opts) {
        CharacterIterator target = con.ciTarget;
        while (op != null) {
            if (offset > con.limit || offset < con.start) {
                return -1;
            }
            switch (op.type) {
                case 1: {
                    int ch;
                    if (RegularExpression.isSet(opts, 2)) {
                        ch = op.getData();
                        if (dx > 0) {
                            if (offset >= con.limit || !RegularExpression.matchIgnoreCase(ch, target.setIndex(offset))) {
                                return -1;
                            }
                            ++offset;
                        } else {
                            int o1 = offset - 1;
                            if (o1 >= con.limit || o1 < 0 || !RegularExpression.matchIgnoreCase(ch, target.setIndex(o1))) {
                                return -1;
                            }
                            offset = o1;
                        }
                    } else {
                        ch = op.getData();
                        if (dx > 0) {
                            if (offset >= con.limit || ch != target.setIndex(offset)) {
                                return -1;
                            }
                            ++offset;
                        } else {
                            int o1 = offset - 1;
                            if (o1 >= con.limit || o1 < 0 || ch != target.setIndex(o1)) {
                                return -1;
                            }
                            offset = o1;
                        }
                    }
                    op = op.next;
                    break;
                }
                case 0: {
                    int o1;
                    int ch;
                    if (dx > 0) {
                        if (offset >= con.limit) {
                            return -1;
                        }
                        ch = target.setIndex(offset);
                        if (RegularExpression.isSet(opts, 4)) {
                            if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                                ++offset;
                            }
                        } else {
                            if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                                ch = REUtil.composeFromSurrogates(ch, target.setIndex(++offset));
                            }
                            if (RegularExpression.isEOLChar(ch)) {
                                return -1;
                            }
                        }
                        ++offset;
                    } else {
                        o1 = offset - 1;
                        if (o1 >= con.limit || o1 < 0) {
                            return -1;
                        }
                        int ch2 = target.setIndex(o1);
                        if (RegularExpression.isSet(opts, 4)) {
                            if (REUtil.isLowSurrogate(ch2) && o1 - 1 >= 0) {
                                // empty if block
                            }
                        } else {
                            if (REUtil.isLowSurrogate(ch2) && o1 - 1 >= 0) {
                                ch2 = REUtil.composeFromSurrogates(target.setIndex(--o1), ch2);
                            }
                            if (!RegularExpression.isEOLChar(ch2)) {
                                return -1;
                            }
                        }
                        offset = --o1;
                    }
                    op = op.next;
                    break;
                }
                case 3: 
                case 4: {
                    int o1;
                    int ch;
                    if (dx > 0) {
                        if (offset >= con.limit) {
                            return -1;
                        }
                        ch = target.setIndex(offset);
                        if (REUtil.isHighSurrogate(ch) && offset + 1 < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target.setIndex(++offset));
                        }
                        RangeToken tok = op.getToken();
                        if (RegularExpression.isSet(opts, 2)) {
                            if (!(tok = tok.getCaseInsensitiveToken()).match(ch)) {
                                if (ch >= 65536) {
                                    return -1;
                                }
                                char uch = Character.toUpperCase((char)ch);
                                if (!tok.match(uch) && !tok.match(Character.toLowerCase(uch))) {
                                    return -1;
                                }
                            }
                        } else if (!tok.match(ch)) {
                            return -1;
                        }
                        ++offset;
                    } else {
                        o1 = offset - 1;
                        if (o1 >= con.limit || o1 < 0) {
                            return -1;
                        }
                        int ch3 = target.setIndex(o1);
                        if (REUtil.isLowSurrogate(ch3) && o1 - 1 >= 0) {
                            ch3 = REUtil.composeFromSurrogates(target.setIndex(--o1), ch3);
                        }
                        RangeToken tok = op.getToken();
                        if (RegularExpression.isSet(opts, 2)) {
                            if (!(tok = tok.getCaseInsensitiveToken()).match(ch3)) {
                                if (ch3 >= 65536) {
                                    return -1;
                                }
                                char uch = Character.toUpperCase((char)ch3);
                                if (!tok.match(uch) && !tok.match(Character.toLowerCase(uch))) {
                                    return -1;
                                }
                            }
                        } else if (!tok.match(ch3)) {
                            return -1;
                        }
                        offset = o1;
                    }
                    op = op.next;
                    break;
                }
                case 5: {
                    boolean go = false;
                    switch (op.getData()) {
                        case 94: {
                            if (!(RegularExpression.isSet(opts, 8) ? offset != con.start && (offset <= con.start || !RegularExpression.isEOLChar(target.setIndex(offset - 1))) : offset != con.start)) break;
                            return -1;
                        }
                        case 64: {
                            if (offset == con.start || offset > con.start && RegularExpression.isEOLChar(target.setIndex(offset - 1))) break;
                            return -1;
                        }
                        case 36: {
                            if (!(RegularExpression.isSet(opts, 8) ? offset != con.limit && (offset >= con.limit || !RegularExpression.isEOLChar(target.setIndex(offset))) : !(offset == con.limit || offset + 1 == con.limit && RegularExpression.isEOLChar(target.setIndex(offset)) || offset + 2 == con.limit && target.setIndex(offset) == '\r' && target.setIndex(offset + 1) == '\n'))) break;
                            return -1;
                        }
                        case 65: {
                            if (offset == con.start) break;
                            return -1;
                        }
                        case 90: {
                            if (offset == con.limit || offset + 1 == con.limit && RegularExpression.isEOLChar(target.setIndex(offset)) || offset + 2 == con.limit && target.setIndex(offset) == '\r' && target.setIndex(offset + 1) == '\n') break;
                            return -1;
                        }
                        case 122: {
                            if (offset == con.limit) break;
                            return -1;
                        }
                        case 98: {
                            if (con.length == 0) {
                                return -1;
                            }
                            int after = RegularExpression.getWordType(target, con.start, con.limit, offset, opts);
                            if (after == 0) {
                                return -1;
                            }
                            int before = RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts);
                            if (after != before) break;
                            return -1;
                        }
                        case 66: {
                            if (con.length == 0) {
                                go = true;
                            } else {
                                int after = RegularExpression.getWordType(target, con.start, con.limit, offset, opts);
                                boolean bl = go = after == 0 || after == RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts);
                            }
                            if (go) break;
                            return -1;
                        }
                        case 60: {
                            if (con.length == 0 || offset == con.limit) {
                                return -1;
                            }
                            if (RegularExpression.getWordType(target, con.start, con.limit, offset, opts) == 1 && RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts) == 2) break;
                            return -1;
                        }
                        case 62: {
                            if (con.length == 0 || offset == con.start) {
                                return -1;
                            }
                            if (RegularExpression.getWordType(target, con.start, con.limit, offset, opts) == 2 && RegularExpression.getPreviousWordType(target, con.start, con.limit, offset, opts) == 1) break;
                            return -1;
                        }
                    }
                    op = op.next;
                    break;
                }
                case 16: {
                    int refno = op.getData();
                    if (refno <= 0 || refno >= this.nofparen) {
                        throw new RuntimeException("Internal Error: Reference number must be more than zero: " + refno);
                    }
                    if (con.match.getBeginning(refno) < 0 || con.match.getEnd(refno) < 0) {
                        return -1;
                    }
                    int o2 = con.match.getBeginning(refno);
                    int literallen = con.match.getEnd(refno) - o2;
                    if (!RegularExpression.isSet(opts, 2)) {
                        if (dx > 0) {
                            if (!RegularExpression.regionMatches(target, offset, con.limit, o2, literallen)) {
                                return -1;
                            }
                            offset += literallen;
                        } else {
                            if (!RegularExpression.regionMatches(target, offset - literallen, con.limit, o2, literallen)) {
                                return -1;
                            }
                            offset -= literallen;
                        }
                    } else if (dx > 0) {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset, con.limit, o2, literallen)) {
                            return -1;
                        }
                        offset += literallen;
                    } else {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset - literallen, con.limit, o2, literallen)) {
                            return -1;
                        }
                        offset -= literallen;
                    }
                    op = op.next;
                    break;
                }
                case 6: {
                    String literal = op.getString();
                    int literallen = literal.length();
                    if (!RegularExpression.isSet(opts, 2)) {
                        if (dx > 0) {
                            if (!RegularExpression.regionMatches(target, offset, con.limit, literal, literallen)) {
                                return -1;
                            }
                            offset += literallen;
                        } else {
                            if (!RegularExpression.regionMatches(target, offset - literallen, con.limit, literal, literallen)) {
                                return -1;
                            }
                            offset -= literallen;
                        }
                    } else if (dx > 0) {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset, con.limit, literal, literallen)) {
                            return -1;
                        }
                        offset += literallen;
                    } else {
                        if (!RegularExpression.regionMatchesIgnoreCase(target, offset - literallen, con.limit, literal, literallen)) {
                            return -1;
                        }
                        offset -= literallen;
                    }
                    op = op.next;
                    break;
                }
                case 7: {
                    int id = op.getData();
                    if (id >= 0) {
                        int previousOffset = con.offsets[id];
                        if (previousOffset < 0 || previousOffset != offset) {
                            con.offsets[id] = offset;
                        } else {
                            con.offsets[id] = -1;
                            op = op.next;
                            break;
                        }
                    }
                    int ret = this.matchCharacterIterator(con, op.getChild(), offset, dx, opts);
                    if (id >= 0) {
                        con.offsets[id] = -1;
                    }
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.next;
                    break;
                }
                case 9: {
                    int ret = this.matchCharacterIterator(con, op.getChild(), offset, dx, opts);
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.next;
                    break;
                }
                case 8: 
                case 10: {
                    int ret = this.matchCharacterIterator(con, op.next, offset, dx, opts);
                    if (ret >= 0) {
                        return ret;
                    }
                    op = op.getChild();
                    break;
                }
                case 11: {
                    for (int i = 0; i < op.size(); ++i) {
                        int ret = this.matchCharacterIterator(con, op.elementAt(i), offset, dx, opts);
                        if (ret < 0) continue;
                        return ret;
                    }
                    return -1;
                }
                case 15: {
                    int ret;
                    int refno = op.getData();
                    if (con.match != null && refno > 0) {
                        int save = con.match.getBeginning(refno);
                        con.match.setBeginning(refno, offset);
                        ret = this.matchCharacterIterator(con, op.next, offset, dx, opts);
                        if (ret < 0) {
                            con.match.setBeginning(refno, save);
                        }
                        return ret;
                    }
                    if (con.match != null && refno < 0) {
                        int index = -refno;
                        int save = con.match.getEnd(index);
                        con.match.setEnd(index, offset);
                        int ret2 = this.matchCharacterIterator(con, op.next, offset, dx, opts);
                        if (ret2 < 0) {
                            con.match.setEnd(index, save);
                        }
                        return ret2;
                    }
                    op = op.next;
                    break;
                }
                case 20: {
                    if (0 > this.matchCharacterIterator(con, op.getChild(), offset, 1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 21: {
                    if (0 <= this.matchCharacterIterator(con, op.getChild(), offset, 1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 22: {
                    if (0 > this.matchCharacterIterator(con, op.getChild(), offset, -1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 23: {
                    if (0 <= this.matchCharacterIterator(con, op.getChild(), offset, -1, opts)) {
                        return -1;
                    }
                    op = op.next;
                    break;
                }
                case 24: {
                    int ret = this.matchCharacterIterator(con, op.getChild(), offset, dx, opts);
                    if (ret < 0) {
                        return ret;
                    }
                    offset = ret;
                    op = op.next;
                    break;
                }
                case 25: {
                    int localopts = opts;
                    localopts |= op.getData();
                    int ret = this.matchCharacterIterator(con, op.getChild(), offset, dx, localopts &= ~op.getData2());
                    if (ret < 0) {
                        return ret;
                    }
                    offset = ret;
                    op = op.next;
                    break;
                }
                case 26: {
                    Op.ConditionOp cop = (Op.ConditionOp)op;
                    boolean matchp = false;
                    if (cop.refNumber > 0) {
                        if (cop.refNumber >= this.nofparen) {
                            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + cop.refNumber);
                        }
                        matchp = con.match.getBeginning(cop.refNumber) >= 0 && con.match.getEnd(cop.refNumber) >= 0;
                    } else {
                        boolean bl = matchp = 0 <= this.matchCharacterIterator(con, cop.condition, offset, dx, opts);
                    }
                    if (matchp) {
                        op = cop.yes;
                        break;
                    }
                    if (cop.no != null) {
                        op = cop.no;
                        break;
                    }
                    op = cop.next;
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown operation type: " + op.type);
                }
            }
        }
        return RegularExpression.isSet(opts, 512) && offset != con.limit ? -1 : offset;
    }

    private static final int getPreviousWordType(CharacterIterator target, int begin, int end, int offset, int opts) {
        int ret = RegularExpression.getWordType(target, begin, end, --offset, opts);
        while (ret == 0) {
            ret = RegularExpression.getWordType(target, begin, end, --offset, opts);
        }
        return ret;
    }

    private static final int getWordType(CharacterIterator target, int begin, int end, int offset, int opts) {
        if (offset < begin || offset >= end) {
            return 2;
        }
        return RegularExpression.getWordType0(target.setIndex(offset), opts);
    }

    private static final boolean regionMatches(CharacterIterator target, int offset, int limit, String part, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = 0;
        while (partlen-- > 0) {
            if (target.setIndex(offset++) == part.charAt(i++)) continue;
            return false;
        }
        return true;
    }

    private static final boolean regionMatches(CharacterIterator target, int offset, int limit, int offset2, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = offset2;
        while (partlen-- > 0) {
            if (target.setIndex(offset++) == target.setIndex(i++)) continue;
            return false;
        }
        return true;
    }

    private static final boolean regionMatchesIgnoreCase(CharacterIterator target, int offset, int limit, String part, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = 0;
        while (partlen-- > 0) {
            char uch2;
            char uch1;
            char ch2;
            char ch1;
            if ((ch1 = target.setIndex(offset++)) == (ch2 = part.charAt(i++)) || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) continue;
            return false;
        }
        return true;
    }

    private static final boolean regionMatchesIgnoreCase(CharacterIterator target, int offset, int limit, int offset2, int partlen) {
        if (offset < 0) {
            return false;
        }
        if (limit - offset < partlen) {
            return false;
        }
        int i = offset2;
        while (partlen-- > 0) {
            char uch2;
            char uch1;
            char ch2;
            char ch1;
            if ((ch1 = target.setIndex(offset++)) == (ch2 = target.setIndex(i++)) || (uch1 = Character.toUpperCase(ch1)) == (uch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) continue;
            return false;
        }
        return true;
    }

    void prepare() {
        RangeToken firstChar;
        int fresult;
        this.compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!RegularExpression.isSet(this.options, 128) && !RegularExpression.isSet(this.options, 512) && (fresult = this.tokentree.analyzeFirstCharacter(firstChar = Token.createRange(), this.options)) == 1) {
            firstChar.compactRanges();
            this.firstChar = firstChar;
        }
        if (this.operations != null && (this.operations.type == 6 || this.operations.type == 1) && this.operations.next == null) {
            this.fixedStringOnly = true;
            if (this.operations.type == 6) {
                this.fixedString = this.operations.getString();
            } else if (this.operations.getData() >= 65536) {
                this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
            } else {
                char[] ac = new char[]{(char)this.operations.getData()};
                this.fixedString = new String(ac);
            }
            this.fixedStringOptions = this.options;
            this.fixedStringTable = new BMPattern(this.fixedString, 256, RegularExpression.isSet(this.fixedStringOptions, 2));
        } else if (!RegularExpression.isSet(this.options, 256) && !RegularExpression.isSet(this.options, 512)) {
            Token.FixedStringContainer container = new Token.FixedStringContainer();
            this.tokentree.findFixedString(container, this.options);
            this.fixedString = container.token == null ? null : container.token.getString();
            this.fixedStringOptions = container.options;
            if (this.fixedString != null && this.fixedString.length() < 2) {
                this.fixedString = null;
            }
            if (this.fixedString != null) {
                this.fixedStringTable = new BMPattern(this.fixedString, 256, RegularExpression.isSet(this.fixedStringOptions, 2));
            }
        }
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag;
    }

    public RegularExpression(String regex) throws ParseException {
        this.setPattern(regex, null);
    }

    public RegularExpression(String regex, String options) throws ParseException {
        this.setPattern(regex, options);
    }

    RegularExpression(String regex, Token tok, int parens, boolean hasBackReferences, int options) {
        this.regex = regex;
        this.tokentree = tok;
        this.nofparen = parens;
        this.options = options;
        this.hasBackReferences = hasBackReferences;
    }

    public void setPattern(String newPattern) throws ParseException {
        this.setPattern(newPattern, this.options);
    }

    private void setPattern(String newPattern, int options) throws ParseException {
        this.regex = newPattern;
        this.options = options;
        RegexParser rp = RegularExpression.isSet(this.options, 512) ? new ParserForXMLSchema() : new RegexParser();
        this.tokentree = rp.parse(this.regex, this.options);
        this.nofparen = rp.parennumber;
        this.hasBackReferences = rp.hasBackReferences;
        this.operations = null;
        this.context = null;
    }

    public void setPattern(String newPattern, String options) throws ParseException {
        this.setPattern(newPattern, REUtil.parseOptions(options));
    }

    public String getPattern() {
        return this.regex;
    }

    public String toString() {
        return this.tokentree.toString(this.options);
    }

    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RegularExpression)) {
            return false;
        }
        RegularExpression r = (RegularExpression)obj;
        return this.regex.equals(r.regex) && this.options == r.options;
    }

    boolean equals(String pattern, int options) {
        return this.regex.equals(pattern) && this.options == options;
    }

    public int hashCode() {
        return (this.regex + "/" + this.getOptions()).hashCode();
    }

    public int getNumberOfGroups() {
        return this.nofparen;
    }

    private static final int getWordType0(char ch, int opts) {
        if (!RegularExpression.isSet(opts, 64)) {
            if (RegularExpression.isSet(opts, 32)) {
                return Token.getRange("IsWord", true).match(ch) ? 1 : 2;
            }
            return RegularExpression.isWordChar(ch) ? 1 : 2;
        }
        switch (Character.getType(ch)) {
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                return 1;
            }
            case 6: 
            case 7: 
            case 16: {
                return 0;
            }
            case 15: {
                switch (ch) {
                    case '\t': 
                    case '\n': 
                    case '\u000b': 
                    case '\f': 
                    case '\r': {
                        return 2;
                    }
                }
                return 0;
            }
        }
        return 2;
    }

    private static final boolean isEOLChar(int ch) {
        return ch == 10 || ch == 13 || ch == 8232 || ch == 8233;
    }

    private static final boolean isWordChar(int ch) {
        if (ch == 95) {
            return true;
        }
        if (ch < 48) {
            return false;
        }
        if (ch > 122) {
            return false;
        }
        if (ch <= 57) {
            return true;
        }
        if (ch < 65) {
            return false;
        }
        if (ch <= 90) {
            return true;
        }
        return ch >= 97;
    }

    private static final boolean matchIgnoreCase(int chardata, int ch) {
        char uch2;
        if (chardata == ch) {
            return true;
        }
        if (chardata > 65535 || ch > 65535) {
            return false;
        }
        char uch1 = Character.toUpperCase((char)chardata);
        if (uch1 == (uch2 = Character.toUpperCase((char)ch))) {
            return true;
        }
        return Character.toLowerCase(uch1) == Character.toLowerCase(uch2);
    }

    static final class Context {
        CharacterIterator ciTarget;
        String strTarget;
        char[] charTarget;
        int start;
        int limit;
        int length;
        Match match;
        boolean inuse = false;
        int[] offsets;

        Context() {
        }

        private void resetCommon(int nofclosures) {
            this.length = this.limit - this.start;
            this.inuse = true;
            this.match = null;
            if (this.offsets == null || this.offsets.length != nofclosures) {
                this.offsets = new int[nofclosures];
            }
            for (int i = 0; i < nofclosures; ++i) {
                this.offsets[i] = -1;
            }
        }

        void reset(CharacterIterator target, int start, int limit, int nofclosures) {
            this.ciTarget = target;
            this.start = start;
            this.limit = limit;
            this.resetCommon(nofclosures);
        }

        void reset(String target, int start, int limit, int nofclosures) {
            this.strTarget = target;
            this.start = start;
            this.limit = limit;
            this.resetCommon(nofclosures);
        }

        void reset(char[] target, int start, int limit, int nofclosures) {
            this.charTarget = target;
            this.start = start;
            this.limit = limit;
            this.resetCommon(nofclosures);
        }
    }
}

