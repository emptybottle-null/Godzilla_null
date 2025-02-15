/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MemberrefInfo;

class MethodrefInfo
extends MemberrefInfo {
    static final int tag = 10;

    public MethodrefInfo(int cindex, int ntindex, int thisIndex) {
        super(cindex, ntindex, thisIndex);
    }

    public MethodrefInfo(DataInputStream in, int thisIndex) throws IOException {
        super(in, thisIndex);
    }

    @Override
    public int getTag() {
        return 10;
    }

    @Override
    public String getTagName() {
        return "Method";
    }

    @Override
    protected int copy2(ConstPool dest, int cindex, int ntindex) {
        return dest.addMethodrefInfo(cindex, ntindex);
    }
}

