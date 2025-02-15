/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ConstPool;

public class SyntheticAttribute
extends AttributeInfo {
    public static final String tag = "Synthetic";

    SyntheticAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    public SyntheticAttribute(ConstPool cp) {
        super(cp, tag, new byte[0]);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        return new SyntheticAttribute(newCp);
    }
}

