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

class DynamicInfo
extends ConstInfo {
    static final int tag = 17;
    int bootstrap;
    int nameAndType;

    public DynamicInfo(int bootstrapMethod, int ntIndex, int index) {
        super(index);
        this.bootstrap = bootstrapMethod;
        this.nameAndType = ntIndex;
    }

    public DynamicInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.bootstrap = in.readUnsignedShort();
        this.nameAndType = in.readUnsignedShort();
    }

    public int hashCode() {
        return this.bootstrap << 16 ^ this.nameAndType;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DynamicInfo) {
            DynamicInfo iv = (DynamicInfo)obj;
            return iv.bootstrap == this.bootstrap && iv.nameAndType == this.nameAndType;
        }
        return false;
    }

    @Override
    public int getTag() {
        return 17;
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        return dest.addDynamicInfo(this.bootstrap, src.getItem(this.nameAndType).copy(src, dest, map));
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(17);
        out.writeShort(this.bootstrap);
        out.writeShort(this.nameAndType);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("Dynamic #");
        out.print(this.bootstrap);
        out.print(", name&type #");
        out.println(this.nameAndType);
    }
}

