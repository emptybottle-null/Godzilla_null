/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.typedarrays;

import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.typedarrays.NativeArrayBuffer;

public abstract class NativeArrayBufferView
extends IdScriptableObject {
    private static final long serialVersionUID = 6884475582973958419L;
    protected final NativeArrayBuffer arrayBuffer;
    protected final int offset;
    protected final int byteLength;
    private static final int Id_buffer = 1;
    private static final int Id_byteOffset = 2;
    private static final int Id_byteLength = 3;
    private static final int MAX_INSTANCE_ID = 3;

    public NativeArrayBufferView() {
        this.arrayBuffer = NativeArrayBuffer.EMPTY_BUFFER;
        this.offset = 0;
        this.byteLength = 0;
    }

    protected NativeArrayBufferView(NativeArrayBuffer ab, int offset, int byteLength) {
        this.offset = offset;
        this.byteLength = byteLength;
        this.arrayBuffer = ab;
    }

    public NativeArrayBuffer getBuffer() {
        return this.arrayBuffer;
    }

    public int getByteOffset() {
        return this.offset;
    }

    public int getByteLength() {
        return this.byteLength;
    }

    protected static boolean isArg(Object[] args, int i) {
        return args.length > i && !Undefined.instance.equals(args[i]);
    }

    @Override
    protected int getMaxInstanceId() {
        return 3;
    }

    @Override
    protected String getInstanceIdName(int id) {
        switch (id) {
            case 1: {
                return "buffer";
            }
            case 2: {
                return "byteOffset";
            }
            case 3: {
                return "byteLength";
            }
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        switch (id) {
            case 1: {
                return this.arrayBuffer;
            }
            case 2: {
                return ScriptRuntime.wrapInt(this.offset);
            }
            case 3: {
                return ScriptRuntime.wrapInt(this.byteLength);
            }
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        int id = 0;
        String X = null;
        int s_length = s.length();
        if (s_length == 6) {
            X = "buffer";
            id = 1;
        } else if (s_length == 10) {
            char c = s.charAt(4);
            if (c == 'L') {
                X = "byteLength";
                id = 3;
            } else if (c == 'O') {
                X = "byteOffset";
                id = 2;
            }
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        return NativeArrayBufferView.instanceIdInfo(5, id);
    }
}

