/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;

public class MethodParametersAttribute
extends AttributeInfo {
    public static final String tag = "MethodParameters";

    MethodParametersAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    public MethodParametersAttribute(ConstPool cp, String[] names, int[] flags) {
        super(cp, tag);
        byte[] data = new byte[names.length * 4 + 1];
        data[0] = (byte)names.length;
        for (int i = 0; i < names.length; ++i) {
            ByteArray.write16bit(cp.addUtf8Info(names[i]), data, i * 4 + 1);
            ByteArray.write16bit(flags[i], data, i * 4 + 3);
        }
        this.set(data);
    }

    public int size() {
        return this.info[0] & 0xFF;
    }

    public int name(int i) {
        return ByteArray.readU16bit(this.info, i * 4 + 1);
    }

    public String parameterName(int i) {
        return this.getConstPool().getUtf8Info(this.name(i));
    }

    public int accessFlags(int i) {
        return ByteArray.readU16bit(this.info, i * 4 + 3);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        int s = this.size();
        ConstPool cp = this.getConstPool();
        String[] names = new String[s];
        int[] flags = new int[s];
        for (int i = 0; i < s; ++i) {
            names[i] = cp.getUtf8Info(this.name(i));
            flags[i] = this.accessFlags(i);
        }
        return new MethodParametersAttribute(newCp, names, flags);
    }
}

