/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.util;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public abstract class AsymmetricAlgorithmProvider
extends AlgorithmProvider {
    protected void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String string, String string2, String string3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string4 = string + "WITH" + string2;
        String string5 = string + "with" + string2;
        String string6 = string + "With" + string2;
        String string7 = string + "/" + string2;
        configurableProvider.addAlgorithm("Signature." + string4, string3);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + string5, string4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + string6, string4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + string7, string4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + aSN1ObjectIdentifier, string4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, string4);
    }

    protected void registerOid(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String string, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier, string);
        configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator." + aSN1ObjectIdentifier, string);
        configurableProvider.addKeyInfoConverter(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    protected void registerOidAlgorithmParameters(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, string);
    }

    protected void registerOidAlgorithmParameterGenerator(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + aSN1ObjectIdentifier, string);
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + aSN1ObjectIdentifier, string);
    }
}

