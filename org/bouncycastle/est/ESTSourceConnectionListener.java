/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.Source;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface ESTSourceConnectionListener<T, I> {
    public ESTRequest onConnection(Source<T> var1, ESTRequest var2) throws IOException;
}

