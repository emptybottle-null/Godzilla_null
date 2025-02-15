/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.xmlbeans.impl.regex.ParseException;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.apache.xmlbeans.impl.regex.RangeToken;
import org.apache.xmlbeans.impl.regex.Token;

class RegexParser {
    static final int T_CHAR = 0;
    static final int T_EOF = 1;
    static final int T_OR = 2;
    static final int T_STAR = 3;
    static final int T_PLUS = 4;
    static final int T_QUESTION = 5;
    static final int T_LPAREN = 6;
    static final int T_RPAREN = 7;
    static final int T_DOT = 8;
    static final int T_LBRACKET = 9;
    static final int T_BACKSOLIDUS = 10;
    static final int T_CARET = 11;
    static final int T_DOLLAR = 12;
    static final int T_LPAREN2 = 13;
    static final int T_LOOKAHEAD = 14;
    static final int T_NEGATIVELOOKAHEAD = 15;
    static final int T_LOOKBEHIND = 16;
    static final int T_NEGATIVELOOKBEHIND = 17;
    static final int T_INDEPENDENT = 18;
    static final int T_SET_OPERATIONS = 19;
    static final int T_POSIX_CHARCLASS_START = 20;
    static final int T_COMMENT = 21;
    static final int T_MODIFIERS = 22;
    static final int T_CONDITION = 23;
    static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
    int offset;
    String regex;
    int regexlen;
    int options;
    ResourceBundle resources;
    int chardata;
    int nexttoken;
    protected static final int S_NORMAL = 0;
    protected static final int S_INBRACKETS = 1;
    protected static final int S_INXBRACKETS = 2;
    int context = 0;
    int parennumber = 1;
    boolean hasBackReferences;
    Vector references = null;

    public RegexParser() {
        this.setLocale(Locale.getDefault());
    }

    public RegexParser(Locale locale) {
        this.setLocale(locale);
    }

    public void setLocale(Locale locale) {
        try {
            this.resources = ResourceBundle.getBundle("org.apache.xmlbeans.impl.regex.message", locale);
        } catch (MissingResourceException mre) {
            throw new RuntimeException("Installation Problem???  Couldn't load messages: " + mre.getMessage());
        }
    }

    final ParseException ex(String key, int loc) {
        return new ParseException(this.resources.getString(key), loc);
    }

    private final boolean isSet(int flag) {
        return (this.options & flag) == flag;
    }

    synchronized Token parse(String regex, int options) throws ParseException {
        this.options = options;
        this.offset = 0;
        this.setContext(0);
        this.parennumber = 1;
        this.hasBackReferences = false;
        this.regex = regex;
        if (this.isSet(16)) {
            this.regex = REUtil.stripExtendedComment(this.regex);
        }
        this.regexlen = this.regex.length();
        this.next();
        Token ret = this.parseRegex();
        if (this.offset != this.regexlen) {
            throw this.ex("parser.parse.1", this.offset);
        }
        if (this.references != null) {
            for (int i = 0; i < this.references.size(); ++i) {
                ReferencePosition position = (ReferencePosition)this.references.elementAt(i);
                if (this.parennumber > position.refNumber) continue;
                throw this.ex("parser.parse.2", position.position);
            }
            this.references.removeAllElements();
        }
        return ret;
    }

    protected final void setContext(int con) {
        this.context = con;
    }

    final int read() {
        return this.nexttoken;
    }

