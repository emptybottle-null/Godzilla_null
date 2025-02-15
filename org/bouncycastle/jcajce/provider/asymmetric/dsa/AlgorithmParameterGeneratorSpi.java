/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.DSAParametersGenerator;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAlgorithmParameterGeneratorSpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

public class AlgorithmParameterGeneratorSpi
extends BaseAlgorithmParameterGeneratorSpi {
    protected SecureRandom random;
    protected int strength = 2048;
    protected DSAParameterGenerationParameters params;

    protected void engineInit(int n, SecureRandom secureRandom) {
        if (n < 512 || n > 3072) {
            throw new InvalidParameterException("strength must be from 512 - 3072");
        }
        if (n <= 1024 && n % 64 != 0) {
            throw new InvalidParameterException("strength must be a multiple of 64 below 1024 bits.");
        }
        if (n > 1024 && n % 1024 != 0) {
            throw new InvalidParameterException("strength must be a multiple of 1024 above 1024 bits.");
        }
        this.strength = n;
        this.random = secureRandom;
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DSA parameter generation.");
    }

    protected AlgorithmParameters engineGenerateParameters() {
        AlgorithmParameters algorithmParameters;
        DSAParametersGenerator dSAParametersGenerator = this.strength <= 1024 ? new DSAParametersGenerator() : new DSAParametersGenerator(new SHA256Digest());
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        int n = PrimeCertaintyCalculator.getDefaultCertainty(this.strength);
        if (this.strength == 1024) {
            this.params = new DSAParameterGenerationParameters(1024, 160, n, this.random);
            dSAParametersGenerator.init(this.params);
        } else if (this.strength > 1024) {
            this.params = new DSAParameterGenerationParameters(this.strength, 256, n, this.random);
            dSAParametersGenerator.init(this.params);
        } else {
            dSAParametersGenerator.init(this.strength, n, this.random);
        }
        DSAParameters dSAParameters = dSAParametersGenerator.generateParameters();
        try {
            algorithmParameters = this.createParametersInstance("DSA");
            algorithmParameters.init(new DSAParameterSpec(dSAParameters.getP(), dSAParameters.getQ(), dSAParameters.getG()));
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return algorithmParameters;
    }
}

