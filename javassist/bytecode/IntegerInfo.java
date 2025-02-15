/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javassist.bytecode.ConstInfo;
import javassist.bytecode.ConstPool;

class IntegerInfo
extends ConstInfo {
    static final int tag = 3;
    int value;

    public IntegerInfo(int v, int index) {
        super(index);
        this.value = v;
    }

    public IntegerInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.value = in.readInt();
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return obj instanceof IntegerInfo && ((IntegerInfo)obj).value == this.value;
    }

    @Override
    public int getTag() {
        return 3;
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        return dest.addIntegerInfo(this.value);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(3);
        out.writeInt(this.value);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("Integer ");
        out.println(this.value);
    }
}

