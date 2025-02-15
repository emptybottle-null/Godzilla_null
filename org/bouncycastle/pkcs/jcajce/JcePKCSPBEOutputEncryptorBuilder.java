/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.pkcs.jcajce.JceUtils;

public class JcePKCSPBEOutputEncryptorBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private ASN1ObjectIdentifier algorithm;
    private ASN1ObjectIdentifier keyEncAlgorithm;
    private SecureRandom random;
    private SecretKeySizeProvider keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;
    private int iterationCount = 1024;
    private AlgorithmIdentifier prf = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);

    public JcePKCSPBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (this.isPKCS12(aSN1ObjectIdentifier)) {
            this.algorithm = aSN1ObjectIdentifier;
            this.keyEncAlgorithm = aSN1ObjectIdentifier;
        } else {
            this.algorithm = PKCSObjectIdentifiers.id_PBES2;
            this.keyEncAlgorithm = aSN1ObjectIdentifier;
        }
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setPRF(AlgorithmIdentifier algorithmIdentifier) {
        this.prf = algorithmIdentifier;
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.keySizeProvider = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setIterationCount(int n) {
        this.iterationCount = n;
        return this;
    }

    public OutputEncryptor build(final char[] cArray) throws OperatorCreationException {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        try {
            AlgorithmIdentifier algorithmIdentifier;
            Cipher cipher;
            if (this.isPKCS12(this.algorithm)) {
                byte[] byArray = new byte[20];
                this.random.nextBytes(byArray);
                cipher = this.helper.createCipher(this.algorithm.getId());
                cipher.init(1, new PKCS12KeyWithParameters(cArray, byArray, this.iterationCount));
                algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, new PKCS12PBEParams(byArray, this.iterationCount));
            } else if (this.algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                byte[] byArray = new byte[JceUtils.getSaltSize(this.prf.getAlgorithm())];
                this.random.nextBytes(byArray);
                SecretKeyFactory secretKeyFactory = this.helper.createSecretKeyFactory(JceUtils.getAlgorithm(this.prf.getAlgorithm()));
                SecretKey secretKey = secretKeyFactory.generateSecret(new PBEKeySpec(cArray, byArray, this.iterationCount, this.keySizeProvider.getKeySize(new AlgorithmIdentifier(this.keyEncAlgorithm))));
                cipher = this.helper.createCipher(this.keyEncAlgorithm.getId());
                cipher.init(1, (Key)secretKey, this.random);
                PBES2Parameters pBES2Parameters = new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(byArray, this.iterationCount, this.prf)), new EncryptionScheme(this.keyEncAlgorithm, ASN1Primitive.fromByteArray(cipher.getParameters().getEncoded())));
                algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, pBES2Parameters);
            } else {
                throw new OperatorCreationException("unrecognised algorithm");
            }
            return new OutputEncryptor(){

                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return algorithmIdentifier;
                }

                public OutputStream getOutputStream(OutputStream outputStream) {
                    return new CipherOutputStream(outputStream, cipher);
                }

                public GenericKey getKey() {
                    if (JcePKCSPBEOutputEncryptorBuilder.this.isPKCS12(algorithmIdentifier.getAlgorithm())) {
                        return new GenericKey(algorithmIdentifier, JcePKCSPBEOutputEncryptorBuilder.PKCS12PasswordToBytes(cArray));
                    }
                    return new GenericKey(algorithmIdentifier, JcePKCSPBEOutputEncryptorBuilder.PKCS5PasswordToBytes(cArray));
                }
            };
        } catch (Exception exception) {
            throw new OperatorCreationException("unable to create OutputEncryptor: " + exception.getMessage(), exception);
        }
    }

    private boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha1_pkcs12) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha256_pkcs12);
    }

    private static byte[] PKCS5PasswordToBytes(char[] cArray) {
        if (cArray != null) {
            byte[] byArray = new byte[cArray.length];
            for (int i = 0; i != byArray.length; ++i) {
                byArray[i] = (byte)cArray[i];
            }
            return byArray;
        }
        return new byte[0];
    }

    private static byte[] PKCS12PasswordToBytes(char[] cArray) {
        if (cArray != null && cArray.length > 0) {
            byte[] byArray = new byte[(cArray.length + 1) * 2];
            for (int i = 0; i != cArray.length; ++i) {
                byArray[i * 2] = (byte)(cArray[i] >>> 8);
                byArray[i * 2 + 1] = (byte)cArray[i];
            }
            return byArray;
        }
        return new byte[0];
    }
}

