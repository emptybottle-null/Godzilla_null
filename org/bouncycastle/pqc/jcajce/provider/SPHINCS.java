/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyFactorySpi;

public class SPHINCS {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.sphincs.";

    public static class Mappings
    extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyPairGeneratorSpi");
            this.addSignatureAlgorithm(configurableProvider, "SHA512", "SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.SignatureSpi$withSha512", PQCObjectIdentifiers.sphincs256_with_SHA512);
            this.addSignatureAlgorithm(configurableProvider, "SHA3-512", "SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.SignatureSpi$withSha3_512", PQCObjectIdentifiers.sphincs256_with_SHA3_512);
            Sphincs256KeyFactorySpi sphincs256KeyFactorySpi = new Sphincs256KeyFactorySpi();
            this.registerOid(configurableProvider, PQCObjectIdentifiers.sphincs256, "SPHINCS256", sphincs256KeyFactorySpi);
            this.registerOidAlgorithmParameters(configurableProvider, PQCObjectIdentifiers.sphincs256, "SPHINCS256");
        }
    }
}

