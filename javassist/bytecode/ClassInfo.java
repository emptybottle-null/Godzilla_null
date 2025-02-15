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

class ClassInfo
extends ConstInfo {
    static final int tag = 7;
    int name;

    public ClassInfo(int className, int index) {
        super(index);
        this.name = className;
    }

    public ClassInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.name = in.readUnsignedShort();
    }

    public int hashCode() {
        return this.name;
    }

    public boolean equals(Object obj) {
        return obj instanceof ClassInfo && ((ClassInfo)obj).name == this.name;
    }

    @Override
    public int getTag() {
        return 7;
    }

    @Override
    public String getClassName(ConstPool cp) {
        return cp.getUtf8Info(this.name);
    }

    @Override
    public void renameClass(ConstPool cp, String oldName, String newName, Map<ConstInfo, ConstInfo> cache) {
        String s;
        String nameStr = cp.getUtf8Info(this.name);
        String newNameStr = null;
        if (nameStr.equals(oldName)) {
            newNameStr = newName;
        } else if (nameStr.charAt(0) == '[' && nameStr != (s = Descriptor.rename(nameStr, oldName, newName))) {
            newNameStr = s;
        }
        if (newNameStr != null) {
            if (cache == null) {
                this.name = cp.addUtf8Info(newNameStr);
            } else {
                cache.remove(this);
                this.name = cp.addUtf8Info(newNameStr);
                cache.put(this, this);
            }
        }
    }

    @Override
    public void renameClass(ConstPool cp, Map<String, String> map, Map<ConstInfo, ConstInfo> cache) {
        String oldName = cp.getUtf8Info(this.name);
        String newName = null;
        if (oldName.charAt(0) == '[') {
            String s = Descriptor.rename(oldName, map);
            if (oldName != s) {
                newName = s;
            }
        } else {
            String s = map.get(oldName);
            if (s != null && !s.equals(oldName)) {
                newName = s;
            }
        }
        if (newName != null) {
            if (cache == null) {
                this.name = cp.addUtf8Info(newName);
            } else {
                cache.remove(this);
                this.name = cp.addUtf8Info(newName);
                cache.put(this, this);
            }
        }
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        String newname;
        String classname = src.getUtf8Info(this.name);
        if (map != null && (newname = map.get(classname)) != null) {
            classname = newname;
        }
        return dest.addClassInfo(classname);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(7);
        out.writeShort(this.name);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("Class #");
        out.println(this.name);
    }
}

