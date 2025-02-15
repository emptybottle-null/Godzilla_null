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
import javassist.bytecode.Descriptor;

class MethodTypeInfo
extends ConstInfo {
    static final int tag = 16;
    int descriptor;

    public MethodTypeInfo(int desc, int index) {
        super(index);
        this.descriptor = desc;
    }

    public MethodTypeInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.descriptor = in.readUnsignedShort();
    }

    public int hashCode() {
        return this.descriptor;
    }

    public boolean equals(Object obj) {
        if (obj instanceof MethodTypeInfo) {
            return ((MethodTypeInfo)obj).descriptor == this.descriptor;
        }
        return false;
    }

    @Override
    public int getTag() {
        return 16;
    }

    @Override
    public void renameClass(ConstPool cp, String oldName, String newName, Map<ConstInfo, ConstInfo> cache) {
        String desc2;
        String desc = cp.getUtf8Info(this.descriptor);
        if (desc != (desc2 = Descriptor.rename(desc, oldName, newName))) {
            if (cache == null) {
                this.descriptor = cp.addUtf8Info(desc2);
            } else {
                cache.remove(this);
                this.descriptor = cp.addUtf8Info(desc2);
                cache.put(this, this);
            }
        }
    }

    @Override
    public void renameClass(ConstPool cp, Map<String, String> map, Map<ConstInfo, ConstInfo> cache) {
        String desc2;
        String desc = cp.getUtf8Info(this.descriptor);
        if (desc != (desc2 = Descriptor.rename(desc, map))) {
            if (cache == null) {
                this.descriptor = cp.addUtf8Info(desc2);
            } else {
                cache.remove(this);
                this.descriptor = cp.addUtf8Info(desc2);
                cache.put(this, this);
            }
        }
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        String desc = src.getUtf8Info(this.descriptor);
        desc = Descriptor.rename(desc, map);
        return dest.addMethodTypeInfo(dest.addUtf8Info(desc));
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(16);
        out.writeShort(this.descriptor);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("MethodType #");
        out.println(this.descriptor);
    }
}

