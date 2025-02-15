/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PasswordConverter;
import org.bouncycastle.jcajce.PBKDF2Key;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.util.Integers;

public class PBEPBKDF2 {
    private static final Map prfCodes = new HashMap();

    private PBEPBKDF2() {
    }

    static {
        prfCodes.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(6));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA1, Integers.valueOf(1));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA256, Integers.valueOf(4));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(7));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(8));
        prfCodes.put(PKCSObjectIdentifiers.id_hmacWithSHA512, Integers.valueOf(9));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, Integers.valueOf(11));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(10));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(12));
        prfCodes.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, Integers.valueOf(13));
    }

    public static class AlgParams
    extends BaseAlgorithmParameters {
        PBKDF2Params params;

        protected byte[] engineGetEncoded() {
            try {
                return this.params.getEncoded("DER");
            } catch (IOException iOException) {
                throw new RuntimeException("Oooops! " + iOException.toString());
            }
        }

        protected byte[] engineGetEncoded(String string) {
            if (this.isASN1FormatString(string)) {
                return this.engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == PBEParameterSpec.class) {
                return new PBEParameterSpec(this.params.getSalt(), this.params.getIterationCount().intValue());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBKDF2 PBE parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PBKDF2 PBE parameters algorithm parameters object");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec)algorithmParameterSpec;
            this.params = new PBKDF2Params(pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
        }

        protected void engineInit(byte[] byArray) throws IOException {
            this.params = PBKDF2Params.getInstance(ASN1Primitive.fromByteArray(byArray));
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (this.isASN1FormatString(string)) {
                this.engineInit(byArray);
                return;
            }
            throw new IOException("Unknown parameters format in PBKDF2 parameters object");
        }

        protected String engineToString() {
            return "PBKDF2 Parameters";
        }
    }

    public static class BasePBKDF2
    extends BaseSecretKeyFactory {
        private int scheme;
        private int defaultDigest;

        public BasePBKDF2(String string, int n) {
            this(string, n, 1);
        }

        public BasePBKDF2(String string, int n, int n2) {
            super(string, PKCSObjectIdentifiers.id_PBKDF2);
            this.scheme = n;
            this.defaultDigest = n2;
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            if (keySpec instanceof PBEKeySpec) {
                PBEKeySpec pBEKeySpec = (PBEKeySpec)keySpec;
                if (pBEKeySpec.getSalt() == null) {
                    return new PBKDF2Key(((PBEKeySpec)keySpec).getPassword(), this.scheme == 1 ? PasswordConverter.ASCII : PasswordConverter.UTF8);
                }
                if (pBEKeySpec.getIterationCount() <= 0) {
                    throw new InvalidKeySpecException("positive iteration count required: " + pBEKeySpec.getIterationCount());
                }
                if (pBEKeySpec.getKeyLength() <= 0) {
                    throw new InvalidKeySpecException("positive key length required: " + pBEKeySpec.getKeyLength());
                }
                if (pBEKeySpec.getPassword().length == 0) {
                    throw new IllegalArgumentException("password empty");
                }
                if (pBEKeySpec instanceof PBKDF2KeySpec) {
                    PBKDF2KeySpec pBKDF2KeySpec = (PBKDF2KeySpec)pBEKeySpec;
                    int n = this.getDigestCode(pBKDF2KeySpec.getPrf().getAlgorithm());
                    int n2 = pBEKeySpec.getKeyLength();
                    int n3 = -1;
                    CipherParameters cipherParameters = PBE.Util.makePBEMacParameters(pBEKeySpec, this.scheme, n, n2);
                    return new BCPBEKey(this.algName, this.algOid, this.scheme, n, n2, n3, pBEKeySpec, cipherParameters);
                }
                int n = this.defaultDigest;
                int n4 = pBEKeySpec.getKeyLength();
                int n5 = -1;
                CipherParameters cipherParameters = PBE.Util.makePBEMacParameters(pBEKeySpec, this.scheme, n, n4);
                return new BCPBEKey(this.algName, this.algOid, this.scheme, n, n4, n5, pBEKeySpec, cipherParameters);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }

        private int getDigestCode(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws InvalidKeySpecException {
            Integer n = (Integer)prfCodes.get(aSN1ObjectIdentifier);
            if (n != null) {
                return n;
            }
            throw new InvalidKeySpecException("Invalid KeySpec: unknown PRF algorithm " + aSN1ObjectIdentifier);
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = PBEPBKDF2.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.PBKDF2", PREFIX + "$AlgParams");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.id_PBKDF2, "PBKDF2");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2", PREFIX + "$PBKDF2withUTF8");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITHHMACSHA1", "PBKDF2");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITHHMACSHA1ANDUTF8", "PBKDF2");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.id_PBKDF2, "PBKDF2");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHASCII", PREFIX + "$PBKDF2with8BIT");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITH8BIT", "PBKDF2WITHASCII");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBKDF2WITHHMACSHA1AND8BIT", "PBKDF2WITHASCII");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA224", PREFIX + "$PBKDF2withSHA224");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA256", PREFIX + "$PBKDF2withSHA256");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA384", PREFIX + "$PBKDF2withSHA384");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA512", PREFIX + "$PBKDF2withSHA512");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-224", PREFIX + "$PBKDF2withSHA3_224");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-256", PREFIX + "$PBKDF2withSHA3_256");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-384", PREFIX + "$PBKDF2withSHA3_384");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACSHA3-512", PREFIX + "$PBKDF2withSHA3_512");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBKDF2WITHHMACGOST3411", PREFIX + "$PBKDF2withGOST3411");
        }
    }

    public static class PBKDF2with8BIT
    extends BasePBKDF2 {
        public PBKDF2with8BIT() {
            super("PBKDF2", 1);
        }
    }

    public static class PBKDF2withGOST3411
    extends BasePBKDF2 {
        public PBKDF2withGOST3411() {
            super("PBKDF2", 5, 6);
        }
    }

    public static class PBKDF2withSHA224
    extends BasePBKDF2 {
        public PBKDF2withSHA224() {
            super("PBKDF2", 5, 7);
        }
    }

    public static class PBKDF2withSHA256
    extends BasePBKDF2 {
        public PBKDF2withSHA256() {
            super("PBKDF2", 5, 4);
        }
    }

    public static class PBKDF2withSHA384
    extends BasePBKDF2 {
        public PBKDF2withSHA384() {
            super("PBKDF2", 5, 8);
        }
    }

    public static class PBKDF2withSHA3_224
    extends BasePBKDF2 {
        public PBKDF2withSHA3_224() {
            super("PBKDF2", 5, 10);
        }
    }

    public static class PBKDF2withSHA3_256
    extends BasePBKDF2 {
        public PBKDF2withSHA3_256() {
            super("PBKDF2", 5, 11);
        }
    }

    public static class PBKDF2withSHA3_384
    extends BasePBKDF2 {
        public PBKDF2withSHA3_384() {
            super("PBKDF2", 5, 12);
        }
    }

    public static class PBKDF2withSHA3_512
    extends BasePBKDF2 {
        public PBKDF2withSHA3_512() {
            super("PBKDF2", 5, 13);
        }
    }

    public static class PBKDF2withSHA512
    extends BasePBKDF2 {
        public PBKDF2withSHA512() {
            super("PBKDF2", 5, 9);
        }
    }

    public static class PBKDF2withUTF8
    extends BasePBKDF2 {
        public PBKDF2withUTF8() {
            super("PBKDF2", 5);
        }
    }
}

