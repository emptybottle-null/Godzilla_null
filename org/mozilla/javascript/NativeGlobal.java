/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.Serializable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.IdFunctionCall;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeError;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLLib;

public class NativeGlobal
implements Serializable,
IdFunctionCall {
    static final long serialVersionUID = 6080442165748707530L;
    private static final String URI_DECODE_RESERVED = ";/?:@&=+$,#";
    private static final int INVALID_UTF8 = Integer.MAX_VALUE;
    private static final Object FTAG = "Global";
    private static final int Id_decodeURI = 1;
    private static final int Id_decodeURIComponent = 2;
    private static final int Id_encodeURI = 3;
    private static final int Id_encodeURIComponent = 4;
    private static final int Id_escape = 5;
    private static final int Id_eval = 6;
    private static final int Id_isFinite = 7;
    private static final int Id_isNaN = 8;
    private static final int Id_isXMLName = 9;
    private static final int Id_parseFloat = 10;
    private static final int Id_parseInt = 11;
    private static final int Id_unescape = 12;
    private static final int Id_uneval = 13;
    private static final int LAST_SCOPE_FUNCTION_ID = 13;
    private static final int Id_new_CommonError = 14;

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeGlobal obj = new NativeGlobal();
        for (int id = 1; id <= 13; ++id) {
            String name;
            int arity = 1;
            switch (id) {
                case 1: {
                    name = "decodeURI";
                    break;
                }
                case 2: {
                    name = "decodeURIComponent";
                    break;
                }
                case 3: {
                    name = "encodeURI";
                    break;
                }
                case 4: {
                    name = "encodeURIComponent";
                    break;
                }
                case 5: {
                    name = "escape";
                    break;
                }
                case 6: {
                    name = "eval";
                    break;
                }
                case 7: {
                    name = "isFinite";
                    break;
                }
                case 8: {
                    name = "isNaN";
                    break;
                }
                case 9: {
                    name = "isXMLName";
                    break;
                }
                case 10: {
                    name = "parseFloat";
                    break;
                }
                case 11: {
                    name = "parseInt";
                    arity = 2;
                    break;
                }
                case 12: {
                    name = "unescape";
                    break;
                }
                case 13: {
                    name = "uneval";
                    break;
                }
                default: {
                    throw Kit.codeBug();
                }
            }
            IdFunctionObject f = new IdFunctionObject(obj, FTAG, id, name, arity, scope);
            if (sealed) {
                f.sealObject();
            }
            f.exportAsScopeProperty();
        }
        ScriptableObject.defineProperty(scope, "NaN", ScriptRuntime.NaNobj, 7);
        ScriptableObject.defineProperty(scope, "Infinity", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        ScriptableObject.defineProperty(scope, "undefined", Undefined.instance, 7);
        for (TopLevel.NativeErrors error : TopLevel.NativeErrors.values()) {
            if (error == TopLevel.NativeErrors.Error) continue;
            String name = error.name();
            ScriptableObject errorProto = (ScriptableObject)ScriptRuntime.newBuiltinObject(cx, scope, TopLevel.Builtins.Error, ScriptRuntime.emptyArgs);
            errorProto.put("name", (Scriptable)errorProto, (Object)name);
            errorProto.put("message", (Scriptable)errorProto, (Object)"");
            IdFunctionObject ctor = new IdFunctionObject(obj, FTAG, 14, name, 1, scope);
            ctor.markAsConstructor(errorProto);
            errorProto.put("constructor", (Scriptable)errorProto, (Object)ctor);
            errorProto.setAttributes("constructor", 2);
            if (sealed) {
                errorProto.sealObject();
                ctor.sealObject();
            }
            ctor.exportAsScopeProperty();
        }
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (f.hasTag(FTAG)) {
            int methodId = f.methodId();
            switch (methodId) {
                case 1: 
                case 2: {
                    String str = ScriptRuntime.toString(args, 0);
                    return NativeGlobal.decode(str, methodId == 1);
                }
                case 3: 
                case 4: {
                    String str = ScriptRuntime.toString(args, 0);
                    return NativeGlobal.encode(str, methodId == 3);
                }
                case 5: {
                    return this.js_escape(args);
                }
                case 6: {
                    return this.js_eval(cx, scope, args);
                }
                case 7: {
                    double d;
                    boolean result = args.length < 1 ? false : (d = ScriptRuntime.toNumber(args[0])) == d && d != Double.POSITIVE_INFINITY && d != Double.NEGATIVE_INFINITY;
                    return ScriptRuntime.wrapBoolean(result);
                }
                case 8: {
                    double d;
                    boolean result = args.length < 1 ? true : (d = ScriptRuntime.toNumber(args[0])) != d;
                    return ScriptRuntime.wrapBoolean(result);
                }
                case 9: {
                    Object name = args.length == 0 ? Undefined.instance : args[0];
                    XMLLib xmlLib = XMLLib.extractFromScope(scope);
                    return ScriptRuntime.wrapBoolean(xmlLib.isXMLName(cx, name));
                }
                case 10: {
                    return this.js_parseFloat(args);
                }
                case 11: {
                    return this.js_parseInt(args);
                }
                case 12: {
                    return this.js_unescape(args);
                }
                case 13: {
                    Object value = args.length != 0 ? args[0] : Undefined.instance;
                    return ScriptRuntime.uneval(cx, scope, value);
                }
                case 14: {
                    return NativeError.make(cx, scope, f, args);
                }
            }
        }
        throw f.unknown();
    }

    private Object js_parseInt(Object[] args) {
        char c;
        String s = ScriptRuntime.toString(args, 0);
        int radix = ScriptRuntime.toInt32(args, 1);
        int len = s.length();
        if (len == 0) {
            return ScriptRuntime.NaNobj;
        }
        boolean negative = false;
        int start = 0;
        while (ScriptRuntime.isStrWhiteSpaceChar(c = s.charAt(start)) && ++start < len) {
        }
        if (c == '+' || (negative = c == '-')) {
            ++start;
        }
        int NO_RADIX = -1;
        if (radix == 0) {
            radix = -1;
        } else {
            if (radix < 2 || radix > 36) {
                return ScriptRuntime.NaNobj;
            }
            if (radix == 16 && len - start > 1 && s.charAt(start) == '0' && ((c = s.charAt(start + 1)) == 'x' || c == 'X')) {
                start += 2;
            }
        }
        if (radix == -1) {
            radix = 10;
            if (len - start > 1 && s.charAt(start) == '0') {
                c = s.charAt(start + 1);
                if (c == 'x' || c == 'X') {
                    radix = 16;
                    start += 2;
                } else if ('0' <= c && c <= '9') {
                    radix = 8;
                    ++start;
                }
            }
        }
        double d = ScriptRuntime.stringToNumber(s, start, radix);
        return ScriptRuntime.wrapNumber(negative ? -d : d);
    }

    private Object js_parseFloat(Object[] args) {
        char c;
        if (args.length < 1) {
            return ScriptRuntime.NaNobj;
        }
        String s = ScriptRuntime.toString(args[0]);
        int len = s.length();
        int start = 0;
        while (true) {
            if (start == len) {
                return ScriptRuntime.NaNobj;
            }
            c = s.charAt(start);
            if (!ScriptRuntime.isStrWhiteSpaceChar(c)) break;
            ++start;
        }
        int i = start;
        if (c == '+' || c == '-') {
            if (++i == len) {
                return ScriptRuntime.NaNobj;
            }
            c = s.charAt(i);
        }
        if (c == 'I') {
            if (i + 8 <= len && s.regionMatches(i, "Infinity", 0, 8)) {
                double d = s.charAt(start) == '-' ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                return ScriptRuntime.wrapNumber(d);
            }
            return ScriptRuntime.NaNobj;
        }
        int decimal = -1;
        int exponent = -1;
        boolean exponentValid = false;
        block9: while (i < len) {
            switch (s.charAt(i)) {
                case '.': {
                    if (decimal != -1) break block9;
                    decimal = i;
                    break;
                }
                case 'E': 
                case 'e': {
                    if (exponent != -1 || i == len - 1) break block9;
                    exponent = i;
                    break;
                }
                case '+': 
                case '-': {
                    if (exponent != i - 1) break block9;
                    if (i != len - 1) break;
                    --i;
                    break block9;
                }
                case '0': 
                case '1': 
                case '2': 
                case '3': 
                case '4': 
                case '5': 
                case '6': 
                case '7': 
                case '8': 
                case '9': {
                    if (exponent == -1) break;
                    exponentValid = true;
                    break;
                }
                default: {
                    break block9;
                }
            }
            ++i;
        }
        if (exponent != -1 && !exponentValid) {
            i = exponent;
        }
        s = s.substring(start, i);
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException ex) {
            return ScriptRuntime.NaNobj;
        }
    }

    private Object js_escape(Object[] args) {
        double d;
        boolean URL_XALPHAS = true;
        int URL_XPALPHAS = 2;
        int URL_PATH = 4;
        String s = ScriptRuntime.toString(args, 0);
        int mask = 7;
        if (args.length > 1 && ((d = ScriptRuntime.toNumber(args[1])) != d || (double)(mask = (int)d) != d || 0 != (mask & 0xFFFFFFF8))) {
            throw Context.reportRuntimeError0("msg.bad.esc.mask");
        }
        StringBuilder sb = null;
        int L = s.length();
        for (int k = 0; k != L; ++k) {
            int hexSize;
            char c = s.charAt(k);
            if (mask != 0 && (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '@' || c == '*' || c == '_' || c == '-' || c == '.' || 0 != (mask & 4) && (c == '/' || c == '+'))) {
                if (sb == null) continue;
                sb.append(c);
                continue;
            }
            if (sb == null) {
                sb = new StringBuilder(L + 3);
                sb.append(s);
                sb.setLength(k);
            }
            if (c < '\u0100') {
                if (c == ' ' && mask == 2) {
                    sb.append('+');
                    continue;
                }
                sb.append('%');
                hexSize = 2;
            } else {
                sb.append('%');
                sb.append('u');
                hexSize = 4;
            }
            for (int shift = (hexSize - 1) * 4; shift >= 0; shift -= 4) {
                int digit = 0xF & c >> shift;
                int hc = digit < 10 ? 48 + digit : 55 + digit;
                sb.append((char)hc);
            }
        }
        return sb == null ? s : sb.toString();
    }

    private Object js_unescape(Object[] args) {
        String s = ScriptRuntime.toString(args, 0);
        int firstEscapePos = s.indexOf(37);
        if (firstEscapePos >= 0) {
            int L = s.length();
            char[] buf = s.toCharArray();
            int destination = firstEscapePos;
            int k = firstEscapePos;
            while (k != L) {
                char c = buf[k];
                if (c == '%' && ++k != L) {
                    int end;
                    int start;
                    if (buf[k] == 'u') {
                        start = k + 1;
                        end = k + 5;
                    } else {
                        start = k;
                        end = k + 2;
                    }
                    if (end <= L) {
                        int x = 0;
                        for (int i = start; i != end; ++i) {
                            x = Kit.xDigitToInt(buf[i], x);
                        }
                        if (x >= 0) {
                            c = (char)x;
                            k = end;
                        }
                    }
                }
                buf[destination] = c;
                ++destination;
            }
            s = new String(buf, 0, destination);
        }
        return s;
    }

    private Object js_eval(Context cx, Scriptable scope, Object[] args) {
        Scriptable global = ScriptableObject.getTopLevelScope(scope);
        return ScriptRuntime.evalSpecial(cx, global, global, args, "eval code", 1);
    }

    static boolean isEvalFunction(Object functionObj) {
        IdFunctionObject function;
        return functionObj instanceof IdFunctionObject && (function = (IdFunctionObject)functionObj).hasTag(FTAG) && function.methodId() == 6;
    }

    @Deprecated
    public static EcmaError constructError(Context cx, String error, String message, Scriptable scope) {
        return ScriptRuntime.constructError(error, message);
    }

    @Deprecated
    public static EcmaError constructError(Context cx, String error, String message, Scriptable scope, String sourceName, int lineNumber, int columnNumber, String lineSource) {
        return ScriptRuntime.constructError(error, message, sourceName, lineNumber, lineSource, columnNumber);
    }

    private static String encode(String str, boolean fullUri) {
        byte[] utf8buf = null;
        StringBuilder sb = null;
        int length = str.length();
        for (int k = 0; k != length; ++k) {
            int V;
            int C = str.charAt(k);
            if (NativeGlobal.encodeUnescaped((char)C, fullUri)) {
                if (sb == null) continue;
                sb.append((char)C);
                continue;
            }
            if (sb == null) {
                sb = new StringBuilder(length + 3);
                sb.append(str);
                sb.setLength(k);
                utf8buf = new byte[6];
            }
            if (56320 <= C && C <= 57343) {
                throw NativeGlobal.uriError();
            }
            if (C < 55296 || 56319 < C) {
                V = C;
            } else {
                if (++k == length) {
                    throw NativeGlobal.uriError();
                }
                char C2 = str.charAt(k);
                if ('\udc00' > C2 || C2 > '\udfff') {
                    throw NativeGlobal.uriError();
                }
                V = (C - 55296 << 10) + (C2 - 56320) + 65536;
            }
            int L = NativeGlobal.oneUcs4ToUtf8Char(utf8buf, V);
            for (int j = 0; j < L; ++j) {
                int d = 0xFF & utf8buf[j];
                sb.append('%');
                sb.append(NativeGlobal.toHexChar(d >>> 4));
                sb.append(NativeGlobal.toHexChar(d & 0xF));
            }
        }
        return sb == null ? str : sb.toString();
    }

    private static char toHexChar(int i) {
        if (i >> 4 != 0) {
            Kit.codeBug();
        }
        return (char)(i < 10 ? i + 48 : i - 10 + 65);
    }

    private static int unHex(char c) {
        if ('A' <= c && c <= 'F') {
            return c - 65 + 10;
        }
        if ('a' <= c && c <= 'f') {
            return c - 97 + 10;
        }
        if ('0' <= c && c <= '9') {
            return c - 48;
        }
        return -1;
    }

    private static int unHex(char c1, char c2) {
        int i1 = NativeGlobal.unHex(c1);
        int i2 = NativeGlobal.unHex(c2);
        if (i1 >= 0 && i2 >= 0) {
            return i1 << 4 | i2;
        }
        return -1;
    }

    private static String decode(String str, boolean fullUri) {
        char[] buf = null;
        int bufTop = 0;
        int k = 0;
        int length = str.length();
        while (k != length) {
            char C = str.charAt(k);
            if (C != '%') {
                if (buf != null) {
                    buf[bufTop++] = C;
                }
                ++k;
                continue;
            }
            if (buf == null) {
                buf = new char[length];
                str.getChars(0, k, buf, 0);
                bufTop = k;
            }
            int start = k;
            if (k + 3 > length) {
                throw NativeGlobal.uriError();
            }
            int B = NativeGlobal.unHex(str.charAt(k + 1), str.charAt(k + 2));
            if (B < 0) {
                throw NativeGlobal.uriError();
            }
            k += 3;
            if ((B & 0x80) == 0) {
                C = (char)B;
            } else {
                int minUcs4Char;
                int ucs4Char;
                int utf8Tail;
                if ((B & 0xC0) == 128) {
                    throw NativeGlobal.uriError();
                }
                if ((B & 0x20) == 0) {
                    utf8Tail = 1;
                    ucs4Char = B & 0x1F;
                    minUcs4Char = 128;
                } else if ((B & 0x10) == 0) {
                    utf8Tail = 2;
                    ucs4Char = B & 0xF;
                    minUcs4Char = 2048;
                } else if ((B & 8) == 0) {
                    utf8Tail = 3;
                    ucs4Char = B & 7;
                    minUcs4Char = 65536;
                } else if ((B & 4) == 0) {
                    utf8Tail = 4;
                    ucs4Char = B & 3;
                    minUcs4Char = 0x200000;
                } else if ((B & 2) == 0) {
                    utf8Tail = 5;
                    ucs4Char = B & 1;
                    minUcs4Char = 0x4000000;
                } else {
                    throw NativeGlobal.uriError();
                }
                if (k + 3 * utf8Tail > length) {
                    throw NativeGlobal.uriError();
                }
                for (int j = 0; j != utf8Tail; ++j) {
                    if (str.charAt(k) != '%') {
                        throw NativeGlobal.uriError();
                    }
                    B = NativeGlobal.unHex(str.charAt(k + 1), str.charAt(k + 2));
                    if (B < 0 || (B & 0xC0) != 128) {
                        throw NativeGlobal.uriError();
                    }
                    ucs4Char = ucs4Char << 6 | B & 0x3F;
                    k += 3;
                }
                if (ucs4Char < minUcs4Char || ucs4Char >= 55296 && ucs4Char <= 57343) {
                    ucs4Char = Integer.MAX_VALUE;
                } else if (ucs4Char == 65534 || ucs4Char == 65535) {
                    ucs4Char = 65533;
                }
                if (ucs4Char >= 65536) {
                    if ((ucs4Char -= 65536) > 1048575) {
                        throw NativeGlobal.uriError();
                    }
                    char H = (char)((ucs4Char >>> 10) + 55296);
                    C = (char)((ucs4Char & 0x3FF) + 56320);
                    buf[bufTop++] = H;
                } else {
                    C = (char)ucs4Char;
                }
            }
            if (fullUri && URI_DECODE_RESERVED.indexOf(C) >= 0) {
                for (int x = start; x != k; ++x) {
                    buf[bufTop++] = str.charAt(x);
                }
                continue;
            }
            buf[bufTop++] = C;
        }
        return buf == null ? str : new String(buf, 0, bufTop);
    }

    private static boolean encodeUnescaped(char c, boolean fullUri) {
        if ('A' <= c && c <= 'Z' || 'a' <= c && c <= 'z' || '0' <= c && c <= '9') {
            return true;
        }
        if ("-_.!~*'()".indexOf(c) >= 0) {
            return true;
        }
        if (fullUri) {
            return URI_DECODE_RESERVED.indexOf(c) >= 0;
        }
        return false;
    }

    private static EcmaError uriError() {
        return ScriptRuntime.constructError("URIError", ScriptRuntime.getMessage0("msg.bad.uri"));
    }

    private static int oneUcs4ToUtf8Char(byte[] utf8Buffer, int ucs4Char) {
        int utf8Length = 1;
        if ((ucs4Char & 0xFFFFFF80) == 0) {
            utf8Buffer[0] = (byte)ucs4Char;
        } else {
            int a = ucs4Char >>> 11;
            utf8Length = 2;
            while (a != 0) {
                a >>>= 5;
                ++utf8Length;
            }
            int i = utf8Length;
            while (--i > 0) {
                utf8Buffer[i] = (byte)(ucs4Char & 0x3F | 0x80);
                ucs4Char >>>= 6;
            }
            utf8Buffer[0] = (byte)(256 - (1 << 8 - utf8Length) + ucs4Char);
        }
        return utf8Length;
    }
}

