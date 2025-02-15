/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.RC532Engine;
import org.bouncycastle.crypto.engines.RC564Engine;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.macs.CFBBlockCipherMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

public final class RC5 {
    private RC5() {
    }

    public static class AlgParamGen
    extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC5 parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            AlgorithmParameters algorithmParameters;
            byte[] byArray = new byte[8];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(byArray);
            try {
                algorithmParameters = this.createParametersInstance("RC5");
                algorithmParameters.init(new IvParameterSpec(byArray));
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
            return algorithmParameters;
        }
    }

    public static class AlgParams
    extends IvAlgorithmParameters {
        protected String engineToString() {
            return "RC5 IV";
        }
    }

    public static class CBC32
    extends BaseBlockCipher {
        public CBC32() {
            super(new CBCBlockCipher(new RC532Engine()), 64);
        }
    }

    public static class CFB8Mac32
    extends BaseMac {
        public CFB8Mac32() {
            super(new CFBBlockCipherMac(new RC532Engine()));
        }
    }

    public static class ECB32
    extends BaseBlockCipher {
        public ECB32() {
            super(new RC532Engine());
        }
    }

    public static class ECB64
    extends BaseBlockCipher {
        public ECB64() {
            super(new RC564Engine());
        }
    }

    public static class KeyGen32
    extends BaseKeyGenerator {
        public KeyGen32() {
            super("RC5", 128, new CipherKeyGenerator());
        }
    }

    public static class KeyGen64
    extends BaseKeyGenerator {
        public KeyGen64() {
            super("RC5-64", 256, new CipherKeyGenerator());
        }
    }

    public static class Mac32
    extends BaseMac {
        public Mac32() {
            super(new CBCBlockCipherMac(new RC532Engine()));
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = RC5.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Cipher.RC5", PREFIX + "$ECB32");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.RC5-32", "RC5");
            configurableProvider.addAlgorithm("Cipher.RC5-64", PREFIX + "$ECB64");
            configurableProvider.addAlgorithm("KeyGenerator.RC5", PREFIX + "$KeyGen32");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.RC5-32", "RC5");
            configurableProvider.addAlgorithm("KeyGenerator.RC5-64", PREFIX + "$KeyGen64");
            configurableProvider.addAlgorithm("AlgorithmParameters.RC5", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameters.RC5-64", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("Mac.RC5MAC", PREFIX + "$Mac32");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.RC5", "RC5MAC");
            configurableProvider.addAlgorithm("Mac.RC5MAC/CFB8", PREFIX + "$CFB8Mac32");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.RC5/CFB8", "RC5MAC/CFB8");
        }
    }
}

