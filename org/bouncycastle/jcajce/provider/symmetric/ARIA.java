/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.cms.CCMParameters;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.asn1.nsri.NSRIObjectIdentifiers;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.ARIAEngine;
import org.bouncycastle.crypto.engines.ARIAWrapEngine;
import org.bouncycastle.crypto.engines.ARIAWrapPadEngine;
import org.bouncycastle.crypto.engines.RFC3211WrapEngine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.crypto.macs.GMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.GcmSpecUtil;
import org.bouncycastle.jcajce.provider.symmetric.SymmetricAlgorithmProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;

public final class ARIA {
    private ARIA() {
    }

    public static class AlgParamGen
    extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for ARIA parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            AlgorithmParameters algorithmParameters;
            byte[] byArray = new byte[16];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(byArray);
            try {
                algorithmParameters = this.createParametersInstance("ARIA");
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
            return "ARIA IV";
        }
    }

    public static class AlgParamsCCM
    extends BaseAlgorithmParameters {
        private CCMParameters ccmParams;

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.ccmParams = CCMParameters.getInstance(GcmSpecUtil.extractGcmParameters(algorithmParameterSpec));
            } else if (algorithmParameterSpec instanceof AEADParameterSpec) {
                this.ccmParams = new CCMParameters(((AEADParameterSpec)algorithmParameterSpec).getNonce(), ((AEADParameterSpec)algorithmParameterSpec).getMacSizeInBits() / 8);
            } else {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }

        protected void engineInit(byte[] byArray) throws IOException {
            this.ccmParams = CCMParameters.getInstance(byArray);
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (!this.isASN1FormatString(string)) {
                throw new IOException("unknown format specified");
            }
            this.ccmParams = CCMParameters.getInstance(byArray);
        }

        protected byte[] engineGetEncoded() throws IOException {
            return this.ccmParams.getEncoded();
        }

        protected byte[] engineGetEncoded(String string) throws IOException {
            if (!this.isASN1FormatString(string)) {
                throw new IOException("unknown format specified");
            }
            return this.ccmParams.getEncoded();
        }

        protected String engineToString() {
            return "CCM";
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(clazz)) {
                if (GcmSpecUtil.gcmSpecExists()) {
                    return GcmSpecUtil.extractGcmSpec(this.ccmParams.toASN1Primitive());
                }
                return new AEADParameterSpec(this.ccmParams.getNonce(), this.ccmParams.getIcvLen() * 8);
            }
            if (clazz == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.ccmParams.getNonce(), this.ccmParams.getIcvLen() * 8);
            }
            if (clazz == IvParameterSpec.class) {
                return new IvParameterSpec(this.ccmParams.getNonce());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + clazz.getName());
        }
    }

    public static class AlgParamsGCM
    extends BaseAlgorithmParameters {
        private GCMParameters gcmParams;

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (GcmSpecUtil.isGcmSpec(algorithmParameterSpec)) {
                this.gcmParams = GcmSpecUtil.extractGcmParameters(algorithmParameterSpec);
            } else if (algorithmParameterSpec instanceof AEADParameterSpec) {
                this.gcmParams = new GCMParameters(((AEADParameterSpec)algorithmParameterSpec).getNonce(), ((AEADParameterSpec)algorithmParameterSpec).getMacSizeInBits() / 8);
            } else {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }

        protected void engineInit(byte[] byArray) throws IOException {
            this.gcmParams = GCMParameters.getInstance(byArray);
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (!this.isASN1FormatString(string)) {
                throw new IOException("unknown format specified");
            }
            this.gcmParams = GCMParameters.getInstance(byArray);
        }

        protected byte[] engineGetEncoded() throws IOException {
            return this.gcmParams.getEncoded();
        }

        protected byte[] engineGetEncoded(String string) throws IOException {
            if (!this.isASN1FormatString(string)) {
                throw new IOException("unknown format specified");
            }
            return this.gcmParams.getEncoded();
        }

        protected String engineToString() {
            return "GCM";
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == AlgorithmParameterSpec.class || GcmSpecUtil.isGcmSpec(clazz)) {
                if (GcmSpecUtil.gcmSpecExists()) {
                    return GcmSpecUtil.extractGcmSpec(this.gcmParams.toASN1Primitive());
                }
                return new AEADParameterSpec(this.gcmParams.getNonce(), this.gcmParams.getIcvLen() * 8);
            }
            if (clazz == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.gcmParams.getNonce(), this.gcmParams.getIcvLen() * 8);
            }
            if (clazz == IvParameterSpec.class) {
                return new IvParameterSpec(this.gcmParams.getNonce());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + clazz.getName());
        }
    }

    public static class CBC
    extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new ARIAEngine()), 128);
        }
    }

    public static class CFB
    extends BaseBlockCipher {
        public CFB() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new ARIAEngine(), 128)), 128);
        }
    }

    public static class ECB
    extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider(){

                public BlockCipher get() {
                    return new ARIAEngine();
                }
            });
        }
    }

    public static class GMAC
    extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new ARIAEngine())));
        }
    }

    public static class KeyGen
    extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int n) {
            super("ARIA", n, new CipherKeyGenerator());
        }
    }

    public static class KeyGen128
    extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    public static class KeyGen192
    extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    public static class KeyGen256
    extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    public static class Mappings
    extends SymmetricAlgorithmProvider {
        private static final String PREFIX = ARIA.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.ARIA", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIA", PREFIX + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_cbc, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_ofb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria128_cfb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria192_cfb, "ARIA");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator", NSRIObjectIdentifiers.id_aria256_cfb, "ARIA");
            configurableProvider.addAlgorithm("Cipher.ARIA", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_ecb, PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_ecb, PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_ecb, PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_cbc, PREFIX + "$CBC");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_cbc, PREFIX + "$CBC");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_cbc, PREFIX + "$CBC");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_cfb, PREFIX + "$CFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_cfb, PREFIX + "$CFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_cfb, PREFIX + "$CFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria128_ofb, PREFIX + "$OFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria192_ofb, PREFIX + "$OFB");
            configurableProvider.addAlgorithm("Cipher", NSRIObjectIdentifiers.id_aria256_ofb, PREFIX + "$OFB");
            configurableProvider.addAlgorithm("Cipher.ARIARFC3211WRAP", PREFIX + "$RFC3211Wrap");
            configurableProvider.addAlgorithm("Cipher.ARIAWRAP", PREFIX + "$Wrap");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_kw, "ARIAWRAP");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.ARIAKW", "ARIAWRAP");
            configurableProvider.addAlgorithm("Cipher.ARIAWRAPPAD", PREFIX + "$WrapPad");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_kwp, "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.ARIAKWP", "ARIAWRAPPAD");
            configurableProvider.addAlgorithm("KeyGenerator.ARIA", PREFIX + "$KeyGen");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_kw, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_kw, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_kw, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_kwp, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_kwp, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_kwp, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ecb, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ecb, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ecb, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_cbc, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_cbc, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_cbc, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_cfb, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_cfb, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_cfb, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ofb, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ofb, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ofb, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_ccm, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_ccm, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_ccm, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria128_gcm, PREFIX + "$KeyGen128");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria192_gcm, PREFIX + "$KeyGen192");
            configurableProvider.addAlgorithm("KeyGenerator", NSRIObjectIdentifiers.id_aria256_gcm, PREFIX + "$KeyGen256");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIACCM", PREFIX + "$AlgParamGenCCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria128_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria192_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria256_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_ccm, "CCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_ccm, "CCM");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.ARIAGCM", PREFIX + "$AlgParamGenGCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria128_gcm, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria192_gcm, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + NSRIObjectIdentifiers.id_aria256_gcm, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria128_gcm, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria192_gcm, "GCM");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher", NSRIObjectIdentifiers.id_aria256_gcm, "GCM");
            this.addGMacAlgorithm(configurableProvider, "ARIA", PREFIX + "$GMAC", PREFIX + "$KeyGen");
            this.addPoly1305Algorithm(configurableProvider, "ARIA", PREFIX + "$Poly1305", PREFIX + "$Poly1305KeyGen");
        }
    }

    public static class OFB
    extends BaseBlockCipher {
        public OFB() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new ARIAEngine(), 128)), 128);
        }
    }

    public static class Poly1305
    extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new ARIAEngine()));
        }
    }

    public static class Poly1305KeyGen
    extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-ARIA", 256, new Poly1305KeyGenerator());
        }
    }

    public static class RFC3211Wrap
    extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new ARIAEngine()), 16);
        }
    }

    public static class Wrap
    extends BaseWrapCipher {
        public Wrap() {
            super(new ARIAWrapEngine());
        }
    }

    public static class WrapPad
    extends BaseWrapCipher {
        public WrapPad() {
            super(new ARIAWrapPadEngine());
        }
    }
}

