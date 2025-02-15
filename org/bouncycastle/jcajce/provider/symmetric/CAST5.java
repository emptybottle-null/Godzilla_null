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
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.misc.CAST5CBCParameters;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.CAST5Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

public final class CAST5 {
    private CAST5() {
    }

    public static class AlgParamGen
    extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for CAST5 parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            AlgorithmParameters algorithmParameters;
            byte[] byArray = new byte[8];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(byArray);
            try {
                algorithmParameters = this.createParametersInstance("CAST5");
                algorithmParameters.init(new IvParameterSpec(byArray));
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
            return algorithmParameters;
        }
    }

    public static class AlgParams
    extends BaseAlgorithmParameters {
        private byte[] iv;
        private int keyLength = 128;

        protected byte[] engineGetEncoded() {
            byte[] byArray = new byte[this.iv.length];
            System.arraycopy(this.iv, 0, byArray, 0, this.iv.length);
            return byArray;
        }

        protected byte[] engineGetEncoded(String string) throws IOException {
            if (this.isASN1FormatString(string)) {
                return new CAST5CBCParameters(this.engineGetEncoded(), this.keyLength).getEncoded();
            }
            if (string.equals("RAW")) {
                return this.engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to CAST5 parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof IvParameterSpec)) {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a CAST5 parameters algorithm parameters object");
            }
            this.iv = ((IvParameterSpec)algorithmParameterSpec).getIV();
        }

        protected void engineInit(byte[] byArray) throws IOException {
            this.iv = new byte[byArray.length];
            System.arraycopy(byArray, 0, this.iv, 0, this.iv.length);
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (this.isASN1FormatString(string)) {
                ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
                CAST5CBCParameters cAST5CBCParameters = CAST5CBCParameters.getInstance(aSN1InputStream.readObject());
                this.keyLength = cAST5CBCParameters.getKeyLength();
                this.iv = cAST5CBCParameters.getIV();
                return;
            }
            if (string.equals("RAW")) {
                this.engineInit(byArray);
                return;
            }
            throw new IOException("Unknown parameters format in IV parameters object");
        }

        protected String engineToString() {
            return "CAST5 Parameters";
        }
    }

    public static class CBC
    extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new CAST5Engine()), 64);
        }
    }

    public static class ECB
    extends BaseBlockCipher {
        public ECB() {
            super(new CAST5Engine());
        }
    }

    public static class KeyGen
    extends BaseKeyGenerator {
        public KeyGen() {
            super("CAST5", 128, new CipherKeyGenerator());
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = CAST5.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.CAST5", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.2.840.113533.7.66.10", "CAST5");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.CAST5", PREFIX + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator.1.2.840.113533.7.66.10", "CAST5");
            configurableProvider.addAlgorithm("Cipher.CAST5", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Cipher", MiscObjectIdentifiers.cast5CBC, PREFIX + "$CBC");
            configurableProvider.addAlgorithm("KeyGenerator.CAST5", PREFIX + "$KeyGen");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator", MiscObjectIdentifiers.cast5CBC, "CAST5");
        }
    }
}

