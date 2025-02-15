/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import org.bouncycastle.eac.jcajce.EACHelper;

class ProviderEACHelper
implements EACHelper {
    private final Provider provider;

    ProviderEACHelper(Provider provider) {
        this.provider = provider;
    }

    public KeyFactory createKeyFactory(String string) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(string, this.provider);
    }
}

