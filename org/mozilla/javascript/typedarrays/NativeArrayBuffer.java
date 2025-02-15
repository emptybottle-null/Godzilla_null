/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.typedarrays.NativeArrayBufferView;

public class NativeArrayBuffer
extends IdScriptableObject {
    private static final long serialVersionUID = 3110411773054879549L;
    public static final String CLASS_NAME = "ArrayBuffer";
    private static final byte[] EMPTY_BUF = new byte[0];
    public static final NativeArrayBuffer EMPTY_BUFFER = new NativeArrayBuffer();
    final byte[] buffer;
    private static final int Id_constructor = 1;
    private static final int Id_slice = 2;
    private static final int Id_isView = 3;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final int ConstructorId_isView = -3;
    private static final int Id_byteLength = 1;
    private static final int MAX_INSTANCE_ID = 1;

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeArrayBuffer na = new NativeArrayBuffer();
        na.exportAsJSClass(3, scope, sealed);
    }

    public NativeArrayBuffer() {
        this.buffer = EMPTY_BUF;
    }

    public NativeArrayBuffer(int len) {
        if (len < 0) {
            throw ScriptRuntime.constructError("RangeError", "Negative array length " + len);
        }
        this.buffer = len == 0 ? EMPTY_BUF : new byte[len];
    }

    public int getLength() {
        return this.buffer.length;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public NativeArrayBuffer slice(int s, int e) {
        int end = Math.max(0, Math.min(this.buffer.length, e < 0 ? this.buffer.length + e : e));
        int start = Math.min(end, Math.max(0, s < 0 ? this.buffer.length + s : s));
        int len = end - start;
        NativeArrayBuffer newBuf = new NativeArrayBuffer(len);
        System.arraycopy(this.buffer, start, newBuf.buffer, 0, len);
        return newBuf;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(CLASS_NAME)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case -3: {
                return NativeArrayBuffer.isArg(args, 0) && args[0] instanceof NativeArrayBufferView;
            }
            case 1: {
                int length = NativeArrayBuffer.isArg(args, 0) ? ScriptRuntime.toInt32(args[0]) : 0;
                return new NativeArrayBuffer(length);
            }
            case 2: {
                NativeArrayBuffer self = NativeArrayBuffer.realThis(thisObj, f);
                int start = NativeArrayBuffer.isArg(args, 0) ? ScriptRuntime.toInt32(args[0]) : 0;
                int end = NativeArrayBuffer.isArg(args, 1) ? ScriptRuntime.toInt32(args[1]) : self.buffer.length;
                return self.slice(start, end);
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static NativeArrayBuffer realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeArrayBuffer)) {
            throw NativeArrayBuffer.incompatibleCallError(f);
        }
        return (NativeArrayBuffer)thisObj;
    }

    private static boolean isArg(Object[] args, int i) {
        return args.length > i && !Undefined.instance.equals(args[i]);
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
                s = "slice";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(CLASS_NAME, id, s, arity);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block5: {
            id = 0;
            String X = null;
            int s_length = s.length();
            if (s_length == 5) {
                X = "slice";
                id = 2;
            } else if (s_length == 6) {
                X = "isView";
                id = 3;
            } else if (s_length == 11) {
                X = "constructor";
                id = 1;
            }
            if (X == null || X == s || X.equals(s)) break block5;
            id = 0;
        }
        return id;
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        this.addIdFunctionProperty(ctor, CLASS_NAME, -3, "isView", 1);
    }

    @Override
    protected int getMaxInstanceId() {
        return 1;
    }

    @Override
    protected String getInstanceIdName(int id) {
        if (id == 1) {
            return "byteLength";
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        if (id == 1) {
            return ScriptRuntime.wrapInt(this.buffer.length);
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        if ("byteLength".equals(s)) {
            return NativeArrayBuffer.instanceIdInfo(5, 1);
        }
        return super.findInstanceIdInfo(s);
    }
}