    final void next() {
        int ret;
        if (this.offset >= this.regexlen) {
            this.chardata = -1;
            this.nexttoken = 1;
            return;
        }
        char ch = this.regex.charAt(this.offset++);
        this.chardata = ch;
        if (this.context == 1) {
            int ret2;
            switch (ch) {
                case '\\': {
                    ret2 = 10;
                    if (this.offset >= this.regexlen) {
                        throw this.ex("parser.next.1", this.offset - 1);
                    }
                    this.chardata = this.regex.charAt(this.offset++);
                    break;
                }
                case '-': {
                    if (this.isSet(512) && this.offset < this.regexlen && this.regex.charAt(this.offset) == '[') {
                        ++this.offset;
                        ret2 = 24;
                        break;
                    }
                    ret2 = 0;
                    break;
                }
                case '[': {
                    if (!this.isSet(512) && this.offset < this.regexlen && this.regex.charAt(this.offset) == ':') {
                        ++this.offset;
                        ret2 = 20;
                        break;
                    }
                }
                default: {
                    char low;
                    if (REUtil.isHighSurrogate(ch) && this.offset < this.regexlen && REUtil.isLowSurrogate(low = this.regex.charAt(this.offset))) {
                        this.chardata = REUtil.composeFromSurrogates(ch, low);
                        ++this.offset;
                    }
                    ret2 = 0;
                }
            }
            this.nexttoken = ret2;
            return;
        }
        block5 : switch (ch) {
            case '|': {
                ret = 2;
                break;
            }
            case '*': {
                ret = 3;
                break;
            }
            case '+': {
                ret = 4;
                break;
            }
            case '?': {
                ret = 5;
                break;
            }
            case ')': {
                ret = 7;
                break;
            }
            case '.': {
                ret = 8;
                break;
            }
            case '[': {
                ret = 9;
                break;
            }
            case '^': {
                ret = 11;
                break;
            }
            case '$': {
                ret = 12;
                break;
            }
            case '(': {
                ret = 6;
                if (this.offset >= this.regexlen || this.regex.charAt(this.offset) != '?') break;
                if (++this.offset >= this.regexlen) {
                    throw this.ex("parser.next.2", this.offset - 1);
                }
                ch = this.regex.charAt(this.offset++);
                switch (ch) {
                    case ':': {
                        ret = 13;
                        break block5;
                    }
                    case '=': {
                        ret = 14;
                        break block5;
                    }
                    case '!': {
                        ret = 15;
                        break block5;
                    }
                    case '[': {
                        ret = 19;
                        break block5;
                    }
                    case '>': {
                        ret = 18;
                        break block5;
                    }
                    case '<': {
                        if (this.offset >= this.regexlen) {
                            throw this.ex("parser.next.2", this.offset - 3);
                        }
                        if ((ch = this.regex.charAt(this.offset++)) == '=') {
                            ret = 16;
                            break block5;
                        }
                        if (ch == '!') {
                            ret = 17;
                            break block5;
                        }
                        throw this.ex("parser.next.3", this.offset - 3);
                    }
                    case '#': {
                        while (this.offset < this.regexlen && (ch = this.regex.charAt(this.offset++)) != ')') {
                        }
                        if (ch != ')') {
                            throw this.ex("parser.next.4", this.offset - 1);
                        }
                        ret = 21;
                        break block5;
                    }
                }
                if (ch == '-' || 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z') {
                    --this.offset;
                    ret = 22;
                    break;
                }
                if (ch == '(') {
                    ret = 23;
                    break;
                }
                throw this.ex("parser.next.2", this.offset - 2);
            }
            case '\\': {
                ret = 10;
                if (this.offset >= this.regexlen) {
                    throw this.ex("parser.next.1", this.offset - 1);
                }
                this.chardata = this.regex.charAt(this.offset++);
                break;
            }
            default: {
                ret = 0;
            }
        }
        this.nexttoken = ret;
    }

    Token parseRegex() throws ParseException {
        Token tok = this.parseTerm();
        Token.UnionToken parent = null;
        while (this.read() == 2) {
            this.next();
            if (parent == null) {
                parent = Token.createUnion();
                ((Token)parent).addChild(tok);
                tok = parent;
            }
            tok.addChild(this.parseTerm());
        }
        return tok;
    }

    Token parseTerm() throws ParseException {
        int ch = this.read();
        if (ch == 2 || ch == 7 || ch == 1) {
            return Token.createEmpty();
        }
        Token tok = this.parseFactor();
        Token.UnionToken concat = null;
        while ((ch = this.read()) != 2 && ch != 7 && ch != 1) {
            if (concat == null) {
                concat = Token.createConcat();
                ((Token)concat).addChild(tok);
                tok = concat;
            }
            ((Token)concat).addChild(this.parseFactor());
        }
        return tok;
    }

    Token processCaret() throws ParseException {
        this.next();
        return Token.token_linebeginning;
    }

    Token processDollar() throws ParseException {
        this.next();
        return Token.token_lineend;
    }

