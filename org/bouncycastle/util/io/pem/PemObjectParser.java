/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io.pem;

import java.io.IOException;
import org.bouncycastle.util.io.pem.PemObject;

public interface PemObjectParser {
    public Object parseObject(PemObject var1) throws IOException;
}

