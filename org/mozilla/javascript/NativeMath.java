/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

final class NativeMath
extends IdScriptableObject {
    static final long serialVersionUID = -8838847185801131569L;
    private static final Object MATH_TAG = "Math";
    private static final int Id_toSource = 1;
    private static final int Id_abs = 2;
    private static final int Id_acos = 3;
    private static final int Id_asin = 4;
    private static final int Id_atan = 5;
    private static final int Id_atan2 = 6;
    private static final int Id_ceil = 7;
    private static final int Id_cos = 8;
    private static final int Id_exp = 9;
    private static final int Id_floor = 10;
    private static final int Id_log = 11;
    private static final int Id_max = 12;
    private static final int Id_min = 13;
    private static final int Id_pow = 14;
    private static final int Id_random = 15;
    private static final int Id_round = 16;
    private static final int Id_sin = 17;
    private static final int Id_sqrt = 18;
    private static final int Id_tan = 19;
    private static final int LAST_METHOD_ID = 19;
    private static final int Id_E = 20;
    private static final int Id_PI = 21;
    private static final int Id_LN10 = 22;
    private static final int Id_LN2 = 23;
    private static final int Id_LOG2E = 24;
    private static final int Id_LOG10E = 25;
    private static final int Id_SQRT1_2 = 26;
    private static final int Id_SQRT2 = 27;
    private static final int MAX_ID = 27;

    static void init(Scriptable scope, boolean sealed) {
        NativeMath obj = new NativeMath();
        obj.activatePrototypeMap(27);
        obj.setPrototype(NativeMath.getObjectPrototype(scope));
        obj.setParentScope(scope);
        if (sealed) {
            obj.sealObject();
        }
        ScriptableObject.defineProperty(scope, "Math", obj, 2);
    }

    private NativeMath() {
    }

    @Override
    public String getClassName() {
        return "Math";
    }

    @Override
    protected void initPrototypeId(int id) {
        if (id <= 19) {
            String name;
            int arity;
            switch (id) {
                case 1: {
                    arity = 0;
                    name = "toSource";
                    break;
                }
                case 2: {
                    arity = 1;
                    name = "abs";
                    break;
                }
                case 3: {
                    arity = 1;
                    name = "acos";
                    break;
                }
                case 4: {
                    arity = 1;
                    name = "asin";
                    break;
                }
                case 5: {
                    arity = 1;
                    name = "atan";
                    break;
                }
                case 6: {
                    arity = 2;
                    name = "atan2";
                    break;
                }
                case 7: {
                    arity = 1;
                    name = "ceil";
                    break;
                }
                case 8: {
                    arity = 1;
                    name = "cos";
                    break;
                }
                case 9: {
                    arity = 1;
                    name = "exp";
                    break;
                }
                case 10: {
                    arity = 1;
                    name = "floor";
                    break;
                }
                case 11: {
                    arity = 1;
                    name = "log";
                    break;
                }
                case 12: {
                    arity = 2;
                    name = "max";
                    break;
                }
                case 13: {
                    arity = 2;
                    name = "min";
                    break;
                }
                case 14: {
                    arity = 2;
                    name = "pow";
                    break;
                }
                case 15: {
                    arity = 0;
                    name = "random";
                    break;
                }
                case 16: {
                    arity = 1;
                    name = "round";
                    break;
                }
                case 17: {
                    arity = 1;
                    name = "sin";
                    break;
                }
                case 18: {
                    arity = 1;
                    name = "sqrt";
                    break;
                }
                case 19: {
                    arity = 1;
                    name = "tan";
                    break;
                }
                default: {
                    throw new IllegalStateException(String.valueOf(id));
                }
            }
            this.initPrototypeMethod(MATH_TAG, id, name, arity);
        } else {
            String name;
            double x;
            switch (id) {
                case 20: {
                    x = Math.E;
                    name = "E";
                    break;
                }
                case 21: {
                    x = Math.PI;
                    name = "PI";
                    break;
                }
                case 22: {
                    x = 2.302585092994046;
                    name = "LN10";
                    break;
                }
                case 23: {
                    x = 0.6931471805599453;
                    name = "LN2";
                    break;
                }
                case 24: {
                    x = 1.4426950408889634;
                    name = "LOG2E";
                    break;
                }
                case 25: {
                    x = 0.4342944819032518;
                    name = "LOG10E";
                    break;
                }
                case 26: {
                    x = 0.7071067811865476;
                    name = "SQRT1_2";
                    break;
                }
                case 27: {
                    x = 1.4142135623730951;
                    name = "SQRT2";
                    break;
                }
                default: {
                    throw new IllegalStateException(String.valueOf(id));
                }
            }
            this.initPrototypeValue(id, name, ScriptRuntime.wrapNumber(x), 7);
        }
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        double x;
        if (!f.hasTag(MATH_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int methodId = f.methodId();
        block0 : switch (methodId) {
            case 1: {
                return "Math";
            }
            case 2: {
                x = ScriptRuntime.toNumber(args, 0);
                x = x == 0.0 ? 0.0 : (x < 0.0 ? -x : x);
                break;
            }
            case 3: 
            case 4: {
                x = ScriptRuntime.toNumber(args, 0);
                if (x == x && -1.0 <= x && x <= 1.0) {
                    x = methodId == 3 ? Math.acos(x) : Math.asin(x);
                    break;
                }
                x = Double.NaN;
                break;
            }
            case 5: {
                x = ScriptRuntime.toNumber(args, 0);
                x = Math.atan(x);
                break;
            }
            case 6: {
                x = ScriptRuntime.toNumber(args, 0);
                x = Math.atan2(x, ScriptRuntime.toNumber(args, 1));
                break;
            }
            case 7: {
                x = ScriptRuntime.toNumber(args, 0);
                x = Math.ceil(x);
                break;
            }
            case 8: {
                x = ScriptRuntime.toNumber(args, 0);
                x = x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY ? Double.NaN : Math.cos(x);
                break;
            }
            case 9: {
                x = ScriptRuntime.toNumber(args, 0);
                x = x == Double.POSITIVE_INFINITY ? x : (x == Double.NEGATIVE_INFINITY ? 0.0 : Math.exp(x));
                break;
            }
            case 10: {
                x = ScriptRuntime.toNumber(args, 0);
                x = Math.floor(x);
                break;
            }
            case 11: {
                x = ScriptRuntime.toNumber(args, 0);
                x = x < 0.0 ? Double.NaN : Math.log(x);
                break;
            }
            case 12: 
            case 13: {
                x = methodId == 12 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                for (int i = 0; i != args.length; ++i) {
                    double d = ScriptRuntime.toNumber(args[i]);
                    if (d != d) {
                        x = d;
                        break block0;
                    }
                    x = methodId == 12 ? Math.max(x, d) : Math.min(x, d);
                }
                break;
            }
            case 14: {
                x = ScriptRuntime.toNumber(args, 0);
                x = this.js_pow(x, ScriptRuntime.toNumber(args, 1));
                break;
            }
            case 15: {
                x = Math.random();
                break;
            }
            case 16: {
                x = ScriptRuntime.toNumber(args, 0);
                if (x != x || x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) break;
                long l = Math.round(x);
                if (l != 0L) {
                    x = l;
                    break;
                }
                if (x < 0.0) {
                    x = ScriptRuntime.negativeZero;
                    break;
                }
                if (x == 0.0) break;
                x = 0.0;
                break;
            }
            case 17: {
                x = ScriptRuntime.toNumber(args, 0);
                x = x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY ? Double.NaN : Math.sin(x);
                break;
            }
            case 18: {
                x = ScriptRuntime.toNumber(args, 0);
                x = Math.sqrt(x);
                break;
            }
            case 19: {
                x = ScriptRuntime.toNumber(args, 0);
                x = Math.tan(x);
                break;
            }
            default: {
                throw new IllegalStateException(String.valueOf(methodId));
            }
        }
        return ScriptRuntime.wrapNumber(x);
    }

    private double js_pow(double x, double y) {
        double result;
        if (y != y) {
            result = y;
        } else if (y == 0.0) {
            result = 1.0;
        } else if (x == 0.0) {
            long y_long;
            result = 1.0 / x > 0.0 ? (y > 0.0 ? 0.0 : Double.POSITIVE_INFINITY) : ((double)(y_long = (long)y) == y && (y_long & 1L) != 0L ? (y > 0.0 ? -0.0 : Double.NEGATIVE_INFINITY) : (y > 0.0 ? 0.0 : Double.POSITIVE_INFINITY));
        } else {
            result = Math.pow(x, y);
            if (result != result) {
                if (y == Double.POSITIVE_INFINITY) {
                    if (x < -1.0 || 1.0 < x) {
                        result = Double.POSITIVE_INFINITY;
                    } else if (-1.0 < x && x < 1.0) {
                        result = 0.0;
                    }
                } else if (y == Double.NEGATIVE_INFINITY) {
                    if (x < -1.0 || 1.0 < x) {
                        result = 0.0;
                    } else if (-1.0 < x && x < 1.0) {
                        result = Double.POSITIVE_INFINITY;
                    }
                } else if (x == Double.POSITIVE_INFINITY) {
                    result = y > 0.0 ? Double.POSITIVE_INFINITY : 0.0;
                } else if (x == Double.NEGATIVE_INFINITY) {
                    long y_long = (long)y;
                    result = (double)y_long == y && (y_long & 1L) != 0L ? (y > 0.0 ? Double.NEGATIVE_INFINITY : -0.0) : (y > 0.0 ? Double.POSITIVE_INFINITY : 0.0);
                }
            }
        }
        return result;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected int findPrototypeId(String s) {
        int id = 0;
        String X = null;
        block0 : switch (s.length()) {
            case 1: {
                if (s.charAt(0) != 'E') break;
                return 20;
            }
            case 2: {
                if (s.charAt(0) != 'P' || s.charAt(1) != 'I') break;
                return 21;
            }
            case 3: {
                switch (s.charAt(0)) {
                    case 'L': {
                        if (s.charAt(2) != '2' || s.charAt(1) != 'N') break;
                        return 23;
                    }
                    case 'a': {
                        if (s.charAt(2) != 's' || s.charAt(1) != 'b') break;
                        return 2;
                    }
                    case 'c': {
                        if (s.charAt(2) != 's' || s.charAt(1) != 'o') break;
                        return 8;
                    }
                    case 'e': {
                        if (s.charAt(2) != 'p' || s.charAt(1) != 'x') break;
                        return 9;
                    }
                    case 'l': {
                        if (s.charAt(2) != 'g' || s.charAt(1) != 'o') break;
                        return 11;
                    }
                    case 'm': {
                        char c = s.charAt(2);
                        if (c == 'n') {
                            if (s.charAt(1) != 'i') break;
                            return 13;
                        }
                        if (c != 'x' || s.charAt(1) != 'a') break;
                        return 12;
                    }
                    case 'p': {
                        if (s.charAt(2) != 'w' || s.charAt(1) != 'o') break;
                        return 14;
                    }
                    case 's': {
                        if (s.charAt(2) != 'n' || s.charAt(1) != 'i') break;
                        return 17;
                    }
                    case 't': {
                        if (s.charAt(2) != 'n' || s.charAt(1) != 'a') break;
                        return 19;
                    }
                }
                break;
            }
            case 4: {
                switch (s.charAt(1)) {
                    case 'N': {
                        X = "LN10";
                        id = 22;
                        break block0;
                    }
                    case 'c': {
                        X = "acos";
                        id = 3;
                        break block0;
                    }
                    case 'e': {
                        X = "ceil";
                        id = 7;
                        break block0;
                    }
                    case 'q': {
                        X = "sqrt";
                        id = 18;
                        break block0;
                    }
                    case 's': {
                        X = "asin";
                        id = 4;
                        break block0;
                    }
                    case 't': {
                        X = "atan";
                        id = 5;
                        break block0;
                    }
                }
                break;
            }
            case 5: {
                switch (s.charAt(0)) {
                    case 'L': {
                        X = "LOG2E";
                        id = 24;
                        break block0;
                    }
                    case 'S': {
                        X = "SQRT2";
                        id = 27;
                        break block0;
                    }
                    case 'a': {
                        X = "atan2";
                        id = 6;
                        break block0;
                    }
                    case 'f': {
                        X = "floor";
                        id = 10;
                        break block0;
                    }
                    case 'r': {
                        X = "round";
                        id = 16;
                        break block0;
                    }
                }
                break;
            }
            case 6: {
                char c = s.charAt(0);
                if (c == 'L') {
                    X = "LOG10E";
                    id = 25;
                    break;
                }
                if (c != 'r') break;
                X = "random";
                id = 15;
                break;
            }
            case 7: {
                X = "SQRT1_2";
                id = 26;
                break;
            }
            case 8: {
                X = "toSource";
                id = 1;
            }
        }
        if (X == null) return id;
        if (X == s) return id;
        if (X.equals(s)) return id;
        return 0;
    }
}

