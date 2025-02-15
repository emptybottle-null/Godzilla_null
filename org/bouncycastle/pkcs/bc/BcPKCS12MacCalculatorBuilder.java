/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.bc;

import java.security.SecureRandom;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
import org.bouncycastle.pkcs.bc.PKCS12PBEUtils;

public class BcPKCS12MacCalculatorBuilder
implements PKCS12MacCalculatorBuilder {
    private ExtendedDigest digest;
    private AlgorithmIdentifier algorithmIdentifier;
    private SecureRandom random;
    private int saltLength;
    private int iterationCount = 1024;

    public BcPKCS12MacCalculatorBuilder() {
        this(new SHA1Digest(), new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
    }

    public BcPKCS12MacCalculatorBuilder(ExtendedDigest extendedDigest, AlgorithmIdentifier algorithmIdentifier) {
        this.digest = extendedDigest;
        this.algorithmIdentifier = algorithmIdentifier;
        this.saltLength = extendedDigest.getDigestSize();
    }

    public BcPKCS12MacCalculatorBuilder setIterationCount(int n) {
        this.iterationCount = n;
        return this;
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public MacCalculator build(char[] cArray) {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        byte[] byArray = new byte[this.saltLength];
        this.random.nextBytes(byArray);
        return PKCS12PBEUtils.createMacCalculator(this.algorithmIdentifier.getAlgorithm(), this.digest, new PKCS12PBEParams(byArray, this.iterationCount), cArray);
    }
}

