/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import org.bouncycastle.est.Source;

public interface ESTClientSourceProvider {
    public Source makeSource(String var1, int var2) throws IOException;
}

