/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.DSAKeyPairGenerator;
import org.bouncycastle.crypto.generators.DSAParametersGenerator;
import org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.BCDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.BCDSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Properties;

public class KeyPairGeneratorSpi
extends KeyPairGenerator {
    private static Hashtable params = new Hashtable();
    private static Object lock = new Object();
    DSAKeyGenerationParameters param;
    DSAKeyPairGenerator engine = new DSAKeyPairGenerator();
    int strength = 2048;
    SecureRandom random = new SecureRandom();
    boolean initialised = false;

    public KeyPairGeneratorSpi() {
        super("DSA");
    }

    public void initialize(int n, SecureRandom secureRandom) {
        if (n < 512 || n > 4096 || n < 1024 && n % 64 != 0 || n >= 1024 && n % 1024 != 0) {
            throw new InvalidParameterException("strength must be from 512 - 4096 and a multiple of 1024 above 1024");
        }
        this.strength = n;
        this.random = secureRandom;
        this.initialised = false;
    }

    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof DSAParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DSAParameterSpec");
        }
        DSAParameterSpec dSAParameterSpec = (DSAParameterSpec)algorithmParameterSpec;
        this.param = new DSAKeyGenerationParameters(secureRandom, new DSAParameters(dSAParameterSpec.getP(), dSAParameterSpec.getQ(), dSAParameterSpec.getG()));
        this.engine.init(this.param);
        this.initialised = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public KeyPair generateKeyPair() {
        Object object;
        Object object2;
        Object object3;
        if (!this.initialised) {
            object3 = Integers.valueOf(this.strength);
            if (params.containsKey(object3)) {
                this.param = (DSAKeyGenerationParameters)params.get(object3);
            } else {
                object2 = lock;
                synchronized (object2) {
                    if (params.containsKey(object3)) {
                        this.param = (DSAKeyGenerationParameters)params.get(object3);
                    } else {
                        int n = PrimeCertaintyCalculator.getDefaultCertainty(this.strength);
                        if (this.strength == 1024) {
                            object = new DSAParametersGenerator();
                            if (Properties.isOverrideSet("org.bouncycastle.dsa.FIPS186-2for1024bits")) {
                                ((DSAParametersGenerator)object).init(this.strength, n, this.random);
                            } else {
                                DSAParameterGenerationParameters dSAParameterGenerationParameters = new DSAParameterGenerationParameters(1024, 160, n, this.random);
                                ((DSAParametersGenerator)object).init(dSAParameterGenerationParameters);
                            }
                        } else if (this.strength > 1024) {
                            DSAParameterGenerationParameters dSAParameterGenerationParameters = new DSAParameterGenerationParameters(this.strength, 256, n, this.random);
                            object = new DSAParametersGenerator(new SHA256Digest());
                            ((DSAParametersGenerator)object).init(dSAParameterGenerationParameters);
                        } else {
                            object = new DSAParametersGenerator();
                            ((DSAParametersGenerator)object).init(this.strength, n, this.random);
                        }
                        this.param = new DSAKeyGenerationParameters(this.random, ((DSAParametersGenerator)object).generateParameters());
                        params.put(object3, this.param);
                    }
                }
            }
            this.engine.init(this.param);
            this.initialised = true;
        }
        object3 = this.engine.generateKeyPair();
        object2 = (DSAPublicKeyParameters)((AsymmetricCipherKeyPair)object3).getPublic();
        object = (DSAPrivateKeyParameters)((AsymmetricCipherKeyPair)object3).getPrivate();
        return new KeyPair(new BCDSAPublicKey((DSAPublicKeyParameters)object2), new BCDSAPrivateKey((DSAPrivateKeyParameters)object));
    }
}

