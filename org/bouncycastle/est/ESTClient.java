/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTResponse;

public interface ESTClient {
    public ESTResponse doRequest(ESTRequest var1) throws IOException;
}

