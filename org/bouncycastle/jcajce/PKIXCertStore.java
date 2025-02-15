/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import java.security.cert.Certificate;
import java.util.Collection;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface PKIXCertStore<T extends Certificate>
extends Store<T> {
    @Override
    public Collection<T> getMatches(Selector<T> var1) throws StoreException;
}

