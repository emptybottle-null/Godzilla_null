/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import javassist.bytecode.MethodInfo;

public class BadBytecode
extends Exception {
    private static final long serialVersionUID = 1L;

    public BadBytecode(int opcode) {
        super("bytecode " + opcode);
    }

    public BadBytecode(String msg) {
        super(msg);
    }

    public BadBytecode(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BadBytecode(MethodInfo minfo, Throwable cause) {
        super(minfo.toString() + " in " + minfo.getConstPool().getClassName() + ": " + cause.getMessage(), cause);
    }
}

