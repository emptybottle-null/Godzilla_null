/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ElGamalKeyPairGenerator;
import org.bouncycastle.crypto.generators.ElGamalParametersGenerator;
import org.bouncycastle.crypto.params.ElGamalKeyGenerationParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.elgamal.BCElGamalPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.elgamal.BCElGamalPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

public class KeyPairGeneratorSpi
extends KeyPairGenerator {
    ElGamalKeyGenerationParameters param;
    ElGamalKeyPairGenerator engine = new ElGamalKeyPairGenerator();
    int strength = 1024;
    int certainty = 20;
    SecureRandom random = new SecureRandom();
    boolean initialised = false;

    public KeyPairGeneratorSpi() {
        super("ElGamal");
    }

    public void initialize(int n, SecureRandom secureRandom) {
        this.strength = n;
        this.random = secureRandom;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof ElGamalParameterSpec) && !(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec or an ElGamalParameterSpec");
        }
        if (algorithmParameterSpec instanceof ElGamalParameterSpec) {
            ElGamalParameterSpec elGamalParameterSpec = (ElGamalParameterSpec)algorithmParameterSpec;
            this.param = new ElGamalKeyGenerationParameters(secureRandom, new ElGamalParameters(elGamalParameterSpec.getP(), elGamalParameterSpec.getG()));
        } else {
            DHParameterSpec dHParameterSpec = (DHParameterSpec)algorithmParameterSpec;
            this.param = new ElGamalKeyGenerationParameters(secureRandom, new ElGamalParameters(dHParameterSpec.getP(), dHParameterSpec.getG(), dHParameterSpec.getL()));
        }
        this.engine.init(this.param);
        this.initialised = true;
    }

    public KeyPair generateKeyPair() {
        Object object;
        Object object2;
        if (!this.initialised) {
            object2 = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(this.strength);
            if (object2 != null) {
                this.param = new ElGamalKeyGenerationParameters(this.random, new ElGamalParameters(((DHParameterSpec)object2).getP(), ((DHParameterSpec)object2).getG(), ((DHParameterSpec)object2).getL()));
            } else {
                object = new ElGamalParametersGenerator();
                ((ElGamalParametersGenerator)object).init(this.strength, this.certainty, this.random);
                this.param = new ElGamalKeyGenerationParameters(this.random, ((ElGamalParametersGenerator)object).generateParameters());
            }
            this.engine.init(this.param);
            this.initialised = true;
        }
        object2 = this.engine.generateKeyPair();
        object = (ElGamalPublicKeyParameters)((AsymmetricCipherKeyPair)object2).getPublic();
        ElGamalPrivateKeyParameters elGamalPrivateKeyParameters = (ElGamalPrivateKeyParameters)((AsymmetricCipherKeyPair)object2).getPrivate();
        return new KeyPair(new BCElGamalPublicKey((ElGamalPublicKeyParameters)object), new BCElGamalPrivateKey(elGamalPrivateKeyParameters));
    }
}

