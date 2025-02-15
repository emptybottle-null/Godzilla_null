/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.bc;

import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDigestProvider;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilderProvider;
import org.bouncycastle.pkcs.bc.PKCS12PBEUtils;

public class BcPKCS12MacCalculatorBuilderProvider
implements PKCS12MacCalculatorBuilderProvider {
    private BcDigestProvider digestProvider;

    public BcPKCS12MacCalculatorBuilderProvider(BcDigestProvider bcDigestProvider) {
        this.digestProvider = bcDigestProvider;
    }

    public PKCS12MacCalculatorBuilder get(final AlgorithmIdentifier algorithmIdentifier) {
        return new PKCS12MacCalculatorBuilder(){

            public MacCalculator build(char[] cArray) throws OperatorCreationException {
                PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                return PKCS12PBEUtils.createMacCalculator(algorithmIdentifier.getAlgorithm(), BcPKCS12MacCalculatorBuilderProvider.this.digestProvider.get(algorithmIdentifier), pKCS12PBEParams, cArray);
            }

            public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
                return new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
            }
        };
    }
}

