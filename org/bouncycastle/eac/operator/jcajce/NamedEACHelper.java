/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac.operator.jcajce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import org.bouncycastle.eac.operator.jcajce.EACHelper;

class NamedEACHelper
extends EACHelper {
    private final String providerName;

    NamedEACHelper(String string) {
        this.providerName = string;
    }

    protected Signature createSignature(String string) throws NoSuchProviderException, NoSuchAlgorithmException {
        return Signature.getInstance(string, this.providerName);
    }
}

