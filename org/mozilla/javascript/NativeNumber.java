/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.DToA;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

final class NativeNumber
extends IdScriptableObject {
    static final long serialVersionUID = 3504516769741512101L;
    private static final Object NUMBER_TAG = "Number";
    private static final int MAX_PRECISION = 100;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 4;
    private static final int Id_valueOf = 5;
    private static final int Id_toFixed = 6;
    private static final int Id_toExponential = 7;
    private static final int Id_toPrecision = 8;
    private static final int MAX_PROTOTYPE_ID = 8;
    private double doubleValue;

    static void init(Scriptable scope, boolean sealed) {
        NativeNumber obj = new NativeNumber(0.0);
        obj.exportAsJSClass(8, scope, sealed);
    }

    NativeNumber(double number) {
        this.doubleValue = number;
    }

    @Override
    public String getClassName() {
        return "Number";
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        int attr = 7;
        ctor.defineProperty("NaN", ScriptRuntime.NaNobj, 7);
        ctor.defineProperty("POSITIVE_INFINITY", ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        ctor.defineProperty("NEGATIVE_INFINITY", ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY), 7);
        ctor.defineProperty("MAX_VALUE", ScriptRuntime.wrapNumber(Double.MAX_VALUE), 7);
        ctor.defineProperty("MIN_VALUE", ScriptRuntime.wrapNumber(Double.MIN_VALUE), 7);
        super.fillConstructorProperties(ctor);
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 1;
                s = "constructor";
                break;
            }
            case 2: {
                arity = 1;
                s = "toString";
                break;
            }
            case 3: {
                arity = 1;
                s = "toLocaleString";
                break;
            }
            case 4: {
                arity = 0;
                s = "toSource";
                break;
            }
            case 5: {
                arity = 0;
                s = "valueOf";
                break;
            }
            case 6: {
                arity = 1;
                s = "toFixed";
                break;
            }
            case 7: {
                arity = 1;
                s = "toExponential";
                break;
            }
            case 8: {
                arity = 1;
                s = "toPrecision";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(NUMBER_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(NUMBER_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        if (id == 1) {
            double val;
            double d = val = args.length >= 1 ? ScriptRuntime.toNumber(args[0]) : 0.0;
            if (thisObj == null) {
                return new NativeNumber(val);
            }
            return ScriptRuntime.wrapNumber(val);
        }
        if (!(thisObj instanceof NativeNumber)) {
            throw NativeNumber.incompatibleCallError(f);
        }
        double value = ((NativeNumber)thisObj).doubleValue;
        switch (id) {
            case 2: 
            case 3: {
                int base = args.length == 0 || args[0] == Undefined.instance ? 10 : ScriptRuntime.toInt32(args[0]);
                return ScriptRuntime.numberToString(value, base);
            }
            case 4: {
                return "(new Number(" + ScriptRuntime.toString(value) + "))";
            }
            case 5: {
                return ScriptRuntime.wrapNumber(value);
            }
            case 6: {
                return NativeNumber.num_to(value, args, 2, 2, -20, 0);
            }
            case 7: {
                if (Double.isNaN(value)) {
                    return "NaN";
                }
                if (Double.isInfinite(value)) {
                    if (value >= 0.0) {
                        return "Infinity";
                    }
                    return "-Infinity";
                }
                return NativeNumber.num_to(value, args, 1, 3, 0, 1);
            }
            case 8: {
                if (args.length == 0 || args[0] == Undefined.instance) {
                    return ScriptRuntime.numberToString(value, 10);
                }
                if (Double.isNaN(value)) {
                    return "NaN";
                }
                if (Double.isInfinite(value)) {
                    if (value >= 0.0) {
                        return "Infinity";
                    }
                    return "-Infinity";
                }
                return NativeNumber.num_to(value, args, 0, 4, 1, 0);
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    public String toString() {
        return ScriptRuntime.numberToString(this.doubleValue, 10);
    }

    private static String num_to(double val, Object[] args, int zeroArgMode, int oneArgMode, int precisionMin, int precisionOffset) {
        int precision;
        if (args.length == 0) {
            precision = 0;
            oneArgMode = zeroArgMode;
        } else {
            double p = ScriptRuntime.toInteger(args[0]);
            if (p < (double)precisionMin || p > 100.0) {
                String msg = ScriptRuntime.getMessage1("msg.bad.precision", ScriptRuntime.toString(args[0]));
                throw ScriptRuntime.constructError("RangeError", msg);
            }
            precision = ScriptRuntime.toInt32(p);
        }
        StringBuilder sb = new StringBuilder();
        DToA.JS_dtostr(sb, oneArgMode, precision + precisionOffset, val);
        return sb.toString();
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block10: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 7: {
                    char c = s.charAt(0);
                    if (c == 't') {
                        X = "toFixed";
                        id = 6;
                        break;
                    }
                    if (c != 'v') break;
                    X = "valueOf";
                    id = 5;
                    break;
                }
                case 8: {
                    char c = s.charAt(3);
                    if (c == 'o') {
                        X = "toSource";
                        id = 4;
                        break;
                    }
                    if (c != 't') break;
                    X = "toString";
                    id = 2;
                    break;
                }
                case 11: {
                    char c = s.charAt(0);
                    if (c == 'c') {
                        X = "constructor";
                        id = 1;
                        break;
                    }
                    if (c != 't') break;
                    X = "toPrecision";
                    id = 8;
                    break;
                }
                case 13: {
                    X = "toExponential";
                    id = 7;
                    break;
                }
                case 14: {
                    X = "toLocaleString";
                    id = 3;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block10;
            id = 0;
        }
        return id;
    }
}

