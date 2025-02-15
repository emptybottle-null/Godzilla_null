/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.typedarrays.ByteIo;
import org.mozilla.javascript.typedarrays.Conversions;
import org.mozilla.javascript.typedarrays.NativeArrayBuffer;
import org.mozilla.javascript.typedarrays.NativeArrayBufferView;

public class NativeDataView
extends NativeArrayBufferView {
    private static final long serialVersionUID = 1427967607557438968L;
    public static final String CLASS_NAME = "DataView";
    private static final int Id_constructor = 1;
    private static final int Id_getInt8 = 2;
    private static final int Id_getUint8 = 3;
    private static final int Id_getInt16 = 4;
    private static final int Id_getUint16 = 5;
    private static final int Id_getInt32 = 6;
    private static final int Id_getUint32 = 7;
    private static final int Id_getFloat32 = 8;
    private static final int Id_getFloat64 = 9;
    private static final int Id_setInt8 = 10;
    private static final int Id_setUint8 = 11;
    private static final int Id_setInt16 = 12;
    private static final int Id_setUint16 = 13;
    private static final int Id_setInt32 = 14;
    private static final int Id_setUint32 = 15;
    private static final int Id_setFloat32 = 16;
    private static final int Id_setFloat64 = 17;
    private static final int MAX_PROTOTYPE_ID = 17;

    public NativeDataView() {
    }

    public NativeDataView(NativeArrayBuffer ab, int offset, int length) {
        super(ab, offset, length);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeDataView dv = new NativeDataView();
        dv.exportAsJSClass(17, scope, sealed);
    }

    private void rangeCheck(int offset, int len) {
        if (offset < 0 || offset + len > this.byteLength) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
    }

    private void checkOffset(Object[] args, int pos) {
        if (args.length <= pos) {
            throw ScriptRuntime.constructError("TypeError", "missing required offset parameter");
        }
        if (Undefined.instance.equals(args[pos])) {
            throw ScriptRuntime.constructError("RangeError", "invalid offset");
        }
    }

    private void checkValue(Object[] args, int pos) {
        if (args.length <= pos) {
            throw ScriptRuntime.constructError("TypeError", "missing required value parameter");
        }
        if (Undefined.instance.equals(args[pos])) {
            throw ScriptRuntime.constructError("RangeError", "invalid value parameter");
        }
    }

    private static NativeDataView realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeDataView)) {
            throw NativeDataView.incompatibleCallError(f);
        }
        return (NativeDataView)thisObj;
    }

    private NativeDataView js_constructor(NativeArrayBuffer ab, int offset, int length) {
        if (length < 0) {
            throw ScriptRuntime.constructError("RangeError", "length out of range");
        }
        if (offset < 0 || offset + length > ab.getLength()) {
            throw ScriptRuntime.constructError("RangeError", "offset out of range");
        }
        return new NativeDataView(ab, offset, length);
    }

    private Object js_getInt(int bytes, boolean signed, Object[] args) {
        this.checkOffset(args, 0);
        int offset = ScriptRuntime.toInt32(args[0]);
        this.rangeCheck(offset, bytes);
        boolean littleEndian = NativeDataView.isArg(args, 1) && bytes > 1 && ScriptRuntime.toBoolean(args[1]);
        switch (bytes) {
            case 1: {
                return signed ? ByteIo.readInt8(this.arrayBuffer.buffer, offset) : ByteIo.readUint8(this.arrayBuffer.buffer, offset);
            }
            case 2: {
                return signed ? ByteIo.readInt16(this.arrayBuffer.buffer, offset, littleEndian) : ByteIo.readUint16(this.arrayBuffer.buffer, offset, littleEndian);
            }
            case 4: {
                return signed ? ByteIo.readInt32(this.arrayBuffer.buffer, offset, littleEndian) : ByteIo.readUint32(this.arrayBuffer.buffer, offset, littleEndian);
            }
        }
        throw new AssertionError();
    }

    private Object js_getFloat(int bytes, Object[] args) {
        this.checkOffset(args, 0);
        int offset = ScriptRuntime.toInt32(args[0]);
        this.rangeCheck(offset, bytes);
        boolean littleEndian = NativeDataView.isArg(args, 1) && bytes > 1 && ScriptRuntime.toBoolean(args[1]);
        switch (bytes) {
            case 4: {
                return ByteIo.readFloat32(this.arrayBuffer.buffer, offset, littleEndian);
            }
            case 8: {
                return ByteIo.readFloat64(this.arrayBuffer.buffer, offset, littleEndian);
            }
        }
        throw new AssertionError();
    }

    private void js_setInt(int bytes, boolean signed, Object[] args) {
        this.checkOffset(args, 0);
        this.checkValue(args, 1);
        int offset = ScriptRuntime.toInt32(args[0]);
        this.rangeCheck(offset, bytes);
        boolean littleEndian = NativeDataView.isArg(args, 2) && bytes > 1 && ScriptRuntime.toBoolean(args[2]);
        switch (bytes) {
            case 1: {
                if (signed) {
                    ByteIo.writeInt8(this.arrayBuffer.buffer, offset, Conversions.toInt8(args[1]));
                    break;
                }
                ByteIo.writeUint8(this.arrayBuffer.buffer, offset, Conversions.toUint8(args[1]));
                break;
            }
            case 2: {
                if (signed) {
                    ByteIo.writeInt16(this.arrayBuffer.buffer, offset, Conversions.toInt16(args[1]), littleEndian);
                    break;
                }
                ByteIo.writeUint16(this.arrayBuffer.buffer, offset, Conversions.toUint16(args[1]), littleEndian);
                break;
            }
            case 4: {
                if (signed) {
                    ByteIo.writeInt32(this.arrayBuffer.buffer, offset, Conversions.toInt32(args[1]), littleEndian);
                    break;
                }
                ByteIo.writeUint32(this.arrayBuffer.buffer, offset, Conversions.toUint32(args[1]), littleEndian);
                break;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    private void js_setFloat(int bytes, Object[] args) {
        this.checkOffset(args, 0);
        this.checkValue(args, 1);
        int offset = ScriptRuntime.toInt32(args[0]);
        this.rangeCheck(offset, bytes);
        boolean littleEndian = NativeDataView.isArg(args, 2) && bytes > 1 && ScriptRuntime.toBoolean(args[2]);
        double val = ScriptRuntime.toNumber(args[1]);
        switch (bytes) {
            case 4: {
                ByteIo.writeFloat32(this.arrayBuffer.buffer, offset, val, littleEndian);
                break;
            }
            case 8: {
                ByteIo.writeFloat64(this.arrayBuffer.buffer, offset, val, littleEndian);
                break;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(this.getClassName())) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                if (NativeDataView.isArg(args, 0) && args[0] instanceof NativeArrayBuffer) {
                    NativeArrayBuffer ab = (NativeArrayBuffer)args[0];
                    int off = NativeDataView.isArg(args, 1) ? ScriptRuntime.toInt32(args[1]) : 0;
                    int len = NativeDataView.isArg(args, 2) ? ScriptRuntime.toInt32(args[2]) : ab.getLength() - off;
                    return this.js_constructor(ab, off, len);
                }
                throw ScriptRuntime.constructError("TypeError", "Missing parameters");
            }
            case 2: {
                return NativeDataView.realThis(thisObj, f).js_getInt(1, true, args);
            }
            case 3: {
                return NativeDataView.realThis(thisObj, f).js_getInt(1, false, args);
            }
            case 4: {
                return NativeDataView.realThis(thisObj, f).js_getInt(2, true, args);
            }
            case 5: {
                return NativeDataView.realThis(thisObj, f).js_getInt(2, false, args);
            }
            case 6: {
                return NativeDataView.realThis(thisObj, f).js_getInt(4, true, args);
            }
            case 7: {
                return NativeDataView.realThis(thisObj, f).js_getInt(4, false, args);
            }
            case 8: {
                return NativeDataView.realThis(thisObj, f).js_getFloat(4, args);
            }
            case 9: {
                return NativeDataView.realThis(thisObj, f).js_getFloat(8, args);
            }
            case 10: {
                NativeDataView.realThis(thisObj, f).js_setInt(1, true, args);
                return Undefined.instance;
            }
            case 11: {
                NativeDataView.realThis(thisObj, f).js_setInt(1, false, args);
                return Undefined.instance;
            }
            case 12: {
                NativeDataView.realThis(thisObj, f).js_setInt(2, true, args);
                return Undefined.instance;
            }
            case 13: {
                NativeDataView.realThis(thisObj, f).js_setInt(2, false, args);
                return Undefined.instance;
            }
            case 14: {
                NativeDataView.realThis(thisObj, f).js_setInt(4, true, args);
                return Undefined.instance;
            }
            case 15: {
                NativeDataView.realThis(thisObj, f).js_setInt(4, false, args);
                return Undefined.instance;
            }
            case 16: {
                NativeDataView.realThis(thisObj, f).js_setFloat(4, args);
                return Undefined.instance;
            }
            case 17: {
                NativeDataView.realThis(thisObj, f).js_setFloat(8, args);
                return Undefined.instance;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
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
                s = "getInt8";
                break;
            }
            case 3: {
                arity = 1;
                s = "getUint8";
                break;
            }
            case 4: {
                arity = 1;
                s = "getInt16";
                break;
            }
            case 5: {
                arity = 1;
                s = "getUint16";
                break;
            }
            case 6: {
                arity = 1;
                s = "getInt32";
                break;
            }
            case 7: {
                arity = 1;
                s = "getUint32";
                break;
            }
            case 8: {
                arity = 1;
                s = "getFloat32";
                break;
            }
            case 9: {
                arity = 1;
                s = "getFloat64";
                break;
            }
            case 10: {
                arity = 2;
                s = "setInt8";
                break;
            }
            case 11: {
                arity = 2;
                s = "setUint8";
                break;
            }
            case 12: {
                arity = 2;
                s = "setInt16";
                break;
            }
            case 13: {
                arity = 2;
                s = "setUint16";
                break;
            }
            case 14: {
                arity = 2;
                s = "setInt32";
                break;
            }
            case 15: {
                arity = 2;
                s = "setUint32";
                break;
            }
            case 16: {
                arity = 2;
                s = "setFloat32";
                break;
            }
            case 17: {
                arity = 2;
                s = "setFloat64";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(this.getClassName(), id, s, arity);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block19: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 7: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        X = "getInt8";
                        id = 2;
                        break;
                    }
                    if (c != 's') break;
                    X = "setInt8";
                    id = 10;
                    break;
                }
                case 8: {
                    char c = s.charAt(6);
                    if (c == '1') {
                        c = s.charAt(0);
                        if (c == 'g') {
                            X = "getInt16";
                            id = 4;
                            break;
                        }
                        if (c != 's') break;
                        X = "setInt16";
                        id = 12;
                        break;
                    }
                    if (c == '3') {
                        c = s.charAt(0);
                        if (c == 'g') {
                            X = "getInt32";
                            id = 6;
                            break;
                        }
                        if (c != 's') break;
                        X = "setInt32";
                        id = 14;
                        break;
                    }
                    if (c != 't') break;
                    c = s.charAt(0);
                    if (c == 'g') {
                        X = "getUint8";
                        id = 3;
                        break;
                    }
                    if (c != 's') break;
                    X = "setUint8";
                    id = 11;
                    break;
                }
                case 9: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        c = s.charAt(8);
                        if (c == '2') {
                            X = "getUint32";
                            id = 7;
                            break;
                        }
                        if (c != '6') break;
                        X = "getUint16";
                        id = 5;
                        break;
                    }
                    if (c != 's') break;
                    c = s.charAt(8);
                    if (c == '2') {
                        X = "setUint32";
                        id = 15;
                        break;
                    }
                    if (c != '6') break;
                    X = "setUint16";
                    id = 13;
                    break;
                }
                case 10: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        c = s.charAt(9);
                        if (c == '2') {
                            X = "getFloat32";
                            id = 8;
                            break;
                        }
                        if (c != '4') break;
                        X = "getFloat64";
                        id = 9;
                        break;
                    }
                    if (c != 's') break;
                    c = s.charAt(9);
                    if (c == '2') {
                        X = "setFloat32";
                        id = 16;
                        break;
                    }
                    if (c != '4') break;
                    X = "setFloat64";
                    id = 17;
                    break;
                }
                case 11: {
                    X = "constructor";
                    id = 1;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block19;
            id = 0;
        }
        return id;
    }
}

