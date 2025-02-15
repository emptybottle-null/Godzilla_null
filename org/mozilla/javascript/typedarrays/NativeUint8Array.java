/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.typedarrays.ByteIo;
import org.mozilla.javascript.typedarrays.Conversions;
import org.mozilla.javascript.typedarrays.NativeArrayBuffer;
import org.mozilla.javascript.typedarrays.NativeTypedArrayView;

public class NativeUint8Array
extends NativeTypedArrayView<Integer> {
    private static final long serialVersionUID = -3349419704390398895L;
    private static final String CLASS_NAME = "Uint8Array";

    public NativeUint8Array() {
    }

    public NativeUint8Array(NativeArrayBuffer ab, int off, int len) {
        super(ab, off, len, len);
    }

    public NativeUint8Array(int len) {
        this(new NativeArrayBuffer(len), 0, len);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeUint8Array a = new NativeUint8Array();
        a.exportAsJSClass(4, scope, sealed);
    }

    @Override
    protected NativeTypedArrayView construct(NativeArrayBuffer ab, int off, int len) {
        return new NativeUint8Array(ab, off, len);
    }

    @Override
    public int getBytesPerElement() {
        return 1;
    }

    @Override
    protected NativeTypedArrayView realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeUint8Array)) {
            throw NativeUint8Array.incompatibleCallError(f);
        }
        return (NativeUint8Array)thisObj;
    }

    @Override
    protected Object js_get(int index) {
        if (this.checkIndex(index)) {
            return Undefined.instance;
        }
        return ByteIo.readUint8(this.arrayBuffer.buffer, index + this.offset);
    }

    @Override
    protected Object js_set(int index, Object c) {
        if (this.checkIndex(index)) {
            return Undefined.instance;
        }
        int val = Conversions.toUint8(c);
        ByteIo.writeUint8(this.arrayBuffer.buffer, index + this.offset, val);
        return null;
    }

    @Override
    public Integer get(int i) {
        if (this.checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Integer)this.js_get(i);
    }

    @Override
    public Integer set(int i, Integer aByte) {
        if (this.checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Integer)this.js_set(i, aByte);
    }
}

