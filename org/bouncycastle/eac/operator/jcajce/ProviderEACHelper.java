/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import org.bouncycastle.eac.operator.jcajce.EACHelper;

class ProviderEACHelper
extends EACHelper {
    private final Provider provider;

    ProviderEACHelper(Provider provider) {
        this.provider = provider;
    }

    protected Signature createSignature(String string) throws NoSuchAlgorithmException {
        return Signature.getInstance(string, this.provider);
    }
}

