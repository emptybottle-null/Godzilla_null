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

class InvokeDynamicInfo
extends ConstInfo {
    static final int tag = 18;
    int bootstrap;
    int nameAndType;

    public InvokeDynamicInfo(int bootstrapMethod, int ntIndex, int index) {
        super(index);
        this.bootstrap = bootstrapMethod;
        this.nameAndType = ntIndex;
    }

    public InvokeDynamicInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.bootstrap = in.readUnsignedShort();
        this.nameAndType = in.readUnsignedShort();
    }

    public int hashCode() {
        return this.bootstrap << 16 ^ this.nameAndType;
    }

    public boolean equals(Object obj) {
        if (obj instanceof InvokeDynamicInfo) {
            InvokeDynamicInfo iv = (InvokeDynamicInfo)obj;
            return iv.bootstrap == this.bootstrap && iv.nameAndType == this.nameAndType;
        }
        return false;
    }

    @Override
    public int getTag() {
        return 18;
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        return dest.addInvokeDynamicInfo(this.bootstrap, src.getItem(this.nameAndType).copy(src, dest, map));
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(18);
        out.writeShort(this.bootstrap);
        out.writeShort(this.nameAndType);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("InvokeDynamic #");
        out.print(this.bootstrap);
        out.print(", name&type #");
        out.println(this.nameAndType);
    }
}

