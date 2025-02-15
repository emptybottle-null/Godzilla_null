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

public class NestMembersAttribute
extends AttributeInfo {
    public static final String tag = "NestMembers";

    NestMembersAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    private NestMembersAttribute(ConstPool cp, byte[] info) {
        super(cp, tag, info);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        byte[] src = this.get();
        byte[] dest = new byte[src.length];
        ConstPool cp = this.getConstPool();
        int n = ByteArray.readU16bit(src, 0);
        ByteArray.write16bit(n, dest, 0);
        int i = 0;
        int j = 2;
        while (i < n) {
            int index = ByteArray.readU16bit(src, j);
            int newIndex = cp.copy(index, newCp, classnames);
            ByteArray.write16bit(newIndex, dest, j);
            ++i;
            j += 2;
        }
        return new NestMembersAttribute(newCp, dest);
    }

    public int numberOfClasses() {
        return ByteArray.readU16bit(this.info, 0);
    }

    public int memberClass(int index) {
        return ByteArray.readU16bit(this.info, index * 2 + 2);
    }
}

