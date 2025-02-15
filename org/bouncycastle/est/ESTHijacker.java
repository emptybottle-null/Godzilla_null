/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTResponse;
import org.bouncycastle.est.Source;

public interface ESTHijacker {
    public ESTResponse hijack(ESTRequest var1, Source var2) throws IOException;
}

