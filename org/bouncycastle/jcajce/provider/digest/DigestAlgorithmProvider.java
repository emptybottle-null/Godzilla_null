/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.digest;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

abstract class DigestAlgorithmProvider
extends AlgorithmProvider {
    DigestAlgorithmProvider() {
    }

    protected void addHMACAlgorithm(ConfigurableProvider configurableProvider, String string, String string2, String string3) {
        String string4 = "HMAC" + string;
        configurableProvider.addAlgorithm("Mac." + string4, string2);
        configurableProvider.addAlgorithm("Alg.Alias.Mac.HMAC-" + string, string4);
        configurableProvider.addAlgorithm("Alg.Alias.Mac.HMAC/" + string, string4);
        configurableProvider.addAlgorithm("KeyGenerator." + string4, string3);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.HMAC-" + string, string4);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.HMAC/" + string, string4);
    }

    protected void addHMACAlias(ConfigurableProvider configurableProvider, String string, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string2 = "HMAC" + string;
        configurableProvider.addAlgorithm("Alg.Alias.Mac." + aSN1ObjectIdentifier, string2);
        configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + aSN1ObjectIdentifier, string2);
    }
}

