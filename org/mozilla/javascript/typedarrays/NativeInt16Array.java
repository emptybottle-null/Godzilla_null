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

public class NativeInt16Array
extends NativeTypedArrayView<Short> {
    private static final long serialVersionUID = -8592870435287581398L;
    private static final String CLASS_NAME = "Int16Array";
    private static final int BYTES_PER_ELEMENT = 2;

    public NativeInt16Array() {
    }

    public NativeInt16Array(NativeArrayBuffer ab, int off, int len) {
        super(ab, off, len, len * 2);
    }

    public NativeInt16Array(int len) {
        this(new NativeArrayBuffer(len * 2), 0, len);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeInt16Array a = new NativeInt16Array();
        a.exportAsJSClass(4, scope, sealed);
    }

    @Override
    protected NativeTypedArrayView construct(NativeArrayBuffer ab, int off, int len) {
        return new NativeInt16Array(ab, off, len);
    }

    @Override
    public int getBytesPerElement() {
        return 2;
    }

    @Override
    protected NativeTypedArrayView realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeInt16Array)) {
            throw NativeInt16Array.incompatibleCallError(f);
        }
        return (NativeInt16Array)thisObj;
    }

    @Override
    protected Object js_get(int index) {
        if (this.checkIndex(index)) {
            return Undefined.instance;
        }
        return ByteIo.readInt16(this.arrayBuffer.buffer, index * 2 + this.offset, false);
    }

    @Override
    protected Object js_set(int index, Object c) {
        if (this.checkIndex(index)) {
            return Undefined.instance;
        }
        int val = Conversions.toInt16(c);
        ByteIo.writeInt16(this.arrayBuffer.buffer, index * 2 + this.offset, val, false);
        return null;
    }

    @Override
    public Short get(int i) {
        if (this.checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Short)this.js_get(i);
    }

    @Override
    public Short set(int i, Short aByte) {
        if (this.checkIndex(i)) {
            throw new IndexOutOfBoundsException();
        }
        return (Short)this.js_set(i, aByte);
    }
}