    Token processLookahead() throws ParseException {
        this.next();
        Token.ParenToken tok = Token.createLook(20, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processNegativelookahead() throws ParseException {
        this.next();
        Token.ParenToken tok = Token.createLook(21, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processLookbehind() throws ParseException {
        this.next();
        Token.ParenToken tok = Token.createLook(22, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processNegativelookbehind() throws ParseException {
        this.next();
        Token.ParenToken tok = Token.createLook(23, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processBacksolidus_A() throws ParseException {
        this.next();
        return Token.token_stringbeginning;
    }

    Token processBacksolidus_Z() throws ParseException {
        this.next();
        return Token.token_stringend2;
    }

    Token processBacksolidus_z() throws ParseException {
        this.next();
        return Token.token_stringend;
    }

    Token processBacksolidus_b() throws ParseException {
        this.next();
        return Token.token_wordedge;
    }

    Token processBacksolidus_B() throws ParseException {
        this.next();
        return Token.token_not_wordedge;
    }

    Token processBacksolidus_lt() throws ParseException {
        this.next();
        return Token.token_wordbeginning;
    }

    Token processBacksolidus_gt() throws ParseException {
        this.next();
        return Token.token_wordend;
    }

    Token processStar(Token tok) throws ParseException {
        this.next();
        if (this.read() == 5) {
            this.next();
            return Token.createNGClosure(tok);
        }
        return Token.createClosure(tok);
    }

    Token processPlus(Token tok) throws ParseException {
        this.next();
        if (this.read() == 5) {
            this.next();
            return Token.createConcat(tok, Token.createNGClosure(tok));
        }
        return Token.createConcat(tok, Token.createClosure(tok));
    }

    Token processQuestion(Token tok) throws ParseException {
        this.next();
        Token.UnionToken par = Token.createUnion();
        if (this.read() == 5) {
            this.next();
            ((Token)par).addChild(Token.createEmpty());
            ((Token)par).addChild(tok);
        } else {
            ((Token)par).addChild(tok);
            ((Token)par).addChild(Token.createEmpty());
        }
        return par;
    }

    boolean checkQuestion(int off) {
        return off < this.regexlen && this.regex.charAt(off) == '?';
    }

    Token processParen() throws ParseException {
        this.next();
        int p = this.parennumber++;
        Token.ParenToken tok = Token.createParen(this.parseRegex(), p);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processParen2() throws ParseException {
        this.next();
        Token.ParenToken tok = Token.createParen(this.parseRegex(), 0);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processCondition() throws ParseException {
        if (this.offset + 1 >= this.regexlen) {
            throw this.ex("parser.factor.4", this.offset);
        }
        int refno = -1;
        Token condition = null;
        char ch = this.regex.charAt(this.offset);
        if ('1' <= ch && ch <= '9') {
            refno = ch - 48;
            this.hasBackReferences = true;
            if (this.references == null) {
                this.references = new Vector();
            }
            this.references.addElement(new ReferencePosition(refno, this.offset));
            ++this.offset;
            if (this.regex.charAt(this.offset) != ')') {
                throw this.ex("parser.factor.1", this.offset);
            }
            ++this.offset;
        } else {
            if (ch == '?') {
                --this.offset;
            }
            this.next();
            condition = this.parseFactor();
            switch (condition.type) {
                case 20: 
                case 21: 
                case 22: 
                case 23: {
                    break;
                }
                case 8: {
                    if (this.read() == 7) break;
                    throw this.ex("parser.factor.1", this.offset - 1);
                }
                default: {
                    throw this.ex("parser.factor.5", this.offset);
                }
            }
        }
        this.next();
        Token yesPattern = this.parseRegex();
        Token noPattern = null;
        if (yesPattern.type == 2) {
            if (yesPattern.size() != 2) {
                throw this.ex("parser.factor.6", this.offset);
            }
            noPattern = yesPattern.getChild(1);
            yesPattern = yesPattern.getChild(0);
        }
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return Token.createCondition(refno, condition, yesPattern, noPattern);
    }

    Token processModifiers() throws ParseException {
        Token.ModifierToken tok;
        int v;
        int add = 0;
        int mask = 0;
        int ch = -1;
        while (this.offset < this.regexlen && (v = REUtil.getOptionValue(ch = (int)this.regex.charAt(this.offset))) != 0) {
            add |= v;
            ++this.offset;
        }
        if (this.offset >= this.regexlen) {
            throw this.ex("parser.factor.2", this.offset - 1);
        }
        if (ch == 45) {
            ++this.offset;
            while (this.offset < this.regexlen && (v = REUtil.getOptionValue(ch = (int)this.regex.charAt(this.offset))) != 0) {
                mask |= v;
                ++this.offset;
            }
            if (this.offset >= this.regexlen) {
                throw this.ex("parser.factor.2", this.offset - 1);
            }
        }
        if (ch == 58) {
            ++this.offset;
            this.next();
            tok = Token.createModifierGroup(this.parseRegex(), add, mask);
            if (this.read() != 7) {
                throw this.ex("parser.factor.1", this.offset - 1);
            }
            this.next();
        } else if (ch == 41) {
            ++this.offset;
            this.next();
            tok = Token.createModifierGroup(this.parseRegex(), add, mask);
        } else {
            throw this.ex("parser.factor.3", this.offset);
        }
        return tok;
    }

    Token processIndependent() throws ParseException {
        this.next();
        Token.ParenToken tok = Token.createLook(24, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token processBacksolidus_c() throws ParseException {
        char ch2;
        if (this.offset >= this.regexlen || ((ch2 = this.regex.charAt(this.offset++)) & 0xFFE0) != 64) {
            throw this.ex("parser.atom.1", this.offset - 1);
        }
        this.next();
        return Token.createChar(ch2 - 64);
    }

    Token processBacksolidus_C() throws ParseException {
        throw this.ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_i() throws ParseException {
        Token.CharToken tok = Token.createChar(105);
        this.next();
        return tok;
    }

    Token processBacksolidus_I() throws ParseException {
        throw this.ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_g() throws ParseException {
        this.next();
        return Token.getGraphemePattern();
    }

    Token processBacksolidus_X() throws ParseException {
        this.next();
        return Token.getCombiningCharacterSequence();
    }

    Token processBackreference() throws ParseException {
        int refnum = this.chardata - 48;
        Token.StringToken tok = Token.createBackReference(refnum);
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(refnum, this.offset - 2));
        this.next();
        return tok;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    Token parseFactor() throws ParseException {
        int ch = this.read();
        switch (ch) {
            case 11: {
                return this.processCaret();
            }
            case 12: {
                return this.processDollar();
            }
            case 14: {
                return this.processLookahead();
            }
            case 15: {
                return this.processNegativelookahead();
            }
            case 16: {
                return this.processLookbehind();
            }
            case 17: {
                return this.processNegativelookbehind();
            }
            case 21: {
                this.next();
                return Token.createEmpty();
            }
            case 10: {
                switch (this.chardata) {
                    case 65: {
                        return this.processBacksolidus_A();
                    }
                    case 90: {
                        return this.processBacksolidus_Z();
                    }
                    case 122: {
                        return this.processBacksolidus_z();
                    }
                    case 98: {
                        return this.processBacksolidus_b();
                    }
                    case 66: {
                        return this.processBacksolidus_B();
                    }
                    case 60: {
                        return this.processBacksolidus_lt();
                    }
                    case 62: {
                        return this.processBacksolidus_gt();
                    }
                }
            }
        }
        Token tok = this.parseAtom();
        ch = this.read();
        switch (ch) {
            case 3: {
                return this.processStar(tok);
            }
            case 4: {
                return this.processPlus(tok);
            }
            case 5: {
                return this.processQuestion(tok);
            }
            case 0: {
                if (this.chardata != 123 || this.offset >= this.regexlen) return tok;
                int off = this.offset;
                int min = 0;
                int max = -1;
                char c = this.regex.charAt(off++);
                ch = c;
                if (c < '0' || ch > 57) throw this.ex("parser.quantifier.1", this.offset);
                min = ch - 48;
                while (off < this.regexlen) {
                    char c2 = this.regex.charAt(off++);
                    ch = c2;
                    if (c2 < '0' || ch > 57) break;
                    if ((min = min * 10 + ch - 48) >= 0) continue;
                    throw this.ex("parser.quantifier.5", this.offset);
                }
                max = min;
                if (ch == 44) {
                    if (off >= this.regexlen) {
                        throw this.ex("parser.quantifier.3", this.offset);
                    }
                    char c3 = this.regex.charAt(off++);
                    ch = c3;
                    if (c3 >= '0' && ch <= 57) {
                        max = ch - 48;
                        while (off < this.regexlen) {
                            char c4 = this.regex.charAt(off++);
                            ch = c4;
                            if (c4 < '0' || ch > 57) break;
                            if ((max = max * 10 + ch - 48) >= 0) continue;
                            throw this.ex("parser.quantifier.5", this.offset);
                        }
                        if (min > max) {
                            throw this.ex("parser.quantifier.4", this.offset);
                        }
                    } else {
                        max = -1;
                    }
                }
                if (ch != 125) {
                    throw this.ex("parser.quantifier.2", this.offset);
                }
                if (this.checkQuestion(off)) {
                    tok = Token.createNGClosure(tok);
                    this.offset = off + 1;
                } else {
                    tok = Token.createClosure(tok);
                    this.offset = off;
                }
                tok.setMin(min);
                tok.setMax(max);
                this.next();
            }
        }
        return tok;
    }

    Token parseAtom() throws ParseException {
        int ch = this.read();
        Token tok = null;
        switch (ch) {
            case 6: {
                return this.processParen();
            }
            case 13: {
                return this.processParen2();
            }
            case 23: {
                return this.processCondition();
            }
            case 22: {
                return this.processModifiers();
            }
            case 18: {
                return this.processIndependent();
            }
            case 8: {
                this.next();
                tok = Token.token_dot;
                break;
            }
            case 9: {
                return this.parseCharacterClass(true);
            }
            case 19: {
                return this.parseSetOperations();
            }
            case 10: {
                switch (this.chardata) {
                    case 68: 
                    case 83: 
                    case 87: 
                    case 100: 
                    case 115: 
                    case 119: {
                        tok = this.getTokenForShorthand(this.chardata);
                        this.next();
                        return tok;
                    }
                    case 101: 
                    case 102: 
                    case 110: 
                    case 114: 
                    case 116: 
                    case 117: 
                    case 118: 
                    case 120: {
                        int ch2 = this.decodeEscaped();
                        if (ch2 < 65536) {
                            tok = Token.createChar(ch2);
                            break;
                        }
                        tok = Token.createString(REUtil.decomposeToSurrogates(ch2));
                        break;
                    }
                    case 99: {
                        return this.processBacksolidus_c();
                    }
                    case 67: {
                        return this.processBacksolidus_C();
                    }
                    case 105: {
                        return this.processBacksolidus_i();
                    }
                    case 73: {
                        return this.processBacksolidus_I();
                    }
                    case 103: {
                        return this.processBacksolidus_g();
                    }
                    case 88: {
                        return this.processBacksolidus_X();
                    }
                    case 49: 
                    case 50: 
                    case 51: 
                    case 52: 
                    case 53: 
                    case 54: 
                    case 55: 
                    case 56: 
                    case 57: {
                        return this.processBackreference();
                    }
                    case 80: 
                    case 112: {
                        int pstart = this.offset;
                        tok = this.processBacksolidus_pP(this.chardata);
                        if (tok != null) break;
                        throw this.ex("parser.atom.5", pstart);
                    }
                    default: {
                        tok = Token.createChar(this.chardata);
                    }
                }
                this.next();
                break;
            }
            case 0: {
                if (this.chardata == 93 || this.chardata == 123 || this.chardata == 125) {
                    throw this.ex("parser.atom.4", this.offset - 1);
                }
                tok = Token.createChar(this.chardata);
                int high = this.chardata;
                this.next();
                if (!REUtil.isHighSurrogate(high) || this.read() != 0 || !REUtil.isLowSurrogate(this.chardata)) break;
                char[] sur = new char[]{(char)high, (char)this.chardata};
                tok = Token.createParen(Token.createString(new String(sur)), 0);
                this.next();
                break;
            }
            default: {
                throw this.ex("parser.atom.4", this.offset - 1);
            }
        }
        return tok;
    }

    protected RangeToken processBacksolidus_pP(int c) throws ParseException {
        this.next();
        if (this.read() != 0 || this.chardata != 123) {
            throw this.ex("parser.atom.2", this.offset - 1);
        }
        boolean positive = c == 112;
        int namestart = this.offset;
        int nameend = this.regex.indexOf(125, namestart);
        if (nameend < 0) {
            throw this.ex("parser.atom.3", this.offset);
        }
        String pname = this.regex.substring(namestart, nameend);
        this.offset = nameend + 1;
        return Token.getRange(pname, positive, this.isSet(512));
    }

    int processCIinCharacterClass(RangeToken tok, int c) {
        return this.decodeEscaped();
    }

    protected RangeToken parseCharacterClass(boolean useNrange) throws ParseException {
        int type;
        RangeToken tok;
        this.setContext(1);
        this.next();
        boolean nrange = false;
        RangeToken base = null;
        if (this.read() == 0 && this.chardata == 94) {
            nrange = true;
            this.next();
            if (useNrange) {
                tok = Token.createNRange();
            } else {
                base = Token.createRange();
                base.addRange(0, 0x10FFFF);
                tok = Token.createRange();
            }
        } else {
            tok = Token.createRange();
        }
        boolean firstloop = true;
        while ((type = this.read()) != 1 && (type != 0 || this.chardata != 93 || firstloop)) {
            boolean end;
            int c;
            block27: {
                block26: {
                    firstloop = false;
                    c = this.chardata;
                    end = false;
                    if (type != 10) break block26;
                    switch (c) {
                        case 68: 
                        case 83: 
                        case 87: 
                        case 100: 
                        case 115: 
                        case 119: {
                            tok.mergeRanges(this.getTokenForShorthand(c));
                            end = true;
                            break;
                        }
                        case 67: 
                        case 73: 
                        case 99: 
                        case 105: {
                            c = this.processCIinCharacterClass(tok, c);
                            if (c < 0) {
                                end = true;
                                break;
                            }
                            break block27;
                        }
                        case 80: 
                        case 112: {
                            int pstart = this.offset;
                            RangeToken tok2 = this.processBacksolidus_pP(c);
                            if (tok2 == null) {
                                throw this.ex("parser.atom.5", pstart);
                            }
                            tok.mergeRanges(tok2);
                            end = true;
                            break;
                        }
                        default: {
                            c = this.decodeEscaped();
                            break;
                        }
                    }
                    break block27;
                }
                if (type == 20) {
                    String name;
                    RangeToken range;
                    int nameend = this.regex.indexOf(58, this.offset);
                    if (nameend < 0) {
                        throw this.ex("parser.cc.1", this.offset);
                    }
                    boolean positive = true;
                    if (this.regex.charAt(this.offset) == '^') {
                        ++this.offset;
                        positive = false;
                    }
                    if ((range = Token.getRange(name = this.regex.substring(this.offset, nameend), positive, this.isSet(512))) == null) {
                        throw this.ex("parser.cc.3", this.offset);
                    }
                    tok.mergeRanges(range);
                    end = true;
                    if (nameend + 1 >= this.regexlen || this.regex.charAt(nameend + 1) != ']') {
                        throw this.ex("parser.cc.1", nameend);
                    }
                    this.offset = nameend + 2;
                }
            }
            this.next();
            if (!end) {
                if (this.read() != 0 || this.chardata != 45) {
                    tok.addRange(c, c);
                } else {
                    this.next();
                    type = this.read();
                    if (type == 1) {
                        throw this.ex("parser.cc.2", this.offset);
                    }
                    if (type == 0 && this.chardata == 93) {
                        tok.addRange(c, c);
                        tok.addRange(45, 45);
                    } else {
                        int rangeend = this.chardata;
                        if (type == 10) {
                            rangeend = this.decodeEscaped();
                        }
                        this.next();
                        tok.addRange(c, rangeend);
                    }
                }
            }
            if (!this.isSet(1024) || this.read() != 0 || this.chardata != 44) continue;
            this.next();
        }
        if (this.read() == 1) {
            throw this.ex("parser.cc.2", this.offset);
        }
        if (!useNrange && nrange) {
            base.subtractRanges(tok);
            tok = base;
        }
        tok.sortRanges();
        tok.compactRanges();
        this.setContext(0);
        this.next();
        return tok;
    }

    protected RangeToken parseSetOperations() throws ParseException {
        int type;
        RangeToken tok = this.parseCharacterClass(false);
        while ((type = this.read()) != 7) {
            int ch = this.chardata;
            if (type == 0 && (ch == 45 || ch == 38) || type == 4) {
                this.next();
                if (this.read() != 9) {
                    throw this.ex("parser.ope.1", this.offset - 1);
                }
                RangeToken t2 = this.parseCharacterClass(false);
                if (type == 4) {
                    tok.mergeRanges(t2);
                    continue;
                }
                if (ch == 45) {
                    tok.subtractRanges(t2);
                    continue;
                }
                if (ch == 38) {
                    tok.intersectRanges(t2);
                    continue;
                }
                throw new RuntimeException("ASSERT");
            }
            throw this.ex("parser.ope.2", this.offset - 1);
        }
        this.next();
        return tok;
    }

    Token getTokenForShorthand(int ch) {
        Token tok;
        switch (ch) {
            case 100: {
                tok = this.isSet(32) ? Token.getRange("Nd", true) : Token.token_0to9;
                break;
            }
            case 68: {
                tok = this.isSet(32) ? Token.getRange("Nd", false) : Token.token_not_0to9;
                break;
            }
            case 119: {
                tok = this.isSet(32) ? Token.getRange("IsWord", true) : Token.token_wordchars;
                break;
            }
            case 87: {
                tok = this.isSet(32) ? Token.getRange("IsWord", false) : Token.token_not_wordchars;
                break;
            }
            case 115: {
                tok = this.isSet(32) ? Token.getRange("IsSpace", true) : Token.token_spaces;
                break;
            }
            case 83: {
                tok = this.isSet(32) ? Token.getRange("IsSpace", false) : Token.token_not_spaces;
                break;
            }
            default: {
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
            }
        }
        return tok;
    }

    int decodeEscaped() throws ParseException {
        if (this.read() != 10) {
            throw this.ex("parser.next.1", this.offset - 1);
        }
        int c = this.chardata;
        switch (c) {
            case 101: {
                c = 27;
                break;
            }
            case 102: {
                c = 12;
                break;
            }
            case 110: {
                c = 10;
                break;
            }
            case 114: {
                c = 13;
                break;
            }
            case 116: {
                c = 9;
                break;
            }
            case 120: {
                this.next();
                if (this.read() != 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                if (this.chardata == 123) {
                    int v1 = 0;
                    int uv = 0;
                    while (true) {
                        this.next();
                        if (this.read() != 0) {
                            throw this.ex("parser.descape.1", this.offset - 1);
                        }
                        v1 = RegexParser.hexChar(this.chardata);
                        if (v1 < 0) break;
                        if (uv > uv * 16) {
                            throw this.ex("parser.descape.2", this.offset - 1);
                        }
                        uv = uv * 16 + v1;
                    }
                    if (this.chardata != 125) {
                        throw this.ex("parser.descape.3", this.offset - 1);
                    }
                    if (uv > 0x10FFFF) {
                        throw this.ex("parser.descape.4", this.offset - 1);
                    }
                    c = uv;
                    break;
                }
                int v1 = 0;
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                int uv = v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                c = uv = uv * 16 + v1;
                break;
            }
            case 117: {
                int v1 = 0;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                int uv = v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                uv = uv * 16 + v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                uv = uv * 16 + v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                c = uv = uv * 16 + v1;
                break;
            }
            case 118: {
                int v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                int uv = v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                uv = uv * 16 + v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                uv = uv * 16 + v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                uv = uv * 16 + v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                uv = uv * 16 + v1;
                this.next();
                if (this.read() != 0 || (v1 = RegexParser.hexChar(this.chardata)) < 0) {
                    throw this.ex("parser.descape.1", this.offset - 1);
                }
                if ((uv = uv * 16 + v1) > 0x10FFFF) {
                    throw this.ex("parser.descappe.4", this.offset - 1);
                }
                c = uv;
                break;
            }
            case 65: 
            case 90: 
            case 122: {
                throw this.ex("parser.descape.5", this.offset - 2);
            }
        }
        return c;
    }

    private static final int hexChar(int ch) {
        if (ch < 48) {
            return -1;
        }
        if (ch > 102) {
            return -1;
        }
        if (ch <= 57) {
            return ch - 48;
        }
        if (ch < 65) {
            return -1;
        }
        if (ch <= 70) {
            return ch - 65 + 10;
        }
        if (ch < 97) {
            return -1;
        }
        return ch - 97 + 10;
    }

    static class ReferencePosition {
        int refNumber;
        int position;

        ReferencePosition(int n, int pos) {
            this.refNumber = n;
            this.position = pos;
        }
    }
}

