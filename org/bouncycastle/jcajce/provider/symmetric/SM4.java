/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.macs.GMac;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.SymmetricAlgorithmProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

public final class SM4 {
    private SM4() {
    }

    public static class AlgParamGen
    extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for SM4 parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            AlgorithmParameters algorithmParameters;
            byte[] byArray = new byte[16];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(byArray);
            try {
                algorithmParameters = this.createParametersInstance("SM4");
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
            return "SM4 IV";
        }
    }

    public static class CMAC
    extends BaseMac {
        public CMAC() {
            super(new CMac(new SM4Engine()));
        }
    }

    public static class ECB
    extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider(){

                public BlockCipher get() {
                    return new SM4Engine();
                }
            });
        }
    }

    public static class GMAC
    extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new SM4Engine())));
        }
    }

    public static class KeyGen
    extends BaseKeyGenerator {
        public KeyGen() {
            super("SM4", 128, new CipherKeyGenerator());
        }
    }

    public static class Mappings
    extends SymmetricAlgorithmProvider {
        private static final String PREFIX = SM4.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.SM4", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.SM4", PREFIX + "$AlgParamGen");
            configurableProvider.addAlgorithm("Cipher.SM4", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("KeyGenerator.SM4", PREFIX + "$KeyGen");
            this.addCMacAlgorithm(configurableProvider, "SM4", PREFIX + "$CMAC", PREFIX + "$KeyGen");
            this.addGMacAlgorithm(configurableProvider, "SM4", PREFIX + "$GMAC", PREFIX + "$KeyGen");
            this.addPoly1305Algorithm(configurableProvider, "SM4", PREFIX + "$Poly1305", PREFIX + "$Poly1305KeyGen");
        }
    }

    public static class Poly1305
    extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new SM4Engine()));
        }
    }

    public static class Poly1305KeyGen
    extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-SM4", 256, new Poly1305KeyGenerator());
        }
    }
}

