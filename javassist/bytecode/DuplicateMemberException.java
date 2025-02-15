/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import javassist.CannotCompileException;

public class DuplicateMemberException
extends CannotCompileException {
    private static final long serialVersionUID = 1L;

    public DuplicateMemberException(String msg) {
        super(msg);
    }
}

