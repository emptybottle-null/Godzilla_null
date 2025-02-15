/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

public abstract class BaseAlgorithmParameters
extends AlgorithmParametersSpi {
    protected boolean isASN1FormatString(String string) {
        return string == null || string.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == null) {
            throw new NullPointerException("argument to getParameterSpec must not be null");
        }
        return this.localEngineGetParameterSpec(clazz);
    }

    protected abstract AlgorithmParameterSpec localEngineGetParameterSpec(Class var1) throws InvalidParameterSpecException;
}

