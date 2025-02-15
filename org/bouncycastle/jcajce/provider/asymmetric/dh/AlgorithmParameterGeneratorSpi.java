/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dh;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHGenParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.generators.DHParametersGenerator;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAlgorithmParameterGeneratorSpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

public class AlgorithmParameterGeneratorSpi
extends BaseAlgorithmParameterGeneratorSpi {
    protected SecureRandom random;
    protected int strength = 2048;
    private int l = 0;

    protected void engineInit(int n, SecureRandom secureRandom) {
        this.strength = n;
        this.random = secureRandom;
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof DHGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("DH parameter generator requires a DHGenParameterSpec for initialisation");
        }
        DHGenParameterSpec dHGenParameterSpec = (DHGenParameterSpec)algorithmParameterSpec;
        this.strength = dHGenParameterSpec.getPrimeSize();
        this.l = dHGenParameterSpec.getExponentSize();
        this.random = secureRandom;
    }

    protected AlgorithmParameters engineGenerateParameters() {
        AlgorithmParameters algorithmParameters;
        DHParametersGenerator dHParametersGenerator = new DHParametersGenerator();
        int n = PrimeCertaintyCalculator.getDefaultCertainty(this.strength);
        if (this.random != null) {
            dHParametersGenerator.init(this.strength, n, this.random);
        } else {
            dHParametersGenerator.init(this.strength, n, new SecureRandom());
        }
        DHParameters dHParameters = dHParametersGenerator.generateParameters();
        try {
            algorithmParameters = this.createParametersInstance("DH");
            algorithmParameters.init(new DHParameterSpec(dHParameters.getP(), dHParameters.getG(), this.l));
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return algorithmParameters;
    }
}

