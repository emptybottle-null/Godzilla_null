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

class StringInfo
extends ConstInfo {
    static final int tag = 8;
    int string;

    public StringInfo(int str, int index) {
        super(index);
        this.string = str;
    }

    public StringInfo(DataInputStream in, int index) throws IOException {
        super(index);
        this.string = in.readUnsignedShort();
    }

    public int hashCode() {
        return this.string;
    }

    public boolean equals(Object obj) {
        return obj instanceof StringInfo && ((StringInfo)obj).string == this.string;
    }

    @Override
    public int getTag() {
        return 8;
    }

    @Override
    public int copy(ConstPool src, ConstPool dest, Map<String, String> map) {
        return dest.addStringInfo(src.getUtf8Info(this.string));
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(8);
        out.writeShort(this.string);
    }

    @Override
    public void print(PrintWriter out) {
        out.print("String #");
        out.println(this.string);
    }
}

