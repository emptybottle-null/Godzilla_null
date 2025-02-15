/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.apache.xmlbeans.impl.regex.RangeToken;

class Token
implements Serializable {
    static final boolean COUNTTOKENS = true;
    static int tokens = 0;
    static final int CHAR = 0;
    static final int DOT = 11;
    static final int CONCAT = 1;
    static final int UNION = 2;
    static final int CLOSURE = 3;
    static final int RANGE = 4;
    static final int NRANGE = 5;
    static final int PAREN = 6;
    static final int EMPTY = 7;
    static final int ANCHOR = 8;
    static final int NONGREEDYCLOSURE = 9;
    static final int STRING = 10;
    static final int BACKREFERENCE = 12;
    static final int LOOKAHEAD = 20;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int LOOKBEHIND = 22;
    static final int NEGATIVELOOKBEHIND = 23;
    static final int INDEPENDENT = 24;
    static final int MODIFIERGROUP = 25;
    static final int CONDITION = 26;
    static final int UTF16_MAX = 0x10FFFF;
    int type;
    static Token token_dot;
    static Token token_0to9;
    static Token token_wordchars;
    static Token token_not_0to9;
    static Token token_not_wordchars;
    static Token token_spaces;
    static Token token_not_spaces;
    static Token token_empty;
    static Token token_linebeginning;
    static Token token_linebeginning2;
    static Token token_lineend;
    static Token token_stringbeginning;
    static Token token_stringend;
    static Token token_stringend2;
    static Token token_wordedge;
    static Token token_not_wordedge;
    static Token token_wordbeginning;
    static Token token_wordend;
    static final int FC_CONTINUE = 0;
    static final int FC_TERMINAL = 1;
    static final int FC_ANY = 2;
    private static final Hashtable categories;
    private static final Hashtable categories2;
    private static final String[] categoryNames;
    static final int CHAR_INIT_QUOTE = 29;
    static final int CHAR_FINAL_QUOTE = 30;
    static final int CHAR_LETTER = 31;
    static final int CHAR_MARK = 32;
    static final int CHAR_NUMBER = 33;
    static final int CHAR_SEPARATOR = 34;
    static final int CHAR_OTHER = 35;
    static final int CHAR_PUNCTUATION = 36;
    static final int CHAR_SYMBOL = 37;
    private static final String[] blockNames;
    static final String blockRanges = "\u0000\u007f\u0080\u00ff\u0100\u017f\u0180\u024f\u0250\u02af\u02b0\u02ff\u0300\u036f\u0370\u03ff\u0400\u04ff\u0530\u058f\u0590\u05ff\u0600\u06ff\u0700\u074f\u0780\u07bf\u0900\u097f\u0980\u09ff\u0a00\u0a7f\u0a80\u0aff\u0b00\u0b7f\u0b80\u0bff\u0c00\u0c7f\u0c80\u0cff\u0d00\u0d7f\u0d80\u0dff\u0e00\u0e7f\u0e80\u0eff\u0f00\u0fff\u1000\u109f\u10a0\u10ff\u1100\u11ff\u1200\u137f\u13a0\u13ff\u1400\u167f\u1680\u169f\u16a0\u16ff\u1780\u17ff\u1800\u18af\u1e00\u1eff\u1f00\u1fff\u2000\u206f\u2070\u209f\u20a0\u20cf\u20d0\u20ff\u2100\u214f\u2150\u218f\u2190\u21ff\u2200\u22ff\u2300\u23ff\u2400\u243f\u2440\u245f\u2460\u24ff\u2500\u257f\u2580\u259f\u25a0\u25ff\u2600\u26ff\u2700\u27bf\u2800\u28ff\u2e80\u2eff\u2f00\u2fdf\u2ff0\u2fff\u3000\u303f\u3040\u309f\u30a0\u30ff\u3100\u312f\u3130\u318f\u3190\u319f\u31a0\u31bf\u3200\u32ff\u3300\u33ff\u3400\u4db5\u4e00\u9fff\ua000\ua48f\ua490\ua4cf\uac00\ud7a3\ue000\uf8ff\uf900\ufaff\ufb00\ufb4f\ufb50\ufdff\ufe20\ufe2f\ufe30\ufe4f\ufe50\ufe6f\ufe70\ufefe\ufeff\ufeff\uff00\uffef";
    static final int[] nonBMPBlockRanges;
    private static final int NONBMP_BLOCK_START = 84;
    static Hashtable nonxs;
    static final String viramaString = "\u094d\u09cd\u0a4d\u0acd\u0b4d\u0bcd\u0c4d\u0ccd\u0d4d\u0e3a\u0f84";
    private static Token token_grapheme;
    private static Token token_ccs;

    static ParenToken createLook(int type, Token child) {
        ++tokens;
        return new ParenToken(type, child, 0);
    }

    static ParenToken createParen(Token child, int pnumber) {
        ++tokens;
        return new ParenToken(6, child, pnumber);
    }

    static ClosureToken createClosure(Token tok) {
        ++tokens;
        return new ClosureToken(3, tok);
    }

    static ClosureToken createNGClosure(Token tok) {
        ++tokens;
        return new ClosureToken(9, tok);
    }

    static ConcatToken createConcat(Token tok1, Token tok2) {
        ++tokens;
        return new ConcatToken(tok1, tok2);
    }

    static UnionToken createConcat() {
        ++tokens;
        return new UnionToken(1);
    }

    static UnionToken createUnion() {
        ++tokens;
        return new UnionToken(2);
    }

    static Token createEmpty() {
        return token_empty;
    }

    static RangeToken createRange() {
        ++tokens;
        return new RangeToken(4);
    }

    static RangeToken createNRange() {
        ++tokens;
        return new RangeToken(5);
    }

    static CharToken createChar(int ch) {
        ++tokens;
        return new CharToken(0, ch);
    }

    private static CharToken createAnchor(int ch) {
        ++tokens;
        return new CharToken(8, ch);
    }

    static StringToken createBackReference(int refno) {
        ++tokens;
        return new StringToken(12, null, refno);
    }

    static StringToken createString(String str) {
        ++tokens;
        return new StringToken(10, str, 0);
    }

    static ModifierToken createModifierGroup(Token child, int add, int mask) {
        ++tokens;
        return new ModifierToken(child, add, mask);
    }

    static ConditionToken createCondition(int refno, Token condition, Token yespat, Token nopat) {
        ++tokens;
        return new ConditionToken(refno, condition, yespat, nopat);
    }

    protected Token(int type) {
        this.type = type;
    }

    int size() {
        return 0;
    }

    Token getChild(int index) {
        return null;
    }

    void addChild(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void addRange(int start, int end) {
        throw new RuntimeException("Not supported.");
    }

    protected void sortRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void compactRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void mergeRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void subtractRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void intersectRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    static Token complementRanges(Token tok) {
        return RangeToken.complementRanges(tok);
    }

    void setMin(int min) {
    }

    void setMax(int max) {
    }

    int getMin() {
        return -1;
    }

    int getMax() {
        return -1;
    }

    int getReferenceNumber() {
        return 0;
    }

    String getString() {
        return null;
    }

    int getParenNumber() {
        return 0;
    }

    int getChar() {
        return -1;
    }

    public String toString() {
        return this.toString(0);
    }

    public String toString(int options) {
        return this.type == 11 ? "." : "";
    }

    final int getMinLength() {
        switch (this.type) {
            case 1: {
                int sum = 0;
                for (int i = 0; i < this.size(); ++i) {
                    sum += this.getChild(i).getMinLength();
                }
                return sum;
            }
            case 2: 
            case 26: {
                if (this.size() == 0) {
                    return 0;
                }
                int ret = this.getChild(0).getMinLength();
                for (int i = 1; i < this.size(); ++i) {
                    int min = this.getChild(i).getMinLength();
                    if (min >= ret) continue;
                    ret = min;
                }
                return ret;
            }
            case 3: 
            case 9: {
                if (this.getMin() >= 0) {
                    return this.getMin() * this.getChild(0).getMinLength();
                }
                return 0;
            }
            case 7: 
            case 8: {
                return 0;
            }
            case 0: 
            case 4: 
            case 5: 
            case 11: {
                return 1;
            }
            case 6: 
            case 24: 
            case 25: {
                return this.getChild(0).getMinLength();
            }
            case 12: {
                return 0;
            }
            case 10: {
                return this.getString().length();
            }
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                return 0;
            }
        }
        throw new RuntimeException("Token#getMinLength(): Invalid Type: " + this.type);
    }

    final int getMaxLength() {
        switch (this.type) {
            case 1: {
                int sum = 0;
                for (int i = 0; i < this.size(); ++i) {
                    int d = this.getChild(i).getMaxLength();
                    if (d < 0) {
                        return -1;
                    }
                    sum += d;
                }
                return sum;
            }
            case 2: 
            case 26: {
                if (this.size() == 0) {
                    return 0;
                }
                int ret = this.getChild(0).getMaxLength();
                for (int i = 1; ret >= 0 && i < this.size(); ++i) {
                    int max = this.getChild(i).getMaxLength();
                    if (max < 0) {
                        ret = -1;
                        break;
                    }
                    if (max <= ret) continue;
                    ret = max;
                }
                return ret;
            }
            case 3: 
            case 9: {
                if (this.getMax() >= 0) {
                    return this.getMax() * this.getChild(0).getMaxLength();
                }
                return -1;
            }
            case 7: 
            case 8: {
                return 0;
            }
            case 0: {
                return 1;
            }
            case 4: 
            case 5: 
            case 11: {
                return 2;
            }
            case 6: 
            case 24: 
            case 25: {
                return this.getChild(0).getMaxLength();
            }
            case 12: {
                return -1;
            }
            case 10: {
                return this.getString().length();
            }
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                return 0;
            }
        }
        throw new RuntimeException("Token#getMaxLength(): Invalid Type: " + this.type);
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag;
    }

    final int analyzeFirstCharacter(RangeToken result, int options) {
        switch (this.type) {
            case 1: {
                int ret = 0;
                for (int i = 0; i < this.size() && (ret = this.getChild(i).analyzeFirstCharacter(result, options)) == 0; ++i) {
                }
                return ret;
            }
            case 2: {
                if (this.size() == 0) {
                    return 0;
                }
                int ret2 = 0;
                boolean hasEmpty = false;
                for (int i = 0; i < this.size() && (ret2 = this.getChild(i).analyzeFirstCharacter(result, options)) != 2; ++i) {
                    if (ret2 != 0) continue;
                    hasEmpty = true;
                }
                return hasEmpty ? 0 : ret2;
            }
            case 26: {
                int ret3 = this.getChild(0).analyzeFirstCharacter(result, options);
                if (this.size() == 1) {
                    return 0;
                }
                if (ret3 == 2) {
                    return ret3;
                }
                int ret4 = this.getChild(1).analyzeFirstCharacter(result, options);
                if (ret4 == 2) {
                    return ret4;
                }
                return ret3 == 0 || ret4 == 0 ? 0 : 1;
            }
            case 3: 
            case 9: {
                this.getChild(0).analyzeFirstCharacter(result, options);
                return 0;
            }
            case 7: 
            case 8: {
                return 0;
            }
            case 0: {
                int ch = this.getChar();
                result.addRange(ch, ch);
                if (ch < 65536 && Token.isSet(options, 2)) {
                    ch = Character.toUpperCase((char)ch);
                    result.addRange(ch, ch);
                    ch = Character.toLowerCase((char)ch);
                    result.addRange(ch, ch);
                }
                return 1;
            }
            case 11: {
                if (Token.isSet(options, 4)) {
                    return 0;
                }
                return 0;
            }
            case 4: {
                if (Token.isSet(options, 2)) {
                    result.mergeRanges(((RangeToken)this).getCaseInsensitiveToken());
                } else {
                    result.mergeRanges(this);
                }
                return 1;
            }
            case 5: {
                if (Token.isSet(options, 2)) {
                    result.mergeRanges(Token.complementRanges(((RangeToken)this).getCaseInsensitiveToken()));
                } else {
                    result.mergeRanges(Token.complementRanges(this));
                }
                return 1;
            }
            case 6: 
            case 24: {
                return this.getChild(0).analyzeFirstCharacter(result, options);
            }
            case 25: {
                options |= ((ModifierToken)this).getOptions();
                return this.getChild(0).analyzeFirstCharacter(result, options &= ~((ModifierToken)this).getOptionsMask());
            }
            case 12: {
                result.addRange(0, 0x10FFFF);
                return 2;
            }
            case 10: {
                char ch2;
                int cha = this.getString().charAt(0);
                if (REUtil.isHighSurrogate(cha) && this.getString().length() >= 2 && REUtil.isLowSurrogate(ch2 = this.getString().charAt(1))) {
                    cha = REUtil.composeFromSurrogates(cha, ch2);
                }
                result.addRange(cha, cha);
                if (cha < 65536 && Token.isSet(options, 2)) {
                    cha = Character.toUpperCase((char)cha);
                    result.addRange(cha, cha);
                    cha = Character.toLowerCase((char)cha);
                    result.addRange(cha, cha);
                }
                return 1;
            }
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                return 0;
            }
        }
        throw new RuntimeException("Token#analyzeHeadCharacter(): Invalid Type: " + this.type);
    }

    private final boolean isShorterThan(Token tok) {
        if (tok == null) {
            return false;
        }
        if (this.type != 10) {
            throw new RuntimeException("Internal Error: Illegal type: " + this.type);
        }
        int mylength = this.getString().length();
        if (tok.type != 10) {
            throw new RuntimeException("Internal Error: Illegal type: " + tok.type);
        }
        int otherlength = tok.getString().length();
        return mylength < otherlength;
    }

    final void findFixedString(FixedStringContainer container, int options) {
        switch (this.type) {
            case 1: {
                Token prevToken = null;
                int prevOptions = 0;
                for (int i = 0; i < this.size(); ++i) {
                    this.getChild(i).findFixedString(container, options);
                    if (prevToken != null && !prevToken.isShorterThan(container.token)) continue;
                    prevToken = container.token;
                    prevOptions = container.options;
                }
                container.token = prevToken;
                container.options = prevOptions;
                return;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 7: 
            case 8: 
            case 9: 
            case 11: 
            case 12: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 26: {
                container.token = null;
                return;
            }
            case 0: {
                container.token = null;
                return;
            }
            case 10: {
                container.token = this;
                container.options = options;
                return;
            }
            case 6: 
            case 24: {
                this.getChild(0).findFixedString(container, options);
                return;
            }
            case 25: {
                options |= ((ModifierToken)this).getOptions();
                this.getChild(0).findFixedString(container, options &= ~((ModifierToken)this).getOptionsMask());
                return;
            }
        }
        throw new RuntimeException("Token#findFixedString(): Invalid Type: " + this.type);
    }

    boolean match(int ch) {
        throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected static RangeToken getRange(String name, boolean positive) {
        if (categories.size() == 0) {
            Hashtable hashtable = categories;
            synchronized (hashtable) {
                int i;
                Token[] ranges = new Token[categoryNames.length];
                for (int i2 = 0; i2 < ranges.length; ++i2) {
                    ranges[i2] = Token.createRange();
                }
                for (i = 0; i < 65536; ++i) {
                    int type = Character.getType((char)i);
                    if (type == 21 || type == 22) {
                        if (i == 171 || i == 8216 || i == 8219 || i == 8220 || i == 8223 || i == 8249) {
                            type = 29;
                        }
                        if (i == 187 || i == 8217 || i == 8221 || i == 8250) {
                            type = 30;
                        }
                    }
                    ranges[type].addRange(i, i);
                    switch (type) {
                        case 1: 
                        case 2: 
                        case 3: 
                        case 4: 
                        case 5: {
                            type = 31;
                            break;
                        }
                        case 6: 
                        case 7: 
                        case 8: {
                            type = 32;
                            break;
                        }
                        case 9: 
                        case 10: 
                        case 11: {
                            type = 33;
                            break;
                        }
                        case 12: 
                        case 13: 
                        case 14: {
                            type = 34;
                            break;
                        }
                        case 0: 
                        case 15: 
                        case 16: 
                        case 18: 
                        case 19: {
                            type = 35;
                            break;
                        }
                        case 20: 
                        case 21: 
                        case 22: 
                        case 23: 
                        case 24: 
                        case 29: 
                        case 30: {
                            type = 36;
                            break;
                        }
                        case 25: 
                        case 26: 
                        case 27: 
                        case 28: {
                            type = 37;
                            break;
                        }
                        default: {
                            throw new RuntimeException("org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: " + type);
                        }
                    }
                    ranges[type].addRange(i, i);
                }
                ranges[0].addRange(65536, 0x10FFFF);
                for (i = 0; i < ranges.length; ++i) {
                    if (categoryNames[i] == null) continue;
                    if (i == 0) {
                        ranges[i].addRange(65536, 0x10FFFF);
                    }
                    categories.put(categoryNames[i], ranges[i]);
                    categories2.put(categoryNames[i], Token.complementRanges(ranges[i]));
                }
                StringBuffer buffer = new StringBuffer(50);
                for (int i3 = 0; i3 < blockNames.length; ++i3) {
                    int location;
                    RangeToken r1 = Token.createRange();
                    if (i3 < 84) {
                        location = i3 * 2;
                        char rstart = blockRanges.charAt(location);
                        char rend = blockRanges.charAt(location + 1);
                        ((Token)r1).addRange(rstart, rend);
                    } else {
                        location = (i3 - 84) * 2;
                        ((Token)r1).addRange(nonBMPBlockRanges[location], nonBMPBlockRanges[location + 1]);
                    }
                    String n = blockNames[i3];
                    if (n.equals("Specials")) {
                        ((Token)r1).addRange(65520, 65533);
                    }
                    if (n.equals("Private Use")) {
                        ((Token)r1).addRange(983040, 1048573);
                        ((Token)r1).addRange(0x100000, 1114109);
                    }
                    categories.put(n, r1);
                    categories2.put(n, Token.complementRanges(r1));
                    buffer.setLength(0);
                    buffer.append("Is");
                    if (n.indexOf(32) >= 0) {
                        for (int ci = 0; ci < n.length(); ++ci) {
                            if (n.charAt(ci) == ' ') continue;
                            buffer.append(n.charAt(ci));
                        }
                    } else {
                        buffer.append(n);
                    }
                    Token.setAlias(buffer.toString(), n, true);
                }
                Token.setAlias("ASSIGNED", "Cn", false);
                Token.setAlias("UNASSIGNED", "Cn", true);
                RangeToken all = Token.createRange();
                ((Token)all).addRange(0, 0x10FFFF);
                categories.put("ALL", all);
                categories2.put("ALL", Token.complementRanges(all));
                Token.registerNonXS("ASSIGNED");
                Token.registerNonXS("UNASSIGNED");
                Token.registerNonXS("ALL");
                RangeToken isalpha = Token.createRange();
                ((Token)isalpha).mergeRanges(ranges[1]);
                ((Token)isalpha).mergeRanges(ranges[2]);
                ((Token)isalpha).mergeRanges(ranges[5]);
                categories.put("IsAlpha", isalpha);
                categories2.put("IsAlpha", Token.complementRanges(isalpha));
                Token.registerNonXS("IsAlpha");
                RangeToken isalnum = Token.createRange();
                ((Token)isalnum).mergeRanges(isalpha);
                ((Token)isalnum).mergeRanges(ranges[9]);
                categories.put("IsAlnum", isalnum);
                categories2.put("IsAlnum", Token.complementRanges(isalnum));
                Token.registerNonXS("IsAlnum");
                RangeToken isspace = Token.createRange();
                ((Token)isspace).mergeRanges(token_spaces);
                ((Token)isspace).mergeRanges(ranges[34]);
                categories.put("IsSpace", isspace);
                categories2.put("IsSpace", Token.complementRanges(isspace));
                Token.registerNonXS("IsSpace");
                RangeToken isword = Token.createRange();
                ((Token)isword).mergeRanges(isalnum);
                ((Token)isword).addRange(95, 95);
                categories.put("IsWord", isword);
                categories2.put("IsWord", Token.complementRanges(isword));
                Token.registerNonXS("IsWord");
                RangeToken isascii = Token.createRange();
                ((Token)isascii).addRange(0, 127);
                categories.put("IsASCII", isascii);
                categories2.put("IsASCII", Token.complementRanges(isascii));
                Token.registerNonXS("IsASCII");
                RangeToken isnotgraph = Token.createRange();
                ((Token)isnotgraph).mergeRanges(ranges[35]);
                ((Token)isnotgraph).addRange(32, 32);
                categories.put("IsGraph", Token.complementRanges(isnotgraph));
                categories2.put("IsGraph", isnotgraph);
                Token.registerNonXS("IsGraph");
                RangeToken isxdigit = Token.createRange();
                ((Token)isxdigit).addRange(48, 57);
                ((Token)isxdigit).addRange(65, 70);
                ((Token)isxdigit).addRange(97, 102);
                categories.put("IsXDigit", Token.complementRanges(isxdigit));
                categories2.put("IsXDigit", isxdigit);
                Token.registerNonXS("IsXDigit");
                Token.setAlias("IsDigit", "Nd", true);
                Token.setAlias("IsUpper", "Lu", true);
                Token.setAlias("IsLower", "Ll", true);
                Token.setAlias("IsCntrl", "C", true);
                Token.setAlias("IsPrint", "C", false);
                Token.setAlias("IsPunct", "P", true);
                Token.registerNonXS("IsDigit");
                Token.registerNonXS("IsUpper");
                Token.registerNonXS("IsLower");
                Token.registerNonXS("IsCntrl");
                Token.registerNonXS("IsPrint");
                Token.registerNonXS("IsPunct");
                Token.setAlias("alpha", "IsAlpha", true);
                Token.setAlias("alnum", "IsAlnum", true);
                Token.setAlias("ascii", "IsASCII", true);
                Token.setAlias("cntrl", "IsCntrl", true);
                Token.setAlias("digit", "IsDigit", true);
                Token.setAlias("graph", "IsGraph", true);
                Token.setAlias("lower", "IsLower", true);
                Token.setAlias("print", "IsPrint", true);
                Token.setAlias("punct", "IsPunct", true);
                Token.setAlias("space", "IsSpace", true);
                Token.setAlias("upper", "IsUpper", true);
                Token.setAlias("word", "IsWord", true);
                Token.setAlias("xdigit", "IsXDigit", true);
                Token.registerNonXS("alpha");
                Token.registerNonXS("alnum");
                Token.registerNonXS("ascii");
                Token.registerNonXS("cntrl");
                Token.registerNonXS("digit");
                Token.registerNonXS("graph");
                Token.registerNonXS("lower");
                Token.registerNonXS("print");
                Token.registerNonXS("punct");
                Token.registerNonXS("space");
                Token.registerNonXS("upper");
                Token.registerNonXS("word");
                Token.registerNonXS("xdigit");
            }
        }
        RangeToken tok = positive ? (RangeToken)categories.get(name) : (RangeToken)categories2.get(name);
        return tok;
    }

    protected static RangeToken getRange(String name, boolean positive, boolean xs) {
        RangeToken range = Token.getRange(name, positive);
        if (xs && range != null && Token.isRegisterNonXS(name)) {
            range = null;
        }
        return range;
    }

    protected static void registerNonXS(String name) {
        if (nonxs == null) {
            nonxs = new Hashtable();
        }
        nonxs.put(name, name);
    }

    protected static boolean isRegisterNonXS(String name) {
        if (nonxs == null) {
            return false;
        }
        return nonxs.containsKey(name);
    }

    private static void setAlias(String newName, String name, boolean positive) {
        Token t1 = (Token)categories.get(name);
        Token t2 = (Token)categories2.get(name);
        if (positive) {
            categories.put(newName, t1);
            categories2.put(newName, t2);
        } else {
            categories2.put(newName, t1);
            categories.put(newName, t2);
        }
    }

    static synchronized Token getGraphemePattern() {
        if (token_grapheme != null) {
            return token_grapheme;
        }
        RangeToken base_char = Token.createRange();
        ((Token)base_char).mergeRanges(Token.getRange("ASSIGNED", true));
        ((Token)base_char).subtractRanges(Token.getRange("M", true));
        ((Token)base_char).subtractRanges(Token.getRange("C", true));
        RangeToken virama = Token.createRange();
        for (int i = 0; i < viramaString.length(); ++i) {
            char ch = viramaString.charAt(i);
            ((Token)virama).addRange(i, i);
        }
        RangeToken combiner_wo_virama = Token.createRange();
        ((Token)combiner_wo_virama).mergeRanges(Token.getRange("M", true));
        ((Token)combiner_wo_virama).addRange(4448, 4607);
        ((Token)combiner_wo_virama).addRange(65438, 65439);
        UnionToken left = Token.createUnion();
        ((Token)left).addChild(base_char);
        ((Token)left).addChild(token_empty);
        Token foo = Token.createUnion();
        ((Token)foo).addChild(Token.createConcat(virama, Token.getRange("L", true)));
        ((Token)foo).addChild(combiner_wo_virama);
        foo = Token.createClosure(foo);
        foo = Token.createConcat(left, foo);
        token_grapheme = foo;
        return token_grapheme;
    }

    static synchronized Token getCombiningCharacterSequence() {
        if (token_ccs != null) {
            return token_ccs;
        }
        Token foo = Token.createClosure(Token.getRange("M", true));
        foo = Token.createConcat(Token.getRange("M", false), foo);
        token_ccs = foo;
        return token_ccs;
    }

    static {
        token_empty = new Token(7);
        token_linebeginning = Token.createAnchor(94);
        token_linebeginning2 = Token.createAnchor(64);
        token_lineend = Token.createAnchor(36);
        token_stringbeginning = Token.createAnchor(65);
        token_stringend = Token.createAnchor(122);
        token_stringend2 = Token.createAnchor(90);
        token_wordedge = Token.createAnchor(98);
        token_not_wordedge = Token.createAnchor(66);
        token_wordbeginning = Token.createAnchor(60);
        token_wordend = Token.createAnchor(62);
        token_dot = new Token(11);
        token_0to9 = Token.createRange();
        token_0to9.addRange(48, 57);
        token_wordchars = Token.createRange();
        token_wordchars.addRange(48, 57);
        token_wordchars.addRange(65, 90);
        token_wordchars.addRange(95, 95);
        token_wordchars.addRange(97, 122);
        token_spaces = Token.createRange();
        token_spaces.addRange(9, 9);
        token_spaces.addRange(10, 10);
        token_spaces.addRange(12, 12);
        token_spaces.addRange(13, 13);
        token_spaces.addRange(32, 32);
        token_not_0to9 = Token.complementRanges(token_0to9);
        token_not_wordchars = Token.complementRanges(token_wordchars);
        token_not_spaces = Token.complementRanges(token_spaces);
        categories = new Hashtable();
        categories2 = new Hashtable();
        categoryNames = new String[]{"Cn", "Lu", "Ll", "Lt", "Lm", "Lo", "Mn", "Me", "Mc", "Nd", "Nl", "No", "Zs", "Zl", "Zp", "Cc", "Cf", null, "Co", "Cs", "Pd", "Ps", "Pe", "Pc", "Po", "Sm", "Sc", "Sk", "So", "Pi", "Pf", "L", "M", "N", "Z", "C", "P", "S"};
        blockNames = new String[]{"Basic Latin", "Latin-1 Supplement", "Latin Extended-A", "Latin Extended-B", "IPA Extensions", "Spacing Modifier Letters", "Combining Diacritical Marks", "Greek", "Cyrillic", "Armenian", "Hebrew", "Arabic", "Syriac", "Thaana", "Devanagari", "Bengali", "Gurmukhi", "Gujarati", "Oriya", "Tamil", "Telugu", "Kannada", "Malayalam", "Sinhala", "Thai", "Lao", "Tibetan", "Myanmar", "Georgian", "Hangul Jamo", "Ethiopic", "Cherokee", "Unified Canadian Aboriginal Syllabics", "Ogham", "Runic", "Khmer", "Mongolian", "Latin Extended Additional", "Greek Extended", "General Punctuation", "Superscripts and Subscripts", "Currency Symbols", "Combining Marks for Symbols", "Letterlike Symbols", "Number Forms", "Arrows", "Mathematical Operators", "Miscellaneous Technical", "Control Pictures", "Optical Character Recognition", "Enclosed Alphanumerics", "Box Drawing", "Block Elements", "Geometric Shapes", "Miscellaneous Symbols", "Dingbats", "Braille Patterns", "CJK Radicals Supplement", "Kangxi Radicals", "Ideographic Description Characters", "CJK Symbols and Punctuation", "Hiragana", "Katakana", "Bopomofo", "Hangul Compatibility Jamo", "Kanbun", "Bopomofo Extended", "Enclosed CJK Letters and Months", "CJK Compatibility", "CJK Unified Ideographs Extension A", "CJK Unified Ideographs", "Yi Syllables", "Yi Radicals", "Hangul Syllables", "Private Use", "CJK Compatibility Ideographs", "Alphabetic Presentation Forms", "Arabic Presentation Forms-A", "Combining Half Marks", "CJK Compatibility Forms", "Small Form Variants", "Arabic Presentation Forms-B", "Specials", "Halfwidth and Fullwidth Forms", "Old Italic", "Gothic", "Deseret", "Byzantine Musical Symbols", "Musical Symbols", "Mathematical Alphanumeric Symbols", "CJK Unified Ideographs Extension B", "CJK Compatibility Ideographs Supplement", "Tags"};
        nonBMPBlockRanges = new int[]{66304, 66351, 66352, 66383, 66560, 66639, 118784, 119039, 119040, 119295, 119808, 120831, 131072, 173782, 194560, 195103, 917504, 917631};
        nonxs = null;
        token_grapheme = null;
        token_ccs = null;
    }

    static class UnionToken
    extends Token
    implements Serializable {
        Vector children;

        UnionToken(int type) {
            super(type);
        }

        @Override
        void addChild(Token tok) {
            int ch;
            StringBuffer buffer;
            int nextMaxLength;
            if (tok == null) {
                return;
            }
            if (this.children == null) {
                this.children = new Vector();
            }
            if (this.type == 2) {
                this.children.addElement(tok);
                return;
            }
            if (tok.type == 1) {
                for (int i = 0; i < tok.size(); ++i) {
                    this.addChild(tok.getChild(i));
                }
                return;
            }
            int size = this.children.size();
            if (size == 0) {
                this.children.addElement(tok);
                return;
            }
            Token previous = (Token)this.children.elementAt(size - 1);
            if (previous.type != 0 && previous.type != 10 || tok.type != 0 && tok.type != 10) {
                this.children.addElement(tok);
                return;
            }
            int n = nextMaxLength = tok.type == 0 ? 2 : tok.getString().length();
            if (previous.type == 0) {
                buffer = new StringBuffer(2 + nextMaxLength);
                ch = previous.getChar();
                if (ch >= 65536) {
                    buffer.append(REUtil.decomposeToSurrogates(ch));
                } else {
                    buffer.append((char)ch);
                }
                previous = Token.createString(null);
                this.children.setElementAt(previous, size - 1);
            } else {
                buffer = new StringBuffer(previous.getString().length() + nextMaxLength);
                buffer.append(previous.getString());
            }
            if (tok.type == 0) {
                ch = tok.getChar();
                if (ch >= 65536) {
                    buffer.append(REUtil.decomposeToSurrogates(ch));
                } else {
                    buffer.append((char)ch);
                }
            } else {
                buffer.append(tok.getString());
            }
            ((StringToken)previous).string = new String(buffer);
        }

        @Override
        int size() {
            return this.children == null ? 0 : this.children.size();
        }

        @Override
        Token getChild(int index) {
            return (Token)this.children.elementAt(index);
        }

        @Override
        public String toString(int options) {
            String ret;
            if (this.type == 1) {
                String ret2;
                if (this.children.size() == 2) {
                    Token ch = this.getChild(0);
                    Token ch2 = this.getChild(1);
                    ret2 = ch2.type == 3 && ch2.getChild(0) == ch ? ch.toString(options) + "+" : (ch2.type == 9 && ch2.getChild(0) == ch ? ch.toString(options) + "+?" : ch.toString(options) + ch2.toString(options));
                } else {
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < this.children.size(); ++i) {
                        sb.append(((Token)this.children.elementAt(i)).toString(options));
                    }
                    ret2 = new String(sb);
                }
                return ret2;
            }
            if (this.children.size() == 2 && this.getChild((int)1).type == 7) {
                ret = this.getChild(0).toString(options) + "?";
            } else if (this.children.size() == 2 && this.getChild((int)0).type == 7) {
                ret = this.getChild(1).toString(options) + "??";
            } else {
                StringBuffer sb = new StringBuffer();
                sb.append(((Token)this.children.elementAt(0)).toString(options));
                for (int i = 1; i < this.children.size(); ++i) {
                    sb.append('|');
                    sb.append(((Token)this.children.elementAt(i)).toString(options));
                }
                ret = new String(sb);
            }
            return ret;
        }
    }

    static class ModifierToken
    extends Token
    implements Serializable {
        Token child;
        int add;
        int mask;

        ModifierToken(Token tok, int add, int mask) {
            super(25);
            this.child = tok;
            this.add = add;
            this.mask = mask;
        }

        @Override
        int size() {
            return 1;
        }

        @Override
        Token getChild(int index) {
            return this.child;
        }

        int getOptions() {
            return this.add;
        }

        int getOptionsMask() {
            return this.mask;
        }

        @Override
        public String toString(int options) {
            return "(?" + (this.add == 0 ? "" : REUtil.createOptionString(this.add)) + (this.mask == 0 ? "" : REUtil.createOptionString(this.mask)) + ":" + this.child.toString(options) + ")";
        }
    }

    static class ConditionToken
    extends Token
    implements Serializable {
        int refNumber;
        Token condition;
        Token yes;
        Token no;

        ConditionToken(int refno, Token cond, Token yespat, Token nopat) {
            super(26);
            this.refNumber = refno;
            this.condition = cond;
            this.yes = yespat;
            this.no = nopat;
        }

        @Override
        int size() {
            return this.no == null ? 1 : 2;
        }

        @Override
        Token getChild(int index) {
            if (index == 0) {
                return this.yes;
            }
            if (index == 1) {
                return this.no;
            }
            throw new RuntimeException("Internal Error: " + index);
        }

        @Override
        public String toString(int options) {
            String ret = this.refNumber > 0 ? "(?(" + this.refNumber + ")" : (this.condition.type == 8 ? "(?(" + this.condition + ")" : "(?" + this.condition);
            ret = this.no == null ? ret + this.yes + ")" : ret + this.yes + "|" + this.no + ")";
            return ret;
        }
    }

    static class ParenToken
    extends Token
    implements Serializable {
        Token child;
        int parennumber;

        ParenToken(int type, Token tok, int paren) {
            super(type);
            this.child = tok;
            this.parennumber = paren;
        }

        @Override
        int size() {
            return 1;
        }

        @Override
        Token getChild(int index) {
            return this.child;
        }

        @Override
        int getParenNumber() {
            return this.parennumber;
        }

        @Override
        public String toString(int options) {
            String ret = null;
            switch (this.type) {
                case 6: {
                    if (this.parennumber == 0) {
                        ret = "(?:" + this.child.toString(options) + ")";
                        break;
                    }
                    ret = "(" + this.child.toString(options) + ")";
                    break;
                }
                case 20: {
                    ret = "(?=" + this.child.toString(options) + ")";
                    break;
                }
                case 21: {
                    ret = "(?!" + this.child.toString(options) + ")";
                    break;
                }
                case 22: {
                    ret = "(?<=" + this.child.toString(options) + ")";
                    break;
                }
                case 23: {
                    ret = "(?<!" + this.child.toString(options) + ")";
                    break;
                }
                case 24: {
                    ret = "(?>" + this.child.toString(options) + ")";
                }
            }
            return ret;
        }
    }

    static class ClosureToken
    extends Token
    implements Serializable {
        int min;
        int max;
        Token child;

        ClosureToken(int type, Token tok) {
            super(type);
            this.child = tok;
            this.setMin(-1);
            this.setMax(-1);
        }

        @Override
        int size() {
            return 1;
        }

        @Override
        Token getChild(int index) {
            return this.child;
        }

        @Override
        final void setMin(int min) {
            this.min = min;
        }

        @Override
        final void setMax(int max) {
            this.max = max;
        }

        @Override
        final int getMin() {
            return this.min;
        }

        @Override
        final int getMax() {
            return this.max;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public String toString(int options) {
            if (this.type == 3) {
                if (this.getMin() < 0 && this.getMax() < 0) {
                    return this.child.toString(options) + "*";
                }
                if (this.getMin() == this.getMax()) {
                    return this.child.toString(options) + "{" + this.getMin() + "}";
                }
                if (this.getMin() >= 0 && this.getMax() >= 0) {
                    return this.child.toString(options) + "{" + this.getMin() + "," + this.getMax() + "}";
                }
                if (this.getMin() < 0) throw new RuntimeException("Token#toString(): CLOSURE " + this.getMin() + ", " + this.getMax());
                if (this.getMax() >= 0) throw new RuntimeException("Token#toString(): CLOSURE " + this.getMin() + ", " + this.getMax());
                return this.child.toString(options) + "{" + this.getMin() + ",}";
            }
            if (this.getMin() < 0 && this.getMax() < 0) {
                return this.child.toString(options) + "*?";
            }
            if (this.getMin() == this.getMax()) {
                return this.child.toString(options) + "{" + this.getMin() + "}?";
            }
            if (this.getMin() >= 0 && this.getMax() >= 0) {
                return this.child.toString(options) + "{" + this.getMin() + "," + this.getMax() + "}?";
            }
            if (this.getMin() < 0) throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + this.getMin() + ", " + this.getMax());
            if (this.getMax() >= 0) throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + this.getMin() + ", " + this.getMax());
            return this.child.toString(options) + "{" + this.getMin() + ",}?";
        }
    }

    static class CharToken
    extends Token
    implements Serializable {
        int chardata;

        CharToken(int type, int ch) {
            super(type);
            this.chardata = ch;
        }

        @Override
        int getChar() {
            return this.chardata;
        }

        @Override
        public String toString(int options) {
            String ret;
            block0 : switch (this.type) {
                case 0: {
                    switch (this.chardata) {
                        case 40: 
                        case 41: 
                        case 42: 
                        case 43: 
                        case 46: 
                        case 63: 
                        case 91: 
                        case 92: 
                        case 123: 
                        case 124: {
                            ret = "\\" + (char)this.chardata;
                            break block0;
                        }
                        case 12: {
                            ret = "\\f";
                            break block0;
                        }
                        case 10: {
                            ret = "\\n";
                            break block0;
                        }
                        case 13: {
                            ret = "\\r";
                            break block0;
                        }
                        case 9: {
                            ret = "\\t";
                            break block0;
                        }
                        case 27: {
                            ret = "\\e";
                            break block0;
                        }
                    }
                    if (this.chardata >= 65536) {
                        String pre = "0" + Integer.toHexString(this.chardata);
                        ret = "\\v" + pre.substring(pre.length() - 6, pre.length());
                        break;
                    }
                    ret = "" + (char)this.chardata;
                    break;
                }
                case 8: {
                    if (this == token_linebeginning || this == token_lineend) {
                        ret = "" + (char)this.chardata;
                        break;
                    }
                    ret = "\\" + (char)this.chardata;
                    break;
                }
                default: {
                    ret = null;
                }
            }
            return ret;
        }

        @Override
        boolean match(int ch) {
            if (this.type == 0) {
                return ch == this.chardata;
            }
            throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
        }
    }

    static class ConcatToken
    extends Token
    implements Serializable {
        Token child;
        Token child2;

        ConcatToken(Token t1, Token t2) {
            super(1);
            this.child = t1;
            this.child2 = t2;
        }

        @Override
        int size() {
            return 2;
        }

        @Override
        Token getChild(int index) {
            return index == 0 ? this.child : this.child2;
        }

        @Override
        public String toString(int options) {
            String ret = this.child2.type == 3 && this.child2.getChild(0) == this.child ? this.child.toString(options) + "+" : (this.child2.type == 9 && this.child2.getChild(0) == this.child ? this.child.toString(options) + "+?" : this.child.toString(options) + this.child2.toString(options));
            return ret;
        }
    }

    static class StringToken
    extends Token
    implements Serializable {
        String string;
        int refNumber;

        StringToken(int type, String str, int n) {
            super(type);
            this.string = str;
            this.refNumber = n;
        }

        @Override
        int getReferenceNumber() {
            return this.refNumber;
        }

        @Override
        String getString() {
            return this.string;
        }

        @Override
        public String toString(int options) {
            if (this.type == 12) {
                return "\\" + this.refNumber;
            }
            return REUtil.quoteMeta(this.string);
        }
    }

    static class FixedStringContainer {
        Token token = null;
        int options = 0;

        FixedStringContainer() {
        }
    }
}

