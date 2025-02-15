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

public class NestHostAttribute
extends AttributeInfo {
    public static final String tag = "NestHost";

    NestHostAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    private NestHostAttribute(ConstPool cp, int hostIndex) {
        super(cp, tag, new byte[2]);
        ByteArray.write16bit(hostIndex, this.get(), 0);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        int hostIndex = ByteArray.readU16bit(this.get(), 0);
        int newHostIndex = this.getConstPool().copy(hostIndex, newCp, classnames);
        return new NestHostAttribute(newCp, newHostIndex);
    }

    public int hostClassIndex() {
        return ByteArray.readU16bit(this.info, 0);
    }
}

