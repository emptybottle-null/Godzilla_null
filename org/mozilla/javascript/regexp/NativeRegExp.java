/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.regexp.CompilerState;
import org.mozilla.javascript.regexp.NativeRegExpCtor;
import org.mozilla.javascript.regexp.REBackTrackData;
import org.mozilla.javascript.regexp.RECharSet;
import org.mozilla.javascript.regexp.RECompiled;
import org.mozilla.javascript.regexp.REGlobalData;
import org.mozilla.javascript.regexp.RENode;
import org.mozilla.javascript.regexp.REProgState;
import org.mozilla.javascript.regexp.RegExpImpl;
import org.mozilla.javascript.regexp.SubString;

public class NativeRegExp
extends IdScriptableObject
implements Function {
    static final long serialVersionUID = 4965263491464903264L;
    private static final Object REGEXP_TAG = new Object();
    public static final int JSREG_GLOB = 1;
    public static final int JSREG_FOLD = 2;
    public static final int JSREG_MULTILINE = 4;
    public static final int TEST = 0;
    public static final int MATCH = 1;
    public static final int PREFIX = 2;
    private static final boolean debug = false;
    private static final byte REOP_SIMPLE_START = 1;
    private static final byte REOP_EMPTY = 1;
    private static final byte REOP_BOL = 2;
    private static final byte REOP_EOL = 3;
    private static final byte REOP_WBDRY = 4;
    private static final byte REOP_WNONBDRY = 5;
    private static final byte REOP_DOT = 6;
    private static final byte REOP_DIGIT = 7;
    private static final byte REOP_NONDIGIT = 8;
    private static final byte REOP_ALNUM = 9;
    private static final byte REOP_NONALNUM = 10;
    private static final byte REOP_SPACE = 11;
    private static final byte REOP_NONSPACE = 12;
    private static final byte REOP_BACKREF = 13;
    private static final byte REOP_FLAT = 14;
    private static final byte REOP_FLAT1 = 15;
    private static final byte REOP_FLATi = 16;
    private static final byte REOP_FLAT1i = 17;
    private static final byte REOP_UCFLAT1 = 18;
    private static final byte REOP_UCFLAT1i = 19;
    private static final byte REOP_CLASS = 22;
    private static final byte REOP_NCLASS = 23;
    private static final byte REOP_SIMPLE_END = 23;
    private static final byte REOP_QUANT = 25;
    private static final byte REOP_STAR = 26;
    private static final byte REOP_PLUS = 27;
    private static final byte REOP_OPT = 28;
    private static final byte REOP_LPAREN = 29;
    private static final byte REOP_RPAREN = 30;
    private static final byte REOP_ALT = 31;
    private static final byte REOP_JUMP = 32;
    private static final byte REOP_ASSERT = 41;
    private static final byte REOP_ASSERT_NOT = 42;
    private static final byte REOP_ASSERTTEST = 43;
    private static final byte REOP_ASSERTNOTTEST = 44;
    private static final byte REOP_MINIMALSTAR = 45;
    private static final byte REOP_MINIMALPLUS = 46;
    private static final byte REOP_MINIMALOPT = 47;
    private static final byte REOP_MINIMALQUANT = 48;
    private static final byte REOP_ENDCHILD = 49;
    private static final byte REOP_REPEAT = 51;
    private static final byte REOP_MINIMALREPEAT = 52;
    private static final byte REOP_ALTPREREQ = 53;
    private static final byte REOP_ALTPREREQi = 54;
    private static final byte REOP_ALTPREREQ2 = 55;
    private static final byte REOP_END = 57;
    private static final int ANCHOR_BOL = -2;
    private static final int INDEX_LEN = 2;
    private static final int Id_lastIndex = 1;
    private static final int Id_source = 2;
    private static final int Id_global = 3;
    private static final int Id_ignoreCase = 4;
    private static final int Id_multiline = 5;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int Id_compile = 1;
    private static final int Id_toString = 2;
    private static final int Id_toSource = 3;
    private static final int Id_exec = 4;
    private static final int Id_test = 5;
    private static final int Id_prefix = 6;
    private static final int MAX_PROTOTYPE_ID = 6;
    private RECompiled re;
    Object lastIndex = 0.0;
    private int lastIndexAttr = 6;

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeRegExp proto = new NativeRegExp();
        proto.re = NativeRegExp.compileRE(cx, "", null, false);
        proto.activatePrototypeMap(6);
        proto.setParentScope(scope);
        proto.setPrototype(NativeRegExp.getObjectPrototype(scope));
        NativeRegExpCtor ctor = new NativeRegExpCtor();
        proto.defineProperty("constructor", ctor, 2);
        ScriptRuntime.setFunctionProtoAndParent(ctor, scope);
        ctor.setImmunePrototypeProperty(proto);
        if (sealed) {
            proto.sealObject();
            ctor.sealObject();
        }
        NativeRegExp.defineProperty(scope, "RegExp", ctor, 2);
    }

    NativeRegExp(Scriptable scope, RECompiled regexpCompiled) {
        this.re = regexpCompiled;
        this.lastIndex = 0.0;
        ScriptRuntime.setBuiltinProtoAndParent(this, scope, TopLevel.Builtins.RegExp);
    }

    @Override
    public String getClassName() {
        return "RegExp";
    }

    @Override
    public String getTypeOf() {
        return "object";
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return this.execSub(cx, scope, args, 1);
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        return (Scriptable)this.execSub(cx, scope, args, 1);
    }

    Scriptable compile(Context cx, Scriptable scope, Object[] args) {
        if (args.length > 0 && args[0] instanceof NativeRegExp) {
            if (args.length > 1 && args[1] != Undefined.instance) {
                throw ScriptRuntime.typeError0("msg.bad.regexp.compile");
            }
            NativeRegExp thatObj = (NativeRegExp)args[0];
            this.re = thatObj.re;
            this.lastIndex = thatObj.lastIndex;
            return this;
        }
        String s = args.length == 0 || args[0] instanceof Undefined ? "" : NativeRegExp.escapeRegExp(args[0]);
        String global = args.length > 1 && args[1] != Undefined.instance ? ScriptRuntime.toString(args[1]) : null;
        this.re = NativeRegExp.compileRE(cx, s, global, false);
        this.lastIndex = 0.0;
        return this;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append('/');
        if (this.re.source.length != 0) {
            buf.append(this.re.source);
        } else {
            buf.append("(?:)");
        }
        buf.append('/');
        if ((this.re.flags & 1) != 0) {
            buf.append('g');
        }
        if ((this.re.flags & 2) != 0) {
            buf.append('i');
        }
        if ((this.re.flags & 4) != 0) {
            buf.append('m');
        }
        return buf.toString();
    }

    NativeRegExp() {
    }

    private static RegExpImpl getImpl(Context cx) {
        return (RegExpImpl)ScriptRuntime.getRegExpProxy(cx);
    }

    private static String escapeRegExp(Object src) {
        String s = ScriptRuntime.toString(src);
        StringBuilder sb = null;
        int start = 0;
        int slash = s.indexOf(47);
        while (slash > -1) {
            if (slash == start || s.charAt(slash - 1) != '\\') {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(s, start, slash);
                sb.append("\\/");
                start = slash + 1;
            }
            slash = s.indexOf(47, slash + 1);
        }
        if (sb != null) {
            sb.append(s, start, s.length());
            s = sb.toString();
        }
        return s;
    }

    private Object execSub(Context cx, Scriptable scopeObj, Object[] args, int matchType) {
        Object rval;
        String str;
        RegExpImpl reImpl = NativeRegExp.getImpl(cx);
        if (args.length == 0) {
            str = reImpl.input;
            if (str == null) {
                str = ScriptRuntime.toString(Undefined.instance);
            }
        } else {
            str = ScriptRuntime.toString(args[0]);
        }
        double d = 0.0;
        if ((this.re.flags & 1) != 0) {
            d = ScriptRuntime.toInteger(this.lastIndex);
        }
        if (d < 0.0 || (double)str.length() < d) {
            this.lastIndex = 0.0;
            rval = null;
        } else {
            int[] indexp = new int[]{(int)d};
            rval = this.executeRegExp(cx, scopeObj, reImpl, str, indexp, matchType);
            if ((this.re.flags & 1) != 0) {
                this.lastIndex = rval == null || rval == Undefined.instance ? 0.0 : (double)indexp[0];
            }
        }
        return rval;
    }

    static RECompiled compileRE(Context cx, String str, String global, boolean flat) {
        RECompiled regexp = new RECompiled(str);
        int length = str.length();
        int flags = 0;
        if (global != null) {
            for (int i = 0; i < global.length(); ++i) {
                char c = global.charAt(i);
                int f = 0;
                if (c == 'g') {
                    f = 1;
                } else if (c == 'i') {
                    f = 2;
                } else if (c == 'm') {
                    f = 4;
                } else {
                    NativeRegExp.reportError("msg.invalid.re.flag", String.valueOf(c));
                }
                if ((flags & f) != 0) {
                    NativeRegExp.reportError("msg.invalid.re.flag", String.valueOf(c));
                }
                flags |= f;
            }
        }
        regexp.flags = flags;
        CompilerState state = new CompilerState(cx, regexp.source, length, flags);
        if (flat && length > 0) {
            state.result = new RENode(14);
            state.result.chr = state.cpbegin[0];
            state.result.length = length;
            state.result.flatIndex = 0;
            state.progLength += 5;
        } else {
            if (!NativeRegExp.parseDisjunction(state)) {
                return null;
            }
            if (state.maxBackReference > state.parenCount) {
                state = new CompilerState(cx, regexp.source, length, flags);
                state.backReferenceLimit = state.parenCount;
                if (!NativeRegExp.parseDisjunction(state)) {
                    return null;
                }
            }
        }
        regexp.program = new byte[state.progLength + 1];
        if (state.classCount != 0) {
            regexp.classList = new RECharSet[state.classCount];
            regexp.classCount = state.classCount;
        }
        int endPC = NativeRegExp.emitREBytecode(state, regexp, 0, state.result);
        regexp.program[endPC++] = 57;
        regexp.parenCount = state.parenCount;
        switch (regexp.program[0]) {
            case 18: 
            case 19: {
                regexp.anchorCh = (char)NativeRegExp.getIndex(regexp.program, 1);
                break;
            }
            case 15: 
            case 17: {
                regexp.anchorCh = (char)(regexp.program[1] & 0xFF);
                break;
            }
            case 14: 
            case 16: {
                int k = NativeRegExp.getIndex(regexp.program, 1);
                regexp.anchorCh = regexp.source[k];
                break;
            }
            case 2: {
                regexp.anchorCh = -2;
                break;
            }
            case 31: {
                RENode n = state.result;
                if (n.kid.op != 2 || n.kid2.op != 2) break;
                regexp.anchorCh = -2;
            }
        }
        return regexp;
    }

    static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isWord(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || NativeRegExp.isDigit(c) || c == '_';
    }

    private static boolean isControlLetter(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }

    private static boolean isLineTerm(char c) {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int c) {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(c);
    }

    private static char upcase(char ch) {
        if (ch < '\u0080') {
            if ('a' <= ch && ch <= 'z') {
                return (char)(ch + -32);
            }
            return ch;
        }
        char cu = Character.toUpperCase(ch);
        return cu < '\u0080' ? ch : cu;
    }

    private static char downcase(char ch) {
        if (ch < '\u0080') {
            if ('A' <= ch && ch <= 'Z') {
                return (char)(ch + 32);
            }
            return ch;
        }
        char cl = Character.toLowerCase(ch);
        return cl < '\u0080' ? ch : cl;
    }

    private static int toASCIIHexDigit(int c) {
        if (c < 48) {
            return -1;
        }
        if (c <= 57) {
            return c - 48;
        }
        if (97 <= (c |= 0x20) && c <= 102) {
            return c - 97 + 10;
        }
        return -1;
    }

    private static boolean parseDisjunction(CompilerState state) {
        int index;
        if (!NativeRegExp.parseAlternative(state)) {
            return false;
        }
        char[] source = state.cpbegin;
        if ((index = state.cp++) != source.length && source[index] == '|') {
            RENode result = new RENode(31);
            result.kid = state.result;
            if (!NativeRegExp.parseDisjunction(state)) {
                return false;
            }
            result.kid2 = state.result;
            state.result = result;
            if (result.kid.op == 14 && result.kid2.op == 14) {
                result.op = (byte)((state.flags & 2) == 0 ? 53 : 54);
                result.chr = result.kid.chr;
                result.index = result.kid2.chr;
                state.progLength += 13;
            } else if (result.kid.op == 22 && result.kid.index < 256 && result.kid2.op == 14 && (state.flags & 2) == 0) {
                result.op = (byte)55;
                result.chr = result.kid2.chr;
                result.index = result.kid.index;
                state.progLength += 13;
            } else if (result.kid.op == 14 && result.kid2.op == 22 && result.kid2.index < 256 && (state.flags & 2) == 0) {
                result.op = (byte)55;
                result.chr = result.kid.chr;
                result.index = result.kid2.index;
                state.progLength += 13;
            } else {
                state.progLength += 9;
            }
        }
        return true;
    }

    private static boolean parseAlternative(CompilerState state) {
        RENode headTerm = null;
        RENode tailTerm = null;
        char[] source = state.cpbegin;
        block0: while (true) {
            if (state.cp == state.cpend || source[state.cp] == '|' || state.parenNesting != 0 && source[state.cp] == ')') {
                state.result = headTerm == null ? new RENode(1) : headTerm;
                return true;
            }
            if (!NativeRegExp.parseTerm(state)) {
                return false;
            }
            if (headTerm == null) {
                tailTerm = headTerm = state.result;
            } else {
                tailTerm.next = state.result;
            }
            while (true) {
                if (tailTerm.next == null) continue block0;
                tailTerm = tailTerm.next;
            }
            break;
        }
    }

    private static boolean calculateBitmapSize(CompilerState state, RENode target, char[] src, int index, int end) {
        int rangeStart = 0;
        int max = 0;
        boolean inRange = false;
        target.bmsize = 0;
        target.sense = true;
        if (index == end) {
            return true;
        }
        if (src[index] == '^') {
            ++index;
            target.sense = false;
        }
        while (index != end) {
            int localMax = 0;
            int nDigits = 2;
            block0 : switch (src[index]) {
                case '\\': {
                    int n = ++index;
                    ++index;
                    int c = src[n];
                    switch (c) {
                        case 98: {
                            localMax = 8;
                            break block0;
                        }
                        case 102: {
                            localMax = 12;
                            break block0;
                        }
                        case 110: {
                            localMax = 10;
                            break block0;
                        }
                        case 114: {
                            localMax = 13;
                            break block0;
                        }
                        case 116: {
                            localMax = 9;
                            break block0;
                        }
                        case 118: {
                            localMax = 11;
                            break block0;
                        }
                        case 99: {
                            if (index < end && NativeRegExp.isControlLetter(src[index])) {
                                localMax = (char)(src[index++] & 0x1F);
                            } else {
                                --index;
                            }
                            localMax = 92;
                            break block0;
                        }
                        case 117: {
                            nDigits += 2;
                        }
                        case 120: {
                            int i;
                            int n2 = 0;
                            for (i = 0; i < nDigits && index < end; ++i) {
                                if ((n2 = Kit.xDigitToInt(c = src[index++], n2)) >= 0) continue;
                                index -= i + 1;
                                n2 = 92;
                                break;
                            }
                            localMax = n2;
                            break block0;
                        }
                        case 100: {
                            if (inRange) {
                                NativeRegExp.reportError("msg.bad.range", "");
                                return false;
                            }
                            localMax = 57;
                            break block0;
                        }
                        case 68: 
                        case 83: 
                        case 87: 
                        case 115: 
                        case 119: {
                            if (inRange) {
                                NativeRegExp.reportError("msg.bad.range", "");
                                return false;
                            }
                            target.bmsize = 65536;
                            return true;
                        }
                        case 48: 
                        case 49: 
                        case 50: 
                        case 51: 
                        case 52: 
                        case 53: 
                        case 54: 
                        case 55: {
                            int i;
                            int n2 = c - 48;
                            c = src[index];
                            if (48 <= c && c <= 55) {
                                n2 = 8 * n2 + (c - 48);
                                if (48 <= (c = src[++index]) && c <= 55) {
                                    ++index;
                                    i = 8 * n2 + (c - 48);
                                    if (i <= 255) {
                                        n2 = i;
                                    } else {
                                        --index;
                                    }
                                }
                            }
                            localMax = n2;
                            break block0;
                        }
                    }
                    localMax = c;
                    break;
                }
                default: {
                    localMax = src[index++];
                }
            }
            if (inRange) {
                if (rangeStart > localMax) {
                    NativeRegExp.reportError("msg.bad.range", "");
                    return false;
                }
                inRange = false;
            } else if (index < end - 1 && src[index] == '-') {
                ++index;
                inRange = true;
                rangeStart = (char)localMax;
                continue;
            }
            if ((state.flags & 2) != 0) {
                char cd;
                char cu = NativeRegExp.upcase((char)localMax);
                localMax = cu >= (cd = NativeRegExp.downcase((char)localMax)) ? cu : cd;
            }
            if (localMax <= max) continue;
            max = localMax;
        }
        target.bmsize = max + 1;
        return true;
    }

    private static void doFlat(CompilerState state, char c) {
        state.result = new RENode(14);
        state.result.chr = c;
        state.result.length = 1;
        state.result.flatIndex = -1;
        state.progLength += 3;
    }

    private static int getDecimalValue(char c, CompilerState state, int maxValue, String overflowMessageId) {
        boolean overflow = false;
        int start = state.cp;
        char[] src = state.cpbegin;
        int value = c - 48;
        while (state.cp != state.cpend && NativeRegExp.isDigit(c = src[state.cp])) {
            if (!overflow) {
                int v = value * 10 + (c - 48);
                if (v < maxValue) {
                    value = v;
                } else {
                    overflow = true;
                    value = maxValue;
                }
            }
            ++state.cp;
        }
        if (overflow) {
            NativeRegExp.reportError(overflowMessageId, String.valueOf(src, start, state.cp - start));
        }
        return value;
    }

    private static boolean parseTerm(CompilerState state) {
        char[] src = state.cpbegin;
        char c = src[state.cp++];
        int nDigits = 2;
        int parenBaseCount = state.parenCount;
        block0 : switch (c) {
            case '^': {
                state.result = new RENode(2);
                ++state.progLength;
                return true;
            }
            case '$': {
                state.result = new RENode(3);
                ++state.progLength;
                return true;
            }
            case '\\': {
                if (state.cp < state.cpend) {
                    c = src[state.cp++];
                    switch (c) {
                        case 'b': {
                            state.result = new RENode(4);
                            ++state.progLength;
                            return true;
                        }
                        case 'B': {
                            state.result = new RENode(5);
                            ++state.progLength;
                            return true;
                        }
                        case '0': {
                            NativeRegExp.reportWarning(state.cx, "msg.bad.backref", "");
                            int num = 0;
                            while (num < 32 && state.cp < state.cpend && (c = src[state.cp]) >= '0' && c <= '7') {
                                ++state.cp;
                                num = 8 * num + (c - 48);
                            }
                            c = (char)num;
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case '1': 
                        case '2': 
                        case '3': 
                        case '4': 
                        case '5': 
                        case '6': 
                        case '7': 
                        case '8': 
                        case '9': {
                            int termStart = state.cp - 1;
                            int num = NativeRegExp.getDecimalValue(c, state, 65535, "msg.overlarge.backref");
                            if (num > state.backReferenceLimit) {
                                NativeRegExp.reportWarning(state.cx, "msg.bad.backref", "");
                            }
                            if (num > state.backReferenceLimit) {
                                state.cp = termStart;
                                if (c >= '8') {
                                    c = '\\';
                                    NativeRegExp.doFlat(state, c);
                                    break;
                                }
                                ++state.cp;
                                num = c - 48;
                                while (num < 32 && state.cp < state.cpend && (c = src[state.cp]) >= '0' && c <= '7') {
                                    ++state.cp;
                                    num = 8 * num + (c - 48);
                                }
                                c = (char)num;
                                NativeRegExp.doFlat(state, c);
                                break;
                            }
                            state.result = new RENode(13);
                            state.result.parenIndex = num - 1;
                            state.progLength += 3;
                            if (state.maxBackReference >= num) break block0;
                            state.maxBackReference = num;
                            break;
                        }
                        case 'f': {
                            c = '\f';
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 'n': {
                            c = '\n';
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 'r': {
                            c = '\r';
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 't': {
                            c = '\t';
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 'v': {
                            c = '\u000b';
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 'c': {
                            if (state.cp < state.cpend && NativeRegExp.isControlLetter(src[state.cp])) {
                                c = (char)(src[state.cp++] & 0x1F);
                            } else {
                                --state.cp;
                                c = '\\';
                            }
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 'u': {
                            nDigits += 2;
                        }
                        case 'x': {
                            int n = 0;
                            for (int i = 0; i < nDigits && state.cp < state.cpend; ++i) {
                                if ((n = Kit.xDigitToInt(c = src[state.cp++], n)) >= 0) continue;
                                state.cp -= i + 2;
                                n = src[state.cp++];
                                break;
                            }
                            c = (char)n;
                            NativeRegExp.doFlat(state, c);
                            break;
                        }
                        case 'd': {
                            state.result = new RENode(7);
                            ++state.progLength;
                            break;
                        }
                        case 'D': {
                            state.result = new RENode(8);
                            ++state.progLength;
                            break;
                        }
                        case 's': {
                            state.result = new RENode(11);
                            ++state.progLength;
                            break;
                        }
                        case 'S': {
                            state.result = new RENode(12);
                            ++state.progLength;
                            break;
                        }
                        case 'w': {
                            state.result = new RENode(9);
                            ++state.progLength;
                            break;
                        }
                        case 'W': {
                            state.result = new RENode(10);
                            ++state.progLength;
                            break;
                        }
                        default: {
                            state.result = new RENode(14);
                            state.result.chr = c;
                            state.result.length = 1;
                            state.result.flatIndex = state.cp - 1;
                            state.progLength += 3;
                            break;
                        }
                    }
                    break;
                }
                NativeRegExp.reportError("msg.trail.backslash", "");
                return false;
            }
            case '(': {
                RENode result = null;
                int termStart = state.cp;
                if (state.cp + 1 < state.cpend && src[state.cp] == '?' && ((c = src[state.cp + 1]) == '=' || c == '!' || c == ':')) {
                    state.cp += 2;
                    if (c == '=') {
                        result = new RENode(41);
                        state.progLength += 4;
                    } else if (c == '!') {
                        result = new RENode(42);
                        state.progLength += 4;
                    }
                } else {
                    result = new RENode(29);
                    state.progLength += 6;
                    result.parenIndex = state.parenCount++;
                }
                ++state.parenNesting;
                if (!NativeRegExp.parseDisjunction(state)) {
                    return false;
                }
                if (state.cp == state.cpend || src[state.cp] != ')') {
                    NativeRegExp.reportError("msg.unterm.paren", "");
                    return false;
                }
                ++state.cp;
                --state.parenNesting;
                if (result == null) break;
                result.kid = state.result;
                state.result = result;
                break;
            }
            case ')': {
                NativeRegExp.reportError("msg.re.unmatched.right.paren", "");
                return false;
            }
            case '[': {
                int termStart;
                state.result = new RENode(22);
                state.result.startIndex = termStart = state.cp;
                while (true) {
                    if (state.cp == state.cpend) {
                        NativeRegExp.reportError("msg.unterm.class", "");
                        return false;
                    }
                    if (src[state.cp] == '\\') {
                        ++state.cp;
                    } else if (src[state.cp] == ']') break;
                    ++state.cp;
                }
                state.result.kidlen = state.cp - termStart;
                state.result.index = state.classCount++;
                if (!NativeRegExp.calculateBitmapSize(state, state.result, src, termStart, state.cp++)) {
                    return false;
                }
                state.progLength += 3;
                break;
            }
            case '.': {
                state.result = new RENode(6);
                ++state.progLength;
                break;
            }
            case '*': 
            case '+': 
            case '?': {
                NativeRegExp.reportError("msg.bad.quant", String.valueOf(src[state.cp - 1]));
                return false;
            }
            default: {
                state.result = new RENode(14);
                state.result.chr = c;
                state.result.length = 1;
                state.result.flatIndex = state.cp - 1;
                state.progLength += 3;
            }
        }
        RENode term = state.result;
        if (state.cp == state.cpend) {
            return true;
        }
        boolean hasQ = false;
        switch (src[state.cp]) {
            case '+': {
                state.result = new RENode(25);
                state.result.min = 1;
                state.result.max = -1;
                state.progLength += 8;
                hasQ = true;
                break;
            }
            case '*': {
                state.result = new RENode(25);
                state.result.min = 0;
                state.result.max = -1;
                state.progLength += 8;
                hasQ = true;
                break;
            }
            case '?': {
                state.result = new RENode(25);
                state.result.min = 0;
                state.result.max = 1;
                state.progLength += 8;
                hasQ = true;
                break;
            }
            case '{': {
                int min = 0;
                int max = -1;
                int leftCurl = state.cp++;
                if (state.cp < src.length && NativeRegExp.isDigit(c = src[state.cp])) {
                    ++state.cp;
                    min = NativeRegExp.getDecimalValue(c, state, 65535, "msg.overlarge.min");
                    c = src[state.cp];
                    if (c == ',') {
                        if (NativeRegExp.isDigit(c = src[++state.cp])) {
                            ++state.cp;
                            max = NativeRegExp.getDecimalValue(c, state, 65535, "msg.overlarge.max");
                            c = src[state.cp];
                            if (min > max) {
                                NativeRegExp.reportError("msg.max.lt.min", String.valueOf(src[state.cp]));
                                return false;
                            }
                        }
                    } else {
                        max = min;
                    }
                    if (c == '}') {
                        state.result = new RENode(25);
                        state.result.min = min;
                        state.result.max = max;
                        state.progLength += 12;
                        hasQ = true;
                    }
                }
                if (hasQ) break;
                state.cp = leftCurl;
                break;
            }
        }
        if (!hasQ) {
            return true;
        }
        ++state.cp;
        state.result.kid = term;
        state.result.parenIndex = parenBaseCount;
        state.result.parenCount = state.parenCount - parenBaseCount;
        if (state.cp < state.cpend && src[state.cp] == '?') {
            ++state.cp;
            state.result.greedy = false;
        } else {
            state.result.greedy = true;
        }
        return true;
    }

    private static void resolveForwardJump(byte[] array, int from, int pc) {
        if (from > pc) {
            throw Kit.codeBug();
        }
        NativeRegExp.addIndex(array, from, pc - from);
    }

    private static int getOffset(byte[] array, int pc) {
        return NativeRegExp.getIndex(array, pc);
    }

    private static int addIndex(byte[] array, int pc, int index) {
        if (index < 0) {
            throw Kit.codeBug();
        }
        if (index > 65535) {
            throw Context.reportRuntimeError("Too complex regexp");
        }
        array[pc] = (byte)(index >> 8);
        array[pc + 1] = (byte)index;
        return pc + 2;
    }

    private static int getIndex(byte[] array, int pc) {
        return (array[pc] & 0xFF) << 8 | array[pc + 1] & 0xFF;
    }

    private static int emitREBytecode(CompilerState state, RECompiled re, int pc, RENode t) {
        byte[] program = re.program;
        while (t != null) {
            program[pc++] = t.op;
            switch (t.op) {
                case 1: {
                    --pc;
                    break;
                }
                case 53: 
                case 54: 
                case 55: {
                    boolean ignoreCase = t.op == 54;
                    NativeRegExp.addIndex(program, pc, ignoreCase ? NativeRegExp.upcase(t.chr) : t.chr);
                    NativeRegExp.addIndex(program, pc += 2, ignoreCase ? (int)NativeRegExp.upcase((char)t.index) : t.index);
                    pc += 2;
                }
                case 31: {
                    RENode nextAlt = t.kid2;
                    int nextAltFixup = pc;
                    pc += 2;
                    pc = NativeRegExp.emitREBytecode(state, re, pc, t.kid);
                    program[pc++] = 32;
                    int nextTermFixup = pc;
                    NativeRegExp.resolveForwardJump(program, nextAltFixup, pc += 2);
                    pc = NativeRegExp.emitREBytecode(state, re, pc, nextAlt);
                    program[pc++] = 32;
                    nextAltFixup = pc;
                    NativeRegExp.resolveForwardJump(program, nextTermFixup, pc += 2);
                    NativeRegExp.resolveForwardJump(program, nextAltFixup, pc);
                    break;
                }
                case 14: {
                    if (t.flatIndex != -1) {
                        while (t.next != null && t.next.op == 14 && t.flatIndex + t.length == t.next.flatIndex) {
                            t.length += t.next.length;
                            t.next = t.next.next;
                        }
                    }
                    if (t.flatIndex != -1 && t.length > 1) {
                        program[pc - 1] = (state.flags & 2) != 0 ? 16 : 14;
                        pc = NativeRegExp.addIndex(program, pc, t.flatIndex);
                        pc = NativeRegExp.addIndex(program, pc, t.length);
                        break;
                    }
                    if (t.chr < '\u0100') {
                        program[pc - 1] = (state.flags & 2) != 0 ? 17 : 15;
                        program[pc++] = (byte)t.chr;
                        break;
                    }
                    program[pc - 1] = (state.flags & 2) != 0 ? 19 : 18;
                    pc = NativeRegExp.addIndex(program, pc, t.chr);
                    break;
                }
                case 29: {
                    pc = NativeRegExp.addIndex(program, pc, t.parenIndex);
                    pc = NativeRegExp.emitREBytecode(state, re, pc, t.kid);
                    program[pc++] = 30;
                    pc = NativeRegExp.addIndex(program, pc, t.parenIndex);
                    break;
                }
                case 13: {
                    pc = NativeRegExp.addIndex(program, pc, t.parenIndex);
                    break;
                }
                case 41: {
                    int nextTermFixup = pc;
                    pc += 2;
                    pc = NativeRegExp.emitREBytecode(state, re, pc, t.kid);
                    program[pc++] = 43;
                    NativeRegExp.resolveForwardJump(program, nextTermFixup, pc);
                    break;
                }
                case 42: {
                    int nextTermFixup = pc;
                    pc += 2;
                    pc = NativeRegExp.emitREBytecode(state, re, pc, t.kid);
                    program[pc++] = 44;
                    NativeRegExp.resolveForwardJump(program, nextTermFixup, pc);
                    break;
                }
                case 25: {
                    if (t.min == 0 && t.max == -1) {
                        program[pc - 1] = t.greedy ? 26 : 45;
                    } else if (t.min == 0 && t.max == 1) {
                        program[pc - 1] = t.greedy ? 28 : 47;
                    } else if (t.min == 1 && t.max == -1) {
                        program[pc - 1] = t.greedy ? 27 : 46;
                    } else {
                        if (!t.greedy) {
                            program[pc - 1] = 48;
                        }
                        pc = NativeRegExp.addIndex(program, pc, t.min);
                        pc = NativeRegExp.addIndex(program, pc, t.max + 1);
                    }
                    pc = NativeRegExp.addIndex(program, pc, t.parenCount);
                    int nextTermFixup = pc = NativeRegExp.addIndex(program, pc, t.parenIndex);
                    pc += 2;
                    pc = NativeRegExp.emitREBytecode(state, re, pc, t.kid);
                    program[pc++] = 49;
                    NativeRegExp.resolveForwardJump(program, nextTermFixup, pc);
                    break;
                }
                case 22: {
                    if (!t.sense) {
                        program[pc - 1] = 23;
                    }
                    pc = NativeRegExp.addIndex(program, pc, t.index);
                    re.classList[t.index] = new RECharSet(t.bmsize, t.startIndex, t.kidlen, t.sense);
                    break;
                }
            }
            t = t.next;
        }
        return pc;
    }

    private static void pushProgState(REGlobalData gData, int min, int max, int cp, REBackTrackData backTrackLastToSave, int continuationOp, int continuationPc) {
        gData.stateStackTop = new REProgState(gData.stateStackTop, min, max, cp, backTrackLastToSave, continuationOp, continuationPc);
    }

    private static REProgState popProgState(REGlobalData gData) {
        REProgState state = gData.stateStackTop;
        gData.stateStackTop = state.previous;
        return state;
    }

    private static void pushBackTrackState(REGlobalData gData, byte op, int pc) {
        REProgState state = gData.stateStackTop;
        gData.backTrackStackTop = new REBackTrackData(gData, op, pc, gData.cp, state.continuationOp, state.continuationPc);
    }

    private static void pushBackTrackState(REGlobalData gData, byte op, int pc, int cp, int continuationOp, int continuationPc) {
        gData.backTrackStackTop = new REBackTrackData(gData, op, pc, cp, continuationOp, continuationPc);
    }

    private static boolean flatNMatcher(REGlobalData gData, int matchChars, int length, String input, int end) {
        if (gData.cp + length > end) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (gData.regexp.source[matchChars + i] == input.charAt(gData.cp + i)) continue;
            return false;
        }
        gData.cp += length;
        return true;
    }

    private static boolean flatNIMatcher(REGlobalData gData, int matchChars, int length, String input, int end) {
        if (gData.cp + length > end) {
            return false;
        }
        char[] source = gData.regexp.source;
        for (int i = 0; i < length; ++i) {
            char c1 = source[matchChars + i];
            char c2 = input.charAt(gData.cp + i);
            if (c1 == c2 || NativeRegExp.upcase(c1) == NativeRegExp.upcase(c2)) continue;
            return false;
        }
        gData.cp += length;
        return true;
    }

    private static boolean backrefMatcher(REGlobalData gData, int parenIndex, String input, int end) {
        if (gData.parens == null || parenIndex >= gData.parens.length) {
            return false;
        }
        int parenContent = gData.parensIndex(parenIndex);
        if (parenContent == -1) {
            return true;
        }
        int len = gData.parensLength(parenIndex);
        if (gData.cp + len > end) {
            return false;
        }
        if ((gData.regexp.flags & 2) != 0) {
            for (int i = 0; i < len; ++i) {
                char c2;
                char c1 = input.charAt(parenContent + i);
                if (c1 == (c2 = input.charAt(gData.cp + i)) || NativeRegExp.upcase(c1) == NativeRegExp.upcase(c2)) continue;
                return false;
            }
        } else if (!input.regionMatches(parenContent, input, gData.cp, len)) {
            return false;
        }
        gData.cp += len;
        return true;
    }

    private static void addCharacterToCharSet(RECharSet cs, char c) {
        int byteIndex = c / 8;
        if (c >= cs.length) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        int n = byteIndex;
        cs.bits[n] = (byte)(cs.bits[n] | 1 << (c & 7));
    }

    private static void addCharacterRangeToCharSet(RECharSet cs, char c1, char c2) {
        int byteIndex1 = c1 / 8;
        int byteIndex2 = c2 / 8;
        if (c2 >= cs.length || c1 > c2) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        c1 = (char)(c1 & 7);
        c2 = (char)(c2 & 7);
        if (byteIndex1 == byteIndex2) {
            int n = byteIndex1;
            cs.bits[n] = (byte)(cs.bits[n] | 255 >> 7 - (c2 - c1) << c1);
        } else {
            int n = byteIndex1;
            cs.bits[n] = (byte)(cs.bits[n] | 255 << c1);
            for (int i = byteIndex1 + 1; i < byteIndex2; ++i) {
                cs.bits[i] = -1;
            }
            int n2 = byteIndex2;
            cs.bits[n2] = (byte)(cs.bits[n2] | 255 >> 7 - c2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void processCharSet(REGlobalData gData, RECharSet charSet) {
        RECharSet rECharSet = charSet;
        synchronized (rECharSet) {
            if (!charSet.converted) {
                NativeRegExp.processCharSetImpl(gData, charSet);
                charSet.converted = true;
            }
        }
    }

    private static void processCharSetImpl(REGlobalData gData, RECharSet charSet) {
        int src = charSet.startIndex;
        int end = src + charSet.strlength;
        char rangeStart = '\u0000';
        boolean inRange = false;
        int byteLength = (charSet.length + 7) / 8;
        charSet.bits = new byte[byteLength];
        if (src == end) {
            return;
        }
        if (gData.regexp.source[src] == '^') {
            assert (!charSet.sense);
            ++src;
        } else assert (charSet.sense);
        block21: while (src != end) {
            char thisCh;
            char c;
            int nDigits = 2;
            block0 : switch (gData.regexp.source[src]) {
                case '\\': {
                    int n = ++src;
                    ++src;
                    c = gData.regexp.source[n];
                    switch (c) {
                        case 'b': {
                            thisCh = '\b';
                            break block0;
                        }
                        case 'f': {
                            thisCh = '\f';
                            break block0;
                        }
                        case 'n': {
                            thisCh = '\n';
                            break block0;
                        }
                        case 'r': {
                            thisCh = '\r';
                            break block0;
                        }
                        case 't': {
                            thisCh = '\t';
                            break block0;
                        }
                        case 'v': {
                            thisCh = '\u000b';
                            break block0;
                        }
                        case 'c': {
                            if (src < end && NativeRegExp.isControlLetter(gData.regexp.source[src])) {
                                thisCh = (char)(gData.regexp.source[src++] & 0x1F);
                                break block0;
                            }
                            --src;
                            thisCh = '\\';
                            break block0;
                        }
                        case 'u': {
                            nDigits += 2;
                        }
                        case 'x': {
                            int i;
                            int n2 = 0;
                            for (i = 0; i < nDigits && src < end; ++i) {
                                int digit;
                                if ((digit = NativeRegExp.toASCIIHexDigit(c = gData.regexp.source[src++])) < 0) {
                                    src -= i + 1;
                                    n2 = 92;
                                    break;
                                }
                                n2 = n2 << 4 | digit;
                            }
                            thisCh = (char)n2;
                            break block0;
                        }
                        case '0': 
                        case '1': 
                        case '2': 
                        case '3': 
                        case '4': 
                        case '5': 
                        case '6': 
                        case '7': {
                            int i;
                            int n2 = c - 48;
                            c = gData.regexp.source[src];
                            if ('0' <= c && c <= '7') {
                                n2 = 8 * n2 + (c - 48);
                                if ('0' <= (c = gData.regexp.source[++src]) && c <= '7') {
                                    ++src;
                                    i = 8 * n2 + (c - 48);
                                    if (i <= 255) {
                                        n2 = i;
                                    } else {
                                        --src;
                                    }
                                }
                            }
                            thisCh = (char)n2;
                            break block0;
                        }
                        case 'd': {
                            NativeRegExp.addCharacterRangeToCharSet(charSet, '0', '9');
                            continue block21;
                        }
                        case 'D': {
                            NativeRegExp.addCharacterRangeToCharSet(charSet, '\u0000', '/');
                            NativeRegExp.addCharacterRangeToCharSet(charSet, ':', (char)(charSet.length - 1));
                            continue block21;
                        }
                        case 's': {
                            int i;
                            for (i = charSet.length - 1; i >= 0; --i) {
                                if (!NativeRegExp.isREWhiteSpace(i)) continue;
                                NativeRegExp.addCharacterToCharSet(charSet, (char)i);
                            }
                            continue block21;
                        }
                        case 'S': {
                            int i;
                            for (i = charSet.length - 1; i >= 0; --i) {
                                if (NativeRegExp.isREWhiteSpace(i)) continue;
                                NativeRegExp.addCharacterToCharSet(charSet, (char)i);
                            }
                            continue block21;
                        }
                        case 'w': {
                            int i;
                            for (i = charSet.length - 1; i >= 0; --i) {
                                if (!NativeRegExp.isWord((char)i)) continue;
                                NativeRegExp.addCharacterToCharSet(charSet, (char)i);
                            }
                            continue block21;
                        }
                        case 'W': {
                            int i;
                            for (i = charSet.length - 1; i >= 0; --i) {
                                if (NativeRegExp.isWord((char)i)) continue;
                                NativeRegExp.addCharacterToCharSet(charSet, (char)i);
                            }
                            continue block21;
                        }
                    }
                    thisCh = c;
                    break;
                }
                default: {
                    thisCh = gData.regexp.source[src++];
                }
            }
            if (inRange) {
                if ((gData.regexp.flags & 2) != 0) {
                    assert (rangeStart <= thisCh);
                    c = rangeStart;
                    while (c <= thisCh) {
                        NativeRegExp.addCharacterToCharSet(charSet, c);
                        char uch = NativeRegExp.upcase(c);
                        char dch = NativeRegExp.downcase(c);
                        if (c != uch) {
                            NativeRegExp.addCharacterToCharSet(charSet, uch);
                        }
                        if (c != dch) {
                            NativeRegExp.addCharacterToCharSet(charSet, dch);
                        }
                        if ((c = (char)(c + '\u0001')) != '\u0000') continue;
                        break;
                    }
                } else {
                    NativeRegExp.addCharacterRangeToCharSet(charSet, rangeStart, thisCh);
                }
                inRange = false;
                continue;
            }
            if ((gData.regexp.flags & 2) != 0) {
                NativeRegExp.addCharacterToCharSet(charSet, NativeRegExp.upcase(thisCh));
                NativeRegExp.addCharacterToCharSet(charSet, NativeRegExp.downcase(thisCh));
            } else {
                NativeRegExp.addCharacterToCharSet(charSet, thisCh);
            }
            if (src >= end - 1 || gData.regexp.source[src] != '-') continue;
            ++src;
            inRange = true;
            rangeStart = thisCh;
        }
    }

    private static boolean classMatcher(REGlobalData gData, RECharSet charSet, char ch) {
        if (!charSet.converted) {
            NativeRegExp.processCharSet(gData, charSet);
        }
        int byteIndex = ch >> 3;
        return (charSet.length == 0 || ch >= charSet.length || (charSet.bits[byteIndex] & 1 << (ch & 7)) == 0) ^ charSet.sense;
    }

    private static boolean reopIsSimple(int op) {
        return op >= 1 && op <= 23;
    }

    private static int simpleMatch(REGlobalData gData, String input, int op, byte[] program, int pc, int end, boolean updatecp) {
        boolean result = false;
        int startcp = gData.cp;
        switch (op) {
            case 1: {
                result = true;
                break;
            }
            case 2: {
                if (gData.cp != 0 && (!gData.multiline || !NativeRegExp.isLineTerm(input.charAt(gData.cp - 1)))) break;
                result = true;
                break;
            }
            case 3: {
                if (gData.cp != end && (!gData.multiline || !NativeRegExp.isLineTerm(input.charAt(gData.cp)))) break;
                result = true;
                break;
            }
            case 4: {
                result = (gData.cp == 0 || !NativeRegExp.isWord(input.charAt(gData.cp - 1))) ^ (gData.cp >= end || !NativeRegExp.isWord(input.charAt(gData.cp)));
                break;
            }
            case 5: {
                result = (gData.cp == 0 || !NativeRegExp.isWord(input.charAt(gData.cp - 1))) ^ (gData.cp < end && NativeRegExp.isWord(input.charAt(gData.cp)));
                break;
            }
            case 6: {
                if (gData.cp == end || NativeRegExp.isLineTerm(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 7: {
                if (gData.cp == end || !NativeRegExp.isDigit(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 8: {
                if (gData.cp == end || NativeRegExp.isDigit(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 9: {
                if (gData.cp == end || !NativeRegExp.isWord(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 10: {
                if (gData.cp == end || NativeRegExp.isWord(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 11: {
                if (gData.cp == end || !NativeRegExp.isREWhiteSpace(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 12: {
                if (gData.cp == end || NativeRegExp.isREWhiteSpace(input.charAt(gData.cp))) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 13: {
                int parenIndex = NativeRegExp.getIndex(program, pc);
                pc += 2;
                result = NativeRegExp.backrefMatcher(gData, parenIndex, input, end);
                break;
            }
            case 14: {
                int offset = NativeRegExp.getIndex(program, pc);
                int length = NativeRegExp.getIndex(program, pc += 2);
                pc += 2;
                result = NativeRegExp.flatNMatcher(gData, offset, length, input, end);
                break;
            }
            case 15: {
                char matchCh = (char)(program[pc++] & 0xFF);
                if (gData.cp == end || input.charAt(gData.cp) != matchCh) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 16: {
                int offset = NativeRegExp.getIndex(program, pc);
                int length = NativeRegExp.getIndex(program, pc += 2);
                pc += 2;
                result = NativeRegExp.flatNIMatcher(gData, offset, length, input, end);
                break;
            }
            case 17: {
                char c;
                char matchCh = (char)(program[pc++] & 0xFF);
                if (gData.cp == end || matchCh != (c = input.charAt(gData.cp)) && NativeRegExp.upcase(matchCh) != NativeRegExp.upcase(c)) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 18: {
                char matchCh = (char)NativeRegExp.getIndex(program, pc);
                pc += 2;
                if (gData.cp == end || input.charAt(gData.cp) != matchCh) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 19: {
                char c;
                char matchCh = (char)NativeRegExp.getIndex(program, pc);
                pc += 2;
                if (gData.cp == end || matchCh != (c = input.charAt(gData.cp)) && NativeRegExp.upcase(matchCh) != NativeRegExp.upcase(c)) break;
                result = true;
                ++gData.cp;
                break;
            }
            case 22: 
            case 23: {
                int index = NativeRegExp.getIndex(program, pc);
                pc += 2;
                if (gData.cp == end || !NativeRegExp.classMatcher(gData, gData.regexp.classList[index], input.charAt(gData.cp))) break;
                ++gData.cp;
                result = true;
                break;
            }
            default: {
                throw Kit.codeBug();
            }
        }
        if (result) {
            if (!updatecp) {
                gData.cp = startcp;
            }
            return pc;
        }
        gData.cp = startcp;
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    private static boolean executeREBytecode(REGlobalData gData, String input, int end) {
        pc = 0;
        program = gData.regexp.program;
        continuationOp = 57;
        continuationPc = 0;
        result = false;
        op = program[pc++];
        if (gData.regexp.anchorCh < 0 && NativeRegExp.reopIsSimple(op)) {
            anchor = false;
            while (gData.cp <= end) {
                match = NativeRegExp.simpleMatch(gData, input, op, program, pc, end, true);
                if (match >= 0) {
                    anchor = true;
                    pc = match;
                    op = program[pc++];
                    break;
                }
                ++gData.skipped;
                ++gData.cp;
            }
            if (!anchor) {
                return false;
            }
        }
        block26: while (true) {
            block62: {
                block61: {
                    if (!NativeRegExp.reopIsSimple(op)) break block61;
                    match = NativeRegExp.simpleMatch(gData, input, op, program, pc, end, true);
                    v0 = result = match >= 0;
                    if (result) {
                        pc = match;
                    }
                    break block62;
                }
                block0 : switch (op) {
                    case 53: 
                    case 54: 
                    case 55: {
                        matchCh1 = (char)NativeRegExp.getIndex(program, pc);
                        matchCh2 = (char)NativeRegExp.getIndex(program, pc += 2);
                        pc += 2;
                        if (gData.cp == end) {
                            result = false;
                            break;
                        }
                        c = input.charAt(gData.cp);
                        if (op != 55) ** GOTO lbl43
                        if (c != matchCh1 && !NativeRegExp.classMatcher(gData, gData.regexp.classList[matchCh2], c)) {
                            result = false;
                            break;
                        }
                        ** GOTO lbl48
lbl43:
                        // 1 sources

                        if (op == 54) {
                            c = NativeRegExp.upcase(c);
                        }
                        if (c != matchCh1 && c != matchCh2) {
                            result = false;
                            break;
                        }
                    }
lbl48:
                    // 4 sources

                    case 31: {
                        nextpc = pc + NativeRegExp.getOffset(program, pc);
                        pc += 2;
                        op = program[pc++];
                        startcp = gData.cp;
                        if (NativeRegExp.reopIsSimple(op)) {
                            match = NativeRegExp.simpleMatch(gData, input, op, program, pc, end, true);
                            if (match < 0) {
                                op = program[nextpc++];
                                pc = nextpc;
                                continue block26;
                            }
                            result = true;
                            pc = match;
                            op = program[pc++];
                        }
                        nextop = program[nextpc++];
                        NativeRegExp.pushBackTrackState(gData, nextop, nextpc, startcp, continuationOp, continuationPc);
                        continue block26;
                    }
                    case 32: {
                        offset = NativeRegExp.getOffset(program, pc);
                        pc += offset;
                        op = program[pc++];
                        continue block26;
                    }
                    case 29: {
                        parenIndex = NativeRegExp.getIndex(program, pc);
                        pc += 2;
                        gData.setParens(parenIndex, gData.cp, 0);
                        op = program[pc++];
                        continue block26;
                    }
                    case 30: {
                        parenIndex = NativeRegExp.getIndex(program, pc);
                        pc += 2;
                        cap_index = gData.parensIndex(parenIndex);
                        gData.setParens(parenIndex, cap_index, gData.cp - cap_index);
                        op = program[pc++];
                        continue block26;
                    }
                    case 41: {
                        nextpc = pc + NativeRegExp.getIndex(program, pc);
                        pc += 2;
                        op = program[pc++];
                        if (NativeRegExp.reopIsSimple(op) && NativeRegExp.simpleMatch(gData, input, op, program, pc, end, false) < 0) {
                            result = false;
                            break;
                        }
                        NativeRegExp.pushProgState(gData, 0, 0, gData.cp, gData.backTrackStackTop, continuationOp, continuationPc);
                        NativeRegExp.pushBackTrackState(gData, (byte)43, nextpc);
                        continue block26;
                    }
                    case 42: {
                        nextpc = pc + NativeRegExp.getIndex(program, pc);
                        pc += 2;
                        op = program[pc++];
                        if (NativeRegExp.reopIsSimple(op) && (match = NativeRegExp.simpleMatch(gData, input, op, program, pc, end, false)) >= 0 && program[match] == 44) {
                            result = false;
                            break;
                        }
                        NativeRegExp.pushProgState(gData, 0, 0, gData.cp, gData.backTrackStackTop, continuationOp, continuationPc);
                        NativeRegExp.pushBackTrackState(gData, (byte)44, nextpc);
                        continue block26;
                    }
                    case 43: 
                    case 44: {
                        state = NativeRegExp.popProgState(gData);
                        gData.cp = state.index;
                        gData.backTrackStackTop = state.backTrack;
                        continuationPc = state.continuationPc;
                        continuationOp = state.continuationOp;
                        if (op != 44) break;
                        result = result == false;
                        break;
                    }
                    case 25: 
                    case 26: 
                    case 27: 
                    case 28: 
                    case 45: 
                    case 46: 
                    case 47: 
                    case 48: {
                        greedy = false;
                        switch (op) {
                            case 26: {
                                greedy = true;
                            }
                            case 45: {
                                min = 0;
                                max = -1;
                                break;
                            }
                            case 27: {
                                greedy = true;
                            }
                            case 46: {
                                min = 1;
                                max = -1;
                                break;
                            }
                            case 28: {
                                greedy = true;
                            }
                            case 47: {
                                min = 0;
                                max = 1;
                                break;
                            }
                            case 25: {
                                greedy = true;
                            }
                            case 48: {
                                min = NativeRegExp.getOffset(program, pc);
                                max = NativeRegExp.getOffset(program, pc += 2) - 1;
                                pc += 2;
                                break;
                            }
                            default: {
                                throw Kit.codeBug();
                            }
                        }
                        NativeRegExp.pushProgState(gData, min, max, gData.cp, null, continuationOp, continuationPc);
                        if (greedy) {
                            NativeRegExp.pushBackTrackState(gData, (byte)51, pc);
                            continuationOp = 51;
                            continuationPc = pc;
                            pc += 6;
                            op = program[pc++];
                            continue block26;
                        }
                        if (min != 0) {
                            continuationOp = 52;
                            continuationPc = pc;
                            pc += 6;
                            op = program[pc++];
                            continue block26;
                        }
                        NativeRegExp.pushBackTrackState(gData, (byte)52, pc);
                        NativeRegExp.popProgState(gData);
                        pc += 4;
                        pc += NativeRegExp.getOffset(program, pc);
                        op = program[pc++];
                        continue block26;
                    }
                    case 49: {
                        result = true;
                        pc = continuationPc;
                        op = continuationOp;
                        continue block26;
                    }
                    case 51: {
                        do {
                            state = NativeRegExp.popProgState(gData);
                            if (!result) {
                                if (state.min == 0) {
                                    result = true;
                                }
                                continuationPc = state.continuationPc;
                                continuationOp = state.continuationOp;
                                pc += 4;
                                pc += NativeRegExp.getOffset(program, pc);
                                break block0;
                            }
                            if (state.min == 0 && gData.cp == state.index) {
                                result = false;
                                continuationPc = state.continuationPc;
                                continuationOp = state.continuationOp;
                                pc += 4;
                                pc += NativeRegExp.getOffset(program, pc);
                                break block0;
                            }
                            new_min = state.min;
                            new_max = state.max;
                            if (new_min != 0) {
                                --new_min;
                            }
                            if (new_max != -1) {
                                --new_max;
                            }
                            if (new_max == 0) {
                                result = true;
                                continuationPc = state.continuationPc;
                                continuationOp = state.continuationOp;
                                pc += 4;
                                pc += NativeRegExp.getOffset(program, pc);
                                break block0;
                            }
                            nextpc = pc + 6;
                            nextop = program[nextpc];
                            startcp = gData.cp;
                            if (NativeRegExp.reopIsSimple(nextop)) {
                                if ((match = NativeRegExp.simpleMatch(gData, input, nextop, program, ++nextpc, end, true)) < 0) {
                                    result = new_min == 0;
                                    continuationPc = state.continuationPc;
                                    continuationOp = state.continuationOp;
                                    pc += 4;
                                    pc += NativeRegExp.getOffset(program, pc);
                                    break block0;
                                }
                                result = true;
                                nextpc = match;
                            }
                            continuationOp = 51;
                            continuationPc = pc;
                            NativeRegExp.pushProgState(gData, new_min, new_max, startcp, null, state.continuationOp, state.continuationPc);
                            if (new_min != 0) continue;
                            NativeRegExp.pushBackTrackState(gData, (byte)51, pc, startcp, state.continuationOp, state.continuationPc);
                            parenCount = NativeRegExp.getIndex(program, pc);
                            parenIndex = NativeRegExp.getIndex(program, pc + 2);
                            for (k = 0; k < parenCount; ++k) {
                                gData.setParens(parenIndex + k, -1, 0);
                            }
                        } while (program[nextpc] == 49);
                        pc = nextpc;
                        op = program[pc++];
                        continue block26;
                    }
                    case 52: {
                        state = NativeRegExp.popProgState(gData);
                        if (!result) {
                            if (state.max == -1 || state.max > 0) {
                                NativeRegExp.pushProgState(gData, state.min, state.max, gData.cp, null, state.continuationOp, state.continuationPc);
                                continuationOp = 52;
                                continuationPc = pc;
                                parenCount = NativeRegExp.getIndex(program, pc);
                                parenIndex = NativeRegExp.getIndex(program, pc += 2);
                                pc += 4;
                                for (k = 0; k < parenCount; ++k) {
                                    gData.setParens(parenIndex + k, -1, 0);
                                }
                                op = program[pc++];
                                continue block26;
                            }
                            continuationPc = state.continuationPc;
                            continuationOp = state.continuationOp;
                            break;
                        }
                        if (state.min == 0 && gData.cp == state.index) {
                            result = false;
                            continuationPc = state.continuationPc;
                            continuationOp = state.continuationOp;
                            break;
                        }
                        new_min = state.min;
                        new_max = state.max;
                        if (new_min != 0) {
                            --new_min;
                        }
                        if (new_max != -1) {
                            --new_max;
                        }
                        NativeRegExp.pushProgState(gData, new_min, new_max, gData.cp, null, state.continuationOp, state.continuationPc);
                        if (new_min != 0) {
                            continuationOp = 52;
                            continuationPc = pc;
                            parenCount = NativeRegExp.getIndex(program, pc);
                            parenIndex = NativeRegExp.getIndex(program, pc += 2);
                            pc += 4;
                            for (k = 0; k < parenCount; ++k) {
                                gData.setParens(parenIndex + k, -1, 0);
                            }
                            op = program[pc++];
                            continue block26;
                        }
                        continuationPc = state.continuationPc;
                        continuationOp = state.continuationOp;
                        NativeRegExp.pushBackTrackState(gData, (byte)52, pc);
                        NativeRegExp.popProgState(gData);
                        pc += 4;
                        pc += NativeRegExp.getOffset(program, pc);
                        op = program[pc++];
                        continue block26;
                    }
                    case 57: {
                        return true;
                    }
                    default: {
                        throw Kit.codeBug("invalid bytecode");
                    }
                }
            }
            if (!result) {
                backTrackData = gData.backTrackStackTop;
                if (backTrackData != null) {
                    gData.backTrackStackTop = backTrackData.previous;
                    gData.parens = backTrackData.parens;
                    gData.cp = backTrackData.cp;
                    gData.stateStackTop = backTrackData.stateStackTop;
                    continuationOp = backTrackData.continuationOp;
                    continuationPc = backTrackData.continuationPc;
                    pc = backTrackData.pc;
                    op = backTrackData.op;
                    continue;
                }
                return false;
            }
            op = program[pc++];
        }
    }

    private static boolean matchRegExp(REGlobalData gData, RECompiled re, String input, int start, int end, boolean multiline) {
        gData.parens = (long[])(re.parenCount != 0 ? new long[re.parenCount] : null);
        gData.backTrackStackTop = null;
        gData.stateStackTop = null;
        gData.multiline = multiline || (re.flags & 4) != 0;
        gData.regexp = re;
        int anchorCh = gData.regexp.anchorCh;
        for (int i = start; i <= end; ++i) {
            if (anchorCh >= 0) {
                while (true) {
                    if (i == end) {
                        return false;
                    }
                    char matchCh = input.charAt(i);
                    if (matchCh == anchorCh || (gData.regexp.flags & 2) != 0 && NativeRegExp.upcase(matchCh) == NativeRegExp.upcase((char)anchorCh)) break;
                    ++i;
                }
            }
            gData.cp = i;
            gData.skipped = i - start;
            for (int j = 0; j < re.parenCount; ++j) {
                gData.parens[j] = -1L;
            }
            boolean result = NativeRegExp.executeREBytecode(gData, input, end);
            gData.backTrackStackTop = null;
            gData.stateStackTop = null;
            if (result) {
                return true;
            }
            if (anchorCh == -2 && !gData.multiline) {
                gData.skipped = end;
                return false;
            }
            i = start + gData.skipped;
        }
        return false;
    }

    Object executeRegExp(Context cx, Scriptable scope, RegExpImpl res, String str, int[] indexp, int matchType) {
        Scriptable obj;
        Object result;
        int index;
        boolean matches;
        REGlobalData gData = new REGlobalData();
        int start = indexp[0];
        int end = str.length();
        if (start > end) {
            start = end;
        }
        if (!(matches = NativeRegExp.matchRegExp(gData, this.re, str, start, end, res.multiline))) {
            if (matchType != 2) {
                return null;
            }
            return Undefined.instance;
        }
        int ep = indexp[0] = (index = gData.cp);
        int matchlen = ep - (start + gData.skipped);
        index -= matchlen;
        if (matchType == 0) {
            result = Boolean.TRUE;
            obj = null;
        } else {
            result = cx.newArray(scope, 0);
            obj = (Scriptable)result;
            String matchstr = str.substring(index, index + matchlen);
            obj.put(0, obj, (Object)matchstr);
        }
        if (this.re.parenCount == 0) {
            res.parens = null;
            res.lastParen = SubString.emptySubString;
        } else {
            SubString parsub = null;
            res.parens = new SubString[this.re.parenCount];
            for (int num = 0; num < this.re.parenCount; ++num) {
                int cap_index = gData.parensIndex(num);
                if (cap_index != -1) {
                    int cap_length = gData.parensLength(num);
                    res.parens[num] = parsub = new SubString(str, cap_index, cap_length);
                    if (matchType == 0) continue;
                    obj.put(num + 1, obj, (Object)parsub.toString());
                    continue;
                }
                if (matchType == 0) continue;
                obj.put(num + 1, obj, Undefined.instance);
            }
            res.lastParen = parsub;
        }
        if (matchType != 0) {
            obj.put("index", obj, (Object)(start + gData.skipped));
            obj.put("input", obj, (Object)str);
        }
        if (res.lastMatch == null) {
            res.lastMatch = new SubString();
            res.leftContext = new SubString();
            res.rightContext = new SubString();
        }
        res.lastMatch.str = str;
        res.lastMatch.index = index;
        res.lastMatch.length = matchlen;
        res.leftContext.str = str;
        if (cx.getLanguageVersion() == 120) {
            res.leftContext.index = start;
            res.leftContext.length = gData.skipped;
        } else {
            res.leftContext.index = 0;
            res.leftContext.length = start + gData.skipped;
        }
        res.rightContext.str = str;
        res.rightContext.index = ep;
        res.rightContext.length = end - ep;
        return result;
    }

    int getFlags() {
        return this.re.flags;
    }

    private static void reportWarning(Context cx, String messageId, String arg) {
        if (cx.hasFeature(11)) {
            String msg = ScriptRuntime.getMessage1(messageId, arg);
            Context.reportWarning(msg);
        }
    }

    private static void reportError(String messageId, String arg) {
        String msg = ScriptRuntime.getMessage1(messageId, arg);
        throw ScriptRuntime.constructError("SyntaxError", msg);
    }

    @Override
    protected int getMaxInstanceId() {
        return 5;
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        int attr;
        int id = 0;
        String X = null;
        int s_length = s.length();
        if (s_length == 6) {
            char c = s.charAt(0);
            if (c == 'g') {
                X = "global";
                id = 3;
            } else if (c == 's') {
                X = "source";
                id = 2;
            }
        } else if (s_length == 9) {
            char c = s.charAt(0);
            if (c == 'l') {
                X = "lastIndex";
                id = 1;
            } else if (c == 'm') {
                X = "multiline";
                id = 5;
            }
        } else if (s_length == 10) {
            X = "ignoreCase";
            id = 4;
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        switch (id) {
            case 1: {
                attr = this.lastIndexAttr;
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                attr = 7;
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
        return NativeRegExp.instanceIdInfo(attr, id);
    }

    @Override
    protected String getInstanceIdName(int id) {
        switch (id) {
            case 1: {
                return "lastIndex";
            }
            case 2: {
                return "source";
            }
            case 3: {
                return "global";
            }
            case 4: {
                return "ignoreCase";
            }
            case 5: {
                return "multiline";
            }
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        switch (id) {
            case 1: {
                return this.lastIndex;
            }
            case 2: {
                return new String(this.re.source);
            }
            case 3: {
                return ScriptRuntime.wrapBoolean((this.re.flags & 1) != 0);
            }
            case 4: {
                return ScriptRuntime.wrapBoolean((this.re.flags & 2) != 0);
            }
            case 5: {
                return ScriptRuntime.wrapBoolean((this.re.flags & 4) != 0);
            }
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value) {
        switch (id) {
            case 1: {
                this.lastIndex = value;
                return;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return;
            }
        }
        super.setInstanceIdValue(id, value);
    }

    @Override
    protected void setInstanceIdAttributes(int id, int attr) {
        switch (id) {
            case 1: {
                this.lastIndexAttr = attr;
                return;
            }
        }
        super.setInstanceIdAttributes(id, attr);
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 2;
                s = "compile";
                break;
            }
            case 2: {
                arity = 0;
                s = "toString";
                break;
            }
            case 3: {
                arity = 0;
                s = "toSource";
                break;
            }
            case 4: {
                arity = 1;
                s = "exec";
                break;
            }
            case 5: {
                arity = 1;
                s = "test";
                break;
            }
            case 6: {
                arity = 1;
                s = "prefix";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(REGEXP_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(REGEXP_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                return NativeRegExp.realThis(thisObj, f).compile(cx, scope, args);
            }
            case 2: 
            case 3: {
                return NativeRegExp.realThis(thisObj, f).toString();
            }
            case 4: {
                return NativeRegExp.realThis(thisObj, f).execSub(cx, scope, args, 1);
            }
            case 5: {
                Object x = NativeRegExp.realThis(thisObj, f).execSub(cx, scope, args, 0);
                return Boolean.TRUE.equals(x) ? Boolean.TRUE : Boolean.FALSE;
            }
            case 6: {
                return NativeRegExp.realThis(thisObj, f).execSub(cx, scope, args, 2);
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static NativeRegExp realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeRegExp)) {
            throw NativeRegExp.incompatibleCallError(f);
        }
        return (NativeRegExp)thisObj;
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block8: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 4: {
                    char c = s.charAt(0);
                    if (c == 'e') {
                        X = "exec";
                        id = 4;
                        break;
                    }
                    if (c != 't') break;
                    X = "test";
                    id = 5;
                    break;
                }
                case 6: {
                    X = "prefix";
                    id = 6;
                    break;
                }
                case 7: {
                    X = "compile";
                    id = 1;
                    break;
                }
                case 8: {
                    char c = s.charAt(3);
                    if (c == 'o') {
                        X = "toSource";
                        id = 3;
                        break;
                    }
                    if (c != 't') break;
                    X = "toString";
                    id = 2;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block8;
            id = 0;
        }
        return id;
    }
}

