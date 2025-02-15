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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.CryptoProWrapEngine;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.engines.GOST28147WrapEngine;
import org.bouncycastle.crypto.macs.GOST28147Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.GCFBBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;

public final class GOST28147 {
    private static Map<ASN1ObjectIdentifier, String> oidMappings = new HashMap<ASN1ObjectIdentifier, String>();
    private static Map<String, ASN1ObjectIdentifier> nameMappings = new HashMap<String, ASN1ObjectIdentifier>();

    private GOST28147() {
    }

    static {
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_TestParamSet, "E-TEST");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
        nameMappings.put("E-A", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet);
        nameMappings.put("E-B", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet);
        nameMappings.put("E-C", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet);
        nameMappings.put("E-D", CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet);
    }

    public static class AlgParamGen
    extends BaseAlgorithmParameterGenerator {
        byte[] iv = new byte[8];
        byte[] sBox = GOST28147Engine.getSBox("E-A");

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                throw new InvalidAlgorithmParameterException("parameter spec not supported");
            }
            this.sBox = ((GOST28147ParameterSpec)algorithmParameterSpec).getSBox();
        }

        protected AlgorithmParameters engineGenerateParameters() {
            AlgorithmParameters algorithmParameters;
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(this.iv);
            try {
                algorithmParameters = this.createParametersInstance("GOST28147");
                algorithmParameters.init(new GOST28147ParameterSpec(this.sBox, this.iv));
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
            return algorithmParameters;
        }
    }

    public static class AlgParams
    extends BaseAlgParams {
        private ASN1ObjectIdentifier sBox = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet;
        private byte[] iv;

        protected byte[] localGetEncoded() throws IOException {
            return new GOST28147Parameters(this.iv, this.sBox).getEncoded();
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (clazz == GOST28147ParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
                return new GOST28147ParameterSpec(this.sBox, this.iv);
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + clazz.getName());
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec)algorithmParameterSpec).getIV();
            } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                this.iv = ((GOST28147ParameterSpec)algorithmParameterSpec).getIV();
                try {
                    this.sBox = AlgParams.getSBoxOID(((GOST28147ParameterSpec)algorithmParameterSpec).getSBox());
                } catch (IllegalArgumentException illegalArgumentException) {
                    throw new InvalidParameterSpecException(illegalArgumentException.getMessage());
                }
            } else {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            }
        }

        protected void localInit(byte[] byArray) throws IOException {
            ASN1Primitive aSN1Primitive = ASN1Primitive.fromByteArray(byArray);
            if (aSN1Primitive instanceof ASN1OctetString) {
                this.iv = ASN1OctetString.getInstance(aSN1Primitive).getOctets();
            } else if (aSN1Primitive instanceof ASN1Sequence) {
                GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(aSN1Primitive);
                this.sBox = gOST28147Parameters.getEncryptionParamSet();
                this.iv = gOST28147Parameters.getIV();
            } else {
                throw new IOException("Unable to recognize parameters");
            }
        }

        protected String engineToString() {
            return "GOST 28147 IV Parameters";
        }
    }

    public static abstract class BaseAlgParams
    extends BaseAlgorithmParameters {
        private ASN1ObjectIdentifier sBox = CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet;
        private byte[] iv;

        protected final void engineInit(byte[] byArray) throws IOException {
            this.engineInit(byArray, "ASN.1");
        }

        protected final byte[] engineGetEncoded() throws IOException {
            return this.engineGetEncoded("ASN.1");
        }

        protected final byte[] engineGetEncoded(String string) throws IOException {
            if (this.isASN1FormatString(string)) {
                return this.localGetEncoded();
            }
            throw new IOException("Unknown parameter format: " + string);
        }

        protected final void engineInit(byte[] byArray, String string) throws IOException {
            if (byArray == null) {
                throw new NullPointerException("Encoded parameters cannot be null");
            }
            if (this.isASN1FormatString(string)) {
                try {
                    this.localInit(byArray);
                } catch (IOException iOException) {
                    throw iOException;
                } catch (Exception exception) {
                    throw new IOException("Parameter parsing failed: " + exception.getMessage());
                }
            } else {
                throw new IOException("Unknown parameter format: " + string);
            }
        }

        protected byte[] localGetEncoded() throws IOException {
            return new GOST28147Parameters(this.iv, this.sBox).getEncoded();
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == IvParameterSpec.class) {
                return new IvParameterSpec(this.iv);
            }
            if (clazz == GOST28147ParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
                return new GOST28147ParameterSpec(this.sBox, this.iv);
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + clazz.getName());
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.iv = ((IvParameterSpec)algorithmParameterSpec).getIV();
            } else if (algorithmParameterSpec instanceof GOST28147ParameterSpec) {
                this.iv = ((GOST28147ParameterSpec)algorithmParameterSpec).getIV();
                try {
                    this.sBox = BaseAlgParams.getSBoxOID(((GOST28147ParameterSpec)algorithmParameterSpec).getSBox());
                } catch (IllegalArgumentException illegalArgumentException) {
                    throw new InvalidParameterSpecException(illegalArgumentException.getMessage());
                }
            } else {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            }
        }

        protected static ASN1ObjectIdentifier getSBoxOID(String string) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)nameMappings.get(string);
            if (aSN1ObjectIdentifier == null) {
                throw new IllegalArgumentException("Unknown SBOX name: " + string);
            }
            return aSN1ObjectIdentifier;
        }

        protected static ASN1ObjectIdentifier getSBoxOID(byte[] byArray) {
            return BaseAlgParams.getSBoxOID(GOST28147Engine.getSBoxName(byArray));
        }

        abstract void localInit(byte[] var1) throws IOException;
    }

    public static class CBC
    extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new GOST28147Engine()), 64);
        }
    }

    public static class CryptoProWrap
    extends BaseWrapCipher {
        public CryptoProWrap() {
            super(new CryptoProWrapEngine());
        }
    }

    public static class ECB
    extends BaseBlockCipher {
        public ECB() {
            super(new GOST28147Engine());
        }
    }

    public static class GCFB
    extends BaseBlockCipher {
        public GCFB() {
            super(new BufferedBlockCipher(new GCFBBlockCipher(new GOST28147Engine())), 64);
        }
    }

    public static class GostWrap
    extends BaseWrapCipher {
        public GostWrap() {
            super(new GOST28147WrapEngine());
        }
    }

    public static class KeyGen
    extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int n) {
            super("GOST28147", n, new CipherKeyGenerator());
        }
    }

    public static class Mac
    extends BaseMac {
        public Mac() {
            super(new GOST28147Mac());
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = GOST28147.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Cipher.GOST28147", PREFIX + "$ECB");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.GOST", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.Cipher.GOST-28147", "GOST28147");
            configurableProvider.addAlgorithm("Cipher." + CryptoProObjectIdentifiers.gostR28147_gcfb, PREFIX + "$GCFB");
            configurableProvider.addAlgorithm("KeyGenerator.GOST28147", PREFIX + "$KeyGen");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.GOST", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.KeyGenerator." + CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
            configurableProvider.addAlgorithm("AlgorithmParameters.GOST28147", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.GOST28147", PREFIX + "$AlgParamGen");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + CryptoProObjectIdentifiers.gostR28147_gcfb, "GOST28147");
            configurableProvider.addAlgorithm("Cipher." + CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap, PREFIX + "$CryptoProWrap");
            configurableProvider.addAlgorithm("Cipher." + CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap, PREFIX + "$GostWrap");
            configurableProvider.addAlgorithm("Mac.GOST28147MAC", PREFIX + "$Mac");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
        }
    }
}

