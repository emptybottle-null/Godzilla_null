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

class NameAndTypeInfo
extends ConstInfo {
    static final int tag = 12;
    int memberName;
    int typeDescriptor;

    public NameAndTypeInfo(int name, int type, int index) {
        super(index);
        this.memberName = name;
        this.typeDescriptor = type;
    }

    public NameAndTypeInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.memberName = in.readUnsignedShort();
        this.typeDescriptor = in.readUnsignedShort();
    }

    public int hashCode() {
        return this.memberName << 16 ^ this.typeDescriptor;
    }

    public boolean equals(Object obj) {
        if (obj instanceof NameAndTypeInfo) {
            NameAndTypeInfo nti = (NameAndTypeInfo)obj;
            return nti.memberName == this.memberName && nti.typeDescriptor == this.typeDescriptor;
        }
        return false;
    }

    @Override
    public int getTag() {
        return 12;
    }

    @Override
    public void renameClass(ConstPool cp, String oldName, String newName, Map<ConstInfo, ConstInfo> cache) {
        String type2;
        String type = cp.getUtf8Info(this.typeDescriptor);
        if (type != (type2 = Descriptor.rename(type, oldName, newName))) {
            if (cache == null) {
                this.typeDescriptor = cp.addUtf8Info(type2);
            } else {
                cache.remove(this);
                this.typeDescriptor = cp.addUtf8Info(type2);
                cache.put(this, this);
            }
        }
    }

    @Override
    public void renameClass(ConstPool cp, Map<String, String> map, Map<ConstInfo, ConstInfo> cache) {
        String type2;
        String type = cp.getUtf8Info(this.typeDescriptor);
        if (type != (type2 = Descriptor.rename(type, map))) {
            if (cache == null) {
                this.typeDescriptor = cp.addUtf8Info(type2);
            } else {
                cache.remove(this);
                this.typeDescriptor = cp.addUtf8Info(type2);
                cache.put(this, this);
            }
        }
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        String mname = src.getUtf8Info(this.memberName);
        String tdesc = src.getUtf8Info(this.typeDescriptor);
        tdesc = Descriptor.rename(tdesc, map);
        return dest.addNameAndTypeInfo(dest.addUtf8Info(mname), dest.addUtf8Info(tdesc));
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(12);
        out.writeShort(this.memberName);
        out.writeShort(this.typeDescriptor);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("NameAndType #");
        out.print(this.memberName);
        out.print(", type #");
        out.println(this.typeDescriptor);
    }
}

